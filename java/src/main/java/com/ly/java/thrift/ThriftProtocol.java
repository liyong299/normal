package com.yy.g.yysoa.framework.protocol.thrift;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.yy.g.yysoa.common.KEY;
import com.yy.g.yysoa.common.URL;
import com.yy.g.yysoa.common.logger.Logger;
import com.yy.g.yysoa.common.logger.LoggerFactory;
import com.yy.g.yysoa.common.utils.Convert;
import com.yy.g.yysoa.common.utils.ThreadLocalSocketUtils;
import com.yy.g.yysoa.framework.Exporter;
import com.yy.g.yysoa.framework.Invoker;
import com.yy.g.yysoa.framework.ProxyFactory;
import com.yy.g.yysoa.framework.RpcContext;
import com.yy.g.yysoa.framework.RpcException;
import com.yy.g.yysoa.framework.protocol.AbstractProtocol;
import com.yy.g.yysoa.framework.protocol.ProtocolClientValidator;
import com.yy.g.yysoa.framework.protocol.ThreadLocalValidatorUtils;
import com.yy.g.yysoa.framework.proxy.javassist.JavassistProxyFactory;

/**
 * thrift 协议实现要注意不同 版本间的兼容性， 要比较客户端 和 服务端的 thrift 版本
 * 
 * @author guoliping
 *
 */
public class ThriftProtocol extends AbstractProtocol {
	
    protected final Logger   log    = LoggerFactory.getLogger(getClass());
	
	public static final int DEFAULT_PORT = 20796;
	
	public static final String NAME = "thrift";
	
    private final Map<String, TServer> serverMap = new ConcurrentHashMap<String, TServer>();
    
    private final Map<String, ThriftClientPool<?>> clientMap = new ConcurrentHashMap<String, ThriftClientPool<?>>();

    public final ReentrantLock lock = new ReentrantLock();
    
    private ProxyFactory proxyFactory  = new JavassistProxyFactory();
    
    public ThriftProtocol() {
    }

	@Override
	public int getDefaultPort() {
		return DEFAULT_PORT;
	}
	
    public Collection<TServer> getServers() {
        return Collections.unmodifiableCollection(serverMap.values());
    }

    public Collection<Exporter<?>> getExporters() {
        return Collections.unmodifiableCollection(exporterMap.values());
    }
    
    Map<String, Exporter<?>> getExporterMap(){
        return exporterMap;
    }
   
    public Collection<Invoker<?>> getInvokers() {
        return Collections.unmodifiableCollection(invokers);
    }
    
	@Override
    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
    	try {
    		URL url = invoker.getUrl();
    		
			// export service.
			String key = serviceKey(url);
			ThriftExporter<T> exporter = new ThriftExporter<T>(invoker, key, exporterMap);
			exporterMap.put(key, exporter);
			
	        // find server.
	        String address = url.getAddress();
	        //client 也可以暴露一个只有server可以调用的服务。
	        boolean isServer = url.getParameter(KEY.IS_SERVER, true);
	        if (isServer && ! serverMap.containsKey(address)) { // 确保不重复输出，致端口占用冲突
	            serverMap.put(address, openServer(invoker));
	        }
			
			return exporter;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	private <T> TServer openServer(Invoker<T> invoker)
			throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		URL url = invoker.getUrl();
		Class<T> iface = invoker.getInterface();
		final String ifaceClassName = iface.getName();

		int inx = ifaceClassName.lastIndexOf("$");
		String outerClassName = ifaceClassName.substring(0, inx);
		String processorClassFile = outerClassName + "$Processor";
		Class<?> processorClass = Class.forName(processorClassFile);
		
		// 为 调用对象包装一个调用接口
		// 本转化的目的是为了给 thrift 的 TProcessor 提供实现接口
		T proxy = proxyFactory.getProxy(invoker);

		// 关联处理器与 iface 服务的实现
		Constructor<?> constructor = processorClass.getConstructor(iface);
		TProcessor processor = (TProcessor) constructor.newInstance(proxy);
		final int port = url.getPort(DEFAULT_PORT);
		final TServer server = createServer(processor, port);
		
		if(server == null) {
			log.info("get server[" + ifaceClassName + ", " + port + "] fail!");
			throw new RpcException("create server fail:" + url.getAddress() + ", " + ifaceClassName);
		}

		// TODO::启动方式需要研究下， 去掉 Thread.run() 方式
		Thread thread = new Thread(){
			public void run() {
				server.serve();
				log.info("start server[" + ifaceClassName + ", " + port + "] ok!");
			}
		};
		thread.setDaemon(false);
		thread.start();	
		
		return server;
	}
	
	@SuppressWarnings("unchecked")
	@Override
    public <T> Invoker<T> refer(Class<T> serviceType, URL url) throws RpcException {
		// 当用户使用不同的服务名访问相同服务接口时候，可用于区分（原则上，用户应该尽量合并）
        String key = serviceType.getName() + "@" + url.getAddress();
		ThriftClientPool<T> pool = (ThriftClientPool<T>) clientMap.get(key);   
        if (pool == null) {
        	Object obj = ThreadLocalValidatorUtils.get();
        	ProtocolClientValidator<T> validator = (ProtocolClientValidator<T>) obj;
        	pool = getThriftClientPool(serviceType, url, validator);
        	clientMap.put(key, pool);
        }
        
        return new ThriftInvoker<T>(serviceType, pool, url);
    }

	@Override
    public void destroy() {
		
        super.destroy();
        
        // 停止 thrift 服务
        for (String key : new ArrayList<String>(serverMap.keySet())) {
        	TServer server = serverMap.remove(key);
            if (server != null) {
                try {
                    if (logger.isInfoEnabled()) {
                        logger.info("Close thrift server: " + key);
                    }
                    server.stop();
                } catch (Throwable t) {
                    logger.warn(t.getMessage(), t);
                }
            }
        }
        
        // 关闭 thrift 连接池
        for (String key : new ArrayList<String>(clientMap.keySet())) {
        	ThriftClientPool<?> pool = clientMap.remove(key);
            if (pool != null) {
                try {
                    if (logger.isInfoEnabled()) {
                        logger.info("Close thrift client: " + key);
                    }
                    pool.destroy();
                } catch (Throwable t) {
                    logger.warn(t.getMessage(), t);
                }
            }
        }
    }
    
    /**
     * 获取服务端的服务器实例对象
     * @param processor
     * @return
     */
	private <T> TServer createServer(final TProcessor processor, int port) {
		try {
			/*
				重载 TProcessor 的  process 方法，提取远端地址
				
			 		org.apache.thrift.server.TThreadPoolServer.WorkerProcess.run(), 里面会调用 process()
			 		与下面的 args.transportFactory() 处于同一个线程，故可使用线程本地变量传递获得的远端地址
			*/
			TProcessor myprocessor = new TProcessor() {
				@Override
				public boolean process(TProtocol in, TProtocol out) throws TException {
					RpcContext.getContext().setRemoteAddress(ThreadLocalSocketUtils.get());
					return processor.process(in, out);
				}
			};
			
			// 创建  transport
			TServerTransport serverTransport = new TServerSocket(port);
			
			// 使用二进制来编码应用层的数据
	   		TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
	   		args.processorFactory(new TProcessorFactory(myprocessor));
	   		
	   		// 二进制编码格式进行数据传输
	   		args.protocolFactory(new TBinaryProtocol.Factory());
	   		
	   		// 使用普通的socket来传输数据
	   		// args.transportFactory(new TFramedTransport.Factory());
	   		// org.apache.thrift.server.TThreadPoolServer.WorkerProcess.run(), 里面会调用 getTransport()
			args.transportFactory(new TFramedTransport.Factory() {
				@Override
				public TTransport getTransport(TTransport base) {
					if (base instanceof TSocket) {
						TSocket _base = (TSocket) base;
						Socket socket = _base.getSocket();
						if (socket != null) {
							SocketAddress address = socket.getRemoteSocketAddress();
							ThreadLocalSocketUtils.set((InetSocketAddress) address);
						}
					}

					return new TFramedTransport(base) {
						@Override
						public void close() {
							super.close();
							ThreadLocalSocketUtils.remove();
						}
					};
				}
			});

	   		TServer server = new TThreadPoolServer(args);
			return server;
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}
    
	/**
	 * 获取客户端调用链接池
	 * @param serviceType
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> ThriftClientPool<T> getThriftClientPool(Class<T> serviceType, URL url, ProtocolClientValidator<T> validator) {
		String serviceInterface = url.getServiceInterface();
		String ifaceClassName = serviceType.getName();
		if(!ifaceClassName.equals(serviceInterface)) {
			throw new RpcException("not match interface:" + serviceInterface);
		}
		
		int inx = ifaceClassName.lastIndexOf("$");
		String outerClassName = ifaceClassName.substring(0, inx);
		String clientClassFile = outerClassName + "$Client";
		
		try {
			// 超时时间，缺省为10秒无返回，thrift框架抛出超时异常
			int timeout = Convert.toInt(url.getParameter(KEY.TIMEOUT), 10);
			// 维持最小链接数，缺省为0个以节约服务端链接资源
			int min = Convert.toInt(url.getParameter(KEY.THRIFT_POOL_MIN), 0);
			// 最大链接数，缺省为 200 个，防过度消耗服务端链接资源
			int max = Convert.toInt(url.getParameter(KEY.THRIFT_POOL_MAX), 200);
			// 链接空闲释放时间，缺省设置为 600 秒，防频繁创建和释放链接
			int idle = Convert.toInt(url.getParameter(KEY.THRIFT_POOL_IDLE), 600);
			// 链接空闲及有效性检测频率，默认为 30 秒检测一次
			int vtime = Convert.toInt(url.getParameter(KEY.THRIFT_POOL_VTIME), 30);
			
			String host= url.getIp();
			int port = url.getPort();
			Class<T> clazz = (Class<T>) Class.forName(clientClassFile);
			ThriftClientPool<T> pool = new ThriftClientPool<T>(host, port, timeout, clazz, min, max, validator);
			pool.setIdle(idle);
			pool.setVtime(vtime);
			
			return pool;
		} catch (ClassNotFoundException e) {
			throw new RpcException("class not found:" + clientClassFile);
		}
	}
}
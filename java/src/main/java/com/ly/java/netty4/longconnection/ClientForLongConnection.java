package com.ly.java.netty4.longconnection;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ly.java.netty4.longconnection.entries.LoginMsg;
import com.ly.java.netty4.longconnection.entries.PingMsg;
import com.ly.java.netty4.longconnection.entries.RequestMsg;

public class ClientForLongConnection {
	private final static Logger log = LoggerFactory.getLogger(ClientForLongConnection.class);
	private SocketChannel socketChannel;
	private String clientSeq;

	public ClientForLongConnection(String clientSeq) {
		this.clientSeq = clientSeq;
	}

	public void start() throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new LoggingHandler(LogLevel.DEBUG));
			b.remoteAddress(Constants.HTTP_HOST, Constants.HTTP_PORT);
			b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					log.debug("method【initChannel】  " + ch.toString());
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new IdleStateHandler(20, 10, 0));
					pipeline.addLast(new ObjectEncoder());
					pipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
					pipeline.addLast(new ClientForLongConnectionHandler(pingMsg));
				}
			});

			// 连接服务端
			ChannelFuture future = b.connect(Constants.HTTP_HOST, Constants.HTTP_PORT).sync();

			if (future.isSuccess()) {
				socketChannel = (SocketChannel) future.channel();
				System.out.println("connect server  成功---------");
			}

			// 和服务端建立连接
			createServerConnection();
		} finally {
			group.shutdownGracefully();
		}
	}

	private void createServerConnection() {
		LoginMsg loginMsg = new LoginMsg();
		loginMsg.setPassword("123456");
		loginMsg.setUserName("lilei");
		loginMsg.setClientId(clientSeq);

		this.socketChannel.writeAndFlush(loginMsg);
		log.debug("向服务端登录完成");
		genRequestMsg();
		for (;;) {
			try {
				TimeUnit.SECONDS.sleep((long) (Math.random() * 50 + 10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.debug(System.currentTimeMillis() + " | 开始发送消息 | " + clientSeq);

			socketChannel.writeAndFlush(askMsg);
		}
	}

	private RequestMsg askMsg = null;
	private PingMsg pingMsg = null;
	private void genRequestMsg() {
		askMsg = new RequestMsg();
		Map<String, String> params = new HashMap<>();
		params.put("command", "time");
		askMsg.setParams(params);
		askMsg.setClientId(this.clientSeq);

		pingMsg = PingMsg.getInstance();
		pingMsg.setClientId(clientSeq);
	}

	public void sendMsg2Server() {
		log.debug(System.currentTimeMillis() + " | 开始发送消息 | " + clientSeq);
		RequestMsg askMsg = null;
		askMsg = new RequestMsg();
		Map<String, String> params = new HashMap<>();
		params.put("command", "time");
		askMsg.setParams(params);
		askMsg.setClientId(this.clientSeq);
		socketChannel.writeAndFlush(askMsg);
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		final List<ClientForLongConnection> clients = new ArrayList<>();
		for (int i = 0; i < Constants.CLIENT_NUM; i++) {
			TimeUnit.MILLISECONDS.sleep(50);
			new Thread(new ClientBootstrapTask(i + 1000 + "")).start();
		}

		// TODO 异步定时发消息不能成功为什么？
		//		ScheduledExecutorService schService = Executors.newScheduledThreadPool(Constants.CLIENT_NUM);
		//		for (int i = 0; i < Constants.CLIENT_NUM; i++) {
		//			ClientForLongConnection bootstrap = clients.get(i);
		//			schService.scheduleAtFixedRate(new ClientSendMsg2ServerTask(bootstrap), 5, 6, TimeUnit.SECONDS);
		//		}
	}
}

<<<<<<< .mine
///**
// * 项目名称：java
// * 文件包名：com.ly.java.mina
// * 文件名称：服务端代码2.java
// * 版本信息：SCEC_Branches
// * 生成日期：2016年4月26日 下午2:59:50
// * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
// * 
// */
//package com.ly.java.mina;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.charset.Charset;
//
//import org.apache.mina.core.service.IoAcceptor;
//import org.apache.mina.core.session.IdleStatus;
//import org.apache.mina.example.gettingstarted.timeserver.TimeServerHandler;
//import org.apache.mina.filter.codec.ProtocolCodecFilter;
//import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
//import org.apache.mina.filter.logging.LoggingFilter;
//import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
//
///**
// * 功能描述：
// * <p color="red">
// * 实现加载
// * </p>
// * 文件名称：服务端代码2.java
// * 
// * @author ly
// */
//public class 服务端代码 {
//
//    private static final int PORT = 9123;
//
//    public static void main(String[] args) throws IOException {
//	// 创建服务器监听
//	IoAcceptor acceptor = new NioSocketAcceptor();
//
//	// 增加日志过滤器
//	acceptor.getFilterChain().addLast("logger", new LoggingFilter());
//	
//	// 增加编码过滤器
//	acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
//
//	// 指定业务逻辑处理器
//	acceptor.setHandler(new 服务端业务类());
//	
//	// 设置buffer的长度
//	acceptor.getSessionConfig().setReadBufferSize(2048);
//	
//	// 设置连接超时时间
//	acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
//
//	// 绑定端口
//	acceptor.bind(new InetSocketAddress(PORT));
//    }
//}
=======
/**
 * 椤圭洰鍚嶇О锛歫ava
 * 鏂囦欢鍖呭悕锛歝om.ly.java.mina
 * 鏂囦欢鍚嶇О锛氭湇鍔＄浠ｇ爜2.java
 * 鐗堟湰淇℃伅锛歋CEC_Branches
 * 鐢熸垚鏃ユ湡锛�2016骞�4鏈�26鏃� 涓嬪崍2:59:50
 * Copyright (c) 2015-2015娣卞湷甯傛嘲涔呬俊鎭郴缁熻偂浠芥湁闄愬叕鍙�
 * 
 */
package com.ly.java.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.example.gettingstarted.timeserver.TimeServerHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * 鍔熻兘鎻忚堪锛�
 * <p color="red">
 * 瀹炵幇鍔犺浇
 * </p>
 * 鏂囦欢鍚嶇О锛氭湇鍔＄浠ｇ爜2.java
 * 
 * @author ly
 */
public class 鏈嶅姟绔唬鐮� {

    private static final int PORT = 9123;

    public static void main(String[] args) throws IOException {
	// 鍒涘缓鏈嶅姟鍣ㄧ洃鍚�
	IoAcceptor acceptor = new NioSocketAcceptor();

	// 澧炲姞鏃ュ織杩囨护鍣�
	acceptor.getFilterChain().addLast("logger", new LoggingFilter());
	
	// 澧炲姞缂栫爜杩囨护鍣�
	acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

	// 鎸囧畾涓氬姟閫昏緫澶勭悊鍣�
	acceptor.setHandler(new 鏈嶅姟绔笟鍔＄被());
	
	// 璁剧疆buffer鐨勯暱搴�
	acceptor.getSessionConfig().setReadBufferSize(2048);
	
	// 璁剧疆杩炴帴瓒呮椂鏃堕棿
	acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

	// 缁戝畾绔彛
	acceptor.bind(new InetSocketAddress(PORT));
    }
}
>>>>>>> .r35

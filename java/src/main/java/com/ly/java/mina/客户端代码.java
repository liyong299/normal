<<<<<<< .mine
///**
// * 项目名称：java
// * 文件包名：com.ly.java.mina
// * 文件名称：客户端代码.java
// * 版本信息：SCEC_Branches
// * 生成日期：2016年4月26日 下午2:41:18
// * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
// * 
// */
//package com.ly.java.mina;
//
//import java.net.InetSocketAddress;
//import java.nio.charset.Charset;
//
//import org.apache.mina.core.future.ConnectFuture;
//import org.apache.mina.filter.codec.ProtocolCodecFilter;
//import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
//import org.apache.mina.filter.logging.LoggingFilter;
//import org.apache.mina.transport.socket.nio.NioSocketConnector;
//
///**
// * 功能描述：
// * <p color="red">
// * 实现加载
// * </p>
// * 文件名称：客户端代码.java
// * 
// * @author ly
// */
//public class 客户端代码 {
//
//    public static void main(String[] args) {
//	// 创建客户端连接器.
//	NioSocketConnector connector = new NioSocketConnector();
//	connector.getFilterChain().addLast("logger", new LoggingFilter());
//	connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8")))); // 设置编码过滤器
//	connector.setConnectTimeout(30);
//	connector.setHandler(new 客户端业务类());// 设置事件处理器
//	ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 9123));// 建立连接
//	cf.awaitUninterruptibly();// 等待连接创建完成
//	cf.getSession().write("hello");// 发送消息
//	cf.getSession().write("quit");// 发送消息
//	cf.getSession().getCloseFuture().awaitUninterruptibly();// 等待连接断开
//	connector.dispose();
//    }
//}
=======
/**
 * 椤圭洰鍚嶇О锛歫ava
 * 鏂囦欢鍖呭悕锛歝om.ly.java.mina
 * 鏂囦欢鍚嶇О锛氬鎴风浠ｇ爜.java
 * 鐗堟湰淇℃伅锛歋CEC_Branches
 * 鐢熸垚鏃ユ湡锛�2016骞�4鏈�26鏃� 涓嬪崍2:41:18
 * Copyright (c) 2015-2015娣卞湷甯傛嘲涔呬俊鎭郴缁熻偂浠芥湁闄愬叕鍙�
 * 
 */
package com.ly.java.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * 鍔熻兘鎻忚堪锛�
 * <p color="red">
 * 瀹炵幇鍔犺浇
 * </p>
 * 鏂囦欢鍚嶇О锛氬鎴风浠ｇ爜.java
 * 
 * @author ly
 */
public class 瀹㈡埛绔唬鐮� {

    public static void main(String[] args) {
	// 鍒涘缓瀹㈡埛绔繛鎺ュ櫒.
	NioSocketConnector connector = new NioSocketConnector();
	connector.getFilterChain().addLast("logger", new LoggingFilter());
	connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8")))); // 璁剧疆缂栫爜杩囨护鍣�
	connector.setConnectTimeout(30);
	connector.setHandler(new 瀹㈡埛绔笟鍔＄被());// 璁剧疆浜嬩欢澶勭悊鍣�
	ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 9123));// 寤虹珛杩炴帴
	cf.awaitUninterruptibly();// 绛夊緟杩炴帴鍒涘缓瀹屾垚
	cf.getSession().write("hello");// 鍙戦�佹秷鎭�
	cf.getSession().write("quit");// 鍙戦�佹秷鎭�
	cf.getSession().getCloseFuture().awaitUninterruptibly();// 绛夊緟杩炴帴鏂紑
	connector.dispose();
    }
}
>>>>>>> .r35

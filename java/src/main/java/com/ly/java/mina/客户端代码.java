<<<<<<< .mine
///**
// * ÏîÄ¿Ãû³Æ£ºjava
// * ÎÄ¼ş°üÃû£ºcom.ly.java.mina
// * ÎÄ¼şÃû³Æ£º¿Í»§¶Ë´úÂë.java
// * °æ±¾ĞÅÏ¢£ºSCEC_Branches
// * Éú³ÉÈÕÆÚ£º2016Äê4ÔÂ26ÈÕ ÏÂÎç2:41:18
// * Copyright (c) 2015-2015ÉîÛÚÊĞÌ©¾ÃĞÅÏ¢ÏµÍ³¹É·İÓĞÏŞ¹«Ë¾
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
// * ¹¦ÄÜÃèÊö£º
// * <p color="red">
// * ÊµÏÖ¼ÓÔØ
// * </p>
// * ÎÄ¼şÃû³Æ£º¿Í»§¶Ë´úÂë.java
// * 
// * @author ly
// */
//public class ¿Í»§¶Ë´úÂë {
//
//    public static void main(String[] args) {
//	// ´´½¨¿Í»§¶ËÁ¬½ÓÆ÷.
//	NioSocketConnector connector = new NioSocketConnector();
//	connector.getFilterChain().addLast("logger", new LoggingFilter());
//	connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8")))); // ÉèÖÃ±àÂë¹ıÂËÆ÷
//	connector.setConnectTimeout(30);
//	connector.setHandler(new ¿Í»§¶ËÒµÎñÀà());// ÉèÖÃÊÂ¼ş´¦ÀíÆ÷
//	ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 9123));// ½¨Á¢Á¬½Ó
//	cf.awaitUninterruptibly();// µÈ´ıÁ¬½Ó´´½¨Íê³É
//	cf.getSession().write("hello");// ·¢ËÍÏûÏ¢
//	cf.getSession().write("quit");// ·¢ËÍÏûÏ¢
//	cf.getSession().getCloseFuture().awaitUninterruptibly();// µÈ´ıÁ¬½Ó¶Ï¿ª
//	connector.dispose();
//    }
//}
=======
/**
 * é¡¹ç›®åç§°ï¼šjava
 * æ–‡ä»¶åŒ…åï¼šcom.ly.java.mina
 * æ–‡ä»¶åç§°ï¼šå®¢æˆ·ç«¯ä»£ç .java
 * ç‰ˆæœ¬ä¿¡æ¯ï¼šSCEC_Branches
 * ç”Ÿæˆæ—¥æœŸï¼š2016å¹´4æœˆ26æ—¥ ä¸‹åˆ2:41:18
 * Copyright (c) 2015-2015æ·±åœ³å¸‚æ³°ä¹…ä¿¡æ¯ç³»ç»Ÿè‚¡ä»½æœ‰é™å…¬å¸
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
 * åŠŸèƒ½æè¿°ï¼š
 * <p color="red">
 * å®ç°åŠ è½½
 * </p>
 * æ–‡ä»¶åç§°ï¼šå®¢æˆ·ç«¯ä»£ç .java
 * 
 * @author ly
 */
public class å®¢æˆ·ç«¯ä»£ç  {

    public static void main(String[] args) {
	// åˆ›å»ºå®¢æˆ·ç«¯è¿æ¥å™¨.
	NioSocketConnector connector = new NioSocketConnector();
	connector.getFilterChain().addLast("logger", new LoggingFilter());
	connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8")))); // è®¾ç½®ç¼–ç è¿‡æ»¤å™¨
	connector.setConnectTimeout(30);
	connector.setHandler(new å®¢æˆ·ç«¯ä¸šåŠ¡ç±»());// è®¾ç½®äº‹ä»¶å¤„ç†å™¨
	ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 9123));// å»ºç«‹è¿æ¥
	cf.awaitUninterruptibly();// ç­‰å¾…è¿æ¥åˆ›å»ºå®Œæˆ
	cf.getSession().write("hello");// å‘é€æ¶ˆæ¯
	cf.getSession().write("quit");// å‘é€æ¶ˆæ¯
	cf.getSession().getCloseFuture().awaitUninterruptibly();// ç­‰å¾…è¿æ¥æ–­å¼€
	connector.dispose();
    }
}
>>>>>>> .r35

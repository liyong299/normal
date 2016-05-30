<<<<<<< .mine
///**
// * ÏîÄ¿Ãû³Æ£ºjava
// * ÎÄ¼ş°üÃû£ºcom.ly.java.mina
// * ÎÄ¼şÃû³Æ£º·şÎñ¶Ë´úÂë2.java
// * °æ±¾ĞÅÏ¢£ºSCEC_Branches
// * Éú³ÉÈÕÆÚ£º2016Äê4ÔÂ26ÈÕ ÏÂÎç2:59:50
// * Copyright (c) 2015-2015ÉîÛÚÊĞÌ©¾ÃĞÅÏ¢ÏµÍ³¹É·İÓĞÏŞ¹«Ë¾
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
// * ¹¦ÄÜÃèÊö£º
// * <p color="red">
// * ÊµÏÖ¼ÓÔØ
// * </p>
// * ÎÄ¼şÃû³Æ£º·şÎñ¶Ë´úÂë2.java
// * 
// * @author ly
// */
//public class ·şÎñ¶Ë´úÂë {
//
//    private static final int PORT = 9123;
//
//    public static void main(String[] args) throws IOException {
//	// ´´½¨·şÎñÆ÷¼àÌı
//	IoAcceptor acceptor = new NioSocketAcceptor();
//
//	// Ôö¼ÓÈÕÖ¾¹ıÂËÆ÷
//	acceptor.getFilterChain().addLast("logger", new LoggingFilter());
//	
//	// Ôö¼Ó±àÂë¹ıÂËÆ÷
//	acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
//
//	// Ö¸¶¨ÒµÎñÂß¼­´¦ÀíÆ÷
//	acceptor.setHandler(new ·şÎñ¶ËÒµÎñÀà());
//	
//	// ÉèÖÃbufferµÄ³¤¶È
//	acceptor.getSessionConfig().setReadBufferSize(2048);
//	
//	// ÉèÖÃÁ¬½Ó³¬Ê±Ê±¼ä
//	acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
//
//	// °ó¶¨¶Ë¿Ú
//	acceptor.bind(new InetSocketAddress(PORT));
//    }
//}
=======
/**
 * é¡¹ç›®åç§°ï¼šjava
 * æ–‡ä»¶åŒ…åï¼šcom.ly.java.mina
 * æ–‡ä»¶åç§°ï¼šæœåŠ¡ç«¯ä»£ç 2.java
 * ç‰ˆæœ¬ä¿¡æ¯ï¼šSCEC_Branches
 * ç”Ÿæˆæ—¥æœŸï¼š2016å¹´4æœˆ26æ—¥ ä¸‹åˆ2:59:50
 * Copyright (c) 2015-2015æ·±åœ³å¸‚æ³°ä¹…ä¿¡æ¯ç³»ç»Ÿè‚¡ä»½æœ‰é™å…¬å¸
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
 * åŠŸèƒ½æè¿°ï¼š
 * <p color="red">
 * å®ç°åŠ è½½
 * </p>
 * æ–‡ä»¶åç§°ï¼šæœåŠ¡ç«¯ä»£ç 2.java
 * 
 * @author ly
 */
public class æœåŠ¡ç«¯ä»£ç  {

    private static final int PORT = 9123;

    public static void main(String[] args) throws IOException {
	// åˆ›å»ºæœåŠ¡å™¨ç›‘å¬
	IoAcceptor acceptor = new NioSocketAcceptor();

	// å¢åŠ æ—¥å¿—è¿‡æ»¤å™¨
	acceptor.getFilterChain().addLast("logger", new LoggingFilter());
	
	// å¢åŠ ç¼–ç è¿‡æ»¤å™¨
	acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

	// æŒ‡å®šä¸šåŠ¡é€»è¾‘å¤„ç†å™¨
	acceptor.setHandler(new æœåŠ¡ç«¯ä¸šåŠ¡ç±»());
	
	// è®¾ç½®bufferçš„é•¿åº¦
	acceptor.getSessionConfig().setReadBufferSize(2048);
	
	// è®¾ç½®è¿æ¥è¶…æ—¶æ—¶é—´
	acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

	// ç»‘å®šç«¯å£
	acceptor.bind(new InetSocketAddress(PORT));
    }
}
>>>>>>> .r35

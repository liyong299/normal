<<<<<<< .mine
///**
// * ÏîÄ¿Ãû³Æ£ºjava
// * ÎÄ¼ş°üÃû£ºcom.ly.java.mina
// * ÎÄ¼şÃû³Æ£º·şÎñ¶Ë´úÂë.java
// * °æ±¾ĞÅÏ¢£ºSCEC_Branches
// * Éú³ÉÈÕÆÚ£º2016Äê4ÔÂ26ÈÕ ÏÂÎç2:41:28
// * Copyright (c) 2015-2015ÉîÛÚÊĞÌ©¾ÃĞÅÏ¢ÏµÍ³¹É·İÓĞÏŞ¹«Ë¾
// * 
// */
//package com.ly.java.mina;
//
//import java.util.Date;
//
//import org.apache.mina.core.service.IoHandlerAdapter;
//import org.apache.mina.core.session.IdleStatus;
//import org.apache.mina.core.session.IoSession;
//
///**
// * ¹¦ÄÜÃèÊö£º
// * <p color="red">
// * ÊµÏÖ¼ÓÔØ
// * </p>
// * ÎÄ¼şÃû³Æ£º·şÎñ¶Ë´úÂë.java
// * 
// * @author ly
// */
//public class ·şÎñ¶ËÒµÎñÀà extends IoHandlerAdapter {
//
//    // Á¬½ÓÒì³£Ê±´¦Àí·½·¨
//    @Override
//    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
//	System.out.println("exceptionCaught method was called!");
//	cause.printStackTrace();
//    }
//
//    @Override
//    public void messageReceived(IoSession session, Object message) throws Exception {
//	System.out.println("messageReceived method was called!");
//	String str = message.toString();
//	System.out.println("½ÓÊÕµ½µÄ×Ö·û´®£º" + str);
//	if (str.trim().equalsIgnoreCase("quit")) {
////	    session.close(true);
//	    session.closeNow();
//	    return;
//	}
//	Date date = new Date();
//	session.write(date.toString());
//	System.out.println("Message written...");
//    }
//
//    @Override
//    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
//	System.out.println("sessionIdle method was called!");
//	System.out.println("IDLE " + session.getIdleCount(status));
//    }
//
//}
=======
/**
 * é¡¹ç›®åç§°ï¼šjava
 * æ–‡ä»¶åŒ…åï¼šcom.ly.java.mina
 * æ–‡ä»¶åç§°ï¼šæœåŠ¡ç«¯ä»£ç .java
 * ç‰ˆæœ¬ä¿¡æ¯ï¼šSCEC_Branches
 * ç”Ÿæˆæ—¥æœŸï¼š2016å¹´4æœˆ26æ—¥ ä¸‹åˆ2:41:28
 * Copyright (c) 2015-2015æ·±åœ³å¸‚æ³°ä¹…ä¿¡æ¯ç³»ç»Ÿè‚¡ä»½æœ‰é™å…¬å¸
 * 
 */
package com.ly.java.mina;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * åŠŸèƒ½æè¿°ï¼š
 * <p color="red">
 * å®ç°åŠ è½½
 * </p>
 * æ–‡ä»¶åç§°ï¼šæœåŠ¡ç«¯ä»£ç .java
 * 
 * @author ly
 */
public class æœåŠ¡ç«¯ä¸šåŠ¡ç±» extends IoHandlerAdapter {

    // è¿æ¥å¼‚å¸¸æ—¶å¤„ç†æ–¹æ³•
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
	System.out.println("exceptionCaught method was called!");
	cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
	System.out.println("messageReceived method was called!");
	String str = message.toString();
	System.out.println("æ¥æ”¶åˆ°çš„å­—ç¬¦ä¸²ï¼š" + str);
	if (str.trim().equalsIgnoreCase("quit")) {
//	    session.close(true);
	    session.closeNow();
	    return;
	}
	Date date = new Date();
	session.write(date.toString());
	System.out.println("Message written...");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
	System.out.println("sessionIdle method was called!");
	System.out.println("IDLE " + session.getIdleCount(status));
    }

}
>>>>>>> .r35

/*    */ package cn.mopon.cec.task;
/*    */ 
/*    */ import cn.mopon.cec.core.service.TicketOrderService;
/*    */ import coo.core.util.SpringUtils;

/*    */ import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TicketOrderSyncTask
/*    */   implements Callable<Void>
/*    */ {
/* 15 */   private Logger log = LoggerFactory.getLogger(getClass());
/*    */   
/*    */ 
/*    */ 
/*    */   private String orderId;
/*    */   private  CountDownLatch cdl;

/**
 * 构造方法。
 * 
 * @param orderId
 *            订单ID
 */
public TicketOrderSyncTask(String orderId, CountDownLatch cdl) {
	this.orderId = orderId;
	this.cdl = cdl;
}
/*    */   
/*    */   public Void call() throws Exception
/*    */   {
/*    */     try {
/* 31 */       TicketOrderService ticketOrderService = (TicketOrderService)SpringUtils.getBean("ticketOrderService");
/*    */       
/* 33 */       ticketOrderService.proccessAbnormalOrder(this.orderId);
/*    */     } catch (Exception e) {
/* 35 */       this.log.error("定时处理异常订单时发生异常。", e);
/*    */     }
finally
{
	if(this.cdl != null) cdl.countDown();
	if (log.isDebugEnabled()) log.debug("this order is : " + this.orderId);
}
/* 37 */     return null;
/*    */   }
/*    */ }


/* Location:              E:\01_work\02_cec\03_需求\01_院线通\11_OOM2\cec.task-2.6.2.jar!\cn\mopon\cec\task\TicketOrderSyncTask.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
/*    */ package cn.mopon.cec.task;
/*    */ 
/*    */ import cn.mopon.cec.core.service.ShowService;
/*    */ import coo.core.util.SpringUtils;

/*    */ import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.data.redis.core.RedisTemplate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShowSyncTask
/*    */   implements Callable<Void>
/*    */ {
/* 16 */   private final Logger log = LoggerFactory.getLogger(getClass());
/*    */   
/*    */ private CountDownLatch cdl;
/*    */ 
/*    */   private String cinemaId;
/*    */   
/*    */ 
/*    */ 
/*    */   public ShowSyncTask(String cinemaId, CountDownLatch cdl) {
	this.cinemaId = cinemaId;
	this.cdl = cdl;
}
/*    */   
/*    */   public Void call() throws Exception
/*    */   {
/*    */     try {
/* 32 */       ShowService showService = (ShowService)SpringUtils.getBean("showService");
/* 33 */       showService.syncShows(this.cinemaId);
/*    */     } catch (Exception e) {
/* 35 */       RedisTemplate<String, String> redisTemplate = (RedisTemplate)SpringUtils.getBean("redisTemplate");
/*    */       
/* 37 */       redisTemplate.delete("SyncShow_" + this.cinemaId);
/* 38 */       this.log.error("SyncShows has happened error,cinemaId:" + this.cinemaId, e);
/*    */     }
finally
{
	if (this.cdl != null)this.cdl.countDown();
	if (log.isDebugEnabled()) log.debug("this cinema is : " + this.cinemaId);
}
/* 40 */     return null;
/*    */   }
/*    */ }


/* Location:              E:\01_work\02_cec\03_需求\01_院线通\11_OOM2\cec.task-2.6.2.jar!\cn\mopon\cec\task\ShowSyncTask.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
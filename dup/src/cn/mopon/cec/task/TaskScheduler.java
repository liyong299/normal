package cn.mopon.cec.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import cn.mopon.cec.api.presell.TicketOrderGenService;
import cn.mopon.cec.core.entity.Cinema;
import cn.mopon.cec.core.entity.Show;
import cn.mopon.cec.core.entity.TicketOrder;
import cn.mopon.cec.core.model.FilmSyncModel;
import cn.mopon.cec.core.service.BenefitCardService;
import cn.mopon.cec.core.service.BenefitCardStatService;
import cn.mopon.cec.core.service.ChannelShowService;
import cn.mopon.cec.core.service.CinemaService;
import cn.mopon.cec.core.service.FilmService;
import cn.mopon.cec.core.service.PresellOrderSendRecordService;
import cn.mopon.cec.core.service.ShowService;
import cn.mopon.cec.core.service.SnackOrderService;
import cn.mopon.cec.core.service.SpecialPolicyService;
import cn.mopon.cec.core.service.StatService;
import cn.mopon.cec.core.service.TaskExecuteService;
import cn.mopon.cec.core.service.TaskScheduleService;
import cn.mopon.cec.core.service.TicketOrderService;
import cn.mopon.cec.core.service.TicketVoucherService;
import cn.mopon.cec.core.util.DateUtil;
import cn.mopon.cec.core.util.StringUtil;
import coo.base.util.DateUtils;
import coo.core.util.SpringUtils;

public class TaskScheduler
{
  private Logger log = LoggerFactory.getLogger(getClass());
  private final int intervalTime = 5;
  private static String ip = "";
  
	private static ThreadFactory syncShowsFactory = new NamedThreadFactory(
		"syncShows_" + System.currentTimeMillis(), false);
	private static ThreadFactory processFactory = new NamedThreadFactory(
		"process_" + System.currentTimeMillis(), false);
	private static ExecutorService showThreadPool = null;
	private static ExecutorService processAbnormalThreadPool = null;
 
  
  private static int nThreads = Runtime.getRuntime().availableProcessors() * 2;
  
  static
  {
    ip = StringUtil.getLocalIpStr();
    showThreadPool = Executors.newFixedThreadPool(nThreads,
		syncShowsFactory);
    processAbnormalThreadPool = Executors.newFixedThreadPool(nThreads,
		processFactory);
  }
  public void doTest()
  {
    this.log.info("TaskScheduler.dotest>>>>>>>>>>>>>>>>>>>>>>>>ip:" + ip + "|now:" + DateUtil.getNowDate());
  }
  
  public void syncHalls()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      CinemaService cinemaService = (CinemaService)SpringUtils.getBean("cinemaService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        List<HallSyncTask> tasks = new ArrayList();
        for (Cinema cinema : cinemaService.getProvideTicketEnabledCinemas()) {
          tasks.add(new HallSyncTask(cinema.getId()));
        }
        ExecutorService hallSyncTaskExecutor = Executors.newFixedThreadPool(nThreads);
        
        hallSyncTaskExecutor.invokeAll(tasks);
        hallSyncTaskExecutor.shutdown();
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("syncHalls.ip:" + ip + "定时同步影厅已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("syncHalls.ip:" + ip + "定时同步影厅时发生异常。", e);
    }
  }
  
  public void syncMovicesAndFilms()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      FilmService filmService = (FilmService)SpringUtils.getBean("filmService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        filmService.syncFilms(new FilmSyncModel());
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("syncMovicesAndFilms.ip:" + ip + "定时同步影片已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("syncMovicesAndFilms.ip:" + ip + "定时同步影片时发生异常。", e);
    }
  }
  
  public void syncShows()
  {
    try
    {
      long t1 = System.nanoTime();
      this.log.info("TaskScheduler.syncShows|started>>>t:" + DateUtil.getNowDate());
      
      CinemaService cinemaService = (CinemaService)SpringUtils.getBean("cinemaService");
      List<String> cinemaIds = cinemaService.getIds();
      CountDownLatch cdl = new CountDownLatch(cinemaIds.size());
//      List<ShowSyncTask> tasks = new ArrayList();
      for (String cinemaId : cinemaIds) {
//        tasks.add(new ShowSyncTask(cinemaId));
        showThreadPool.submit(new ShowSyncTask(cinemaId, cdl));
      }
//      ExecutorService showSyncTaskExecutor = Executors.newFixedThreadPool(nThreads);
//      
//      showSyncTaskExecutor.invokeAll(tasks);
      cdl.await();
      this.log.info("TaskScheduler.syncShows|end<<<t:" + DateUtil.getNowDate() + ",total cost time(ms):" + (System.nanoTime() - t1) * 1.0E-6D);
    }
    catch (Exception e)
    {
      this.log.info("TaskScheduler.syncShows|error<<<t:" + DateUtil.getNowDate());
    }
  }
  
  public void cleanShows()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      CinemaService cinemaService = (CinemaService)SpringUtils.getBean("cinemaService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        List<ShowCleanTask> tasks = new ArrayList();
        for (Cinema cinema : cinemaService.getProvideTicketEnabledCinemas()) {
          tasks.add(new ShowCleanTask(cinema.getId()));
        }
        ExecutorService showCleanTaskExecutor = Executors.newFixedThreadPool(nThreads);
        
        showCleanTaskExecutor.invokeAll(tasks);
        showCleanTaskExecutor.shutdown();
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("cleanShows.ip:" + ip + "定时清理排期已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("cleanShows.ip:" + ip + "定时清理排期时发生异常。", e);
    }
  }
  
  public void updateExpiredShows()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      ShowService showService = (ShowService)SpringUtils.getBean("showService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        showService.updateExpiredShows();
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("updateExpiredShows.ip:" + ip + "定时更新过期排期已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("updateExpiredShows.ip:" + ip + "定时更新过期排期时发生异常。", e);
    }
  }
  
  public void updateExpiredChannelShows()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      ChannelShowService channelShowService = (ChannelShowService)SpringUtils.getBean("channelShowService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        channelShowService.updateExpiredChannelShows();
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("updateExpiredChannelShows.ip:" + ip + "定时更新过期渠道排期已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("updateExpiredChannelShows.ip:" + ip + "定时更新过期渠道排期时发生异常。", e);
    }
  }
  
  public void updateExpiredTicketOrder()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      TicketOrderService ticketOrderService = (TicketOrderService)SpringUtils.getBean("ticketOrderService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        ticketOrderService.updateExpiredTicketOrder();
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("updateExpiredTicketOrder.ip:" + ip + "定时更新过期选座票订单已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("updateExpiredTicketOrder.ip:" + ip + "定时更新过期选座票订单时发生异常。", e);
    }
  }
  
  public void cleanExpiredTicketOrder()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      TicketOrderService ticketOrderService = (TicketOrderService)SpringUtils.getBean("ticketOrderService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        ticketOrderService.cleanCanceledTicketOrder();
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("cleanExpiredTicketOrder.ip:" + ip + "定时清理取消的选座票订单已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("cleanExpiredTicketOrder.ip:" + ip + "定时清理取消的选座票订单时发生异常。", e);
    }
  }
  
  public void updateExpiredTicketVoucher()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      TicketVoucherService ticketVoucherService = (TicketVoucherService)SpringUtils.getBean("ticketVoucherService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        ticketVoucherService.updateExpiredTicketVoucher();
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("updateExpiredTicketVoucher.ip:" + ip + "定时更新过期选座票凭证已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("updateExpiredTicketVoucher.ip:" + ip + "定时更新过期选座票凭证时发生异常。", e);
    }
  }
  
  public void processAbnormalOrders()
  {
    TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
    
    TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
    
    TicketOrderService ticketOrderService = (TicketOrderService)SpringUtils.getBean("ticketOrderService");
    try
    {
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
//        List<TicketOrderSyncTask> tasks = new ArrayList();
        List<TicketOrder> orders = ticketOrderService
		.searchAbnormalTicketOrder();
        CountDownLatch cdl = new CountDownLatch(orders.size());
        for (TicketOrder abnormalOrder : orders) {
//          tasks.add(new TicketOrderSyncTask(abnormalOrder.getId()));
          processAbnormalThreadPool.submit(new TicketOrderSyncTask(
			abnormalOrder.getId(), cdl));
        }
//        ExecutorService ticketOrderSyncTaskExecutor = Executors.newFixedThreadPool(nThreads);
//        
//        ticketOrderSyncTaskExecutor.invokeAll(tasks);
        cdl.await();
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("processAbnormalOrders.ip:" + ip + "定时处理异常订单已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("processAbnormalOrders.ip:" + ip + "定时处理异常订单时发生异常。", e);
    }
  }
  
  public void sendProcessAbnormalOrdersFailMail()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      TicketOrderService ticketOrderService = (TicketOrderService)SpringUtils.getBean("ticketOrderService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        ticketOrderService.sendAbnormalOrderFailListMail();
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("sendProcessAbnormalOrdersFailMail.ip:" + ip + "定时发送异常订单处理失败邮件已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("sendProcessAbnormalOrdersFailMail.ip:" + ip + "定时发送异常订单处理失败邮件时发生异常。", e);
    }
  }
  
  public void syncTicketOrderDetail()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      StatService statService = (StatService)SpringUtils.getBean("statService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        Date date = DateUtils.getPrevDay();
        statService.deleteExistStatDateOrder(date);
        statService.syncTicketOrderDetail(date);
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("syncTicketOrderDetail.ip:" + ip + "定时生成选座票订单日统计记录任务已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("syncTicketOrderDetail.ip:" + ip + "定时生成选座票订单日统计记录时发生异常。", e);
    }
  }
  
  public void autoEnableSpecialPolicys()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      SpecialPolicyService specialPolicyService = (SpecialPolicyService)SpringUtils.getBean("specialPolicyService");
      
      ChannelShowService channelShowService = (ChannelShowService)SpringUtils.getBean("channelShowService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        List<Show> shows = specialPolicyService.autoEnableSpecialPolicys();
        
        channelShowService.batchGenChannelShows(shows);
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("autoEnableSpecialPolicys.ip:" + ip + "定时启用特殊定价策略已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("autoEnableSpecialPolicys.ip:" + ip + "定时启用特殊定价策略时发生异常。", e);
    }
  }
  
  @Scheduled(cron="${specialPolicy.auto.disable.cron:0 30 0 * * ?}")
  public void autoDisableSpecialPolicys()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      SpecialPolicyService specialPolicyService = (SpecialPolicyService)SpringUtils.getBean("specialPolicyService");
      
      ChannelShowService channelShowService = (ChannelShowService)SpringUtils.getBean("channelShowService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        List<Show> shows = specialPolicyService.autoDisableSpecialPolicys();
        
        channelShowService.batchGenChannelShows(shows);
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("autoDisableSpecialPolicys.ip:" + ip + "定时停用特殊定价策略已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("autoDisableSpecialPolicys.ip:" + ip + "定时停用特殊定价策略时发生异常。", e);
    }
  }
  
  public void expireBenefitCard()
  {
    try
    {
      this.log.info("expireBenefitCard.ip:" + ip + "|started>>>");
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      BenefitCardService benefitCardService = (BenefitCardService)SpringUtils.getBean("benefitCardService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        benefitCardService.expireBenefitCard();
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("expireBenefitCard.ip:" + ip + "定时过期权益卡已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("expireBenefitCard.ip:" + ip + "定时过期权益卡时发生异常。", e);
    }
  }
  
  public void syncBenefitCardDetail()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      BenefitCardStatService benefitCardStatService = (BenefitCardStatService)SpringUtils.getBean("benefitCardStatService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        Date date = DateUtils.getPrevDay();
        benefitCardStatService.deleteExistStatDateOrder(date);
        benefitCardStatService.saveStatByDate(date);
        benefitCardStatService.saveRechargeStatByDate(date);
        benefitCardStatService.saveConsumeStatByDate(date);
        
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("syncBenefitCardDetail.ip:" + ip + "定时生成权益卡日统计记录任务已完成。");
      }
    }
    catch (Exception e)
    {
      this.log.error("syncBenefitCardDetail.ip:" + ip + "定时生成权益卡日统计记录时发生异常。", e);
    }
  }
  
  public void updateExpiredSnackOrder()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      SnackOrderService snackOrderService = (SnackOrderService)SpringUtils.getBean("snackOrderService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        snackOrderService.updateExpiredSnackOrder();
        
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("updateExpiredSnackOrder.ip:" + ip + "定时生成权益卡日统计记录任务已完成。");
      }
      this.log.info("updateExpiredSnackOrder.ip:" + ip + "定时更新过期卖品订单已完成。");
    }
    catch (Exception e)
    {
      this.log.error("updateExpiredSnackOrder.ip:" + ip + "定时更新过期卖品订单时发生异常。", e);
    }
  }
  
  public void cleanExpiredSnackOrder()
  {
    try
    {
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      SnackOrderService snackOrderService = (SnackOrderService)SpringUtils.getBean("snackOrderService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        snackOrderService.cleanCanceledSnackOrder();
        
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("cleanExpiredSnackOrder.ip:" + ip + "定时生成权益卡日统计记录任务已完成。");
      }
      this.log.info("cleanExpiredSnackOrder.ip:" + ip + "定时清理取消的卖品订单已完成。");
    }
    catch (Exception e)
    {
      this.log.error("cleanExpiredSnackOrder.ip:" + ip + "定时清理取消的卖品订单时发生异常。", e);
    }
  }
  
  public void delChannelShowStatics()
  {
    try
    {
      this.log.info("delChannelShowStatics.ip:" + ip + "定时删除静态文件。>>> begin; ");
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      CinemaService cinemaService = (CinemaService)SpringUtils.getBean("cinemaService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        List<DelChannelShowStaticTask> tasks = new ArrayList();
        for (String cinema : cinemaService.getEnabledCinemaCodes()) {
          tasks.add(new DelChannelShowStaticTask(cinema));
        }
        ExecutorService showSyncTaskExecutor = Executors.newFixedThreadPool(64);
        
        showSyncTaskExecutor.invokeAll(tasks);
        
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("delChannelShowStatics.ip:" + ip + "定时删除静态文件。>>> end; ");
      }
    }
    catch (Exception e)
    {
      this.log.error("delChannelShowStatics.ip:" + ip + "定时删除静态文件。>>> end; ", e);
    }
  }
  
  public void updateExpiredShowSeat()
  {
    try
    {
      this.log.info("TaskScheduler.updateExpiredShowSeat>>>ip:" + ip + "|" + DateUtil.getNowDate());
      
      TaskExecuteService taskSyncService = (TaskExecuteService)SpringUtils.getBean("taskExecuteService");
      
      TaskScheduleService taskScheduleService = (TaskScheduleService)SpringUtils.getBean("taskScheduleService");
      
      TicketOrderService ticketOrderService = (TicketOrderService)SpringUtils.getBean("ticketOrderService");
      if (taskSyncService.isTaskNotRunning(Thread.currentThread().getStackTrace()[1].getMethodName(), 5L))
      {
        this.log.info("TaskScheduler.updateExpiredShowSeat>>>start|ip:" + ip);
        ticketOrderService.updateExpiredShowSeat();
        taskScheduleService.delTaskScheduleByName(Thread.currentThread().getStackTrace()[1].getMethodName());
        
        this.log.info("TaskScheduler.updateExpiredShowSeat<<<end|ip:" + ip);
      }
    }
    catch (Exception e)
    {
      this.log.error("TaskScheduler.updateExpiredShowSeat<<<error|ip:" + ip, e);
    }
  }
  
  public void dealChannelShows()
  {
    this.log.info("TaskScheduler.dealChannelShows>>>ip:" + ip + "|" + DateUtil.getNowDate());
    
    TicketOrderGenService ticketOrderGenService = (TicketOrderGenService)SpringUtils.getBean("ticketOrderGenService");
    try
    {
      ticketOrderGenService.dealChannelShows();
    }
    catch (Exception e)
    {
      this.log.error("TaskScheduler.dealChannelShows<<<error|ip:" + ip, e);
    }
  }
  
  public void syncPresellOrderSend()
  {
    try
    {
      this.log.info("syncPresellOrderSend.ip:" + ip + "定时推送预售订单>>> begin; ");
      
      PresellOrderSendRecordService presellOrderSendRecordService = (PresellOrderSendRecordService)SpringUtils.getBean("presellOrderSendRecordService");
      presellOrderSendRecordService.syncPresellOrderSend();
    }
    catch (Exception e)
    {
      this.log.error("syncPresellOrderSend.ip:" + ip + "定时推送预售订单>>> end; ", e);
    }
  }
}

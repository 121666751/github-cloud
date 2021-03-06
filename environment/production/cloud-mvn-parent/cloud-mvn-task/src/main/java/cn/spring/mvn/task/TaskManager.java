package cn.spring.mvn.task;

import java.util.Calendar;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import cn.spring.mvn.comm.util.CommUtil;
import cn.spring.mvn.system.entity.SysBatchTaskTimer;
/**
 * @author LiuTao @date 2018年6月1日 下午3:53:17
 * @ClassName: TaskTool 
 * @Description: 任务管理器   对定时任务进行新增修改(没有实现删除)
 */
@SuppressWarnings("unchecked")
public class TaskManager {
	
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	/**
	 * @author LiuTao @date 2019年2月2日 下午1:05:28 
	 * @Title: addOrModifyJobByCron 
	 * @Description: 添加一个定时任务,使用传入的任务组名,触发器名,触发器组名
	 * @param jobClazz 任务
	 * @param jobGroupName 任务名
	 * @param jobGroupNumber 任务号
	 * @param cron 定时规则
	 * @throws Exception
	 */
	public static void addOrModifyJobByCron(Class<?> jobClazz, String jobName, String jobNumber, String cron, Object job) throws Exception{
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			TriggerKey triggerKey = new TriggerKey(jobName, jobNumber);
			Trigger trigger = scheduler.getTrigger(triggerKey);
			if(trigger == null){
				// job 唯一标识 
				JobKey jobKey = new JobKey(jobName, jobNumber);
				// 任务名,任务组,任务执行类
				JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) jobClazz).withIdentity(jobKey).build();
				// 触发器
				CronTrigger cronTrigger = TriggerBuilder
										.newTrigger()
										.withIdentity(triggerKey)
										.withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
				
				// 设置参数到JobDataMap中在TaksJob/TaskJobGroup中获取
				jobDetail.getJobDataMap().put("jobTimer", job);//定时任务用
				jobDetail.getJobDataMap().put("jobGroupNumber", jobNumber);//批量任务组使用
				// 触发器时间设定
				scheduler.scheduleJob(jobDetail, cronTrigger);
				// 启动
				if (!scheduler.isShutdown()) {
					scheduler.start();
				}
			}else {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String oldCron = cronTrigger.getCronExpression();
				if(!(oldCron.equalsIgnoreCase(cron))){
					removeJob(jobName, jobNumber);
					addOrModifyJobByCron(jobClazz, jobName, jobNumber, cron, job);
				}
			}
		} catch (SchedulerException e) {
			throw e;
		}
	}
	/**
	 * @author LiuTao @date 2019年2月2日 下午1:05:28 
	 * @Title: addOrModifyJobByTimer 
	 * @Description: 添加一个定时任务,使用传入的任务组名,触发器名,触发器组名
	 * @param jobClazz 任务
	 * @param jobGroupName 任务名
	 * @param jobGroupNumber 任务号
	 * @param cron 定时规则
	 * @throws Exception
	 */
	/**
	 * @author LiuTao @date 2019年2月2日 下午1:37:20 
	 * @Title: addOrModifyJobByTaskTimer 
	 * @Description: 通过定时任务表新增或者修改一个定时任务 
	 * @param jobClazz
	 * @param entity
	 * @throws Exception
	 */
	public static void addOrModifyJobByTaskTimer(Class<?> jobClazz, SysBatchTaskTimer entity) throws Exception{
		String year = entity.getJobPeriodYear();
		String week = entity.getJobPeriodWeek();
		String month = entity.getJobPeriodMonth();
		String day = entity.getJobPeriodDay();
		String hour = entity.getJobPeriodHour();
		String minute = entity.getJobPeriodMinute();
		String second = entity.getJobPeriodSecond();
		//systemBatchTimeDispathControlStatusRuning获得corn的规则
		String  cron = (second == null ? "*": second) +" "+ (minute == null ? "*": minute) +" "+ (hour == null ? "*":hour) +" "+  
				(day == null ? "*":day) +" "+ (month == null ? "":month) +" "+ (week == null ? "":week) +" "+ (year == null ? "*":year);
		cron = "0/30 * * * * ?";//每分钟的每15秒执行一次  ----具体的cron配置不太懂,需要看一看
		String jobName = entity.getJobGroupName() + CommUtil.ACROSS + entity.getJobName();//
		String jobNumber = entity.getJobGroupNumber() + CommUtil.ACROSS + entity.getJobNumber();
		addOrModifyJobByCron(jobClazz, jobName, jobNumber, cron, entity);
		
	}
	/**
	 * @author LiuTao @date 2018年6月4日 下午11:25:17 
	 * @Title: addJobByCron 
	 * @Description:  添加一个定时任务,使用传入的任务组名,触发器名,触发器组名
	 * @param jobClazz 任务
	 * @param jobName 任务名
	 * @param triggerGroupName 定时器组
	 * @param jobGroupName 任务组名
	 * @param cron 定时规则
	 */
	public static void addJobByCron(Class<?> jobClazz, String jobName, String jobNumber, String cron) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			// job 唯一标识 
			JobKey jobKey = new JobKey(jobName, jobNumber);
			// 任务名,任务组,任务执行类
			JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) jobClazz).withIdentity(jobKey).build();
			// 触发器
			TriggerKey triggerKey = new TriggerKey(jobName, jobNumber);
			CronTrigger cronTrigger = TriggerBuilder
									.newTrigger()
									.withIdentity(triggerKey)
									.withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
			// 触发器时间设定
			scheduler.scheduleJob(jobDetail, cronTrigger);
			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
			// 以上步骤没有问题传递参数STATUS为SUCCESS
			jobDetail.getJobDataMap().put("STATUS", "SUCCESS");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * @author LiuTao @date 2018年6月4日 下午11:28:21 
	 * @Title: modifyJobByCron 
	 * @Description: 修改指定任务的触发时间(使用默认的任务组名,触发器名,触发器组名) 
	 * @param jobName 任务名
	 * @param triggerGroupName 定时器组
	 * @param jobGroupName 任务组名
	 * @param cron 定时规则
	 */
	public static void modifyJobByCron(String jobName, String jobNumber, String cron) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			TriggerKey triggerKey = new TriggerKey(jobName, jobNumber);
			CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (cronTrigger == null) {
				return;
			}else {
				String oldCron = cronTrigger.getCronExpression();
				if(!(oldCron.equalsIgnoreCase(cron))){
					JobKey jobKey = new JobKey(jobName, jobNumber);
					JobDetail jobDetail = scheduler.getJobDetail(jobKey);
					Class<?> jobClazz = jobDetail.getJobClass();
					removeJob(jobName, jobNumber);
					addJobByCron(jobClazz, jobName, jobNumber, cron);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * @author LiuTao @date 2018年6月1日 下午12:04:30 
	 * @Title: addJobByTime 
	 * @Description: 添加一个定时任务,使用存入的任务组名,触发器名,触发器组名
	 * @param jobClazz
	 * @param jobName 任务名
	 * @param triggerGroupName
	 * @param jobGroupName 任务
	 * @param time 时间设置
	 */
	public static void addJobByTime(Class<?> jobClazz, String jobName, String jobNumber, long time) {
		Date date = new Date();
		Calendar ca = Calendar.getInstance();
		long millis = date.getTime() + time;
		ca.setTimeInMillis(millis);
		date = ca.getTime();
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			// job 唯一标识 
			JobKey jobKey = new JobKey(jobName, jobNumber);
			// 任务名,任务组,任务执行类
			JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) jobClazz).withIdentity(jobKey).build();
			// 触发器
			TriggerKey triggerKey = new TriggerKey(jobName, jobNumber);
			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity(triggerKey)
					// 延迟一秒执行
					.startAt(date)
					// 每隔一秒执行 并一直重复
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
										.withIntervalInSeconds(10).repeatForever())
					.build();
			// 触发器时间设定
			scheduler.scheduleJob(jobDetail, trigger);
			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
			// 以上步骤没有问题传递参数STATUS为SUCCESS
			jobDetail.getJobDataMap().put("STATUS", "SUCCESS");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * @author LiuTao @date 2018年6月1日 下午12:04:11 
	 * @Title: modifyJobTime 
	 * @Description: 修改一个任务的触发时间(使用默认的任务组名,触发器名,触发器组名)
	 * @param jobName
	 * @param time
	 */
	public static void modifyJobByTime(String jobName, String jobNumber, long time) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			TriggerKey triggerKey = new TriggerKey(jobName, jobNumber);
			Trigger trigger = scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}else {
				JobKey jobKey = new JobKey(jobName, jobNumber);
				JobDetail jobDetail = scheduler.getJobDetail(jobKey);
				Class<?> jobClazz = jobDetail.getJobClass();
				removeJob(jobName, jobNumber);
				addJobByTime(jobClazz, jobName, jobNumber, time);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @author LiuTao @date 2018年6月1日 下午12:03:58 
	 * @Title: removeJob 
	 * @Description: 移除一个任务(使用默认的任务组名,触发器名,触发器组名)
	 * @param jobName
	 */
	public static void removeJob(String jobName, String jobNumber) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			TriggerKey triggerkey = new TriggerKey(jobName, jobNumber);
			JobKey jobKey = new JobKey(jobName, jobNumber);
			scheduler.pauseTrigger(triggerkey);// 停止触发器
			scheduler.unscheduleJob(triggerkey);// 移除触发器
			scheduler.deleteJob(jobKey);// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @author LiuTao @date 2018年6月1日 下午12:03:45 
	 * @Title: startJobs 
	 * @Description: 启动所有定时任务
	 */
	public static void startJobs() {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @author LiuTao @date 2018年6月1日 下午12:03:34 
	 * @Title: shutdownJobs 
	 * @Description: 关闭所有定时任务
	 */
	public static void shutdownJobs() {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

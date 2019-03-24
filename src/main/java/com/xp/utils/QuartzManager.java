package com.xp.utils;

import org.quartz.*;

/**
 * @Author xp
 * @CreateTime 2019/03/24  11:30
 * @Function 动态定时任务管理
 */
public class QuartzManager {

    private Scheduler scheduler;

    public Scheduler getScheduler() {
        return scheduler;
    }
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 新增定时任务
     * */
    public void addJob(String jobName,String jobGroupName,String triggerName,
                        String triggerGroupName,Class jobClass,String cron){

        try
        {
            //1. 指定要运行的任务。设置任务名,任务组名
            JobDetail jobDetail= JobBuilder.newJob(jobClass)
                                .withIdentity(jobName, jobGroupName)
                                .build();

            //2. 配置触发器
            //a. 新建一个触发器构造类
            TriggerBuilder triggerBuilder=TriggerBuilder.newTrigger();
            //b. 设置触发器名,触发器组名
            triggerBuilder.withIdentity(triggerName, triggerGroupName).startNow();
            //c. 设置触发器时间规则
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            //d. 用触发器构造类创建trigger类
            CronTrigger cronTrigger= (CronTrigger) triggerBuilder.build();

            //3. 把上面配置好的任务和触发器注入到调度器中
            scheduler.scheduleJob(jobDetail, cronTrigger);
            
            //4. 启动调度器
            if(!scheduler.isShutdown()){
                scheduler.start();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 修改定时任务cron表达式
     * */
    public void modifrJobTime(String triggerName, String triggerGroupName,String cron){

        try {
            TriggerKey triggerKey=TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger= (CronTrigger) scheduler.getTrigger(triggerKey);
            if(trigger == null){
                return;
            }

            String oldTime=trigger.getCronExpression();
            if(!oldTime.equalsIgnoreCase(cron)){
                TriggerBuilder<Trigger> triggerBuilder=TriggerBuilder.newTrigger();
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();

                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                trigger= (CronTrigger) triggerBuilder.build();
                scheduler.rescheduleJob(triggerKey, trigger);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除定时任务
     * */
    public void removeJob(String jobName,String jobGroupName,String triggerName,
                          String triggerGroupName){
        try {
            TriggerKey triggerKey=TriggerKey.triggerKey(triggerName, triggerGroupName);
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动所有定时任务
     * */
    public void startJobs(){
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭所有定时任务
     * */
    public void shutDownJobs(){
        try {
            if(!scheduler.isShutdown()){
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

package com.xp;


import com.xp.job.MyJob;
import com.xp.job.MyJob01;
import com.xp.utils.QuartzManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App01
{
    public static void main( String[] args ) {
        //启动spring项目
        ApplicationContext context=
                new ClassPathXmlApplicationContext("applicationContext.xml");
        QuartzManager quartzManager= (QuartzManager) context.getBean("quartzManager");
        try {

            System.out.println("增加定时任务,每秒输出一次。。。。。。。。。。。。");
            quartzManager.addJob("firstJobDetail",
                                "jobGroupOne",
                                "cronTrigger",
                                "triggerGroupOne",
                                MyJob01.class,
                                "0/1 * * * * ?");

            Thread.sleep(5000);
            System.out.println("修改定时任务,每2秒输出一次。。。。。。。。。。。。");
            quartzManager.modifrJobTime("cronTrigger",
                                        "triggerGroupOne",
                                        "0/2 * * * * ?");


            Thread.sleep(10000);
            System.out.println("删除定时任务。。。。。。。。。。。。");
            quartzManager.removeJob("firstJobDetail", "jobGroupOne",
                    "firstJobDetail", "triggerGroupOne");
        }catch(Exception e) {
            e.printStackTrace();
        }



    }
}

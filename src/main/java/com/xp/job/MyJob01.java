package com.xp.job;

import com.xp.service.TestService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author xp
 * @CreateTime 2019/03/24  12:42
 * @Function ${VAR}
 */
public class MyJob01 implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        System.out.println("现在时间是  ============  "+sdf.format(date));
    }
}

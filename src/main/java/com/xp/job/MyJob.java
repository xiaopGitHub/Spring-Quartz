package com.xp.job;

import com.xp.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author xp
 * @CreateTime 2019/03/22  23:54
 * @Function 所需执行的任务
 */
public class MyJob {

    @Autowired
    private TestService testService;

    public void say(){
        testService.say();
    }
}

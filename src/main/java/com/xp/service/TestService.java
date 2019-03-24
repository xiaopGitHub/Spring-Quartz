package com.xp.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author xp
 * @CreateTime 2019/03/22  23:51
 * @Function ${VAR}
 */
@Service
public class TestService {
    public void say(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        System.out.println("现在时间是  ============  "+sdf.format(date));
    }
}

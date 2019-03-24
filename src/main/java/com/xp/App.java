package com.xp;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        //启动spring项目
        ApplicationContext context=
                new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}

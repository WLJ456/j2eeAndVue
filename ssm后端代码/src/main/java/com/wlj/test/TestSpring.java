package com.wlj.test;

import com.wlj.service.UserService;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {


    @Test
    public void run1() {
//加载配置文件
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //获取对象
        UserService user= (UserService) ac.getBean("UserService");
        //调用方法
        user.findAll();

    }
}

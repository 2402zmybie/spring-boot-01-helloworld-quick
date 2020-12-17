package com.hr.springboot;

import com.hr.springboot.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


@SpringBootTest
class SpringBoot01HelloworldQuickApplicationTests {

    @Autowired
    private Person person;

    @Autowired
    private ApplicationContext ioc;

    @Test
    void contextLoads() {
        boolean b = ioc.containsBean("helloService");
        System.out.println(b);
        System.out.println(person);
    }

}

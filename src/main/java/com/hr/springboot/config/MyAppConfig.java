package com.hr.springboot.config;

import com.hr.springboot.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration指明当前类是个配置类, 替代之前Spring的配置文件
 */
@Configuration
public class MyAppConfig {

    /**
     * @Bean 将方法的返回值添加到组件中,容器中这个组件默认的id就是方法名
     */
    @Bean("helloService")
    public HelloService helloService() {
        System.out.println("配置类@Bean给容器中添加组件了");
        return new HelloService();
    }
}

package com.hr.springboot.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ConfigurationProperties: 告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
 *  prefix = "person"配置文件中哪个下面的所有属性进行一一映射
 *
 * 只有这个组件是容器中的组件,才能容器提供的@ConfigurationProperties功能
 *
 * @PropertySource加载指定的配置文件
 */

//@PropertySource(value = {"classpath:person.properties"})
@Data
@Component
//ConfigurationProperties支持jsr303校验(加入@Validated注解)
@ConfigurationProperties(prefix = "person")
//@Validated
public class Person {

//    @Value("${person.last-name}")
    private String lastName;
//    @Value("#{11*2}")
    private Integer age;
//    @Value("true")
    private Boolean boss;
    private Date birth;
    @Email
    private String email;

    private Map<String,Object> maps;

    private List<Object> lists;

    private Dog dog;
}

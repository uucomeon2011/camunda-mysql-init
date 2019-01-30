package com.lcl.camunda.demo;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: liuchunli3
 * @Date: 2019/1/24 16:32
 * @Description:
 */
@SpringBootApplication
@EnableProcessApplication
public class CamundaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamundaApplication.class, args);
    }
}

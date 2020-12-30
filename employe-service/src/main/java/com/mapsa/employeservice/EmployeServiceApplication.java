package com.mapsa.employeservice;

import com.mapsa.employeservice.controller.MainController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackageClasses = MainController.class)
public class EmployeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeServiceApplication.class, args);
    }

}

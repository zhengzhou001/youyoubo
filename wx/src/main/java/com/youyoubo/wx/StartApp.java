package com.youyoubo.wx;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.base","com.youyoubo.wx"})
public class StartApp {
	public static void main(String[] args) {
		System.out.println(22);
		SpringApplication.run(StartApp.class, args);
	}
}

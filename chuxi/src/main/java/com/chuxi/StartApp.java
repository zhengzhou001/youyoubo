package com.chuxi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@ComponentScan(basePackages = {"com.base","com.chuxi"})
public class StartApp extends SpringBootServletInitializer implements WebApplicationInitializer{
	public static void main(String[] args) {
		SpringApplication.run(StartApp.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StartApp.class);
	} 
}

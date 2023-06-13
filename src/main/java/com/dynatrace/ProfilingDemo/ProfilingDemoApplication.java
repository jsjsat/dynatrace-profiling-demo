package com.dynatrace.ProfilingDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan 
public class ProfilingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfilingDemoApplication.class, args);
	}
}

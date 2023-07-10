package com.dynatrace.ProfilingDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
/**
 * A simple spring boot application with a servlet that showcases different
 * cpu & threadproblems which can easily be diagnosed with Dyntrace OneAgent
 */
public class ProfilingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfilingDemoApplication.class, args);
	}
}

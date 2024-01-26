package com.digital.rehab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class RehabWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(RehabWorldApplication.class, args);
	}

} 

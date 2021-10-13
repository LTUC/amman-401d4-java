package com.java.demos.spring;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	// allows you to autowire in the class in other parts of the application
//	@Bean
//	public BCrypt encoder() {
//		return new BCrypt();
//	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

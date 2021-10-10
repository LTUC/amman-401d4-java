package com.d4coders.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


// https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-using-springbootapplication-annotation.html
// please read the above link
// the @EnableAutoConfiguration @ComponentScan @Configuration can be used in place of the @SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan
//@Configuration
@SpringBootApplication // this is a convenience annotation which can be used in place the three above
public class HelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}
}

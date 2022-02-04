package com.example.jwt.token.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.example.jwt.token.demo.*" })
public class TokenDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenDemoApplication.class, args);
	}

}

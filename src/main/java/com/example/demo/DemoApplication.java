package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	// TODO: MySQL, WebSecurityConfig, TopController 作成
	// TODO: 環境に応じたDBを作成する必要がある
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	//TODO: 会員登録を作っていないから、ログイン失敗する。
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

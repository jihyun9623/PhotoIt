package com.ssafy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhotoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoServerApplication.class, args);
		System.out.println("Spring Start");
	}
}
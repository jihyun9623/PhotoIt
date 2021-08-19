package com.ssafy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PhotoServerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(PhotoServerApplication.class, args);
		System.out.println("Spring Start");
	}

}
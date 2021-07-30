package com.ssafy;

import com.ssafy.api.service.UserServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhotoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoServerApplication.class, args);
		System.out.println("Spring Start");

		UserServiceTest userServiceTest;
	}
}

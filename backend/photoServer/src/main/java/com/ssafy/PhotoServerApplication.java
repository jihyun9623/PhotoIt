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

//package com.ssafy;

//import com.ssafy.JwtInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.Arrays;


//
//@EnableJpaAuditing
//@EnableScheduling
//@SpringBootApplication
//public class PhotoServerApplication implements WebMvcConfigurer {
//
//	public static void main(String[] args) {
//		SpringApplication.run(PhotoServerApplication.class, args);
//	}
//
//	@Autowired
//	private JwtInterceptor jwtInterceptor;
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		System.out.println(">>> 인터셉터 등록");
////        registry.addInterceptor(jwtInterceptor).addPathPatterns("/user/") // 기본 적용 경로
////                .excludePathPatterns(Arrays.asList("/user/login"));// 적용 제외 경로
//	}
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/")
//				.allowedOrigins("")
//				.allowedMethods("")
//				.allowedHeaders("*")
//				.exposedHeaders("Authorization");
//	}
//}
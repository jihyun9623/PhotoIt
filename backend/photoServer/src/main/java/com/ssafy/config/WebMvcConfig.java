package com.ssafy.config;

import com.ssafy.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    public void addCorsMapping(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .allowCredentials(true);
    }

    @Autowired
    private JwtInterceptor jwtInterceptor;

//    public WebMvcConfig(JwtInterceptor jwtInterceptor){
//        this.jwtInterceptor=jwtInterceptor;
//    }

//    @Override
//        public void addInterceptors(InterceptorRegistry registry) {
//        System.out.println("안들어감~~");
//        registry.addInterceptor(jwtInterceptor)
//                .addPathPatterns("/mypage");
//    }
}
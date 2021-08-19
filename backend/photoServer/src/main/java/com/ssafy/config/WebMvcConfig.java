package com.ssafy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    //private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    @Autowired
    private JwtInterceptor jwtInterceptor;

    public WebMvcConfig(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry)    {
        List<String>exclude_list= Arrays.asList("/*","/user/**", "/search/**", "/studio/**", "/fav/**", "/chat/**");
        //logger.debug("addInterceptors");
          registry.addInterceptor(jwtInterceptor)
                  .excludePathPatterns(exclude_list)
                  .addPathPatterns("/profile")
                  .addPathPatterns("/user/signout")
                  .addPathPatterns("/mypage/**")
                  .addPathPatterns("/studioedit/**");
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .exposedHeaders("Authorization")
//                .allowCredentials(true);
//    }

}


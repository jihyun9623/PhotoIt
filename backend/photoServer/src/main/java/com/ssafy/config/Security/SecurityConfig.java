package com.ssafy.config.Security;

        import com.ssafy.common.util.JwtTokenUtil;
        import lombok.RequiredArgsConstructor;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
        import org.springframework.security.config.http.SessionCreationPolicy;
        import org.springframework.security.crypto.factory.PasswordEncoderFactories;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenUtil jwtTokenProvider;

    // 암호화에 필요한 PasswordEncoder 를 Bean 등록
    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // authenticationManager를 Bean 등록
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors().and()
                .csrf().disable();
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
                //.and()
        http    .httpBasic()
                .and()
                .authorizeRequests()
//                .antMatchers("/studioedot/**").hasRole("ROLE_PG")
//                .antMatchers("/mypage/**").hasRole("ROLE_USER")
//                .antMatchers("/mypage/**").hasRole("ROLE_PG")
//                .antMatchers("/fav/**").hasRole("ROLE_USER")
//                .antMatchers("/fav/**").hasRole("ROLE_PG")
                .antMatchers("/user/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
    }

}

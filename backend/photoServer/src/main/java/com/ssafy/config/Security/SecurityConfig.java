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
                .httpBasic().disable()
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
                .and()

                .authorizeRequests()    // 다음에 반환되는 객체로 호출되는 메소드들은 요청 보안 수준의 세부적인 설정.
 //               .antMatchers("/mypage/**").hasAnyRole()    //user든 pg든 뭐라도 있는 사람만 마이페이지 접속 가능
//                .antMatchers("/studioedit/**").hasRole("ROLE_PG")
//                .antMatchers("/location").authenticated()
//                .antMatchers("/user/signin").authenticated()
//                .antMatchers("/mypage_inter/**").authenticated()
//                .antMatchers("/studioedit/**").authenticated()
//                .antMatchers("/**").permitAll()
                .anyRequest().permitAll();
//                .and()
//                 .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
//                        UsernamePasswordAuthenticationFilter.class);
    }

}
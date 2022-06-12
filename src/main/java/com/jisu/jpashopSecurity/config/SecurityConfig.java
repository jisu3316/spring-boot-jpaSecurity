package com.jisu.jpashopSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() // /user/** 밑으로 들어오는 url는 인증이 필요하다.
//                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") //hasRole 권한이 있어야한다.
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()          //나머지 주소는 권한이 허용이된다.
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .usernameParameter("email")  //form에서 name값을 바꿔주려면 parameter 바꿔줘야함
                .loginProcessingUrl("/login")       //login 조수가 호출이되면 시큐리가 낚아채서 대신 로그인을 진행해줍니다.
                .permitAll()
//                .defaultSuccessUrl("/")
                .and()
                .logout().permitAll();
//                .and()
//                .oauth2Login()
//                .loginPage("/loginForm")  // 구글 로그인이 완료된 뒤의 후처리가 필요함. Tip.코드X, (엑세스토큰+ 사용자 프로필정보O)
//                .userInfoEndpoint()
//                .userService(principalOauth2UserService);
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().antMatchers("/css/**", "/js/**"));
    }
}

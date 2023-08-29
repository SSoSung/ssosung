package com.development.ssosung.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().disable() // CORS 통합을 비활성화
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 인증정보를 서버에 담아 두지 않음
                .and()
                .formLogin().disable() // 기본 spring security login form 안씀
                .httpBasic().disable() // 기본 인증 로그인을 이용하지 않음


                .authorizeRequests()
                .antMatchers("/api/**").permitAll();



        return http.build();
    }
}

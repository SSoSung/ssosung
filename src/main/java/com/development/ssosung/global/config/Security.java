package com.development.ssosung.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class Security {

    private final Cors cors;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http){
//        http
//                .httpBasic().disable()  // 비인증시 login form redirect X (rest api)
//                .csrf().disable()       // crsf 보안 X (rest api)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// jwt token으로 인증
//
//                .and()
//                .authorizeRequests()
//                .requestMatchers("/**").permitAll()
//                .anyRequest().authenticated();
//
//
//        return http.build();
//    }
}

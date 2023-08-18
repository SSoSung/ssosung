package com.development.ssosung.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class Cors {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 자격증명과 함께 요청을 할 수 있는지 여부. 해당 서버에서 Authorization로 사용자 인증도 서비스할 것이라면 true로 응답
        config.addAllowedOrigin("*"); // CORS 요청을 허용할 사이트 (e.g. https://wms.com)
        config.addAllowedHeader("*"); // 특정 헤더를 가진 경우에만 CORS 요청을 허용할 경우
        config.addAllowedMethod("*"); // CORS 요청을 허용할 Http Method들 (e.g. GET, PUT, POST)
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}

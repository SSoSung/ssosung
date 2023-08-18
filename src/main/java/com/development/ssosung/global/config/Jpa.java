package com.development.ssosung.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // BaseTimeEntity Auto 활성화
public class Jpa {
}

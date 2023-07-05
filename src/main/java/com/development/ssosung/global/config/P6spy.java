package com.development.ssosung.global.config;

import com.development.ssosung.global.common.P6spyPrettySqlFormatter;
import com.p6spy.engine.spy.P6SpyOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class P6spy {

    @PostConstruct
    public void setLogMessageFormat(){
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6spyPrettySqlFormatter.class.getName());
    }
}

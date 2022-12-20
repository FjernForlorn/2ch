package com.fjern.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.fjern.app.client", "com.fjern.test.common.client"})
@PropertySource("classpath:application.yml")
public class AppLiveTestConfig {
    AppLiveTestConfig(){super();}
}

package com.fjern.app.run.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.fjern.common", "com.fjern.app.common", "com.fjern.app.web"})
@PropertySource({"classpath:application.properties"})
public class ContextConfig {

    public ContextConfig(){super();}
}

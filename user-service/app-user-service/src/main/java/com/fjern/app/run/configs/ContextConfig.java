package com.fjern.app.run.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:application.yml"})
public class ContextConfig {

    public ContextConfig(){super();}
}

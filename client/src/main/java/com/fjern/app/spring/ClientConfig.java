package com.fjern.app.spring;

import com.fjern.app.web.spring.WebConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.fjern.common.client", "com.fjern.client"})
@Import({WebConfig.class})
public class ClientConfig {
    public ClientConfig(){super();}
}

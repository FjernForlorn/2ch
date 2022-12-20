package com.fjern.app.spring;

import com.fjern.app.web.spring.CommonWebConfig;
import com.fjern.common.client.spring.CommonClientConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.fjern.app.client.ops"})
@Import({CommonWebConfig.class, CommonClientConfig.class})
public class ClientConfig {
    public ClientConfig(){super();}
}

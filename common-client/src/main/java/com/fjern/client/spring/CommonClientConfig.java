package com.fjern.client.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.fjern.client", "com.fjern.common.web.client"})
public class CommonClientConfig {
}

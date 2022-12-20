package com.fjern.common.client.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.fjern.common.client", "com.fjern.common.web.client"})
public class CommonClientConfig {
}

package com.fjern.app.run.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.fjern.common.web", "com.fjern.app.web"})
public class WebConfig {
}

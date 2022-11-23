package com.fjern.app.web.spring;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({"com.fjern.common.web.util", "com.fjern.app.web.util"})

public class CommonWebConfig extends WebMvcConfigurerAdapter {

    public CommonWebConfig() {
        super();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

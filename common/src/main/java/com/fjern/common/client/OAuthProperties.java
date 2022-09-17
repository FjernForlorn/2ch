package com.fjern.common.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OAuthProperties {

    @Value("${http.oauthPath}")
    private String oauthPath;

    @Value("${http.sec.path}")
    private String secPath;



    public OAuthProperties() {
        super();
    }

    public final String getSecPath() {return secPath;}

    public final String getOauthPath() {return oauthPath;}

}

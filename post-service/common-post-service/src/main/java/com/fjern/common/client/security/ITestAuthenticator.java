package com.fjern.common.client.security;


import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

@Component
public interface ITestAuthenticator {

    RequestSpecification givenAuthenticated(String username, String password);
}

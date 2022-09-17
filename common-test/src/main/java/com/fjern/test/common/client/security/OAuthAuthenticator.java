package com.fjern.test.common.client.security;

import com.fjern.common.client.CommonPaths;
import com.fjern.common.client.OAuthProperties;
import io.restassured.RestAssured;
import io.restassured.authentication.OAuthSignature;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class OAuthAuthenticator implements ITestAuthenticator{

    private static final String CLIENT_ID = "live-test";

    private static final String CLIENT_SECRET = "derderder";

    private Logger log = LoggerFactory.getLogger(OAuthAuthenticator.class);

    @Autowired
    private OAuthProperties oAuthProperties;

    @Autowired
    private CommonPaths paths;

    public OAuthAuthenticator() {super();}
    @Override
    public RequestSpecification givenAuthenticated(String username, String password) {
        String accessToken = getAccessToken(username, password);
        return RestAssured.given().auth().oauth2(accessToken, OAuthSignature.HEADER);
    }

    final String getAccessToken(String username, String password) {
        try {
            URI uri = new URI(paths.getProtocol(), null, paths.getHost(), paths.getPort(), oAuthProperties.getSecPath() + oAuthProperties.getOauthPath(), null, null);
            String url = uri.toString();
            log.info(url);
            String encodedCredentials = new String(Base64.encodeBase64((CLIENT_ID+":"+CLIENT_SECRET).getBytes()));

            final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "password");
            params.add("client_id", CLIENT_ID);
            params.add("username", username);
            params.add("password", password);

            final HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + encodedCredentials);

            final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

            final RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            final TokenResponse tokenResponse = restTemplate.postForObject(url, request, TokenResponse.class);
            final String accessToken = tokenResponse.getAccessToken();
            return accessToken;
            } catch (HttpClientErrorException e) {
                log.warn("", e);

                log.info("Full Body = {}", e.getResponseBodyAsString());
            } catch (URISyntaxException e) {
                log.warn("", e);
            }
        return null;
    }


}

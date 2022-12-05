package com.fjern.app.run.configs;

import com.fjern.common.web.client.CommonPaths;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
public class OpenApiConfig {
    @Autowired
    private CommonPaths commonPaths;

    @Value(value = "${http.oauthPath}")
    private String tokenPath;

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().components(new Components()

                        .addSecuritySchemes("spring_oauth"
                                , new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .description("oauth2_flow")
                                        .flows(new OAuthFlows().clientCredentials(
                                                new OAuthFlow().tokenUrl(commonPaths.getServerRoot() + tokenPath)
                                                        .scopes(new Scopes().addString("2ch-web-app", "2ch-web-app")
                                                        ))))
        ).security(Arrays.asList(
                new SecurityRequirement().addList("spring_oauth")))

                .info(new Info()
                        .title("2ch")
                        .description("2ch")
                        .termsOfService("terms")
                        .contact(new Contact().email("yasenstrip@gmail.com").name("Developer: Paul Strongman"))
                        .license(new License().name("MIT"))
                        .version("0.0.1")
                );
    }
}

package com.fjern.app.run;

import com.fjern.app.run.configs.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;


@SpringBootApplication(exclude = {
		ErrorMvcAutoConfiguration.class
})

public class UserServiceApp {
	private final static Class[] CONFIGS = { // @formatter:off
			AppConfig.class,
			ContextConfig.class,
			PersistenceConfig.class,
			ServiceConfig.class,
			WebConfig.class,
			ResourceServerConfiguration.class,
			AuthorizationServerConfiguration.class,
			SecurityConfig.class,
			OpenApiConfig.class,

			UserServiceApp.class
	};

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(CONFIGS);
		springApplication.run(args);

	}
//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//
//		};
//	}
}

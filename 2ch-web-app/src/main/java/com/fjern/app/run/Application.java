package com.fjern.app.run;

import com.fjern.app.run.configs.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;


@SpringBootApplication(exclude = { // @formatter:off
		ErrorMvcAutoConfiguration.class
})// @formatter:on

public class Application {
	private final static Class[] CONFIGS = { // @formatter:off
			AppConfig.class,
			ContextConfig.class,
			PersistenceConfig.class,
			ServiceConfig.class,
			WebConfig.class,
			ResourceServerConfiguration.class,
			AuthorizationServerConfiguration.class,
			SecurityConfig.class,

			Application.class
	}; // @formatter:on

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

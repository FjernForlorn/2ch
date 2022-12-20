package com.fjern.app.run;

import com.fjern.app.run.configs.ContextConfig;
import com.fjern.app.run.configs.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationApp {
    private final static Class[] CONFIGS = {
            WebConfig.class,
            ContextConfig.class,
            NotificationApp.class
    };

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(CONFIGS);
        springApplication.run(args);


    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            RabbitMqMessageProducer producer,
//            NotificationConfig config
//            ) {
//        return args -> {
//            producer.publish(
//                    "foo",
//                    config.getInternalExchange(),
//                    config.getInternalNotificationRoutingKey());
//        };
//    }
}

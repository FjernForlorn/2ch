package com.fjern.app.run.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.fjern.app.persistence.*"})
@EntityScan({"com.fjern.app.persistence.*"})
@EnableJpaRepositories(basePackages = {"com.fjern.app.persistence.repositories"})
//@PropertySource({"classpath:persistence-postgresql.yaml"})
public class PersistenceConfig {

    public PersistenceConfig() {super();}

}

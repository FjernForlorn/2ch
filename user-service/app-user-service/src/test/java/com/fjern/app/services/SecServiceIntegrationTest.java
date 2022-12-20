package com.fjern.app.services;

import com.fjern.app.run.configs.ContextConfig;
import com.fjern.app.run.configs.PersistenceConfig;
import com.fjern.app.run.configs.ServiceConfig;
import com.fjern.app.spring.ClientConfig;
import com.fjern.common.interfaces.NameableEntity;
import com.fjern.test.common.service.AbstractServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextConfig.class, PersistenceConfig.class, ServiceConfig.class, ClientConfig.class}, loader = AnnotationConfigContextLoader.class)
public abstract class SecServiceIntegrationTest<T extends NameableEntity> extends AbstractServiceIntegrationTest<T> {
}

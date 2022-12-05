package com.fjern.test.common.client.template;

import com.fjern.common.interfaces.IEntity;
import com.fjern.common.services.Operations;
import io.restassured.specification.RequestSpecification;

public interface IRestClient<T extends IEntity> extends Operations<T>, RestClientAsResponse<T>, RestClientWithUri<T> {

    RequestSpecification givenAuthenticated();

    String getUri();

}

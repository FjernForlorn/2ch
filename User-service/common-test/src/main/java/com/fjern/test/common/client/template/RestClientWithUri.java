package com.fjern.test.common.client.template;

import com.fjern.common.interfaces.IEntity;

public interface RestClientWithUri<T extends IEntity> extends ReadOnlyRestClientWithUri<T>{

    String createAsUri(final T resource);

}

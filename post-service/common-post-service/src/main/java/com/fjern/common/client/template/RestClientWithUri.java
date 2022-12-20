package com.fjern.common.client.template;

import com.fjern.common.interfaces.IEntity;
import com.fjern.test.common.client.template.ReadOnlyRestClientWithUri;

public interface RestClientWithUri<T extends IEntity> extends ReadOnlyRestClientWithUri<T> {

    String createAsUri(final T resource);

}

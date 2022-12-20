package com.fjern.common.client.template;

import com.fjern.common.interfaces.IEntity;

import java.util.List;

public interface ReadOnlyRestClientWithUri<T extends IEntity> {

    T findOneByUri(String uri);

    List<T> findAllByUri(String uri);

}

package com.fjern.client;

import com.fjern.common.interfaces.NameableEntity;

public interface IDtoOps<T extends NameableEntity> {

    T createNewResource();

    T change(T resource);
}

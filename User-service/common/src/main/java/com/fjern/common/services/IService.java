package com.fjern.common.services;

import com.fjern.common.interfaces.NameableEntity;
import com.fjern.common.interfaces.ByNameApi;

public interface IService<T extends NameableEntity> extends RawService<T>, ByNameApi<T> {
}

package com.fjern.common.services;

import com.fjern.common.interfaces.ByNameApi;
import com.fjern.common.interfaces.NameableEntity;

public interface IService<T extends NameableEntity> extends RawService<T>, ByNameApi<T> {
}

package com.fjern.common.services;

import com.fjern.common.interfaces.NameableEntity;

public abstract class AbstractService<T extends NameableEntity>extends AbstractRawService<T> implements IService<T> {

    public AbstractService(){super();}

}

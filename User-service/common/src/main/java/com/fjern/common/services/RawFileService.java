package com.fjern.common.services;

import com.fjern.common.interfaces.NameableEntity;

import java.util.List;

public abstract class RawFileService<T extends NameableEntity> implements RawService<T> {


    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public T findById(Long id) {
        return null;
    }

    @Override
    public T create(T object) {
        return null;
    }

    @Override
    public T update(T object) {
        return null;
    }

    @Override
    public void delete(Long id){
    }

    protected abstract String getDirectory();
}

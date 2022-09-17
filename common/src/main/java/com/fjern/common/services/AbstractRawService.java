package com.fjern.common.services;

import com.fjern.common.interfaces.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractRawService<T extends IEntity> implements RawService<T>{

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(Long id) {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    @Transactional
    public T create(T object) {
       return getRepo().save(object);
    }

    @Override
    @Transactional
    public T update(T object) {
        return getRepo().save(object);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getRepo().deleteById(id);
    }

    protected abstract JpaRepository<T, Long> getRepo();
}

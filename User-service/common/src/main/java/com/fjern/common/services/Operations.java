package com.fjern.common.services;

import java.io.Serializable;
import java.util.List;

public interface Operations<T extends Serializable> {
    List<T> findAll();

    T findById(Long id);

    T create(T object);

    T update(T object);

    void delete(Long id);
}

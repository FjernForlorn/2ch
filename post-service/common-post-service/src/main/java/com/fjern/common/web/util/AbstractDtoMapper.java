package com.fjern.common.web.util;

import com.fjern.common.interfaces.NameableEntity;
import com.fjern.common.interfaces.Request;
import com.fjern.common.interfaces.Response;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDtoMapper<T extends NameableEntity, D extends Response
        , E extends Request> implements DtoMapper<T, D, E> {

    @Override
    public final List<D> convertListToResponse(List<T> dList) {
        return dList.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Override
    public final List<T> convertListToEntity(List<E> dList) {
        return dList.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    public T convertToEntity(final T entity) {
        return entity;
    };

}

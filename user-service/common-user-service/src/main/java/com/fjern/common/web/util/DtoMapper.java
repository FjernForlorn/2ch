package com.fjern.common.web.util;

import com.fjern.common.interfaces.Request;
import com.fjern.common.interfaces.Response;
import com.fjern.common.interfaces.NameableEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DtoMapper<T extends NameableEntity, D extends Response, E extends Request> {

    List<D> convertListToResponse(List<T> dList);

    D convertToResponse(final T entity);

    List<T> convertListToEntity(List<E> dList);

    T convertToEntity(final E request);

    T convertToEntity(final T entity);


}

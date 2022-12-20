package com.fjern.common.web.util;

import com.fjern.common.interfaces.NameableEntity;
import com.fjern.common.interfaces.Request;
import com.fjern.common.interfaces.Response;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public interface DtoHateoasMapper<T extends NameableEntity
        , D extends Response
        , E extends Request
        , R extends RepresentationModel<R>
        >
        extends DtoMapper<T, D, E>{

    public List<R> convertListToRepresentation(List<T> dList);

    public R convertToRepresentation(final T entity);
}

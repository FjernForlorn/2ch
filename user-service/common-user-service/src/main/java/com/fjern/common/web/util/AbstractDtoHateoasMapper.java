package com.fjern.common.web.util;

import com.fjern.common.interfaces.NameableEntity;
import com.fjern.common.interfaces.Request;
import com.fjern.common.interfaces.Response;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDtoHateoasMapper<T extends NameableEntity, D extends Response, E extends Request
        , R extends RepresentationModel<R>>
        extends AbstractDtoMapper<T, D, E>
        implements DtoHateoasMapper<T, D, E, R>{


    public List<R> convertListToRepresentation(List<T> dList) {
        return dList.stream().map(this::convertToRepresentation).collect(Collectors.toList());
    }
}

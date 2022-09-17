package com.fjern.common.web.util;

import com.fjern.common.interfaces.NameableDto;
import com.fjern.common.interfaces.NameableEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DtoMapper<T extends NameableEntity, D extends NameableDto> {

    List<D> convertListToDto(List<T> dList);

    abstract D convertToDto(final T entity);

    List<T> convertListToEntity(List<D> dList);

    abstract T convertToEntity(final D entity);

}

package com.fjern.common.web.util;

import com.fjern.common.interfaces.NameableDto;
import com.fjern.common.interfaces.NameableEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDtoMapper<T extends NameableEntity, D extends NameableDto> implements DtoMapper<T, D> {

    @Override
    public final List<D> convertListToDto(List<T> dList) {
        return dList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public final List<T> convertListToEntity(List<D> dList) {
        return dList.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

}

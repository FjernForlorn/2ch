package com.fjern.app.web.util.mappers;

import com.fjern.app.persistence.entities.DTOs.UserRequest;
import com.fjern.app.persistence.entities.DTOs.UserResponse;
import com.fjern.app.persistence.entities.User;
import com.fjern.common.web.util.AbstractDtoMapper;
import com.fjern.common.web.util.DtoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper extends AbstractDtoMapper<User, UserResponse, UserRequest>
        implements DtoMapper<User, UserResponse, UserRequest> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserResponse convertToResponse(User entity) {
        return mapper.map(entity, UserResponse.class);
    }

    @Override
    public User convertToEntity(UserRequest request) {
        return mapper.map(request, User.class);
    }
}

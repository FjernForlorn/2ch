package com.fjern.app.web.Mappers;

import com.fjern.app.persistence.entities.User;
import com.fjern.app.persistence.entities.DTOs.UserDto;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {
    public User newAppUserDtoToAppUser (UserDto userDto) {
       User user = new User();
       user.setEmail(userDto.getEmail());
       user.setFullName(userDto.getName());
       user.setPassword(userDto.getPassword());
       return user;
    }
}

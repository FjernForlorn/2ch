package com.fjern.app.client.feign;

import com.fjern.app.persistence.entities.DTOs.UserResponse;
import com.fjern.app.web.util.UtilMappings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(UtilMappings.Services.USER_SERVICE)
public interface UserClient {

    @RequestMapping(UtilMappings.Plural.USERS + "/{id}")
    UserResponse getUser(@PathVariable Long id);
}

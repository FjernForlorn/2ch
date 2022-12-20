package com.fjern.app.web.controllers;

import com.fjern.app.persistence.entities.DTOs.UserRequest;
import com.fjern.app.persistence.entities.DTOs.UserResponse;
import com.fjern.app.persistence.entities.User;
import com.fjern.app.persistence.entities.VerificationToken;
import com.fjern.app.persistence.repositories.VerificationTokenRepository;
import com.fjern.app.services.UserService;
import com.fjern.app.web.util.UtilMappings;
import com.fjern.app.web.util.mappers.UserDtoMapper;
import com.fjern.common.services.RawService;
import com.fjern.common.web.controllers.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(UtilMappings.Plural.USERS)
@RestController
public class UserController extends AbstractController<User, UserResponse, UserRequest> {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDtoMapper mapper;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public UserController() {
        super(User.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAll (HttpServletRequest request){
        return findAllInternal(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser (@RequestBody UserRequest userDto
            , final UriComponentsBuilder uriBuilder, HttpServletResponse response){
        createInternal(userDto, uriBuilder, response);
    }

    @GetMapping("/{id}")
    public UserResponse getById (@PathVariable Long id, HttpServletResponse response
            , UriComponentsBuilder uriBuilder) {
       return findOneInternal(id, response, uriBuilder);
    }

    @PutMapping("/{id}")
    public UserResponse update (@RequestBody User user, @PathVariable Long id) {
        return updateInternal(id, user);
    }

    @GetMapping(UtilMappings.ACTIVATE_USER + "/{token}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void activateUser(@PathVariable String token) {

        VerificationToken verificationToken = verificationTokenRepository.findByName(token);
        User user = verificationToken.getUser();
        user.setIsEnabled(true);
        updateInternal(user.getId(), user);
    }

    @Override
    protected RawService<User> getService() {
        return this.userService;
    }

    @Override
    protected UserDtoMapper getMapper() {
        return mapper;
    }
}

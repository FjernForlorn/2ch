package com.fjern.app.web.controllers;

import com.fjern.app.web.Mappers.AppUserMapper;
import com.fjern.app.persistence.entities.User;
import com.fjern.app.persistence.entities.DTOs.UserDto;
import com.fjern.app.persistence.entities.VerificationToken;
import com.fjern.app.persistence.repositories.VerificationTokenRepository;
import com.fjern.app.services.UserService;
import com.fjern.common.web.controllers.AbstractController;
import com.fjern.common.services.RawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "${api.users.address}")
@RestController
public class UserController extends AbstractController<User> {

    @Autowired
    private UserService userService;

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public UserController() {
        super(User.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll (HttpServletRequest request){
        return findAllInternal(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser (@RequestBody UserDto userDto, final UriComponentsBuilder uriBuilder, HttpServletResponse response){
        createInternal(appUserMapper.newAppUserDtoToAppUser(userDto), uriBuilder, response);
    }

    @GetMapping("/{id}")
    public User getById (@PathVariable Long id, HttpServletResponse response, UriComponentsBuilder uriBuilder) {
       return findOneInternal(id, response, uriBuilder);
    }

    @PutMapping("/{id}")
    public User update (@RequestBody User user, @PathVariable Long id) {
        return updateInternal(id, user);
    }

    @GetMapping("/activateUser/{token}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void activateUser(@PathVariable String token) {
        System.out.println("waaaoww");
        VerificationToken verificationToken = verificationTokenRepository.findByName(token);
        User user = verificationToken.getUser();
        user.setIsEnabled(true);
        updateInternal(user.getId(), user);
    }

    @Override
    protected RawService<User> getService() {
        return this.userService;
    }
}

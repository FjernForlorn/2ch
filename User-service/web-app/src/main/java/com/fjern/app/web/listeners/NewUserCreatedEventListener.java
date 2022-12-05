package com.fjern.app.web.listeners;

import com.fjern.app.persistence.entities.User;
import com.fjern.app.persistence.repositories.UserRepository;
import com.fjern.app.security.MyPasswordEncoder;
import com.fjern.app.services.RoleService;
import com.fjern.app.web.events.NewUserCreatedEvent;
import com.fjern.app.web.events.VerifyNewUserByEmailEvent;
import com.fjern.app.web.util.UtilNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NewUserCreatedEventListener implements ApplicationListener<NewUserCreatedEvent> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public void onApplicationEvent(NewUserCreatedEvent event) {
       User newUser = event.getUser();
       generateUserFields(newUser);
    }

    private void generateUserFields(User newUser) {
        if (newUser.getRoles().isEmpty()) {
            newUser.getRoles().add(roleService.findByName(UtilNames.Roles.ROLE_USER));
        }

        generateUserName(newUser);
        newUser.setPassword(myPasswordEncoder.getPasswordEncoder().encode(newUser.getPassword()));
        User savedUser = userRepository.save(newUser);

        if (!newUser.getIsEnabled()) {
            publisher.publishEvent(new VerifyNewUserByEmailEvent(savedUser));
        }

    }

    private void generateUserName(User newUser) {
        StringBuilder builder = new StringBuilder(newUser.getName());
        String userName = "";
        Long number = 1L;
        while (userName.length() == 0) {
            if (userRepository.findByName(String.valueOf(builder)) == null) {
                userName = builder.toString();
            } else {
                builder.replace(0, builder.length(), newUser.getName()).append(number);
                number += 1L;
            }
        }

        newUser.setName(userName);
    }

}

package com.fjern.app.web.listeners;

import com.fjern.app.persistence.entities.User;
import com.fjern.app.persistence.entities.VerificationToken;
import com.fjern.app.persistence.repositories.UserRepository;
import com.fjern.app.persistence.repositories.VerificationTokenRepository;
import com.fjern.app.services.EmailSender;
import com.fjern.app.web.events.VerifyNewUserByEmailEvent;
import com.fjern.app.web.util.UtilPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VerifyNewUserByEmailEventListener implements ApplicationListener<VerifyNewUserByEmailEvent> {

    @Autowired
    private UtilPaths utilPaths;

    @Value(value = "${api.users.mappings.activateUser}")
    private String activateUserMapping;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailSender emailSender;

    @Override
    public void onApplicationEvent(VerifyNewUserByEmailEvent event) {
        User savedUser = event.getUser();
        verifyUserByEmail(savedUser);
    }

    private void verifyUserByEmail(User savedUser) {
        String token = String.valueOf(UUID.randomUUID());
        String verificationUrl = utilPaths.getUserUri() + activateUserMapping + "/" + token;
        emailSender.sendVerificationUrl(verificationUrl, savedUser.getEmail());
        VerificationToken verificationToken = new VerificationToken(token, savedUser);
        verificationTokenRepository.save(verificationToken);
    }
}

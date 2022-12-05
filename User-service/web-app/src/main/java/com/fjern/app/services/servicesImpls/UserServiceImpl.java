package com.fjern.app.services.servicesImpls;


import com.fjern.app.persistence.entities.User;
import com.fjern.app.persistence.repositories.UserRepository;
import com.fjern.app.persistence.repositories.VerificationTokenRepository;
import com.fjern.app.services.EmailSender;
import com.fjern.app.services.UserService;
import com.fjern.app.web.events.NewUserCreatedEvent;
import com.fjern.common.services.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    protected UserRepository getRepo() {
        return this.userRepository;
    }

    @Override
    public User create(User user) {

        publisher.publishEvent(new NewUserCreatedEvent(user));

        return findByName(user.getName());
    }

    @Override
    public User findByName(final String name) {
        return getRepo().findByName(name);
    }
}

package com.fjern.app.web.events;

import com.fjern.app.persistence.entities.User;
import org.springframework.context.ApplicationEvent;

public class VerifyNewUserByEmailEvent extends ApplicationEvent {

    public VerifyNewUserByEmailEvent(User user) {
        super(user);
    }

    public User getUser() {
        return (User) getSource();
    }
}

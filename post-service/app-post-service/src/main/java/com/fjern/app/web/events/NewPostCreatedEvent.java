package com.fjern.app.web.events;

import com.fjern.app.persistence.entities.Post;
import org.springframework.context.ApplicationEvent;

public class NewPostCreatedEvent extends ApplicationEvent {

    public NewPostCreatedEvent(Post post) {
        super(post);
    }

    public Post getPost() {return (Post) getSource();}
}

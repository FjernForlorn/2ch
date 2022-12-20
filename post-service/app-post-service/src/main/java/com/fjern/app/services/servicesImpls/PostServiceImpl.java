package com.fjern.app.services.servicesImpls;

import com.fjern.app.persistence.entities.Post;
import com.fjern.app.persistence.repositories.PostRepository;
import com.fjern.app.services.PostService;
import com.fjern.app.web.events.NewPostCreatedEvent;
import com.fjern.common.services.AbstractRawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service

public class PostServiceImpl extends AbstractRawService<Post> implements PostService {

    public PostServiceImpl(){
        super();
    }

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    protected PostRepository getRepo() {
        return postRepository;
    }

    @Override
    public Post findByName(String name) {
       return getRepo().findByName(name);
    }

    @Override
    public Post create(Post object) {
        Post createdPost = super.create(object);
        publisher.publishEvent(new NewPostCreatedEvent(createdPost));
        return createdPost;
    }
}

package com.fjern.app.services.servicesImpls;

import com.fjern.app.persistence.entities.User;
import com.fjern.app.persistence.entities.Post;
import com.fjern.app.persistence.repositories.PostRepository;
import com.fjern.common.services.AbstractRawService;
import com.fjern.app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PostServiceImpl extends AbstractRawService<Post> implements PostService {

    public PostServiceImpl(){
        super();
    }

    @Autowired
    private PostRepository postRepository;

    @Value("${page.size}")
    private int pageSize;

    @Override
    public List<Post> findPage(int page) {
        return  postRepository.findAll(PageRequest.of(page,pageSize)).getContent();
    }

    @Override
    public List<Post> findAllByUser(User user) {
        return postRepository.findAllByUser(user);
    }

    @Override
    protected PostRepository getRepo() {
        return postRepository;
    }


    @Override
    public Post findByName(String name) {
       return getRepo().findByName(name);
    }
}

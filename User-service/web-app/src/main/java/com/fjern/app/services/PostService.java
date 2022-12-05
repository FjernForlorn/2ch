package com.fjern.app.services;

import com.fjern.app.persistence.entities.Post;
import com.fjern.app.persistence.entities.User;
import com.fjern.common.services.IService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PostService extends IService<Post> {

    List<Post> findAllByUser(User user);

    List<Post> findPage(int page);

}

package com.fjern.services;

import com.fjern.entities.Post;
import com.fjern.entities.User;

import java.util.List;

public interface PostService extends CrudService<Post, Long>{

    List<Post> findAllByUser(User user);

    List<Post> findPage(int page);

    Post addPost(Post post, Long parentId, User user);

    Post getPost(Long id);
}

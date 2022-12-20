package com.fjern.app.web.util.mappers;

import com.fjern.app.client.feign.UserClient;
import com.fjern.app.persistence.entities.DTOs.PostRequest;
import com.fjern.app.persistence.entities.DTOs.PostResponse;
import com.fjern.app.persistence.entities.DTOs.UserResponse;
import com.fjern.app.persistence.entities.Post;
import com.fjern.common.web.util.AbstractDtoMapper;
import com.fjern.common.web.util.DtoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostDtoMapper extends AbstractDtoMapper<Post, PostResponse, PostRequest>
        implements DtoMapper<Post, PostResponse, PostRequest> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserClient userClient;

    @Override
    public PostResponse convertToResponse(Post entity) {

        PostResponse post = mapper.map(entity, PostResponse.class);
        UserResponse user = userClient.getUser(entity.getUserId());
        post.setUser(user);
        return post;
    }

    @Override
    public Post convertToEntity(PostRequest request) {
        return mapper.map(request, Post.class);
    }
}

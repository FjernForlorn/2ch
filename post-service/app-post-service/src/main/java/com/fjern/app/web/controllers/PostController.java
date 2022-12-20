package com.fjern.app.web.controllers;

import com.fjern.app.persistence.entities.DTOs.PostRequest;
import com.fjern.app.persistence.entities.DTOs.PostResponse;
import com.fjern.app.persistence.entities.Post;
import com.fjern.app.services.PostService;
import com.fjern.app.web.util.UtilMappings;
import com.fjern.app.web.util.mappers.PostDtoMapper;
import com.fjern.common.services.RawService;
import com.fjern.common.web.controllers.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@Controller

@Slf4j
@RequestMapping(UtilMappings.Plural.POSTS)
public class PostController extends AbstractController<Post, PostResponse, PostRequest> {

    @Autowired
    private PostService postService;

    @Autowired
    private PostDtoMapper mapper;

    public PostController() {
        super(Post.class);
    }


    @PostMapping("/add")
    @ResponseBody
    public PostResponse addPost(@Valid @RequestBody PostRequest post, final UriComponentsBuilder uriBuilder, HttpServletResponse response){
        return createInternal(post, uriBuilder, response);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PostResponse findOne(@PathVariable Long id, HttpServletResponse response, UriComponentsBuilder uriBuilder){

        return findOneInternal(id, response, uriBuilder);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@PathVariable Long id, @RequestBody @Valid Post post){
        updateInternal(id, post);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<PostResponse> findAll(HttpServletRequest request){
        return findAllInternal(request);
    }

    @Override
    protected RawService<Post> getService() {
        return postService;
    }

    @Override
    protected PostDtoMapper getMapper() {
        return mapper;
    }
}

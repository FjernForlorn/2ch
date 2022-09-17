package com.fjern.app.web.controllers;

import com.fjern.app.persistence.entities.Post;
import com.fjern.app.services.PostService;
import com.fjern.common.web.controllers.AbstractController;
import com.fjern.common.services.RawService;
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
@RequestMapping("/post")
public class PostController extends AbstractController<Post> {

    @Autowired
    private PostService postService;

    public PostController() {
        super(Post.class);
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost(@Valid @RequestBody Post post, final UriComponentsBuilder uriBuilder, HttpServletResponse response){
        createInternal(post, uriBuilder, response);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Post findOne(@PathVariable Long id, HttpServletResponse response, UriComponentsBuilder uriBuilder){
        return findOneInternal(id, response, uriBuilder);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@PathVariable Long id, @RequestBody @Valid Post post){
        updateInternal(id, post);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Post> findAll(HttpServletRequest request){
        return findAllInternal(request);
    }

    @Override
    protected RawService<Post> getService() {
        return postService;
    }
}

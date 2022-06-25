package com.fjern.controllers;

import com.fjern.entities.Post;
import com.fjern.entities.User;
import com.fjern.services.PostService;
import com.fjern.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@Slf4j
@RequestMapping("/")
public class HomeController {

    private PostService postService;

    private UserService userService;

    public HomeController(PostService postService,UserService userService) {
        this.postService = postService;
        this.userService=userService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView mav= new ModelAndView("index");
        mav.addObject("posts", postService.findAll());

        return mav;
    }

    @PostMapping("/add/post")
    @ResponseBody
    public Post addPost(
            @RequestBody Post post
    ){

        User user=userService.findById(1L);
        post.setUser(user);
       return postService.addPost(post, 1L, user);

    }



    @GetMapping("/{id}")
    @ResponseBody
    public Post getPost(@PathVariable Long id){
        Post post = postService.findById(id);
        return post;
    }
}

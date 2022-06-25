package com.fjern.controllers;

import com.fjern.entities.Post;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    private RestTemplate restTemplate = new RestTemplate();
    final private String url="http://localhost:8080/";


    @Test
    public void givenPostExists_whenGet_thenSuccess(){
        ResponseEntity<Post> response = restTemplate.getForEntity(url+"/1", Post.class);
        assertNotNull(response);
    }

}
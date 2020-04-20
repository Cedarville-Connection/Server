package com.cedarvilleconnection.CedarvilleConnection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cedarvilleconnection.CedarvilleConnection.model.Post;
import com.cedarvilleconnection.CedarvilleConnection.repository.PostRepository;

@RestController
@RequestMapping("/")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView index() {
    	ModelAndView mav = new ModelAndView("index");
    	mav.addObject("posts", getAllPosts());
        return new ModelAndView("index");
    }

    @GetMapping("/post")
    public List<Post> getAllPosts() {
            return postRepository.findAll();
    }
    
//    @GetMapping("/posts")
//    public List<Post> getAllPosts() {
//    	postRepository.findAll();
//    }


}

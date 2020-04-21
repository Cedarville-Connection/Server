package com.cedarvilleconnection.CedarvilleConnection.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cedarvilleconnection.CedarvilleConnection.model.Comment;
import com.cedarvilleconnection.CedarvilleConnection.model.People;
import com.cedarvilleconnection.CedarvilleConnection.model.Post;
import com.cedarvilleconnection.CedarvilleConnection.repository.CommentRepository;
import com.cedarvilleconnection.CedarvilleConnection.repository.PeopleRepository;
import com.cedarvilleconnection.CedarvilleConnection.repository.PostRepository;

@RestController
@RequestMapping("/")
public class PostController {

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private PeopleRepository peopleRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView mav = new ModelAndView("index");
        mav.addObject("posts", getAllPosts());
        return mav;
    }

    @GetMapping("/post")
    public List<Post> getAllPosts() {
            return postRepository.findAll();
    }
    
    @PostMapping("/comment")
    public ModelAndView comment(@RequestParam("comment") String contents, @RequestParam("postId") long postId) {
		Comment comment = new Comment();
		comment.setPost(postRepository.findById(postId).get());
		comment.setUser(peopleRepository.findById((long) 15).get());
		comment.setContents(contents);
		commentRepository.save(comment);
		return index();
    }


}

package com.cedarvilleconnection.CedarvilleConnection.Post;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cedarvilleconnection.CedarvilleConnection.Comment.Comment;
import com.cedarvilleconnection.CedarvilleConnection.Comment.CommentRepository;
import com.cedarvilleconnection.CedarvilleConnection.People.PeopleRepository;

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
    	ModelAndView mav = new ModelAndView("home");
        mav.addObject("posts", getAllPosts());
        return mav;
    }

    @GetMapping("/post")
    public List<Post> getAllPosts() {
            return postRepository.findAll();
    }
    
    @PostMapping("/comment")
    public ModelAndView comment(@RequestParam("comment") String contents, @RequestParam("postId") long postId) {
    	
    	if(contents != null && !contents.trim().isEmpty()) {
			Comment comment = new Comment();
			comment.setPost(postRepository.findById(postId).get());
			comment.setUser(peopleRepository.findById((long) 1).get()); // FIXME: change to current user
			comment.setTimestamp(new Timestamp(new Date().getTime()));
			comment.setContents(contents);
			commentRepository.save(comment);
    	}
		return index();
    }
    
    @PostMapping("/post")
    public ModelAndView comment(@RequestParam("postContents") String content) {
    	if(content != null && !content.trim().isEmpty()) {
	    	Post post = new Post();
	    	post.setComments(new ArrayList<Comment>());
	    	post.setContent(content);
	    	post.setTimestamp(new Timestamp(new Date().getTime()));
	    	post.setUser(peopleRepository.findById((long) 1).get()); // FIXME: change to current user
	    	postRepository.save(post);
    	}
		return index();
    }


}

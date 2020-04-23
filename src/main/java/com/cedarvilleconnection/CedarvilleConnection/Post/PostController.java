package com.cedarvilleconnection.CedarvilleConnection.Post;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.cedarvilleconnection.CedarvilleConnection.People.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cedarvilleconnection.CedarvilleConnection.Comment.Comment;
import com.cedarvilleconnection.CedarvilleConnection.Comment.CommentRepository;
import com.cedarvilleconnection.CedarvilleConnection.People.PeopleRepository;
import com.cedarvilleconnection.CedarvilleConnection.Reaction.Reaction;
import com.cedarvilleconnection.CedarvilleConnection.Reaction.ReactionRepository;

@RestController
@RequestMapping("/")
public class PostController {

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private PeopleRepository peopleRepository;
    
    @Autowired
    private ReactionRepository reactionRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(@AuthenticationPrincipal User auth) {
    	ModelAndView mav = new ModelAndView("home");
    	
    	List<Post> feed = new ArrayList<Post>();
    	People currentUser = peopleRepository.findByUsername(auth.getUsername());
    	for(People p : currentUser.getFollowing()) {
    		for(Post post : p.getPosts()) {
    			feed.add(post);
    		}
    	}
    	feed.addAll(currentUser.getPosts());
    	Collections.sort(feed, new Comparator<Post>() {
    	    @Override
    	    public int compare(Post p1, Post p2) {
    	    	if(p1.getId() == p2.getId()) {
    	    		return 0;
    	    	} else if(p1.getId() > p2.getId()) {
    	    		return -1;
    	    	} else {
    	    		return 1;
    	    	}
    	    }
    	});
    	
    	for(Post post : feed) {
    		for(Reaction react : post.getReactions()) {
    			if(react.getUserId() == currentUser.getId()) {
    				post.setUserHasLiked();
    				break;
    			}
    		}
    	}
    	
        mav.addObject("posts", feed);
        return mav;
    }
    
    public People getCurrentUser(User auth) {
    	return peopleRepository.findByUsername(auth.getUsername());
    }
    
    @PostMapping("/comment")
    public ModelAndView comment(
        @AuthenticationPrincipal User auth,
        @RequestParam("comment") String contents,
        @RequestParam("postId") long postId) {

    	if (contents != null && !contents.trim().isEmpty()) {
			Comment comment = new Comment();
			comment.setPost(postRepository.findById(postId).get());
			comment.setUser(getCurrentUser(auth));
			comment.setTimestamp(new Timestamp(new Date().getTime()));
			comment.setContents(contents);
			commentRepository.save(comment);
    	}
		return index(auth);
    }
    
    @PostMapping("/post")
    public ModelAndView comment(
        @AuthenticationPrincipal User auth,
        @RequestParam("postContents") String content) {

    	if (content != null && !content.trim().isEmpty()) {
	    	Post post = new Post();
	    	post.setComments(new ArrayList<>());
	    	post.setContent(content);
	    	post.setTimestamp(new Timestamp(new Date().getTime()));
	    	post.setUser(getCurrentUser(auth));
	    	postRepository.save(post);
    	}
		return index(auth);
    }

    @PostMapping("/like")
    @ResponseBody
    public String like(@AuthenticationPrincipal User auth,
					   @RequestParam(value = "postId") long postId) {
    	
    	Post post = postRepository.findById(postId).get();
    	People user = getCurrentUser(auth);
    	Reaction reaction = reactionRepository.findByPostAndUser(post, user);
    	
    	if(reaction != null) {
    		reactionRepository.delete(reaction);
    		return "REMOVED";
    	} else {
	    	reaction = new Reaction();
		    reaction.setType(Reaction.LIKE);
		    
		    reaction.setPost(post);
		    reaction.setUser(user);
		    
		    reactionRepository.save(reaction);
		    return "ADDED";
	    }
    }
    
    @GetMapping("/likes")
    public List<Reaction> getAllReactions() {
    	return reactionRepository.findAll();
    }
    

}

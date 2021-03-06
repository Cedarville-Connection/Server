package com.cedarvilleconnection.CedarvilleConnection.People;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.cedarvilleconnection.CedarvilleConnection.Post.Post;
import com.cedarvilleconnection.CedarvilleConnection.Reaction.Reaction;

@RestController
@RequestMapping("/")
public class PeopleController {
    @Autowired
    private PeopleRepository peopleRepository;
    
    @GetMapping("/search")
    public ModelAndView showSearch(@AuthenticationPrincipal User auth, People people) {
    	ModelAndView mav = new ModelAndView("peopleSearch");
        mav.addObject("people", getAllUsers());
        mav.addObject("currentUser", getCurrentUser(auth));
        return mav;
    }
    
    @PostMapping("/search")
    public ModelAndView searchPeople(@AuthenticationPrincipal User auth, @RequestParam("userName") String userName) {
    	ModelAndView mav = new ModelAndView("peopleSearch");
    	List<People> people = peopleRepository.findByName(userName);
		
		if(people == null) {
			people = getAllUsers();
		}
    	
        mav.addObject("people", people);
        mav.addObject("currentUser", getCurrentUser(auth));
        return mav;
    }

    @GetMapping("/people")
    public List<People> getAllUsers() {
        return peopleRepository.findAll();
    }

    @GetMapping("/people/{id}")
    public ModelAndView getPeopleById( @AuthenticationPrincipal User auth,
            @PathVariable(value = "id") Long userId) {
    	
    	ModelAndView mav = new ModelAndView("profile");
    	
        People user = peopleRepository.findById(userId).get();
        List<Post> posts = user.getPosts();

        People currentUser = getCurrentUser(auth);
        long currentId = currentUser.getId();

        boolean isFollowing = false;
        for(People person: user.getFollower()){
            if(person.getId() == currentId){
                isFollowing = true;
            }
        }
        
        for(Post post : posts) {
    		for(Reaction react : post.getReactions()) {
    			if(react.getUserId() == currentUser.getId()) {
    				post.setUserHasLiked();
    				break;
    			}
    		}
    	}
        
        mav.addObject("posts", posts);
        mav.addObject("user", user);
        mav.addObject("currentUser", getCurrentUser(auth));
        mav.addObject("isFollowing", isFollowing);
        return mav;
    }
    
    @GetMapping("/people/{id}/followers")
    public ModelAndView showFollowers(@AuthenticationPrincipal User auth, 
    		@PathVariable(value = "id") Long userId) {
    	
    	People user = peopleRepository.findById(userId).get();
    	
    	ModelAndView mav = new ModelAndView("peopleSearch");
        mav.addObject("people", user.getFollower());
        mav.addObject("currentUser", getCurrentUser(auth));
        return mav;
    }
    
    @GetMapping("/people/{id}/following")
    public ModelAndView showFollowing(@AuthenticationPrincipal User auth, 
    		@PathVariable(value = "id") Long userId) {
    	
    	People user = peopleRepository.findById(userId).get();
    	
    	ModelAndView mav = new ModelAndView("peopleSearch");
        mav.addObject("people", user.getFollowing());
        mav.addObject("currentUser", getCurrentUser(auth));
        return mav;
    }

    @PostMapping("/people")
    public People createPeople(@Valid @RequestBody People person) {
        return peopleRepository.save(person);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<People> updateUser(
            @PathVariable(value = "id") Long userId,
            @Valid @RequestBody People personDetails) {
        People person = peopleRepository.findById(userId).get();

        person.setEmail(personDetails.getEmail());
        person.setLast_name(personDetails.getLast_name());
        person.setFirst_name(personDetails.getFirst_name());
        person.setProfile_pic(personDetails.getProfile_pic());
        person.setAddress(personDetails.getAddress());
        person.setDate(personDetails.getDate());
        person.setGender(personDetails.getGender());

        final People updatedUser = peopleRepository.save(person);
        return ResponseEntity.ok(updatedUser);
    } 

    @DeleteMapping("/people/{id}")
    public Map<String, Boolean> deleteUser(
            @PathVariable(value = "id") Long personId) throws Exception {
        People person = peopleRepository.findById(personId).get();

        peopleRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/follow")
    @ResponseBody
    public ModelAndView follow(@AuthenticationPrincipal User auth,
                               @RequestParam("user") long followingId){
        
        People user = getCurrentUser(auth);
        long currentUserId = user.getId();
        People toFollow = peopleRepository.findById(followingId).get();
        boolean isFollowing =false;
        for (People person : toFollow.getFollower()) {
            if(currentUserId == person.getId()){
                isFollowing = true;
            }
        }
        if(isFollowing){
            user.removeFollowing(toFollow);
            toFollow.removeFollower(user);
        }else{
            user.addFollowing(toFollow);
            toFollow.addFollower(user);
        }
        peopleRepository.save(user);
        return getPeopleById(auth, followingId);
    }
    
    public People getCurrentUser(User auth) {
    	return peopleRepository.findByUsername(auth.getUsername());
    }
}

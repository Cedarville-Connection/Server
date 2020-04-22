package com.cedarvilleconnection.CedarvilleConnection.People;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cedarvilleconnection.CedarvilleConnection.Post.Post;
import com.cedarvilleconnection.CedarvilleConnection.Post.PostRepository;

@RestController
@RequestMapping("/")
public class PeopleController {
    @Autowired
    private PeopleRepository peopleRepository;
    
    @GetMapping("/search")
    public ModelAndView showSearch(People people) {
    	ModelAndView mav = new ModelAndView("peopleSearch");
        mav.addObject("people", getAllUsers());
        return mav;
    }
    
    @PostMapping("/search")
    public ModelAndView searchPeople(@RequestParam("userName") String userName) {
    	ModelAndView mav = new ModelAndView("peopleSearch");
    	List<People> people = peopleRepository.findByName(userName);
		
		if(people == null) {
			people = getAllUsers();
		}
    	
        mav.addObject("people", people);
        return mav;
    }

    @GetMapping("/people")
    public List<People> getAllUsers() {
        return peopleRepository.findAll();
    }

    @GetMapping("/people/{id}")
    public ModelAndView getPeopleById(
            @PathVariable(value = "id") Long userId) {
    	
    	ModelAndView mav = new ModelAndView("profile");
    	
        People user = peopleRepository.findById(userId).get();
        List<Post> posts = user.getPosts();
        
        mav.addObject("posts", posts);
        mav.addObject("user", user);
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
}

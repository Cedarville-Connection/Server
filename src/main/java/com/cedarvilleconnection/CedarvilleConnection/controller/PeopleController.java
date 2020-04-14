package com.cedarvilleconnection.CedarvilleConnection.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cedarvilleconnection.CedarvilleConnection.model.People;
import com.cedarvilleconnection.CedarvilleConnection.exception.ResourceNotFoundException;
import com.cedarvilleconnection.CedarvilleConnection.repository.PeopleRepository;

@RestController
//@RequestMapping("/api/v1")
@RequestMapping("/")
public class PeopleController {
    @Autowired
    private PeopleRepository peopleRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    ModelAndView index() {
        return new ModelAndView("index");
    }
    
    @GetMapping("/search")
    public ModelAndView showSearch(People people) {
    	ModelAndView mav = new ModelAndView("peopleSearch");
        mav.addObject("people", getAllUsers());
        return mav;
    }
    
    @PostMapping("/search")
    public ModelAndView searchPeople(@RequestParam("userId") Long userId) {
    	ModelAndView mav = new ModelAndView("peopleSearch");
    	People user = null;
		try {
			user = peopleRepository.findById(userId)
			        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		List<People> people = new ArrayList<People>();
		people.add(user);
        mav.addObject("people", people);
        return mav;
    }

    @GetMapping("/people")
    public List<People> getAllUsers() {
        return peopleRepository.findAll();
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<People> getPeopleById(
            @PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        People user = peopleRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/people")
    public People createPeople(@Valid @RequestBody People person) {
        return peopleRepository.save(person);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<People> updateUser(
            @PathVariable(value = "id") Long userId,
            @Valid @RequestBody People personDetails) throws ResourceNotFoundException {
        People person = peopleRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));

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
        People person = peopleRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ personId));

        peopleRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

package com.cedarvilleconnection.CedarvilleConnection.controller;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.cedarvilleconnection.CedarvilleConnection.model.People;
import com.cedarvilleconnection.CedarvilleConnection.exception.ResourceNotFoundException;
import com.cedarvilleconnection.CedarvilleConnection.repository.PeopleRepository;

@RestController
@RequestMapping("/api/v1")
public class PeopleController {
    @Autowired
    private PeopleRepository peopleRepository;

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

        person.setEmailId(personDetails.getEmailId());
        person.setLastName(personDetails.getLastName());
        person.setFirstName(personDetails.getFirstName());
        person.setProfilePic(personDetails.getProfilePic());
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

package com.cedarvilleconnection.CedarvilleConnection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cedarvilleconnection.CedarvilleConnection.model.PeoplePost;
import com.cedarvilleconnection.CedarvilleConnection.repository.PeoplePostRepository;

@RestController
@RequestMapping("/")
public class PeoplePostController {

    @Autowired
    private PeoplePostRepository peoplePostRepository;

    @GetMapping("/peoplePost")
    public List<PeoplePost> getAllPost() {
        return peoplePostRepository.findAll();
    }
    
}

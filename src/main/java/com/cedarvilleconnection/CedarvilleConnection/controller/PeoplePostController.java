package com.cedarvilleconnection.CedarvilleConnection.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import com.cedarvilleconnection.CedarvilleConnection.repository.PeoplePostRepository;
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

import com.cedarvilleconnection.CedarvilleConnection.model.PeoplePost;
import com.cedarvilleconnection.CedarvilleConnection.exception.ResourceNotFoundException;
import com.cedarvilleconnection.CedarvilleConnection.repository.PeopleRepository;

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

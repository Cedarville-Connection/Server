package com.cedarvilleconnection.CedarvilleConnection.controller;

import com.cedarvilleconnection.CedarvilleConnection.model.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/")
public class RootController {

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
//        mav.addObject("posts", new PostRepository().findAll());
        return mav;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegister(
        @ModelAttribute("user") UserRegistration userRegistrationObject) {

        // authorities to be granted
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("admin"));

        User user = new User(userRegistrationObject.getEmail(), userRegistrationObject.getPassword(), authorities);
        jdbcUserDetailsManager.createUser(user);
        return new ModelAndView("redirect:/welcome");
    }
}

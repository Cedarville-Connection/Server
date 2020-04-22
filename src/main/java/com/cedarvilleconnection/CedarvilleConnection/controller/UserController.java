package com.cedarvilleconnection.CedarvilleConnection.controller;

import com.cedarvilleconnection.CedarvilleConnection.model.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegister(
        @ModelAttribute("username") UserRegistration userRegistrationObject) {

        // Authorities to be granted
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("user"));

        User user = new User(userRegistrationObject.getUsername(), userRegistrationObject.getPassword(), authorities);
        jdbcUserDetailsManager.createUser(user);
        return new ModelAndView("redirect:/welcome");
    }
}

package com.cedarvilleconnection.CedarvilleConnection.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RequestMapping("/")
public class RootController extends WebMvcConfigurerAdapter {
    @RequestMapping(method = RequestMethod.GET)
    ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
//        mav.addObject("posts", new PostRepository().findAll());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }
}

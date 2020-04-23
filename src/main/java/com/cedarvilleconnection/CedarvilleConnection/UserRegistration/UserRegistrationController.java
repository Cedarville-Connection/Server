package com.cedarvilleconnection.CedarvilleConnection.UserRegistration;

import com.cedarvilleconnection.CedarvilleConnection.People.People;
import com.cedarvilleconnection.CedarvilleConnection.People.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    PeopleRepository peopleRepository;

    @PostMapping
    public ModelAndView processRegister(
        @ModelAttribute("user") UserRegistration userRegistrationObject) {

  //      try {
            // Authorities to be granted
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_user"));

            String username = userRegistrationObject.getUsername();
            String password = userRegistrationObject.getPassword();
            User user = new User(username, "{noop}" + password, authorities);

            // Retrieve the person entry based on the username
            People newUser = new People();
            newUser.setFirst_name(userRegistrationObject.getFirstName());
            newUser.setLast_name(userRegistrationObject.getLastName());
            newUser.setEmail(userRegistrationObject.getEmail());
            newUser.setGender(userRegistrationObject.getGender());
            newUser.setAddress(userRegistrationObject.getAddress());
            newUser.setProfile_pic("");
            newUser.setDate(userRegistrationObject.getDOB());
            newUser.setUsername(userRegistrationObject.getUsername());

            peopleRepository.save(newUser);

            jdbcUserDetailsManager.createUser(user);

//        } catch (Exception ex) {
//            //return new ModelAndView("redirect:/login?regError");
//        }

        return new ModelAndView("redirect:/login?register");
    }
}

package com.cedarvilleconnection.CedarvilleConnection.UserRegistration;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class UserRegistration {

    @NotEmpty(message = "Please provide a username")
    private String username;

    @NotEmpty(message = "Please provide a password")
    private String password;

    @NotEmpty(message = "Please provide your first name")
    private String firstName;

    @NotEmpty(message = "Please provide your last name")
    private String lastName;

    @NotEmpty(message = "Please provide your address")
    private String address;

    @NotEmpty(message = "Please provide your date of birth")
    private String dob;

    @Email(message = "Please provide a valid email")
    @NotEmpty(message = "Please provide an email")
    private String email;

    private int gender;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDOB() {
//        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//        Date parsed;
//        try {
//            parsed = df.parse(this.dob);
//        } catch (ParseException ex) {
//            parsed = new Date(0);
//        }
//
//        return parsed;
        return new Date();
    }
    public void setDOB(Date dob) {
        this.dob = dob.toString();
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() { return gender; }
    public void setGender(int gender) {
        this.gender = gender;
    }

}

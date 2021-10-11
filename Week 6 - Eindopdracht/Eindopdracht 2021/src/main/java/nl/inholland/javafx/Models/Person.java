package nl.inholland.javafx.Models;

import nl.inholland.javafx.Models.Enums.UserType;

public abstract class Person {

    protected String username;
    protected String password;
    protected UserType userType;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }




}

package nl.inholland.javafx.Models;

import nl.inholland.javafx.Models.Enums.UserType;

public class User extends Person{

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.userType = UserType.USER;
    }
}

package nl.inholland.javafx.Models;

import nl.inholland.javafx.Models.Enums.UserType;

public class Admin extends Person{

    public Admin(String username, String password){
        this.username = username;
        this.password = password;
        this.userType = UserType.ADMIN;
    }
}

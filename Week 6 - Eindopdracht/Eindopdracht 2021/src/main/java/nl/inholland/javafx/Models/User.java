package nl.inholland.javafx.Models;

import nl.inholland.javafx.Models.Enums.UserType;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String username;
    private final String password;
    private final UserType userType;
    private final List<Ticket> tickets;

    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        tickets = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

}

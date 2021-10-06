package nl.inholland.javafx.model;

import java.time.LocalDate;

public class Manager extends Person{

    public Manager(int id, String username, String password, String firstName, String lastName, LocalDate dateOfBirth, int age)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;

        this.access = Access.Admin;
    }
}

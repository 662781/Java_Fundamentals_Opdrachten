package nl.inholland.javafx.model;

import java.time.LocalDate;

public abstract class Person {
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public Access getAccess() {
        return access;
    }

    protected int id;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected LocalDate dateOfBirth;
    protected int age;
    protected Access access;
}

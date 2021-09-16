package nl.inholland;

import java.time.LocalDate;
import java.util.Date;

public class Teacher extends Person
{
    public Teacher(int id, String username, String password, String firstName, String lastName, LocalDate dateOfBirth, int age)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;

        this.access = Access.Editor;
    }
    protected void printDetails()
    {
        super.printDetails();
    }

}

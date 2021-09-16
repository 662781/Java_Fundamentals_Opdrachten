package nl.inholland;

import java.time.LocalDate;
import java.util.Date;

public abstract class Person
{
    int id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    int age;
    Access access;

    void printDetails()
    {
        System.out.print(id + "   " + firstName + "    " + lastName + "    " + dateOfBirth + "    " + age + "    ");
        //System.out.printf("%-3s%-10s%-10s%-10s%-3s", id, firstName, lastName, dateOfBirth, age);
        //System.out.println("");
    }


}

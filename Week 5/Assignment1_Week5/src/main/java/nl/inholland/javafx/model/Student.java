package nl.inholland.javafx.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    protected String group;
    protected String result;
    protected int retakes;
    protected List<Integer> grades;

    //Constructor to create student without grades
    public Student(int id, String username, String password, String firstName, String lastName, LocalDate dateOfBirth, int age, String group)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.group = group;

        this.access = Access.Basic;
        grades = new ArrayList<>();

        for (Number grade: grades)
        {
            grades.add(0);
        }
    }

    //Constructor to create student with grades
    public Student(int id, String username, String password, String firstName, String lastName, LocalDate dateOfBirth, int age, String group, List<Integer> grades)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.group = group;
        this.grades = grades;

        this.access = Access.Basic;
    }
}

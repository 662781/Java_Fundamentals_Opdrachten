package nl.inholland;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends Person
{
    protected String group;
    protected String result;
    protected int retakes;
    protected List<Integer> grades;


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

    protected void printDetails()
    {
        super.printDetails();
        System.out.print(group);
        //System.out.printf("%-10s", group);
    }

    protected void printReports()
    {
        for (Number grade: grades)
        {
            System.out.print(grade + "  ");
        }
    }

}

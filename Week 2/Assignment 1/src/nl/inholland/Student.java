package nl.inholland;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Student extends Person
{
    protected String group;
    protected int javaGrade;
    protected int cSharpGrade;
    protected int pythonGrade;
    protected int phpGrade;
    protected String result;
    protected int retakes;


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
        this.javaGrade = 0;
        this.cSharpGrade = 0;
        this.pythonGrade =0;
        this.phpGrade = 0;
    }

    public Student(int id, String username, String password, String firstName, String lastName, LocalDate dateOfBirth, int age, String group, int javaGrade, int cSharpGrade, int pythonGrade, int phpGrade)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.group = group;
        this.javaGrade = javaGrade;
        this.cSharpGrade = cSharpGrade;
        this.pythonGrade =pythonGrade;
        this.phpGrade = phpGrade;

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
        System.out.print(javaGrade + "  " + cSharpGrade + "  " + pythonGrade + "  " + phpGrade + "  ");
    }

    protected void calcResults(int javaGrade, int cSharpGrade, int pythonGrade, int phpGrade)
    {
        if (javaGrade > 55 && cSharpGrade > 55 && pythonGrade > 55 && phpGrade > 55)
        {
            result = "Passed";
        }
        else
        {
            result = "Not Passed";
        }
    }
}

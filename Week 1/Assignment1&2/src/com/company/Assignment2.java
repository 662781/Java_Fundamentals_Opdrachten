package com.company;

import java.util.Scanner;

public class Assignment2 {

    void Start()
    {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter course name: ");

        String course = scanner.next();

        System.out.print("Enter number of students: ");

        int nrOfStudents = scanner.nextInt();

        String[] names = new String[nrOfStudents];
        int[] grades = new int[nrOfStudents];
        int gradeSum = 0;
        int maxNameIndex = 0;
        int maxGrade = 0;

        System.out.println("");

        for (int i = 0; i < names.length; i++)
        {
            System.out.print("Enter name of student " + (i+1) + ": ");

            String name = scanner.next();

            names[i] = name;
        }

        System.out.println("");

        for (int i = 0; i < grades.length; i++)
        {
            System.out.print("Enter grade of " + names[i] + ": ");

            int grade = scanner.nextInt();

            grades[i] = grade;

            gradeSum += grade;
        }

        System.out.println("");

        int avgGrade = gradeSum / nrOfStudents;

        System.out.println("Average grade: " + avgGrade);

        for (int i = 0; i < grades.length; i++)
        {
            if (i == 0 || grades[i] > maxGrade)
            {
                maxGrade = grades[i];
                maxNameIndex = i;
            }
        }
        System.out.println("Student " + names[maxNameIndex] + " has maximum grade: " + maxGrade);

        System.out.println("");

        for (int i = 0; i < grades.length; i++)
        {
            System.out.println("Grade for student " + names[i] + "(course " + course + "): " + grades[i]);
        }




    }




}

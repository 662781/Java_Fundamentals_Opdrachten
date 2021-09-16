package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Main main = new Main();
        main.start();
    }

    void start()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the size of your group and press [ENTER]");

        int GroupSize = scanner.nextInt();

        Student[] students = new Student[GroupSize];

        for (int i = 0; i < GroupSize; i++)
        {
            System.out.println("Please enter the name of student #" + (i+1) + " and press [ENTER]:");

            String name = scanner.next();

            students[i] = new Student(name);
        }

        for (int i = 0; i < GroupSize; i++)
        {
            System.out.println("Student #" + (i+1) + ": " + students[i].Name);
        }

        System.out.println("");

        for (int i = 0; i < GroupSize; i++)
        {
            System.out.println("Is student #" + (i+1) + "(" + students[i].Name + ") present? [Y/N + ENTER]:");

            if(scanner.next().equalsIgnoreCase("y"))
            {
                students[i].Present = true;
            }
            else
            {
                students[i].Present = false;
            }
        }

        System.out.println("");

        for (int i = 0; i < GroupSize; i++)
        {
            System.out.println("Student #" + (i+1)  + ": " + students[i].Name + "          | Present: " + students[i].Present);
        }




    }


}

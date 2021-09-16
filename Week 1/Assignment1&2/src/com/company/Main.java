package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Start which assignment? ");
        int number = scanner.nextInt();

        switch (number)
        {
            case 1: new Assignment1().Start();
                break;
            case 2: new Assignment2().Start();
                break;
        }
    }



}

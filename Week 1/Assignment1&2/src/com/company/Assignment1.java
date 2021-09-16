package com.company;

import java.util.Scanner;

public class Assignment1 {

    void Start(){
        Scanner scanner = new Scanner(System.in);

        printName(scanner);
    }


    public void printName(Scanner scanner){
        System.out.println("Uw naam alstublieft");

        String name = scanner.nextLine();

        System.out.println("Wat is uw leeftijd?");

        int age = scanner.nextInt();

        System.out.println("Dankuwel " + name + "\n" + "Uw leeftijd " + "(" + age + ")" + " is geregistreerd");
    }
}

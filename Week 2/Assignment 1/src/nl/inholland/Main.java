package nl.inholland;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        Main main = new Main();
        main.start();
    }

    void start()
    {
        List<Person> people = createPeople();
        Scanner scanner = new Scanner(System.in);

        String usernameInput;
        String passwordInput;

        System.out.print("Enter username: ");
        usernameInput = scanner.next();

        System.out.print("Enter password: ");
        passwordInput = scanner.next();

        while(!passwordCheck(usernameInput, passwordInput, people))
        {
            System.out.println("Input of password or username is incorrect");

            System.out.print("Enter username: ");
            usernameInput = scanner.next();

            System.out.print("Enter password: ");
            passwordInput = scanner.next();

            if (passwordCheck(usernameInput, passwordInput, people))
            {
                break;
            }
        }

        Person p = getPersonInfo(usernameInput, people);

        Access access = p.access;

        System.out.println("");

        switch (access)
        {
            case Basic: displayBasicMenu(scanner, people);
                break;
            case Editor: displayEditorMenu(scanner, people);
                break;
            case Admin: displayAdminMenu(scanner, people);
                break;
        }

        System.out.println("\n\nLeaving the program now ...");

    }

    //Create Person objects

    List<Person> createPeople()
    {
        //create array
        List<Person> people = new ArrayList<>();
        //create dates
        LocalDate d1 = LocalDate.of(1997 , 12 , 4);
        LocalDate d2 = LocalDate.of(1993 , 8 , 7);
        LocalDate d3 = LocalDate.of(1999 , 11 , 1);
        LocalDate d4 = LocalDate.of(1965 , 12 , 4);
        //create students and add to array
        Person person1 = new Student(1, "emma", "emma12", "Emma", "Smith", d1, 23, "IT-02-A", 54, 50, 66, 54);
        Person person2 = new Student(2, "jack", "jack13", "Jack", "Brown", d1, 27, "IT-02-A", 72, 68, 43, 95);
        Person person3 = new Student(3, "michael", "michael14", "Michael", "Garcia", d1, 21, "IT-02-A", 45, 71, 55, 84);
        //create teacher and add to array
        Person person4 = new Teacher(4, "david", "david15", "David", "Taylor", d4, 55);

        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);

        return people;
    }

    //Checks if there's a match of the password and username in the list

    boolean passwordCheck(String username, String password, List<Person> people)
    {
        boolean checkPassed = false;

        for (Person p: people)
        {
            if (Objects.equals(username, p.username) && Objects.equals(password, p.password))
            {
                checkPassed = true;
                break;
            }
            else
            {
                checkPassed = false;
            }
        }

        return checkPassed;
    }


    //Displays menu's according to the users choice

    void showStudents(List<Person> people)
    {
        System.out.println("LIST OF STUDENTS\n");
        System.out.println("Id " + "FirstName " + "LastName " + "Birthdate " + "Age " + "Group ");

        for (Person p: people)
        {
            if (p instanceof Student)
            {
                p.printDetails();
                System.out.println("");
            }

        }

        System.out.println("");
    }

    void showTeachers(List<Person> people)
    {
        System.out.println("LIST OF TEACHERS\n");
        System.out.println("Id " + "FirstName " + "LastName " + "Birthdate " + "Age ");
        //System.out.printf("%-3s%-10s%-10s%-10s%-3s", "Id", "FirstName" , "LastName", "Birthdate" , "Age");

        for (Person p: people)
        {
            if (p instanceof Teacher)
            {
                p.printDetails();
                System.out.println("");
            }

        }
        System.out.println("");
    }

    void showAddStudents(List<Person> people, Scanner scanner)
    {
        System.out.println("ADD STUDENT\n");

        System.out.print("Choose an ID-Number: ");
        int id = scanner.nextInt();

        System.out.print("Choose a username: ");
        String username = scanner.next();

        System.out.print("Choose a password: ");
        String password = scanner.next();

        System.out.print("Enter first name: ");
        String firstname = scanner.next();

        System.out.print("Enter last name: ");
        String lastname = scanner.next();

        System.out.print("Please enter the date of birth in MM/DD/YYYY: ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        formatter = formatter.withLocale(Locale.ENGLISH);
        LocalDate dateOfBirth = LocalDate.parse(scanner.next(), formatter);

        int age = Math.abs(dateOfBirth.compareTo(LocalDate.now()));

        System.out.print("Enter group: ");
        String group = scanner.next();

        Person student = new Student(id, username, password, firstname, lastname, dateOfBirth, age, group);

        people.add(student);

        System.out.print("\nThe data was successfully added!\n");
    }

    void showReports(List<Person> people, Scanner scanner)
    {
        System.out.println("STUDENT RESULTS\n");
        System.out.println("Id " + "FirstName " + "LastName " + "Birthdate " + "Age " + "Group " + "Java " + "CSharp " + "Python " + "PHP ");

        for (Person p: people)
        {
            if (p instanceof Student)
            {
                p.printDetails();
                ((Student) p).printReports();
                System.out.println("");
            }

        }

        System.out.print("Enter student id (Report Details) | Or 0 back to main menu: ");
        System.out.println("");
        int studentId = scanner.nextInt();

        if(studentId == 0)
        {
            displayEditorMenu(scanner, people);
        }
        else if(studentId > 0)
        {
            for(Person p: people)
            {
                if (studentId == p.id && p instanceof Student)
                {
                    showReportDetails(people, scanner, p);
                }
            }


        }

    }

    void showReportDetails(List<Person> people, Scanner scanner, Person student)
    {
        Student s = (Student) student;

        System.out.println("");
        System.out.println("Report of student " + s.firstName + " " + s.lastName);
        System.out.println("");

        System.out.println("Student Id --- " + s.id + "\nFirst Name --- " + s.firstName + "\nLast Name --- " + s.lastName + "\nAge --- " + s.age);
        System.out.println("");

        System.out.println("COURSES");
        System.out.println("");

        System.out.println("Java --- " + s.javaGrade + "\nCSharp --- " + s.cSharpGrade + "\nPython --- " + s.pythonGrade + "\nPHP --- " + s.phpGrade);

        System.out.println("");
        System.out.println("RESULTS");
        System.out.println("");

        System.out.println("Here come the results! Or not...");
    }


    //Gets the Person object from the person who is logging in, from the list

    Person getPersonInfo(String username, List<Person> people)
    {
        Person person1 = null;

        for (Person p: people)
        {
            if (Objects.equals(username, p.username))
            {
                person1 = p;
            }
        }


        return person1;
    }


    //Displays menu according to access type


    void displayBasicMenu(Scanner scanner, List<Person> people)
    {
        System.out.println("S. Display Students  | T. Display Teachers  | X. Exit  |");
        System.out.println("");
        System.out.print("Please, enter a choice: ");
        String choice = scanner.next();

        System.out.println("");

        while(!Objects.equals(choice, "X") && !Objects.equals(choice, "x"))
        {
            switch (choice)
            {
                case "S":
                case "s":
                    showStudents(people);
                    break;
                case "T":
                case "t":
                    showTeachers(people);
                    break;
                case "X":
                case "x":
                    break;
                default: System.out.println("Please enter a valid choice\n");
            }

            System.out.println("S. Display Students  | T. Display Teachers  | X. Exit  |");
            System.out.println("");
            System.out.print("Please, enter a choice: ");
            choice = scanner.next();

            System.out.println("");
        }
    }

    void displayEditorMenu(Scanner scanner, List<Person> people)
    {
        //System.out.println("Here comes the Editor menu");
        System.out.println("S. Display Students  | T. Display Teachers  | A. Add Students   | R. Display Reports   | X. Exit  |");
        System.out.println("");
        System.out.print("Please, enter a choice: ");
        String choice = scanner.next();

        System.out.println("");

        while(!Objects.equals(choice, "X") && !Objects.equals(choice, "x"))
        {
            switch (choice)
            {
                case "S":
                case "s":
                    showStudents(people);
                    break;
                case "T":
                case "t":
                    showTeachers(people);
                    break;
                case "A":
                case "a":
                    showAddStudents(people, scanner);
                case "X":
                case "x":
                    break;
                case "R":
                case "r":
                    showReports(people, scanner);
                    break;
                default: System.out.println("Please enter a valid choice\n");
            }

            System.out.println("S. Display Students  | T. Display Teachers  | A. Add Students   | R. Display Reports   | X. Exit  |");
            System.out.println("");
            System.out.print("Please, enter a choice: ");
            choice = scanner.next();

            System.out.println("");
        }

    }

    void displayAdminMenu(Scanner scanner, List<Person> people)
    {
        System.out.println("Here comes the Admin menu");
    }
    
    
    
    
}

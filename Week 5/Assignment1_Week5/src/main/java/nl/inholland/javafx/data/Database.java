package nl.inholland.javafx.data;

import nl.inholland.javafx.model.Manager;
import nl.inholland.javafx.model.Person;
import nl.inholland.javafx.model.Student;
import nl.inholland.javafx.model.Teacher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public List<Person> getPeople() {
        return people;
    }

    private List<Person> people;

    public Database(){
        //Create a list of people
        people = createPeople();

        //Load people from .csv file
        //people = loadPeople();
    }

    private List<Person> createPeople()
    {
        //create array
        List<Person> people = new ArrayList<>();
        //create grades lists
        List<Integer> gradesEmma = new ArrayList<>();
        gradesEmma.add(54);
        gradesEmma.add(50);
        gradesEmma.add(66);
        gradesEmma.add(54);
        List<Integer> gradesJack = new ArrayList<>();
        gradesJack.add(72);
        gradesJack.add(68);
        gradesJack.add(43);
        gradesJack.add(95);
        List<Integer> gradesMichael = new ArrayList<>();
        gradesMichael.add(45);
        gradesMichael.add(74);
        gradesMichael.add(55);
        gradesMichael.add(84);
        //create dates
        LocalDate d1 = LocalDate.of(1997 , 12 , 4);
        LocalDate d2 = LocalDate.of(1993 , 8 , 7);
        LocalDate d3 = LocalDate.of(1999 , 11 , 1);
        LocalDate d4 = LocalDate.of(1965 , 12 , 4);
        LocalDate d5 = LocalDate.of(1950, 2 , 12);
        //create students and add to array
        Person person1 = new Student(1, "emma", "emma12", "Emma", "Smith", d1, 24, "IT-02-A", gradesEmma);
        Person person2 = new Student(2, "jack", "jack13", "Jack", "Brown", d2, 28, "IT-02-A", gradesJack);
        Person person3 = new Student(3, "michael", "michael14", "Michael", "Garcia", d3, 22, "IT-02-A", gradesMichael);
        //create teacher and add to array
        Person person4 = new Teacher(4, "david", "david15", "David", "Taylor", d4, 56);
        //create admin and add to array
        Person person5 = new Manager(5, "admin", "admin16", "Ad", "Min", d5, 71);

        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);
        people.add(person5);

        return people;
    }

    private List<Person> loadPeople(){
        return new ArrayList<>();
    }
}

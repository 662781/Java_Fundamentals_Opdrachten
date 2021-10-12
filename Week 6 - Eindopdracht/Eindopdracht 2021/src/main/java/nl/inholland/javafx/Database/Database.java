package nl.inholland.javafx.Database;

import nl.inholland.javafx.Models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Movie> movies;
    private List<Person> users;
    private List<Showing> showingsRoom1;
    private List<Showing> showingsRoom2;
    private List<Room> rooms;

    public Database(){
        movies = createMovies();
        users = createUsers();
        rooms = createRooms();
        showingsRoom1 = createShowingsRoom1();
        showingsRoom2 = createShowingsRoom2();
    }

    //Getters
    public List<Movie> getMovies() {
        return movies;
    }

    public List<Showing> getShowingsRoom1() {
        return showingsRoom1;
    }

    public List<Showing> getShowingsRoom2() {
        return showingsRoom2;
    }

    public List<Person> getUsers() {
        return users;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    //Fill the lists
    private List<Movie> createMovies(){

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Venom: Let There Be Carnage", 200, 12.20, 2));
        movies.add(new Movie("Spider-Man: No Way Home", 400, 16.00, 3));

        return movies;

    }

    private List<Person> createUsers(){
        List<Person> users = new ArrayList<>();
        users.add(new User("PietJanKlaas", "123huts"));
        users.add(new User("Klaas", "tester"));

        return users;
    }

    private List<Room> createRooms(){
        List<Room> rooms = new ArrayList<>();

        rooms.add(new Room("Room 1", this));
        rooms.add(new Room("Room 2", this));

        return rooms;
    }

    private List<Showing> createShowingsRoom1(){
        List<Showing> showings = new ArrayList<>();

        Room room1 = rooms.get(0);

        showings.add(new Showing(room1, movies.get(0), LocalDateTime.of(2021, 9, 10, 20, 0)));
        showings.add(new Showing(room1, movies.get(1), LocalDateTime.of(2021, 9, 10, 22, 30)));

        return showings;
    }

    private List<Showing> createShowingsRoom2(){
        List<Showing> showings = new ArrayList<>();

        Room room2 = rooms.get(1);

        showings.add(new Showing(room2, movies.get(1), LocalDateTime.of(2021, 9, 10, 22, 0)));
        showings.add(new Showing(room2, movies.get(0), LocalDateTime.of(2021, 9, 10, 20, 0)));

        return showings;
    }

    //Checks the name of the room and sets the right showings list
    public List<Showing> getShowingsPerRoom(String name){

        if (name.equals("Room 1")){
            return showingsRoom1;
        }
        else if (name.equals("Room 2")){
            return showingsRoom2;
        }

        return showingsRoom1;

    }
}

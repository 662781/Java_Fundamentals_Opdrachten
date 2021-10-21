package nl.inholland.javafx.Database;

import nl.inholland.javafx.Models.Enums.UserType;
import nl.inholland.javafx.Models.Movie;
import nl.inholland.javafx.Models.Room;
import nl.inholland.javafx.Models.Showing;
import nl.inholland.javafx.Models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private final List<Movie> movies;
    private final List<User> users;
    private final List<Showing> showingsRoom1;
    private final List<Showing> showingsRoom2;
    private final List<Room> rooms;

    public Database() {
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

    public List<User> getUsers() {
        return users;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    //Fill the lists
    private List<Movie> createMovies() {

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Venom: Let There Be Carnage", 12.20, 120));
        movies.add(new Movie("Spider-Man: No Way Home", 16.00, 150));

        return movies;

    }

    private List<User> createUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("PietJanKlaas", "123huts", UserType.USER));
        users.add(new User("Klaas", "tester", UserType.ADMIN));
        users.add(new User("Mark", "DnD3", UserType.USER));
        users.add(new User("Bas", "vcpw", UserType.ADMIN));

        return users;
    }

    private List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();

        rooms.add(new Room("Room 1", this, 400));
        rooms.add(new Room("Room 2", this, 200));

        return rooms;
    }

    private List<Showing> createShowingsRoom1() {
        List<Showing> showings = new ArrayList<>();

        Room room1 = rooms.get(0);

        showings.add(new Showing(room1, movies.get(0), LocalDateTime.of(2021, 9, 10, 20, 0)));
        showings.add(new Showing(room1, movies.get(1), LocalDateTime.of(2021, 9, 10, 22, 30)));

        return showings;
    }

    private List<Showing> createShowingsRoom2() {
        List<Showing> showings = new ArrayList<>();

        Room room2 = rooms.get(1);

        showings.add(new Showing(room2, movies.get(1), LocalDateTime.of(2021, 9, 10, 22, 0)));
        showings.add(new Showing(room2, movies.get(0), LocalDateTime.of(2021, 9, 10, 20, 0)));

        return showings;
    }

    //Checks the name of the room and sets the right showings list
    public List<Showing> getShowingsPerRoom(String name) {

        if (name.equals("Room 1")) {
            return showingsRoom1;
        } else if (name.equals("Room 2")) {
            return showingsRoom2;
        }

        return showingsRoom1;

    }
}

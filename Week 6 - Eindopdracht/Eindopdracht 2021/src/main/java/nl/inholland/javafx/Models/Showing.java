package nl.inholland.javafx.Models;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Showing{
    private Room room;
    private Movie movie;
    private LocalDateTime startTime;
    private int endTime;

    public Showing(Room room, Movie movie, LocalDateTime startTime) {
        this.room = room;
        this.movie = movie;
        this.startTime = startTime;
        //endTime = this.startTime + LocalTime.of(movie.getDuration(), 0);
    }


    public Room getRoom() {
        return room;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }
}

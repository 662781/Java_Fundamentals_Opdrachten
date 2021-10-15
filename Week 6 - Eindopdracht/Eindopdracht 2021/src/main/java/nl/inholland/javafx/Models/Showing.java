package nl.inholland.javafx.Models;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Showing{
    private Room room;
    private Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Showing(Room room, Movie movie, LocalDateTime startTime) {
        this.room = room;
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = startTime;//LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(), (startTime.getHour() + movie.getDuration()), startTime.getMinute());
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

    public LocalDateTime getEndTime() {
        return endTime;
    }
}

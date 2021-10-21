package nl.inholland.javafx.Models;

import java.time.LocalDateTime;

public class Showing{
    private Room room;
    private Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int ticketsAvailable;

    public Showing(Room room, Movie movie, LocalDateTime startTime) {
        this.room = room;
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getDuration());
        ticketsAvailable = room.getAmtOfSeats();
    }


    //Getters
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

    public int getTicketsAvailable() {
        return ticketsAvailable;
    }

    //Setter
    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }


}

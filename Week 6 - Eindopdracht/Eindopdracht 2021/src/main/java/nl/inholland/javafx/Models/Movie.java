package nl.inholland.javafx.Models;

public class Movie {
    //Fields
    private final String title;
    private final double ticketPrice;
    private final int duration;

    public Movie(String title, double ticketPrice, int duration) {
        this.title = title;
        this.ticketPrice = ticketPrice;
        this.duration = duration;
    }

    //Getters
    public String getTitle() {
        return title;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getDuration() {
        return duration;
    }


}

package nl.inholland.javafx.Models;

import java.time.LocalTime;

public class Movie {
    //Fields
    private String title;
    private int amtOfSeats;
    private double ticketPrice;
    private int duration;

    public Movie(String title, int amtOfSeats, double ticketPrice, int duration){
        this.title = title;
        this.amtOfSeats = amtOfSeats;
        this.ticketPrice = ticketPrice;
        this.duration = duration;
    }

    //Getters
    public String getTitle() {
        return title;
    }

    public int getAmtOfSeats() {
        return amtOfSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getDuration() {
        return duration;
    }

    //Setters
    public void setAmtOfSeats(int amtOfSeats) {
        this.amtOfSeats = amtOfSeats;
    }


}

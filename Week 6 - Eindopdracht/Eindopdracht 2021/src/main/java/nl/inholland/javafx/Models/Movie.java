package nl.inholland.javafx.Models;

import java.text.DecimalFormat;
import java.time.LocalTime;

public class Movie {
    //Fields
    private String title;
    private double ticketPrice;
    private int duration;

    public Movie(String title, double ticketPrice, int duration){
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

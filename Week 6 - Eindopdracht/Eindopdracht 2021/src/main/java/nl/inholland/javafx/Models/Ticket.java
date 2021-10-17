package nl.inholland.javafx.Models;

public class Ticket {
    //Fields
    private double price;
    private String movieTitle, lastName;
    private Showing showing;
    //Amount of seats the user purchased
    private int amtOfSeats;

    public Ticket(double price, String movieTitle, int amtOfSeats,String lastName ,Showing showing){
        this.price = price;
        this.movieTitle = movieTitle;
        this.lastName = lastName;
        this.amtOfSeats = amtOfSeats;
        this.showing = showing;
    }

    //Getters
    public double getPrice() {
        return price;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public int getAmtOfSeats() {
        return amtOfSeats;
    }
}


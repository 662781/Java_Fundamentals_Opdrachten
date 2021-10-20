package nl.inholland.javafx.Models;

public class Ticket {
    //Fields
    private double price;
    private String movieTitle, userName;
    private Showing showing;
    //Amount of seats the user purchased
    private int amtOfSeats;

    public Ticket(double price, String movieTitle, int amtOfSeats,String userName ,Showing showing){
        this.price = price;
        this.movieTitle = movieTitle;
        this.userName = userName;
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


package nl.inholland.javafx.Models;

public class Ticket {
    //Fields
    private double price;
    private String movieTitle, userName;
    private Showing showing;
    //Amount of seats the user purchased
    private int amtOfSeats;

    public Ticket(Showing showing, String userName, int amtOfSeats){
        this.showing = showing;
        this.price = showing.getMovie().getTicketPrice();
        this.movieTitle = showing.getMovie().getTitle();
        this.userName = userName;
        this.amtOfSeats = amtOfSeats;

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


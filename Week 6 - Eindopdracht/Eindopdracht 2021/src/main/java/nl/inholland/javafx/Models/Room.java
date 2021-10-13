package nl.inholland.javafx.Models;

import nl.inholland.javafx.Database.Database;
import nl.inholland.javafx.UI.Login;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private Database db;
    private List<Showing> showingList;
    private int amtOfSeats;
    private String roomName;

    public Room(String roomName, Database db, int amtOfSeats){
        this.db = db;
        this.roomName = roomName;
        this.amtOfSeats = amtOfSeats;
        showingList = db.getShowingsPerRoom(roomName);
    }

    //Getters
    public List<Showing> getShowingList() {
        return showingList;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getAmtOfSeats() {
        return amtOfSeats;
    }

    //Setters
    public void setAmtOfSeats(int amtOfSeats) {
        this.amtOfSeats = amtOfSeats;
    }


    //Add showing method
    public void addShowing(Showing showing){
        showingList.add(showing);
    }

}

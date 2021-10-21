package nl.inholland.javafx.Models;

import nl.inholland.javafx.Database.Database;

import java.util.List;

public class Room {

    private final Database db;
    private final String roomName;
    private final int amtOfSeats;
    private List<Showing> showingList;


    public Room(String roomName, Database db, int amtOfSeats) {
        this.db = db;
        this.roomName = roomName;
        this.amtOfSeats = amtOfSeats;
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

    //Add showing method
    public void addShowing(Showing showing) {
        showingList = db.getShowingsPerRoom(this.roomName);
        showingList.add(showing);
    }

}

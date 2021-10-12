package nl.inholland.javafx.Models;

import nl.inholland.javafx.Database.Database;
import nl.inholland.javafx.UI.Login;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private Database db;
    private List<Showing> showingList;
    private String roomName;

    public Room(String roomName, Database db){
        this.db = db;
        this.roomName = roomName;
        showingList = db.getShowingsPerRoom(roomName);
    }

    public List<Showing> getShowingList() {
        return showingList;
    }

    public String getRoomName() {
        return roomName;
    }

    public void addShowing(Showing showing){
        showingList.add(showing);
    }

}

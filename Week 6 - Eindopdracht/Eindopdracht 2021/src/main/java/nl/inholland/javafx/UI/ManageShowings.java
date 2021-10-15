package nl.inholland.javafx.UI;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class ManageShowings extends Window {

    private Scene purchaseTickets;

    public ManageShowings(Scene purchaseTickets){
            this.purchaseTickets = purchaseTickets;
    }

    protected Node setLayout(){
        return new HBox();
    }
}

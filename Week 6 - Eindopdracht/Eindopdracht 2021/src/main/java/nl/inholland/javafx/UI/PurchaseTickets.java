package nl.inholland.javafx.UI;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PurchaseTickets extends Window{

    private Stage loginWindow;

    public PurchaseTickets(Stage loginWindow){
        this.loginWindow = loginWindow;
    }

    protected Node setLayout(){
        return new HBox();
    }
}

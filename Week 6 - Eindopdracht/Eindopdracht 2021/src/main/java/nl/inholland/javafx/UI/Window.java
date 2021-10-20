package nl.inholland.javafx.UI;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.javafx.Database.Database;
import nl.inholland.javafx.Models.User;

public abstract class Window{
    //Fields
    public Stage window;
    public Stage loginWindow;
    public VBox mainLayout;
    public Database db;
    public User userLoggedIn;

    //Getter
    public Stage getStage() {
        return window;
    }
}

package nl.inholland.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import nl.inholland.javafx.Database.Database;
import nl.inholland.javafx.UI.Login;

public class App extends Application {

    @Override
    public void start(Stage window) throws Exception {
        //Show the login window when app starts
        Database db  = new Database();
        Login loginWindow = new Login(db);
    }
}

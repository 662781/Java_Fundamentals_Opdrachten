package nl.inholland.javafx;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.inholland.javafx.ui.LoginWindow;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        //Shows the LoginWindow when started
        LoginWindow lW = new LoginWindow();

    }

}

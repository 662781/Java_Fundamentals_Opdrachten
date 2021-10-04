package nl.inholland.javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("Inholland JavaFX Starter Project");

        GridPane pane = new GridPane();

        pane.setId("GridLayout");

        Scene scene = new Scene(pane);
        scene.getStylesheets().add("resources/css/LoginStyle.css");

        window.setScene(scene);
        window.show();
    }
}

package nl.inholland.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("Car Rental");

        Label lbl_DaysRented = new Label("Number of days rented:");
        Label lbl_KMDriver = new Label("Number of kilometer driven:");
        Label lbl_NrOfLiters = new Label("Number of liters:");
        Label lbl_AmountDue = new Label("Amount due:");


        GridPane layout = new GridPane();

        layout.setHgap(5);
        layout.setVgap(10);




        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
}

package nl.inholland.javafx.ui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainWindow extends WindowSuperClass{
    private Stage loginWindow;

    public MainWindow(Stage loginWindow){
        //Create stage
        window = new Stage();

        //Set window size and title
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("University Project - Main Menu");

        //Initialize loginWindow stage object for later use (logging out)
        this.loginWindow = loginWindow;

        GridPane layout = setLayout();

        //Create scene and add stylesheet
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("css/MainMenuStyle.css");

        //Set the scene and show window
        window.setScene(scene);
        window.show();


    }


    @Override
    public GridPane setLayout() {
        GridPane layout = new GridPane();
        return layout;
    }
}

package nl.inholland.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception
    {
        //Set window size and title
        window.setHeight(150);
        window.setWidth(300);
        window.setTitle("Currency Converter");

        //Create grid and scene (inside of window)
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid);

        //Create nodes
        Label lbl_EuroAmount = new Label("Amount \u20ac:");
        Label lbl_DollarAmount = new Label("Amount $:");
        Label lbl_Result = new Label();
        TextField txt_EuroInput = new TextField();
        Button btn_Convert = new Button("Convert Euro To Dollar");

        //Set layout
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(2);
        grid.setVgap(10);

        //Put nodes in the grid
        grid.addRow(0, lbl_EuroAmount, txt_EuroInput);
        grid.add(btn_Convert, 1, 1);
        grid.addRow(2, lbl_DollarAmount, lbl_Result);

        //Set the scene and show everything
        window.setScene(scene);
        window.show();

        //Retrieve data from text field
        int amtInEuro = Integer.parseInt(txt_EuroInput.getText());

        //Sets text from label if button is clicked
        btn_Convert.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                lbl_Result.setText(String.valueOf(euroToDollar(amtInEuro)));
            }
        });

    }

    //Converts euro amount to dollar amount
    protected double euroToDollar(int amtInEuro)
    {
        return amtInEuro * 1.18;
    }
}

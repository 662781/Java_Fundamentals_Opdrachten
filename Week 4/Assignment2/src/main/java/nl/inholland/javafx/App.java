package nl.inholland.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {

        //Set window size + title
        window.setHeight(250);
        window.setWidth(400);
        window.setTitle("Car Rental");

        //Create nodes
        Label lbl_DaysRented = new Label("Number of days rented:");
        Label lbl_KMDriver = new Label("Number of kilometer driven:");
        Label lbl_NrOfLiters = new Label("Number of liters:");
        Label lbl_AmountDue = new Label("Amount due:");
        Label lbl_AmountDueResult = new Label();
        CheckBox cbx_TankFull = new CheckBox("Fuel tank not full when returned");
        TextField txt_DaysRentedInput = new TextField();
        TextField txt_KMDrivenInput = new TextField();
        TextField txt_NrOfLitersInput = new TextField();
        Button btn_Calc = new Button("Calculate payment");

        //Create grid layout
        GridPane layout = new GridPane();

        //Set spacing
        layout.setPadding(new Insets(10, 10, 10 , 10));
        layout.setHgap(5);
        layout.setVgap(10);

        //Add nodes to grid layout
        layout.add(lbl_DaysRented, 0,0);
        layout.add(lbl_KMDriver, 0 , 1);
        layout.add(cbx_TankFull, 0, 2);
        layout.add(lbl_NrOfLiters, 0, 3);
        layout.add(lbl_AmountDue, 0, 5);
        layout.add(txt_DaysRentedInput, 1, 0);
        layout.add(txt_KMDrivenInput, 1, 1);
        layout.add(txt_NrOfLitersInput, 1, 3);
        layout.add(btn_Calc, 1, 4);
        layout.add(lbl_AmountDueResult, 1, 5);

        //Add layout to scene, scene to window and show window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

        btn_Calc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Retrieve data from nodes
                double daysRented = Integer.parseInt(txt_DaysRentedInput.getText());
                int kmDriven = Integer.parseInt(txt_KMDrivenInput.getText());
                int nrOfLiters = Integer.parseInt(txt_NrOfLitersInput.getText());
                boolean tankFull = false;
                if(!cbx_TankFull.isSelected()){
                    tankFull = true;
                }

                double totalPrice = calcTotalPrice(daysRented, kmDriven, nrOfLiters, tankFull);

                lbl_AmountDueResult.setText("\u20ac" + String.format("%.2f", totalPrice));

            }
        });



    }

    public double calcTotalPrice(double daysRented, int kmDriven, int nrOfLiters, boolean tankFull){

        double kmPerDay = kmDriven / daysRented;
        double dayPrice = 0;
        double kmPrice = 0;
        double tankNotFullExtra = 0;

        //Check if whole days are entered (not decimal numbers)
        if (daysRented % 1 != 0) {
            daysRented = Math.round(daysRented);
        }
        dayPrice = daysRented * 45;

        //Check if KM per day > 100
        if (kmPerDay > 100){
            kmPrice = (kmPerDay-100) * 0.25;
        }

        //Check if tank was full when returned
        if (!tankFull){
            tankNotFullExtra = nrOfLiters * 2;
        }

        //Return total
        return dayPrice + kmPrice + tankNotFullExtra;


    }

}

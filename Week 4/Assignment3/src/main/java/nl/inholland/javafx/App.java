package nl.inholland.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        //Set window size and title
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("Tic-tac-toe");

        //Create grid layout
        GridPane layout = new GridPane();

        //Set spacing
        layout.setPadding(new Insets(10,10,10,10));
        layout.setHgap(5);
        layout.setVgap(10);

        //Create variables for loop in setUpGame
        int row = 0;
        int col = 0;

        layout = setUpGame(row, col, layout);

        //Create and set scene + show window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();


    }

    public void playGame(){
        Player player = new Player(Symbols.X);
        Player computer = new Player(Symbols.O);

        while(!player.winStatus & !computer.winStatus){
            if (player.winStatus){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setContentText("Player won!");
                alert.showAndWait();
            }
            else if(computer.winStatus){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setContentText("Computer won!");
                alert.showAndWait();
            }


        }
    }

    public GridPane setUpGame(int row, int col, GridPane layout){
        //Create 9 buttons and add them to the grid layout
        for (int i = 0; i < 9; i++){
            Button btn = new Button("_");
            btn.setPrefSize(100, 100);
            Font font = new Font(40);
            btn.setFont(font);

            if (col > 2){
                col = 0;
                row++;
            }
            else if(row > 2){
                break;
            }

            layout.add(btn, row, col);
            col++;
        }
        //Create label and add to grid layout
        Label lbl_currentTurn = new Label("Current turn:");
        layout.addRow(3, lbl_currentTurn);

        return layout;
    }




}




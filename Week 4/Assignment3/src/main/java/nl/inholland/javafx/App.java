package nl.inholland.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import java.util.Objects;
import java.util.Random;

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

        //Create playing field(buttons) array and set up buttons on screen
        Button[] playingField = setUpGame(row, col, layout);

        //Create label and add to grid layout
        Label lbl_currentTurn = new Label("Current turn:");
        layout.addRow(3, lbl_currentTurn);

        //Create and set scene + show window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

        //Start the game
        playGame(playingField, lbl_currentTurn);

    }

    public void playGame(Button[] playingField, Label lbl_currentTurn){
        Player player = new Player(Symbols.X);
        Player computer = new Player(Symbols.O);
        Random rnd = new Random();

        while(!player.winStatus && !computer.winStatus){

            //The players turn
            for(Button btn: playingField){
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        btn.setText(player.symbol.toString());
                    }
                });
            }

            //Checks if there's 3 of the same symbols in a row
            checkWinStatus(playingField, player, computer);

            //Computers turn. Create random index to let the computer click the button on that index
            int rndButton = rnd.nextInt(9);

            if (Objects.equals(playingField[rndButton].getText(), "_")) {
                playingField[rndButton].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        playingField[rndButton].setText(computer.symbol.toString());
                    }
                });
            }

            //Checks if there's 3 of the same symbols in a row
            checkWinStatus(playingField, player, computer);

        }


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (player.winStatus){
            alert.setTitle("You won!");
        }
        else {
            alert.setTitle("You lose...");
        }
        alert.show();
    }

    public Button[] setUpGame(int row, int col, GridPane layout){
        Button[] buttons = new Button[9];

        //Create 9 buttons and add them to the grid layout
        for (int i = 0; i < 9; i++){
            Button btn = new Button("_");
            btn.setPrefSize(100, 100);
            Font font = new Font(40);
            btn.setFont(font);
            buttons[i] = btn;

            if (col > 2){
                col = 0;
                row++;
            }
            else if(row > 2) {
                break;
            }
            layout.add(btn, row, col);
            col++;
        }
        return buttons;
    }

    public void checkWinStatus(Button[] playingField, Player player, Player computer){

        for(int i = 0; i < 9; i++){
            //Check if player won
            calcWinStatus(playingField, player, i);

            //Check if computer won
            calcWinStatus(playingField, computer, i);
        }
    }

    private void calcWinStatus(Button[] playingField, Player player, int i) {
        switch(i){
            case 0:
                //Check horizontal
                if(playingField[i + 1].getText().equals(player.symbol.toString()) && playingField[i + 2].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check vertical
                else if(playingField[i + 3].getText().equals(player.symbol.toString()) && playingField[i + 6].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check diagonal
                else if(playingField[i + 4].getText().equals(player.symbol.toString()) && playingField[i + 8].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                break;

            case 1:
                //Check horizontal
                if(playingField[i - 1].getText().equals(player.symbol.toString()) && playingField[i + 1].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check vertical
                else if(playingField[i + 3].getText().equals(player.symbol.toString()) && playingField[i + 6].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                break;

            case 2:
                //Check horizontal
                if(playingField[i - 2].getText().equals(player.symbol.toString()) && playingField[i - 1].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check vertical
                else if(playingField[i + 3].getText().equals(player.symbol.toString()) && playingField[i + 6].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check diagonal
                else if(playingField[i + 2].getText().equals(player.symbol.toString()) && playingField[i + 4].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                break;

            case 3:
                //Check horizontal
                if(playingField[i + 1].getText().equals(player.symbol.toString()) && playingField[i + 2].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check vertical
                else if(playingField[i - 3].getText().equals(player.symbol.toString()) && playingField[i + 3].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                break;

            case 4:
                //Check horizontal
                if(playingField[i - 1].getText().equals(player.symbol.toString()) && playingField[i + 1].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check vertical
                else if(playingField[i - 3].getText().equals(player.symbol.toString()) && playingField[i + 3].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check diagonal
                else if((playingField[i - 4].getText().equals(player.symbol.toString()) && playingField[i + 4].getText().equals(player.symbol.toString())) || (playingField[i - 2].getText().equals(player.symbol.toString()) && playingField[i + 2].getText().equals(player.symbol.toString()))){
                    player.winStatus = true;
                }
                break;

            case 5:
                //Check horizontal
                if(playingField[i - 1].getText().equals(player.symbol.toString()) && playingField[i - 2].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check vertical
                else if(playingField[i - 3].getText().equals(player.symbol.toString()) && playingField[i + 3].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                break;

            case 6:
                //Check horizontal
                if(playingField[i + 1].getText().equals(player.symbol.toString()) && playingField[i + 2].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check vertical
                else if(playingField[i - 3].getText().equals(player.symbol.toString()) && playingField[i - 6].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check diagonal
                else if(playingField[i - 2].getText().equals(player.symbol.toString()) && playingField[i - 4].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                break;

            case 7:
                //Check horizontal
                if(playingField[i - 1].getText().equals(player.symbol.toString()) && playingField[i + 1].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check vertical
                else if(playingField[i - 3].getText().equals(player.symbol.toString()) && playingField[i - 6].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                break;

            case 8:
                //Check horizontal
                if(playingField[i - 1].getText().equals(player.symbol.toString()) && playingField[i - 2].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check vertical
                else if(playingField[i - 3].getText().equals(player.symbol.toString()) && playingField[i - 6].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                //Check diagonal
                else if(playingField[i - 4].getText().equals(player.symbol.toString()) && playingField[i - 8].getText().equals(player.symbol.toString())){
                    player.winStatus = true;
                }
                break;

        }
    }


}




package nl.inholland.javafx.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ExitConfirm{
    private Stage window;

    public ExitConfirm(Stage application){
        window = new Stage();
        window.setTitle("INFO");
        window.setWidth(600);
        window.setHeight(200);

        GridPane main = setLayout();
        Scene scene = new Scene(main);
        scene.getStylesheets().add("css/style.css");

        window.setScene(scene);
        window.show();

        Button btn_OK = (Button) main.getChildren().get(1);
        Button btn_Cancel = (Button) main.getChildren().get(2);


        btn_OK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                window.close();
                application.close();
            }
        });

        btn_Cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                application.show();
                window.close();
            }
        });

    }

    private GridPane setLayout(){
        //Create new GridPane
        GridPane confirmGrid = new GridPane();
        confirmGrid.setPadding(new Insets(10));
        confirmGrid.setHgap(10);
        confirmGrid.setVgap(10);

        //Create nodes
        Label lbl_Message = new Label("Close the window?");
        lbl_Message.setId("exitConfirmMessage");
        Button btn_OK = new Button("OK");
        btn_OK.getStyleClass().add("exitConfirmButton");
        Button btn_Cancel = new Button("Cancel");
        btn_Cancel.getStyleClass().add("exitConfirmButton");

        confirmGrid.addRow(0, lbl_Message);
        confirmGrid.addRow(1, btn_OK, btn_Cancel);

        return confirmGrid;

    }
}

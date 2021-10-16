package nl.inholland.javafx.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import nl.inholland.javafx.Database.Database;
import nl.inholland.javafx.Models.Movie;
import nl.inholland.javafx.Models.Room;

import java.util.ArrayList;
import java.util.List;

public class ManageShowings extends Window {

    private Scene purchaseTickets;
    private Stage loginWindow;
    private VBox layout;
    private List<Movie> movies;
    private List<String> movieTitles;
    private List<Room> rooms;
    private List<String> roomNames;

    public ManageShowings(Scene purchaseTickets, VBox layout, Database db, Stage loginWindow){

        //Initialize data
        this.loginWindow = loginWindow;
        this.purchaseTickets = purchaseTickets;
        this.layout = layout;
        movies = db.getMovies();
        rooms = db.getRooms();
        movieTitles = new ArrayList<>();
        roomNames = new ArrayList<>();


        Scene scene = new Scene(setLayout());
        window.setScene(scene);

        MenuBar menuBar = (MenuBar) layout.getChildren().get(0);

        //Shows the "Manage Movies" menu when the right menu item is clicked
        menuBar.getMenus().get(0).getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new Alert(Alert.AlertType.INFORMATION, "Here comes the Manage Movies menu! Fun!").show();
            }
        });

        //Shows the "Purchase Tickets" menu when the right menu item is clicked
        menuBar.getMenus().get(0).getItems().get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                window.setScene(purchaseTickets);
            }
        });

        //Shows the about-message when the right menu item is clicked
        menuBar.getMenus().get(1).getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new Alert(Alert.AlertType.INFORMATION, "I made this application. Don't try screwing with it, it will screw back. Promise. \nDoes this count as profanity by the way? I hope not.").show();
            }
        });

        //Create event on logout menu-item
        menuBar.getMenus().get(2).getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Initialize a Stage with the value of the LoginWindow for logging out
                loginWindow.show();
                window.close();
            }
        });


    }

    protected VBox setLayout(){

        MenuBar menuBar = (MenuBar) layout.getChildren().get(0);
        Menu adminMenu = menuBar.getMenus().get(0);
        MenuItem showingsItem = adminMenu.getItems().get(0);
        MenuItem ticketsItem = adminMenu.getItems().get(2);
        showingsItem.setVisible(false);
        ticketsItem.setVisible(true);

        VBox mainVBoxShowings = (VBox) layout.getChildren().get(1);
        Label lbl_ShowingsMenuTitle = (Label) mainVBoxShowings.getChildren().get(0);
        lbl_ShowingsMenuTitle.setText("Manage showings");


        GridPane formGrid = (GridPane) layout.getChildren().get(2);
        formGrid.setVisible(false);

        //Create nodes for Form
        Label lbl_Title = new Label("Movie title");
        Label lbl_Room = new Label("Room");
        Label lbl_Seats = new Label("No. of seats");
        Label lbl_SeatsNr = new Label();
        Label lbl_Start = new Label("Start");
        Label lbl_End = new Label("End");
        Label lbl_EndTime = new Label();
        Label lbl_Price = new Label("Price");
        Label lbl_PriceShow = new Label();

        Button btn_AddShowing = new Button("Add Showing");
        Button btn_Clear = new Button("Clear");

        //Create ComboBox with movie titles
        ComboBox<String> cmb_MovieTitle = new ComboBox<>();
        for (Movie movie: this.movies){
            movieTitles.add(movie.getTitle());
        }
        ObservableList<String> movies = FXCollections.observableArrayList(movieTitles);
        cmb_MovieTitle.setItems(movies);

        //Create ComboBox with rooms
        ComboBox<String> cmb_Room = new ComboBox<>();
        for (Room room: this.rooms){
            roomNames.add(room.getRoomName());
        }
        ObservableList<String> rooms = FXCollections.observableArrayList(roomNames);
        cmb_Room.setItems(rooms);

        DatePicker startDatePicker = new DatePicker();

        TextField txt_StartTime = new TextField();
        txt_StartTime.setPromptText("00:00");

        GridPane formGridAddShowing = new GridPane();
        formGridAddShowing.addRow(0, lbl_Title, cmb_MovieTitle, lbl_Start, startDatePicker, txt_StartTime);
        formGridAddShowing.addRow(1, lbl_Room, cmb_Room, lbl_End, lbl_EndTime, btn_AddShowing);
        formGridAddShowing.addRow(2, lbl_Seats, lbl_SeatsNr, lbl_Price, lbl_PriceShow, btn_Clear);

        formGridAddShowing.setHgap(30);
        formGridAddShowing.setVgap(10);

        layout.getChildren().add(2, formGridAddShowing);
        return layout;
    }
}

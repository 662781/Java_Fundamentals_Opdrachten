package nl.inholland.javafx.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import nl.inholland.javafx.Database.Database;
import nl.inholland.javafx.Models.*;

import java.util.ArrayList;
import java.util.List;

public class ManageShowings extends Window {

    private Scene purchaseTicketsScene;
    private Stage loginWindow;
    private VBox layout;
    private List<Movie> movies;
    private List<Room> rooms;
    private List<String> movieTitles, roomNames;


    public ManageShowings(Scene purchaseTickets, VBox layout, Database db, Stage login, Stage purchaseTicketsWindow, User userLoggedIn){

        //Initialize data
        loginWindow = login;
        this.purchaseTicketsScene = purchaseTickets;
        this.layout = layout;
        movies = db.getMovies();
        rooms = db.getRooms();
        movieTitles = new ArrayList<>();
        roomNames = new ArrayList<>();
        window = purchaseTicketsWindow;

        //Set window title
        window.setTitle("Fabulous Cinema | Manage Showings | " + userLoggedIn.getUsername());

        //Create new layout from setLayout()
        VBox manageShowings = setLayout();


        //Create new scene and add stylesheet
        Scene scene = new Scene(manageShowings);
        scene.getStylesheets().add("css/style.css");

        //Set the scene
        window.setScene(scene);

        //Get the MenuBar from the layout
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
                window.setScene(purchaseTicketsScene);
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
                //Show the LoginWindow for logging out and close this window
                loginWindow.show();
                window.close();
            }
        });


    }

    protected VBox setLayout(){

        //Create new VBox
        VBox manageShowings = layout;

        //Get the MenuBar from the old layout and set the visibility of the MenuItem from the current page to false
        //And set the visibility of the purchase tickets MenuItem to show to enable the user to go back
        MenuBar menuBar = (MenuBar) manageShowings.getChildren().get(0);
        Menu adminMenu = menuBar.getMenus().get(0);
        MenuItem showingsItem = adminMenu.getItems().get(0);
        MenuItem ticketsItem = adminMenu.getItems().get(2);
        showingsItem.setVisible(false);
        ticketsItem.setVisible(true);

        //Get the title Label from the old layout and change the title
        VBox mainVBoxShowings = (VBox) manageShowings.getChildren().get(1);
        Label lbl_ShowingsMenuTitle = (Label) mainVBoxShowings.getChildren().get(0);
        lbl_ShowingsMenuTitle.setText("Manage showings");

        //Removing the old Form GridPane from the layout
        GridPane formGrid = (GridPane) manageShowings.getChildren().get(2);
        manageShowings.getChildren().remove(formGrid);

        //Create new Form GridPane
        GridPane formGridAddShowing = new GridPane();
        formGridAddShowing.setPadding(new Insets(10));
        formGridAddShowing.setHgap(30);
        formGridAddShowing.setVgap(10);
        formGridAddShowing.setId("FormGrid");

        //Create nodes for new Form
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


        //Add nodes to new From GridPane
        formGridAddShowing.addRow(0, lbl_Title, cmb_MovieTitle, lbl_Start, startDatePicker, txt_StartTime);
        formGridAddShowing.addRow(1, lbl_Room, cmb_Room, lbl_End, lbl_EndTime, btn_AddShowing);
        formGridAddShowing.addRow(2, lbl_Seats, lbl_SeatsNr, lbl_Price, lbl_PriceShow, btn_Clear);


        //Add the new Form GridPane to the layout
        manageShowings.getChildren().add(2, formGridAddShowing);

        return manageShowings;
    }
}

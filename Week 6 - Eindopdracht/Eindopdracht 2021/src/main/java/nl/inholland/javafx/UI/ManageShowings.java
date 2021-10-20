package nl.inholland.javafx.UI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ManageShowings extends Window {

    private Scene mainScene;
    private Stage loginWindow;
    private VBox mainLayout;
    private Database db;
    private List<Movie> movies;
    private List<Room> rooms;
    private List<String> movieTitles, roomNames;
    private ObservableList<Showing> showingsListRoom1, showingsListRoom2;


    public ManageShowings(Scene main, VBox layout, Database db, Stage login, Stage purchaseTicketsWindow, User userLoggedIn){

        //Initialize data
        loginWindow = login;
        this.mainScene = main;
        mainLayout = layout;
        this.db = db;
        movies = db.getMovies();
        rooms = db.getRooms();
        movieTitles = new ArrayList<>();
        roomNames = new ArrayList<>();
        window = purchaseTicketsWindow;

        //Set window title
        window.setTitle("Fabulous Cinema | Manage Showings | " + userLoggedIn.getUsername());

        //Create new layout from setLayout()
        VBox manageShowings = setLayout();

        //Remove all children from the root
        mainLayout.getChildren().remove(0);
        mainLayout.getChildren().remove(1);
        mainLayout.getChildren().remove(2);
        mainLayout.getChildren().remove(3);

        //Set the manipulated layout as the root of the scene
//        main.setRoot(manageShowings);

        //Add children of manipulated layout to the Scene root (mainLayout)
        mainLayout.getChildren().set(0,manageShowings.getChildren().get(0));
        mainLayout.getChildren().set(1,manageShowings.getChildren().get(1));
        mainLayout.getChildren().set(2,manageShowings.getChildren().get(2));
        mainLayout.getChildren().set(3,manageShowings.getChildren().get(3));

        //Set the scene
//        window.setScene(mainScene);

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
                mainLayout.getChildren().remove(0);
                mainLayout.getChildren().remove(1);
                mainLayout.getChildren().remove(2);
                mainLayout.getChildren().remove(3);
                mainLayout.getChildren().set(0,layout.getChildren().get(0));
                mainLayout.getChildren().set(1,layout.getChildren().get(1));
                mainLayout.getChildren().set(2,layout.getChildren().get(2));
                mainLayout.getChildren().set(3,layout.getChildren().get(3));
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

        //Get the TableViews from the layout
        VBox mainVBoxTickets = (VBox) manageShowings.getChildren().get(1);
        HBox hBoxTickets = (HBox) mainVBoxTickets.getChildren().get(1);
        VBox showingsRoom1 = (VBox) hBoxTickets.getChildren().get(0);
        VBox showingsRoom2 = (VBox) hBoxTickets.getChildren().get(1);

        TableView<Showing> tv_ShowingsRoom1 = (TableView<Showing>) showingsRoom1.getChildren().get(1);
        TableView<Showing> tv_ShowingsRoom2 = (TableView<Showing>) showingsRoom2.getChildren().get(1);

        //Get the form from the layout
        GridPane formGrid = (GridPane) manageShowings.getChildren().get(2);

        //Get all the nodes needed to add a showing
        ComboBox<String> cmb_MovieTitle = (ComboBox<String>) formGrid.getChildren().get(1);
        ComboBox<String> cmb_Room = (ComboBox<String>) formGrid.getChildren().get(6);

        DatePicker startDate = (DatePicker) formGrid.getChildren().get(3);
        TextField startTime = (TextField) formGrid.getChildren().get(4);

        Button btn_AddShowing = (Button) formGrid.getChildren().get(9);
        Button btn_Clear = (Button) formGrid.getChildren().get(14);

        //Get all the labels from the form
        Label lbl_EndTime = (Label) formGrid.getChildren().get(8);
        Label lbl_Seats = (Label) formGrid.getChildren().get(11);
        Label lbl_TicketPrice = (Label) formGrid.getChildren().get(13);

        //Create listeners on the ComboBoxes

        //Sets the text on the labels when another movie is selected
        cmb_MovieTitle.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldSelection, String newSelection) {
                Movie selectedMovie = null;

                for (Movie movie: movies){
                    if (movie.getTitle().equals(newSelection)){
                        selectedMovie = movie;
                    }
                }

                //Gets the date and time + adds them into a LocalDateTime object
                LocalDate dateStart = startDate.getValue();
                if (!startTime.getText().equals("")){

                    LocalTime timeStart = LocalTime.parse(startTime.getText());
                    LocalDateTime startDateTime = LocalDateTime.of(dateStart, timeStart);

                    //Sets the text of the labels
                    String endTime = startDateTime.plusMinutes(selectedMovie.getDuration()).toString();
                    lbl_EndTime.setText(endTime);
                }
                else{
                    lbl_EndTime.setText("End unknown");
                }

                startTime.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String oldContent, String newContent) {

                    }
                });


                lbl_TicketPrice.setText(String.format("%.2f", selectedMovie.getTicketPrice()));
            }
        });

        //Sets the text on the labels when another room is selected
        cmb_Room.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldSelection, String newSelection) {
                Room selectedRoom = null;

                for (Room room: rooms){
                    if (room.getRoomName().equals(cmb_Room.getValue())){
                        selectedRoom = room;
                    }
                }
                lbl_Seats.setText(Integer.toString(selectedRoom.getAmtOfSeats()));
            }
        });

        //Add a showing to the list when the button is clicked and all conditions are true
        btn_AddShowing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //Checks if a start time is entered
                if (!startTime.getText().equals("")){

                    //Gets the date and time + adds them into a LocalDateTime object
                    LocalDate dateStart = startDate.getValue();
                    LocalTime timeStart = LocalTime.parse(startTime.getText());
                    LocalDateTime startDateTime = LocalDateTime.of(dateStart, timeStart);

                    //Gets the Movie object from the list using the movie title from the ComboBox
                    Movie selectedMovie = null;

                    for (Movie movie: movies){
                        if (movie.getTitle().equals(cmb_MovieTitle.getValue())){
                            selectedMovie = movie;
                        }
                        else{
                            selectedMovie = db.getMovies().get(0);
                        }
                    }

                    //Gets the Room object from the list using the room name from the ComboBox
                    Room selectedRoom = null;

                    for (Room room: rooms){
                        if (room.getRoomName().equals(cmb_Room.getValue())){
                            selectedRoom = room;
                        }
                        else{
                            selectedRoom = db.getRooms().get(0);
                        }
                    }

                    Showing newShowing = new Showing(selectedRoom, selectedMovie, startDateTime);

                    //Checks if the new showing overlaps any other showing in the list
                    boolean overlap = false;

                    //Checks for the selected room
                    for (Showing showing: db.getShowingsPerRoom(selectedRoom.getRoomName())){
                        if (!(newShowing.getStartTime().compareTo(showing.getEndTime().plusMinutes(15)) >= 0) && !(newShowing.getEndTime().compareTo(showing.getStartTime().minusMinutes(15)) <= 0)){
                            overlap = true;
                        }
                        else{
                            break;
                        }

                    }

                    if (!overlap){
                        //Insert new showing into list
                        db.getShowingsPerRoom(selectedRoom.getRoomName()).add(newShowing);
                        //Reload lists to show the new showing in the list
                        if (selectedRoom.getRoomName().equals("Room 1")){
                            showingsListRoom1 = FXCollections.observableArrayList(db.getShowingsRoom1());
                            tv_ShowingsRoom1.setItems(showingsListRoom1);
                        }
                        else{
                            showingsListRoom2 = FXCollections.observableArrayList(db.getShowingsRoom2());
                            tv_ShowingsRoom2.setItems(showingsListRoom2);
                        }
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please enter date and time that don't overlap with other showings in the same room");
                        alert.setTitle("Overlap");
                        alert.show();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please enter a time before submitting");
                    alert.setTitle("Empty field");
                    alert.show();
                }
            }
        });

    }

    protected VBox setLayout(){

        //Create new VBox
        VBox manageShowings = mainLayout;

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
        cmb_MovieTitle.getSelectionModel().selectFirst();

        //Create ComboBox with room names
        ComboBox<String> cmb_Room = new ComboBox<>();
        for (Room room: this.rooms){
            roomNames.add(room.getRoomName());
        }
        ObservableList<String> rooms = FXCollections.observableArrayList(roomNames);
        cmb_Room.setItems(rooms);
        cmb_Room.getSelectionModel().selectFirst();

        DatePicker startDatePicker = new DatePicker(LocalDate.now());

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

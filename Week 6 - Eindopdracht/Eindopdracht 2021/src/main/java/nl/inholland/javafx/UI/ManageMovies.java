package nl.inholland.javafx.UI;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.javafx.Database.Database;
import nl.inholland.javafx.Models.Movie;
import nl.inholland.javafx.Models.User;
import nl.inholland.javafx.UI.Forms.MovieForm;

public class ManageMovies extends Window{

    private ObservableList<Movie> moviesList;

    public ManageMovies(VBox layout, Database db, Stage login, Stage mainWindow, User userLoggedIn, VBox showingsTableView){
        //Initialize data
        loginWindow = login;
        mainLayout = layout;
        this.db = db;
        this.userLoggedIn = userLoggedIn;
        window = mainWindow;

        setupManageMoviesScreen();

        //Get the MenuBar from the layout
        MenuBar menuBar = (MenuBar) layout.getChildren().get(0);

        //Shows the "Manage Showings" menu when the right menu item is clicked
        menuBar.getMenus().get(0).getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new ManageShowings(layout, db, login, mainWindow, userLoggedIn, showingsTableView);
            }
        });

        //Shows the "Purchase Tickets" menu when the right menu item is clicked
        menuBar.getMenus().get(0).getItems().get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new PurchaseTickets(loginWindow, db, userLoggedIn, layout, window);
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

        //Get the addMovie form from the layout
        GridPane movie_Form = (GridPane) layout.getChildren().get(2);

        //Get all the nodes needed to add a movie from the Form GridPane
        TextField txt_Title = (TextField) movie_Form.getChildren().get(0);
        TextField txt_Duration = (TextField) movie_Form.getChildren().get(2);

        ComboBox<Double> cmb_Price = (ComboBox<Double>) movie_Form.getChildren().get(1);

        Button btn_AddMovie = (Button) movie_Form.getChildren().get(3);
        Button btn_Clear = (Button) movie_Form.getChildren().get(4);

        //Get the movie TableView node from the layout
        VBox mainTableVbox = (VBox) layout.getChildren().get(1);
        VBox tableViewMovies = (VBox) mainTableVbox.getChildren().get(1);

        TableView<Movie> tv_Movies = (TableView<Movie>) tableViewMovies.getChildren().get(1);

        btn_AddMovie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!txt_Duration.getText().equals("") && !txt_Title.getText().equals("")){

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to add this movie?");
                    alert.setTitle("Add movie");
                    alert.showAndWait().ifPresent(response -> {
                        if (response != ButtonType.CANCEL){

                            //Create new movie
                            Movie newMovie = new Movie(txt_Title.getText(), cmb_Price.getValue(), Integer.parseInt(txt_Duration.getText()));

                            //Check if movie exists
                            boolean exists = false;

                            for(Movie m: db.getMovies()){
                                if (m.getTitle().equals(newMovie.getTitle())) {
                                    exists = true;
                                    break;
                                }
                            }
                            //Add the new movie to the list if it does not already exist
                            if (!exists){
                                db.getMovies().add(newMovie);
                                moviesList = FXCollections.observableArrayList(db.getMovies());
                                tv_Movies.setItems(moviesList);
                            }
                            //Show an alert when movie already exists
                            else{
                                new Alert(Alert.AlertType.ERROR, "Movie title already exists").show();
                            }

                        }
                    });

                }
            }
        });

        btn_Clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                txt_Duration.clear();
                txt_Title.clear();
            }
        });


    }

    protected void setupManageMoviesScreen(){
        //Get the MenuBar from the layout and set the visibility of the MenuItem from the current page to false
        //And set the visibility of the purchase tickets and manage showings MenuItem to true to enable the user to go switch menus
        MenuBar menuBar = (MenuBar) mainLayout.getChildren().get(0);
        Menu adminMenu = menuBar.getMenus().get(0);
        MenuItem showingsItem = adminMenu.getItems().get(0);
        MenuItem moviesItem = adminMenu.getItems().get(1);
        MenuItem ticketsItem = adminMenu.getItems().get(2);
        moviesItem.setVisible(false);
        showingsItem.setVisible(true);
        ticketsItem.setVisible(true);

        //Set window title
        window.setTitle("Fabulous Cinema | Manage Movies | " + userLoggedIn.getUsername());

        //Get the title Label from the old layout and change the title
        VBox mainVBoxShowings = (VBox) mainLayout.getChildren().get(1);
        Label lbl_ShowingsMenuTitle = (Label) mainVBoxShowings.getChildren().get(0);
        lbl_ShowingsMenuTitle.setText("Manage movies");

        //Remove showingForm and Showings HBox
        mainVBoxShowings.getChildren().remove(1);
        mainLayout.getChildren().remove(2);

        //Create new movieForm
        //Add the movieForm to the layout
        mainLayout.getChildren().add(2, new MovieForm().getMovieForm());

        //Create new Movie TableView
        TableView<Movie> tv_Movies = setUpTableView();
        moviesList = FXCollections.observableArrayList(db.getMovies());
        tv_Movies.setItems(moviesList);

        //Create label
        Label movies_Title = new Label("Movies");
        movies_Title.setId("tableMoviesTitle");

        VBox tvMovieBox = new VBox();
        tvMovieBox.getChildren().addAll(movies_Title, tv_Movies);
        tvMovieBox.setAlignment(Pos.CENTER);

        mainVBoxShowings.getChildren().add(1, tvMovieBox);


    }

    private TableView setUpTableView(){
        TableView<Movie> tableView = new TableView<>();

        tableView.setEditable(true);
        tableView.getSelectionModel().setCellSelectionEnabled(false);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Create Columns
        TableColumn<Movie, String> col_Title = new TableColumn<>("Title");
        col_Title.setMinWidth(200);
        col_Title.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));

        TableColumn<Movie, String> col_TicketPrice = new TableColumn<>("Price (Euro)");
        col_TicketPrice.setMinWidth(100);
        col_TicketPrice.setCellValueFactory(c -> new SimpleStringProperty(String.format("%.2f", c.getValue().getTicketPrice())));

        TableColumn<Movie, String> col_Duration = new TableColumn<>("Duration (Min)");
        col_Duration.setMinWidth(100);
        col_Duration.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getDuration())));

        tableView.getColumns().addAll(col_Title, col_TicketPrice, col_Duration);

        return tableView;
    }

}

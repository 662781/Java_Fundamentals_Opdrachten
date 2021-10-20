package nl.inholland.javafx.UI;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.javafx.Database.Database;
import nl.inholland.javafx.Models.Movie;
import nl.inholland.javafx.Models.Showing;
import nl.inholland.javafx.Models.User;
import nl.inholland.javafx.UI.Forms.MovieForm;
import nl.inholland.javafx.UI.Forms.ShowingForm;

public class ManageMovies extends Window{

    private VBox showingsTableView;

    public ManageMovies(VBox layout, Database db, Stage login, Stage mainWindow, User userLoggedIn, VBox showingsTableView){
        //Initialize data
        loginWindow = login;
        mainLayout = layout;
        this.db = db;
        this.userLoggedIn = userLoggedIn;
        this.showingsTableView = showingsTableView;
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

        //Remove Showings HBox and showingForm
        mainLayout.getChildren().remove(1);
        mainLayout.getChildren().remove(2);

        //Create new movieForm
        MovieForm movieForm = new MovieForm();

        //Add the movieForm to the layout
        mainLayout.getChildren().add(2, movieForm.getMovieForm());

        //Create new TableView
        TableView<Movie> tv_Movies = setUpTableView();

        //Create label
        Label movies_Title = new Label("Movies");
        movies_Title.setId("tableMoviesTitle");

        VBox tvMovieBox = new VBox();
        tvMovieBox.getChildren().addAll(movies_Title, tv_Movies);
        tvMovieBox.setAlignment(Pos.CENTER);

        mainLayout.getChildren().add(1, tvMovieBox);


    }

    private TableView setUpTableView(){
        TableView<Movie> tableView = new TableView<>();

        tableView.setEditable(true);
        tableView.getSelectionModel().setCellSelectionEnabled(false);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Create Columns
        TableColumn<Movie, String> col_Title = new TableColumn<>("Title");
        col_Title.setMinWidth(150);
        col_Title.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));

        TableColumn<Movie, String> col_TicketPrice = new TableColumn<>("Price");
        col_TicketPrice.setMinWidth(150);
        col_TicketPrice.setCellValueFactory(c -> new SimpleStringProperty(Double.toString(c.getValue().getTicketPrice())));

        TableColumn<Movie, String> col_Duration = new TableColumn<>("Duration");
        col_Duration.setMinWidth(200);
        col_Duration.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getDuration())));

        tableView.getColumns().addAll(col_Title, col_TicketPrice, col_Duration);

        return tableView;
    }

}

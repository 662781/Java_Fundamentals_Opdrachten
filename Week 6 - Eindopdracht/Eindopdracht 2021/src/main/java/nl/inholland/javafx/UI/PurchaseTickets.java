package nl.inholland.javafx.UI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.javafx.Database.Database;
import nl.inholland.javafx.Models.Enums.UserType;
import nl.inholland.javafx.Models.Showing;
import nl.inholland.javafx.Models.Ticket;
import nl.inholland.javafx.Models.User;
import nl.inholland.javafx.UI.Forms.TicketForm;

import java.util.ArrayList;
import java.util.List;


public class PurchaseTickets extends Window {

    private ObservableList<Showing> showingsListRoom1, showingsListRoom2;
    private final List<Integer> amountOfSeatsChoices;

    //Constructor for first initialization (new Stage)
    public PurchaseTickets(Stage loginWindow, Database db, User userLoggedIn) {
        //Initializing data
        amountOfSeatsChoices = new ArrayList<>();
        this.db = db;
        this.userLoggedIn = userLoggedIn;
        this.loginWindow = loginWindow;
        window = new Stage();

        //Set window size and title
        window.setHeight(600);
        window.setWidth(1450);
        window.setTitle("Fabulous Cinema | Purchase Tickets | " + userLoggedIn.getUsername());

        //Create layout from method setLayout()
        VBox mainLayout = setLayout();

        //Create scene and add stylesheet
        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add("css/style.css");

        //Set the scene and show window
        window.setScene(scene);
        window.show();

        handleAllActions(mainLayout);

    }

    //Constructor for switching between menus
    public PurchaseTickets(Stage loginWindow, Database db, User userLoggedIn, VBox mainLayout, Stage mainWindow) {
        //Initializing data
        amountOfSeatsChoices = new ArrayList<>();
        this.db = db;
        this.userLoggedIn = userLoggedIn;
        this.loginWindow = loginWindow;
        this.window = mainWindow;

        //Get the MenuBar from the layout and set the visibility of the MenuItem from the current page to false
        //And set the visibility of the purchase tickets MenuItem to show to enable the user to go back
        MenuBar menuBar = (MenuBar) mainLayout.getChildren().get(0);
        Menu adminMenu = menuBar.getMenus().get(0);
        MenuItem showingsItem = adminMenu.getItems().get(0);
        MenuItem moviesItem = adminMenu.getItems().get(1);
        MenuItem ticketsItem = adminMenu.getItems().get(2);
        ticketsItem.setVisible(false);
        showingsItem.setVisible(true);
        moviesItem.setVisible(true);

        //Set window title
        window.setTitle("Fabulous Cinema | Purchase Tickets | " + userLoggedIn.getUsername());

        //Get the title Label from the old layout and change the title
        VBox mainVBoxShowings = (VBox) mainLayout.getChildren().get(1);
        Label lbl_ShowingsMenuTitle = (Label) mainVBoxShowings.getChildren().get(0);
        lbl_ShowingsMenuTitle.setText("Purchase Tickets");

        //Remove showingForm and add ticketForm
        mainLayout.getChildren().remove(2);
        mainLayout.getChildren().add(2, new TicketForm(userLoggedIn, new ArrayList<>()).getTicketForm());

        //Remove potential Movie TableView and add Showings TableView
        VBox showingsMain = (VBox) setLayout().getChildren().get(1);
        HBox showingsHBox = (HBox) showingsMain.getChildren().get(1);
        mainVBoxShowings.getChildren().remove(1);
        mainVBoxShowings.getChildren().add(1, showingsHBox);

        handleAllActions(mainLayout);

    }

    protected VBox setLayout() {

        //Create and setup Vertical Main Layout
        VBox layout = new VBox();

        //Create top MenuBar
        MenuBar menuBar = new MenuBar();

        //Create Menus
        Menu adminMenu = new Menu("Admin");
        Menu helpMenu = new Menu("Help");
        Menu logoutMenu = new Menu("Logout");

        //Create MenuItems
        MenuItem aboutItem = new MenuItem("About");
        MenuItem logoutItem = new MenuItem("Logout...");
        MenuItem showingsItem = new MenuItem("Manage Showings");
        MenuItem moviesItem = new MenuItem("Manage Movies");
        MenuItem ticketsItem = new MenuItem("Purchase Tickets");

        //Add the MenuItems to the Menus
        adminMenu.getItems().addAll(showingsItem, moviesItem, ticketsItem);
        helpMenu.getItems().addAll(aboutItem);
        logoutMenu.getItems().addAll(logoutItem);

        //Add all the Menus to the MenuBar
        menuBar.getMenus().addAll(adminMenu, helpMenu, logoutMenu);

        //Hide the menu of the current page
        menuBar.getMenus().get(0).getItems().get(2).setVisible(false);


        //Create center part of the window

        //Create main VBox for this part
        VBox mainVBoxShowings = new VBox();
        mainVBoxShowings.setPadding(new Insets(10));

        //Create Label with title and add to VBox
        Label lbl_titleTickets = new Label("Purchase Tickets");
        lbl_titleTickets.setId("ticketsTitle");
        mainVBoxShowings.getChildren().addAll(lbl_titleTickets);

        //Create secondary Node (HBox) for the 2 TableViews
        HBox hBoxShowings = new HBox();

        hBoxShowings.setPadding(new Insets(10));
        hBoxShowings.setSpacing(10);

        //Create 2 HBoxes for the 2 TableViews
        VBox showingTableRoom1 = new VBox();
        VBox showingTableRoom2 = new VBox();

        //Creat Labels
        Label lbl_tableRoom1Title = new Label("Room 1");
        lbl_tableRoom1Title.setId("tableShowingsTitle");
        Label lbl_tableRoom2Title = new Label("Room 2");
        lbl_tableRoom2Title.setId("tableShowingsTitle");


        //Create TableView for Room1, setting it up and adding to the HBox
        TableView<Showing> tv_Showings = setUpTableView();

        showingsListRoom1 = FXCollections.observableArrayList(db.getShowingsRoom1());
        tv_Showings.setItems(showingsListRoom1);

        showingTableRoom1.getChildren().addAll(lbl_tableRoom1Title, tv_Showings);

        //Create TableView for Room2, setting it up and adding to the HBox

        TableView<Showing> tv_ShowingsRoom2 = setUpTableView();

        showingsListRoom2 = FXCollections.observableArrayList(db.getShowingsRoom2());
        tv_ShowingsRoom2.setItems(showingsListRoom2);

        showingTableRoom2.getChildren().addAll(lbl_tableRoom2Title, tv_ShowingsRoom2);

        //Add the 2 sub HBoxes (TableViews) to the Ticket HBox
        hBoxShowings.getChildren().addAll(showingTableRoom1, showingTableRoom2);
        hBoxShowings.setPadding(new Insets(10, 10, 10, 10));
        hBoxShowings.setId("HBoxTickets");

        mainVBoxShowings.getChildren().addAll(hBoxShowings);

        //Gets the ticketForm from the TicketForm class
        TicketForm ticketForm = new TicketForm(userLoggedIn, amountOfSeatsChoices);
        GridPane formGridPane = ticketForm.getTicketForm();

        //Creat Node (Label) for stripe at the bottom
        Label lbl_Stripe = new Label();
        lbl_Stripe.setPrefSize(window.getWidth(), 20);
        lbl_Stripe.setId("detailLine");


        //Add all nodes to the main layout
        layout.getChildren().addAll(menuBar, mainVBoxShowings, formGridPane, lbl_Stripe);

        return layout;
    }

    private TableView setUpTableView() {
        TableView<Showing> tableView = new TableView<>();

        tableView.setEditable(true);
        tableView.getSelectionModel().setCellSelectionEnabled(false);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Create Columns
        TableColumn<Showing, String> col_StartTime = new TableColumn<>("Start");
        col_StartTime.setMinWidth(150);
        col_StartTime.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStartTime().toString()));

        TableColumn<Showing, String> col_EndTime = new TableColumn<>("End");
        col_EndTime.setMinWidth(150);
        col_EndTime.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEndTime().toString()));

        TableColumn<Showing, String> col_Title = new TableColumn<>("Title");
        col_Title.setMinWidth(200);
        col_Title.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMovie().getTitle()));

        TableColumn<Showing, String> col_Seats = new TableColumn<>("Seats");
        col_Seats.setMinWidth(50);
        col_Seats.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getRoom().getAmtOfSeats())));

        TableColumn<Showing, String> col_TicketsAvailable = new TableColumn<>("Tickets left");
        col_TicketsAvailable.setMinWidth(50);
        col_TicketsAvailable.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getTicketsAvailable())));

        TableColumn<Showing, String> col_Price = new TableColumn<>("Price");
        col_Price.setMinWidth(50);
        col_Price.setCellValueFactory(c -> new SimpleStringProperty(String.format("%.2f", c.getValue().getMovie().getTicketPrice())));

        tableView.getColumns().addAll(col_StartTime, col_EndTime, col_Title, col_Seats, col_TicketsAvailable, col_Price);

        return tableView;
    }

    private void handleMenuActions(VBox layout) {
        //Get all the nodes needed to hide from the layout for the user
        MenuBar menuBar = (MenuBar) layout.getChildren().get(0);

        if (userLoggedIn.getUserType() == UserType.USER) {
            //Hide the Admin menu for the user
            Menu adminMenu = menuBar.getMenus().get(0);
            adminMenu.setVisible(false);
        }

        //Shows the "Manage Showings" menu when the right menu item is clicked
        menuBar.getMenus().get(0).getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                new ManageShowings(layout, db, loginWindow, window, userLoggedIn, (VBox) setLayout().getChildren().get(1));
                //new Alert(Alert.AlertType.INFORMATION, "Here comes the Manage Showings menu! But not yet...").show();
            }
        });

        //Shows the "Manage Movies" menu when the right menu item is clicked
        menuBar.getMenus().get(0).getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new ManageMovies(layout, db, loginWindow, window, userLoggedIn, (VBox) setLayout().getChildren().get(1));
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

    private void handleTableViewActions(VBox layout) {

        //Get the buttons from the Form GridPane
        GridPane formGrid = (GridPane) layout.getChildren().get(2);
        Button btn_Purchase = (Button) formGrid.getChildren().get(8);
        Button btn_Clear = (Button) formGrid.getChildren().get(13);

        //Get the labels and text field from the Form GridPane
        Label lbl_RoomNr = (Label) formGrid.getChildren().get(1);
        Label lbl_Title = (Label) formGrid.getChildren().get(3);
        Label lbl_StartTime = (Label) formGrid.getChildren().get(5);
        Label lbl_EndTime = (Label) formGrid.getChildren().get(10);
        TextField txt_CustomerName = (TextField) formGrid.getChildren().get(12);

        //Get the ComboBox from the Form GridPane
        ComboBox<Integer> cmb_AmtOfSeats = (ComboBox<Integer>) formGrid.getChildren().get(7);

        //Get the TableViews from the layout
        VBox mainVBoxTickets = (VBox) layout.getChildren().get(1);
        HBox hBoxTickets = (HBox) mainVBoxTickets.getChildren().get(1);
        VBox showingsRoom1 = (VBox) hBoxTickets.getChildren().get(0);
        VBox showingsRoom2 = (VBox) hBoxTickets.getChildren().get(1);

        TableView<Showing> tv_ShowingsRoom1 = (TableView<Showing>) showingsRoom1.getChildren().get(1);
        TableView<Showing> tv_ShowingsRoom2 = (TableView<Showing>) showingsRoom2.getChildren().get(1);

        //Create listeners on the selected items from the TableViews

        //Shows form to purchase tickets after an item in the TableView for Room 1 is clicked
        tv_ShowingsRoom1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Showing>() {
            @Override
            public void changed(ObservableValue<? extends Showing> observableValue, Showing oldShowing, Showing newShowing) {
                //Show and fill the form
                formGrid.setVisible(true);
                lbl_RoomNr.setText(newShowing.getRoom().getRoomName());
                lbl_Title.setText(newShowing.getMovie().getTitle());
                lbl_StartTime.setText(newShowing.getStartTime().toString());
                lbl_EndTime.setText(newShowing.getEndTime().toString());

                //Shows a confirmation message that the purchase is complete after the button is clicked and the user did not click "cancel"
                btn_Purchase.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (tv_ShowingsRoom1.getSelectionModel().selectedItemProperty() != null) {
                            //Checks if the Text Field is not empty
                            if (!txt_CustomerName.getText().equals("")) {
                                //Shows an Alert to ask for confirmation
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to buy a ticket?");
                                alert.setTitle("Confirm purchase");
                                alert.showAndWait().ifPresent(response -> {
                                    if (response != ButtonType.CANCEL) {
                                        //Create new Ticket
                                        Ticket ticket = new Ticket(newShowing, txt_CustomerName.getText(), cmb_AmtOfSeats.getValue());

                                        //Show alert to confirm purchase
                                        Alert alertConfirm = new Alert(Alert.AlertType.INFORMATION, "Enjoy the movie! Double-Click Tickets Left to see changes");
                                        alertConfirm.setTitle("Purchase complete");
                                        alertConfirm.showAndWait();

                                        //Add the ticket to the ticket list of the user that is logged in
                                        userLoggedIn.addTicket(ticket);

                                        //Hide the form
                                        formGrid.setVisible(false);

                                        //Subtract the amount of purchased seats of the total amount of available tickets
                                        newShowing.setTicketsAvailable(newShowing.getTicketsAvailable() - cmb_AmtOfSeats.getValue());
                                    }
                                });
                            } else {
                                Alert alertEmptyField = new Alert(Alert.AlertType.INFORMATION, "Please enter your name before purchasing a ticket!");
                                alertEmptyField.setTitle("Empty field");
                                alertEmptyField.show();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please select a showing before submitting!");
                            alert.setTitle("No showing selected");
                            alert.show();
                        }
                    }
                });

                //Reload the list to show the changes
//                showingsListRoom1 = FXCollections.observableArrayList(db.getShowingsRoom1());
//                tv_ShowingsRoom1.setItems(showingsListRoom1);
//                //new Alert(Alert.AlertType.INFORMATION, "List reloaded").show();
            }
        });

        //Shows form to purchase tickets after an item in the TableView for Room 2 is clicked
        tv_ShowingsRoom2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Showing>() {
            @Override
            public void changed(ObservableValue<? extends Showing> observableValue, Showing oldShowing, Showing newShowing) {
                //Show and fill the form
                formGrid.setVisible(true);
                lbl_RoomNr.setText(newShowing.getRoom().getRoomName());
                lbl_Title.setText(newShowing.getMovie().getTitle());
                lbl_StartTime.setText(newShowing.getStartTime().toString());
                lbl_EndTime.setText(newShowing.getEndTime().toString());

                //Shows a confirmation message that the purchase is complete after the button is clicked
                btn_Purchase.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        if (tv_ShowingsRoom2.getSelectionModel().selectedItemProperty() != null) {
                            //Checks if the Text Field is not empty
                            if (!txt_CustomerName.getText().equals("")) {

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to buy a ticket?");
                                alert.setTitle("Confirm purchase");
                                alert.showAndWait().ifPresent(response -> {
                                    if (response != ButtonType.CANCEL) {
                                        //Create new Ticket
                                        Ticket ticket = new Ticket(newShowing, txt_CustomerName.getText(), cmb_AmtOfSeats.getValue());

                                        //Show alert to confirm purchase
                                        Alert alertConfirm = new Alert(Alert.AlertType.INFORMATION, "Enjoy the movie! Double-Click Tickets Left to see changes");
                                        alertConfirm.setTitle("Purchase complete");
                                        alertConfirm.showAndWait();

                                        //Add the ticket to the ticket list of the user that is logged in
                                        userLoggedIn.addTicket(ticket);

                                        formGrid.setVisible(false);

                                        //Subtract the amount of purchased seats of the total amount of available tickets
                                        newShowing.setTicketsAvailable(newShowing.getTicketsAvailable() - cmb_AmtOfSeats.getValue());

//                                        //Reload the list to show the changes
//                                        showingsListRoom2 = FXCollections.observableArrayList(db.getShowingsRoom2());
//                                        tv_ShowingsRoom2.setItems(showingsListRoom2);
                                    }
                                });
                            } else {
                                Alert alertEmptyField = new Alert(Alert.AlertType.INFORMATION, "Please enter your name before purchasing a ticket!");
                                alertEmptyField.setTitle("Empty field");
                                alertEmptyField.show();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please select a showing before submitting!");
                            alert.setTitle("No showing selected");
                            alert.show();
                        }


                    }
                });
            }
        });

        //Clears all the labels and text fields in the form when the button is clicked and hides the form
        btn_Clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lbl_RoomNr.setText("");
                lbl_Title.setText("");
                lbl_StartTime.setText("");
                lbl_EndTime.setText("");
                txt_CustomerName.clear();
                formGrid.setVisible(false);
            }
        });
    }

    public void handleAllActions(VBox layout) {
        //Get the Form GridPane
        GridPane formGrid = (GridPane) layout.getChildren().get(2);

        //Hide the form
        formGrid.setVisible(false);

        //Handle all TableView and Button actions
        handleTableViewActions(layout);

        //Handle all Menu actions
        handleMenuActions(layout);
    }
}

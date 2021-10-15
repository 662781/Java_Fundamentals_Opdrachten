package nl.inholland.javafx.UI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.javafx.Database.Database;
import nl.inholland.javafx.Models.*;
import nl.inholland.javafx.Models.Enums.UserType;

import java.util.ArrayList;
import java.util.List;


public class PurchaseTickets extends Window{

    private User userLoggedIn;
    private Database db;
    private ObservableList<Showing> showingsRoom1;
    private ObservableList<Showing> showingsRoom2;
    private List<Integer> amountOfSeatsChoices;

    public PurchaseTickets(Stage loginWindow, Database db, User userLoggedIn){
        //Initializing data
        showingsRoom1 = FXCollections.observableArrayList(db.getShowingsRoom1());
        showingsRoom2 = FXCollections.observableArrayList(db.getShowingsRoom2());
        amountOfSeatsChoices = new ArrayList<>();
        this.db = db;
        this.userLoggedIn = userLoggedIn;
        window = new Stage();

        //Set window size and title
        window.setHeight(600);
        window.setWidth(1300);
        window.setTitle("Fabulous Cinema | Purchase Tickets | " + userLoggedIn.getUsername());

        //Create layout from method setLayout()
        VBox layout = setLayout();

        //Create scene and add stylesheet
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("css/style.css");

        //Set the scene and show window
        window.setScene(scene);
        window.show();

        //Get all the nodes needed to hide from the layout for the user
        MenuBar menuBar = (MenuBar) layout.getChildren().get(0);

        if (userLoggedIn.getUserType() == UserType.USER){
            //Hide the Admin menu for the user
            Menu adminMenu = menuBar.getMenus().get(0);
            adminMenu.setVisible(false);
        }

        //Get the buttons from the FormGrid
        GridPane formGrid = (GridPane) layout.getChildren().get(2);
        Button btn_Purchase = (Button) formGrid.getChildren().get(8);
        Button btn_Clear = (Button) formGrid.getChildren().get(13);

        //Get the labels and text field from the FormGrid
        Label lbl_RoomNr = (Label) formGrid.getChildren().get(1);
        Label lbl_Title = (Label) formGrid.getChildren().get(3);
        Label lbl_StartTime = (Label) formGrid.getChildren().get(5);
        Label lbl_EndTime = (Label) formGrid.getChildren().get(10);
        TextField txt_CustomerName = (TextField) formGrid.getChildren().get(12);

        //Create events for the buttons
        btn_Purchase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //Show alert to confirm purchase
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Purchase complete");
                alert.setHeaderText("Enjoy the movie!");
                alert.setHeight(50);
                alert.setWidth(50);
                alert.showAndWait();
            }
        });

        //Get the TableViews from the layout
        VBox mainVBoxTickets = (VBox) layout.getChildren().get(1);
        HBox hBoxTickets = (HBox) mainVBoxTickets.getChildren().get(1);
        VBox showingsRoom1 = (VBox) hBoxTickets.getChildren().get(0);
        VBox showingsRoom2 = (VBox) hBoxTickets.getChildren().get(1);

        TableView<Showing> tv_ShowingsRoom1 = (TableView<Showing>) showingsRoom1.getChildren().get(1);
        TableView<Showing> tv_ShowingsRoom2 = (TableView<Showing>) showingsRoom2.getChildren().get(1);

        //Create listeners on the selected items from the TableViews
        tv_ShowingsRoom1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Showing>() {
            @Override
            public void changed(ObservableValue<? extends Showing> observableValue, Showing oldShowing, Showing newShowing) {
                    lbl_RoomNr.setText(newShowing.getRoom().getRoomName());
                    lbl_Title.setText(newShowing.getMovie().getTitle());
                    lbl_StartTime.setText(newShowing.getStartTime().toString());
                    lbl_EndTime.setText(newShowing.getEndTime().toString());
            }
        });

        tv_ShowingsRoom2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Showing>() {
            @Override
            public void changed(ObservableValue<? extends Showing> observableValue, Showing oldShowing, Showing newShowing) {
                lbl_RoomNr.setText(newShowing.getRoom().getRoomName());
                lbl_Title.setText(newShowing.getMovie().getTitle());
                lbl_StartTime.setText(newShowing.getStartTime().toString());
                lbl_EndTime.setText(newShowing.getEndTime().toString());
            }
        });

        //Clears all the labels and text fields in the form when the button is clicked
        btn_Clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lbl_RoomNr.setText("");
                lbl_Title.setText("");
                lbl_StartTime.setText("");
                lbl_EndTime.setText("");
                txt_CustomerName.clear();
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

        //Shows the "Manage Showings" menu when the right menu item is clicked
        menuBar.getMenus().get(0).getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new Alert(Alert.AlertType.INFORMATION, "Here comes the Manage Showings menu! But not yet...").show();
            }
        });
        //Shows the "Manage Movies" menu when the right menu item is clicked
        menuBar.getMenus().get(0).getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new Alert(Alert.AlertType.INFORMATION, "Here comes the Manage Movies menu! Fun!").show();
            }
        });
        //Shows the about-message when the right menu item is clicked
        menuBar.getMenus().get(1).getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new Alert(Alert.AlertType.INFORMATION, "I made this application. Don't try screwing with it, it will screw back. Promise. \nDoes this count as profanity by the way? I hope not.").show();
            }
        });

    }

    @Override
    protected VBox setLayout(){

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

        //Add the MenuItems to the Menus
        adminMenu.getItems().addAll(showingsItem, moviesItem);
        helpMenu.getItems().addAll(aboutItem);
        logoutMenu.getItems().addAll(logoutItem);

        //Add all the Menus to the MenuBar
        menuBar.getMenus().addAll(adminMenu, helpMenu, logoutMenu);


        //Create center part of the window

        //Create main VBox for this part
        VBox mainVBoxTickets = new VBox();
        mainVBoxTickets.setPadding(new Insets(10));

        //Create Label with title and add to VBox
        Label lbl_titleTickets = new Label("Purchase Tickets");
        lbl_titleTickets.setId("ticketsTitle");
        mainVBoxTickets.getChildren().addAll(lbl_titleTickets);

        //Create secondary Node (HBox) for the 2 TableViews
        HBox hBoxTickets = new HBox();

        hBoxTickets.setPadding(new Insets(10));
        hBoxTickets.setSpacing(10);

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

        tv_Showings.setItems(showingsRoom1);

        showingTableRoom1.getChildren().addAll(lbl_tableRoom1Title, tv_Showings);

        //Create TableView for Room2, setting it up and adding to the HBox

        TableView<Showing> tv_ShowingsRoom2 = setUpTableView();

        tv_ShowingsRoom2.setItems(showingsRoom2);

        showingTableRoom2.getChildren().addAll(lbl_tableRoom2Title, tv_ShowingsRoom2);

        //Add the 2 sub HBoxes (TableViews) to the Ticket HBox
        hBoxTickets.getChildren().addAll(showingTableRoom1, showingTableRoom2);
        hBoxTickets.setPadding(new Insets(10, 10, 10, 10));
        hBoxTickets.setId("HBoxTickets");

        mainVBoxTickets.getChildren().addAll(hBoxTickets);


        //Create secondary Node (GridPane) for form
        GridPane formGridPane = new GridPane();

        formGridPane.setPadding(new Insets(10));
        formGridPane.setHgap(30);
        formGridPane.setVgap(10);
        formGridPane.setId("FormGrid");

        //Create Nodes

        //Create all Labels
        Label lbl_Room = new Label("Room");
        Label lbl_RoomNumber = new Label();
        Label lbl_Start = new Label("Start");
        Label lbl_StartTime = new Label();
        Label lbl_End = new Label("End");
        Label lbl_EndTime = new Label();
        Label lbl_Title = new Label("Movie title");
        Label lbl_ShowTitle = new Label();
        Label lbl_NrOfSeats = new Label("No. of seats");
        Label lbl_Name = new Label("Name");

        //Create ComboBox
        ComboBox<Integer> cmb_NrOfSeats = new ComboBox<>();
        amountOfSeatsChoices.add(1);
        amountOfSeatsChoices.add(2);
        amountOfSeatsChoices.add(3);
        amountOfSeatsChoices.add(4);
        amountOfSeatsChoices.add(5);
        ObservableList<Integer> seats = FXCollections.observableArrayList(amountOfSeatsChoices);
        cmb_NrOfSeats.setItems(seats);
        cmb_NrOfSeats.getSelectionModel().selectFirst();

        //Create TextField
        TextField txt_customerName = new TextField();
        txt_customerName.setPromptText("Username");
        txt_customerName.setText(userLoggedIn.getUsername());

        //Create Buttons
        Button btn_Purchase = new Button("Purchase");
        Button btn_Clear = new Button("Clear");

        formGridPane.addRow(0, lbl_Room, lbl_RoomNumber, lbl_Title, lbl_ShowTitle);
        formGridPane.addRow(1, lbl_Start, lbl_StartTime, lbl_NrOfSeats, cmb_NrOfSeats, btn_Purchase);
        formGridPane.addRow(2, lbl_End, lbl_EndTime, lbl_Name, txt_customerName, btn_Clear);

        //Creat Node (Label) for stripe at the bottom
        Label lbl_Stripe = new Label();
        lbl_Stripe.setPrefSize(window.getWidth(), 20);
        lbl_Stripe.setId("detailLine");


        //Add all nodes to the main layout
        layout.getChildren().addAll(menuBar, mainVBoxTickets, formGridPane, lbl_Stripe);

        return layout;
    }

    private TableColumn createColumn(String header, String value, int minWidth){
        TableColumn col = new TableColumn(header);
        col.setMinWidth(minWidth);
        col.setCellValueFactory(new PropertyValueFactory<Showing, String>(value));

        return col;
    }

    private TableView setUpTableView(){
        TableView<Showing> tableView = new TableView<>();

        tableView.setEditable(true);
        tableView.getSelectionModel().setCellSelectionEnabled(false);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Create Columns from within the Showings object
        TableColumn col_StartTime = createColumn("Start", "startTime", 150);
        TableColumn col_EndTime = createColumn("End", "endTime", 150);

        //Create Columns from outside the Showings object
        TableColumn<Showing, String> col_Title = new TableColumn<>("Title");
        col_Title.setMinWidth(200);
        col_Title.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMovie().getTitle()));

        TableColumn<Showing, String> col_Seats = new TableColumn<>("Seats");
        col_Seats.setMinWidth(50);
        col_Seats.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getRoom().getAmtOfSeats())));

        TableColumn<Showing, String> col_Price = new TableColumn<>("Price");
        col_Price.setMinWidth(50);
        col_Price.setCellValueFactory(c -> new SimpleStringProperty(String.format("%.2f", c.getValue().getMovie().getTicketPrice())));

        tableView.getColumns().addAll(col_StartTime, col_EndTime, col_Title, col_Seats, col_Price);

        return tableView;
    }
}

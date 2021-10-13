package nl.inholland.javafx.UI;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
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

    private Stage loginWindow;
    private Database db;
    private ObservableList<Showing> showingsRoom1;
    private ObservableList<Showing> showingsRoom2;
    private List<Integer> amountOfSeatsChoices;

    public PurchaseTickets(Stage loginWindow, Database db, User userLoggedIn){
        //Initializing data

        showingsRoom1 = FXCollections.observableArrayList(db.getShowingsRoom1());
        showingsRoom2 = FXCollections.observableArrayList(db.getShowingsRoom2());
        amountOfSeatsChoices = new ArrayList<>();

        //Initialize a Stage with the value of the LoginWindow for logging out
        this.loginWindow = loginWindow;
        this.db = db;

        window = new Stage();

        //Set window size and title
        window.setHeight(600);
        window.setWidth(800);
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
            menuBar.getMenus().get(0).hide();
        }


    }

    @Override
    protected VBox setLayout(){

        //Create Vertical Main Layout
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

        //Create Label with title and add to VBox
        Label lbl_titleTickets = new Label("Purchase Tickets");
        mainVBoxTickets.getChildren().addAll(lbl_titleTickets);

        //Create secondary Node (HBox) for the 2 TableViews
        HBox hBoxTickets = new HBox();

        //Create 2 HBoxes for the 2 TableViews
        HBox showingTableRoom1 = new HBox();
        HBox showingTableRoom2 = new HBox();

        //Create TableView for Room1, setting it up and adding to HBox
        TableView<Showing> tv_Showings = setUpTableView();

        //Create Columns from within the Showings object
        TableColumn col_StartTime = createColumn("Start", "startTime", 100);
        TableColumn col_EndTime = createColumn("End", "endTime", 100);

        //Create Columns from outside the Showings object
        TableColumn<Showing, String> col_Title = new TableColumn<>("Title");
        col_Title.setMinWidth(150);
        col_Title.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMovie().getTitle()));

        TableColumn<Showing, String> col_Seats = new TableColumn<>("Seats");
        col_Seats.setMinWidth(50);
        col_Seats.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getRoom().getAmtOfSeats())));

        TableColumn<Showing, String> col_Price = new TableColumn<>("Price");
        col_Price.setMinWidth(50);
        col_Price.setCellValueFactory(c -> new SimpleStringProperty(Double.toString(c.getValue().getMovie().getTicketPrice())));

        tv_Showings.getColumns().addAll(col_StartTime, col_EndTime, col_Title, col_Seats, col_Price);

        tv_Showings.setItems(showingsRoom1);

        showingTableRoom1.getChildren().add(tv_Showings);

        //Create TableView for Room2, setting it up and adding to HBox

        tv_Showings.setItems(showingsRoom2);

        showingTableRoom2.getChildren().add(tv_Showings);

        //Add the 2 sub HBoxes (TableViews) to the Ticket HBox
        hBoxTickets.getChildren().addAll(showingTableRoom1, showingTableRoom2);


        //Create secondary Node (GridPane) for form
        GridPane formGridPane = new GridPane();

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
        amountOfSeatsChoices.add(50);
        amountOfSeatsChoices.add(100);
        amountOfSeatsChoices.add(200);
        amountOfSeatsChoices.add(400);
        ObservableList<Integer> seats = FXCollections.observableArrayList(amountOfSeatsChoices);
        cmb_NrOfSeats.setItems(seats);

        //Create TextField
        TextField txt_customerName = new TextField();
        txt_customerName.setPromptText("Please insert your full name");

        //Create Buttons
        Button btn_Purchase = new Button("Purchase");
        Button btn_Clear = new Button("Clear");

        formGridPane.addRow(0, lbl_Room, lbl_RoomNumber, lbl_Title, lbl_ShowTitle);
        formGridPane.addRow(1, lbl_Start, lbl_StartTime, lbl_NrOfSeats, cmb_NrOfSeats, btn_Purchase);
        formGridPane.addRow(2, lbl_End, lbl_EndTime, lbl_Name, txt_customerName, btn_Clear);

        //Creat Node (HBox) for stripe at the bottom
        HBox stripe = new HBox();
        stripe.getStyleClass().add("detailLine");


        //Add all nodes to the main layout
        layout.getChildren().addAll(menuBar, mainVBoxTickets, formGridPane, stripe);

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

        return tableView;
    }
}

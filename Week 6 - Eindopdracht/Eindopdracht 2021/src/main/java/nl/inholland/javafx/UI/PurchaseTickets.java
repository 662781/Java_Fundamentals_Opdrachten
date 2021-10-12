package nl.inholland.javafx.UI;

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

import java.util.ArrayList;
import java.util.List;


public class PurchaseTickets extends Window{

    private Stage loginWindow;
    private Database db;
    private ObservableList<Showing> showingsRoom1;
    private ObservableList<Showing> showingsRoom2;
    private List<Integer> amountOfSeatsChoices;

    public PurchaseTickets(Stage loginWindow, Database db, Person personLoggedIn){
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
        window.setTitle("Fabulous Cinema | Purchase Tickets");

        //Create layout from method setLayout()
        VBox layout = setLayout();

        //Create scene and add stylesheet
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("css/style.css");

        //Set the scene and show window
        window.setScene(scene);
        window.show();
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

        //Create TableView for Room1 and setting it up
        TableView<Showing> tv_ShowingsRoom1 = setUpTableView();

        //Create Columns
        TableColumn col_StartTimeR1 = createColumn("Start", "startTime", 100);
        TableColumn col_EndTimeR1 = createColumn("End", "endTime", 100);
        TableColumn col_TitleR1 = createColumn("Title", db.getRooms().get(0).getShowingList().get(0).getMovie().getTitle(), 100);
        TableColumn col_SeatsR1 = createColumn("Seats", Integer.toString(db.getRooms().get(0).getShowingList().get(0).getMovie().getAmtOfSeats()), 100);
        TableColumn col_PriceR1 = createColumn("Price", Double.toString(db.getRooms().get(0).getShowingList().get(0).getMovie().getTicketPrice()), 100);

        tv_ShowingsRoom1.getColumns().addAll(col_StartTimeR1, col_EndTimeR1, col_TitleR1, col_SeatsR1, col_PriceR1);

        tv_ShowingsRoom1.setItems(showingsRoom1);

        //Create TableView for Room2 and setting it up
        TableView<Showing> tv_ShowingsRoom2 = setUpTableView();

        //Create Columns
        TableColumn col_StartTimeR2 = createColumn("Start", "startTime", 100);
        TableColumn col_EndTimeR2 = createColumn("End", "endTime", 100);
        TableColumn col_TitleR2 = createColumn("Title", db.getRooms().get(1).getShowingList().get(0).getMovie().getTitle(), 100);
        TableColumn col_SeatsR2 = createColumn("Seats", Integer.toString(db.getRooms().get(1).getShowingList().get(0).getMovie().getAmtOfSeats()), 100);
        TableColumn col_PriceR2 = createColumn("Price", Double.toString(db.getRooms().get(1).getShowingList().get(0).getMovie().getTicketPrice()), 100);

        tv_ShowingsRoom2.getColumns().addAll(col_StartTimeR2, col_EndTimeR2, col_TitleR2, col_SeatsR2, col_PriceR2);

        tv_ShowingsRoom2.setItems(showingsRoom2);

        //Add the 2 TableViews to the HBox
        hBoxTickets.getChildren().addAll(tv_ShowingsRoom1, tv_ShowingsRoom2);


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

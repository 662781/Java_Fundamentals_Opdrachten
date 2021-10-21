package nl.inholland.javafx.UI.Forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nl.inholland.javafx.Models.User;

import java.util.List;

public class TicketForm extends Form {

    private final User userLoggedIn;
    private final List<Integer> amountOfSeatsChoices;

    public TicketForm(User userLoggedIn, List<Integer> amountOfSeatsChoices) {
        this.userLoggedIn = userLoggedIn;
        this.amountOfSeatsChoices = amountOfSeatsChoices;
        form = createForm();
    }

    public GridPane getTicketForm() {
        return form;
    }

    @Override
    public GridPane createForm() {
        //Create and setup ticketForm
        GridPane ticketForm = new GridPane();

        ticketForm.setPadding(new Insets(10));
        ticketForm.setHgap(30);
        ticketForm.setVgap(10);
        ticketForm.setId("FormGrid");

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

        ticketForm.addRow(0, lbl_Room, lbl_RoomNumber, lbl_Title, lbl_ShowTitle);
        ticketForm.addRow(1, lbl_Start, lbl_StartTime, lbl_NrOfSeats, cmb_NrOfSeats, btn_Purchase);
        ticketForm.addRow(2, lbl_End, lbl_EndTime, lbl_Name, txt_customerName, btn_Clear);

        return ticketForm;
    }
}

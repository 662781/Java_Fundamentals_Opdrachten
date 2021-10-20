package nl.inholland.javafx.UI.Forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import nl.inholland.javafx.Models.Movie;
import nl.inholland.javafx.Models.Room;

import java.time.LocalDate;
import java.util.List;

public class ShowingForm extends Form{

    private List<Movie> movies;
    private List<Room> rooms;
    private List<String> movieTitles, roomNames;

    public ShowingForm(List<String> movieTitles, List<String> roomNames, List<Movie> movies, List<Room> rooms){
        this.movieTitles = movieTitles;
        this.roomNames = roomNames;
        this.movies = movies;
        this.rooms = rooms;
        form = createForm();
    }

    public GridPane getShowingForm() {
        return form;
    }

    @Override
    public GridPane createForm(){
        //Create new Form GridPane
        GridPane showingForm = new GridPane();
        showingForm.setPadding(new Insets(10));
        showingForm.setHgap(30);
        showingForm.setVgap(10);
        showingForm.setId("FormGrid");

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
        showingForm.addRow(0, lbl_Title, cmb_MovieTitle, lbl_Start, startDatePicker, txt_StartTime);
        showingForm.addRow(1, lbl_Room, cmb_Room, lbl_End, lbl_EndTime, btn_AddShowing);
        showingForm.addRow(2, lbl_Seats, lbl_SeatsNr, lbl_Price, lbl_PriceShow, btn_Clear);

        return showingForm;
    }


}

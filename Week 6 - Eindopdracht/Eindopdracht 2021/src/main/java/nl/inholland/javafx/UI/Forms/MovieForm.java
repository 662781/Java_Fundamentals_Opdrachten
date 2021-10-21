package nl.inholland.javafx.UI.Forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class MovieForm extends Form {

    private ObservableList<Double> prices;
    private final List<Double> pricesList;

    public MovieForm() {
        pricesList = new ArrayList<>();
        form = createForm();
    }

    public GridPane getMovieForm() {
        return form;
    }

    @Override
    public GridPane createForm() {
        //Create new Form GridPane
        GridPane movieForm = new GridPane();
        movieForm.setPadding(new Insets(10));
        movieForm.setHgap(30);
        movieForm.setVgap(10);
        movieForm.setId("FormGrid");

        //Create nodes for new Form
        TextField txt_Title = new TextField();
        txt_Title.setPromptText("Title");
        TextField txt_Duration = new TextField();
        txt_Duration.setPromptText("Minutes");

        //Create ComboBox
        ComboBox<Double> cmb_Price = new ComboBox<>();
        pricesList.add(6.50);
        pricesList.add(8.50);
        pricesList.add(10.00);
        pricesList.add(12.00);
        pricesList.add(15.75);
        prices = FXCollections.observableArrayList(pricesList);
        cmb_Price.setItems(prices);
        cmb_Price.setPromptText("Price");
        cmb_Price.getSelectionModel().selectFirst();

        Button btn_AddMovie = new Button("Add Movie");
        Button btn_Clear = new Button("Clear");

        //Add nodes to new From GridPane
        movieForm.addRow(0, txt_Title, cmb_Price, txt_Duration);
        movieForm.addRow(1, btn_AddMovie, btn_Clear);

        return movieForm;
    }
}

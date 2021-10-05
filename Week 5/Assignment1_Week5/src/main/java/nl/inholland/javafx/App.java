package nl.inholland.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        //Set window size and title
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("University Project");

        VBox layout = setLayout();

        //Create scene and add stylesheet
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("css/LoginStyle.css");

        //Set the scene and show window
        window.setScene(scene);
        window.show();
    }

    public VBox setLayout(){

        //Create main vertical layout
        VBox layout = new VBox();
        layout.setSpacing(20);

        //Create horizontal layouts for alignment nodes
        HBox hbox_UsernameInput = new HBox();
        HBox hbox_PasswordInput = new HBox();
        HBox hbox_Login = new HBox();
        VBox vbox_Title = new VBox();


        //Create image
        Image img = new Image("images/logo.png");
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(64);
        imageView.setFitWidth(64);

        //Create nodes

        //Create label with logo and title
        Label lbl_LogoTitle = new Label("University Project", imageView);
        lbl_LogoTitle.setId("TitleLabel");
        lbl_LogoTitle.setPrefSize(800, 120);
        lbl_LogoTitle.setPadding(new Insets(10,10,10,10));

        //Create empty label
        Label lbl_BlueDetail = new Label();
        lbl_BlueDetail.setPrefSize(800, 20);
        lbl_BlueDetail.getStyleClass().add("blueDetail");

        //Create TextFields
        TextField txt_UsernameInput = new TextField();
        txt_UsernameInput.setPromptText("Username");
        txt_UsernameInput.setPrefSize(400, 20);

        TextField txt_PasswordInput = new TextField();
        txt_PasswordInput.setPromptText("Password");
        txt_PasswordInput.setPrefSize(400, 20);

        //Create button
        Button btn_Login = new Button("Login");
        btn_Login.setId("LoginButton");

        //Add nodes to horizontal sub layouts
        hbox_UsernameInput.getChildren().addAll(txt_UsernameInput);
        hbox_UsernameInput.setAlignment(Pos.CENTER);

        hbox_PasswordInput.getChildren().addAll(txt_PasswordInput);
        hbox_PasswordInput.setAlignment(Pos.CENTER);

        hbox_Login.getChildren().addAll(btn_Login);
        hbox_Login.setAlignment(Pos.CENTER);

        //Add nodes to vertical sub layouts
        vbox_Title.getChildren().addAll(lbl_LogoTitle, lbl_BlueDetail);

        //Add nodes to main layout
        layout.getChildren().addAll(vbox_Title, hbox_UsernameInput, hbox_PasswordInput, hbox_Login);

        return layout;
    }
}

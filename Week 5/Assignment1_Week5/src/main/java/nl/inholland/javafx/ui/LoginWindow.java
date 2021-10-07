package nl.inholland.javafx.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.javafx.data.Database;
import nl.inholland.javafx.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginWindow extends WindowSuperClass {

    private MainWindow mW;
    private Database db;

    public LoginWindow(){

        window = new Stage();
        db = new Database();

        //Set window size and title
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("University Project - Login");

        //Create layout from method setLayout()
        VBox layout = setLayout();

        //Create scene and add stylesheet
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("css/LoginStyle.css");

        //Set the scene and show window
        window.setScene(scene);
        window.show();


        //Retrieve all the nodes you need from the layout
        HBox usernameBox = (HBox) layout.getChildren().get(1);
        HBox passwordBox = (HBox) layout.getChildren().get(2);
        HBox loginButtonBox = (HBox) layout.getChildren().get(3);

        TextField usernameInputText = (TextField) usernameBox.getChildren().get(0);
        TextField passwordInputText = (TextField) passwordBox.getChildren().get(0);
        Button loginButton = (Button) loginButtonBox.getChildren().get(0);

        //Create people in list (later move to file, if you know how)
        List<Person> people = db.getPeople();

        //Executes code if button is clicked
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Checks if field are empty, if not, it checks the username and password
                if (!Objects.equals(usernameInputText.getText(), "") && !Objects.equals(passwordInputText.getText(), "")){

                    if(passwordCheck(usernameInputText.getText(), passwordInputText.getText(), people)){

                        //Show alert when logged in successfully
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Login");
                        //Gets the info of the person logging in
                        Person p = getPersonInfo(usernameInputText.getText(), people);
                        //Sets the header text to welcome that person
                        alert.setHeaderText("Welcome, " + p.getFirstName() + " " + p.getLastName());
                        alert.showAndWait();

                        //Create new MainWindow
                        mW = new MainWindow(window);

                        //Close this window
                        window.close();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("System couldn't find a match; wrong username or password");
                        alert.show();
                    }


                }
            }
        });


    }

    @Override
    public VBox setLayout(){

        //Create main vertical layout
        VBox layout = new VBox();
        layout.setSpacing(20);

        //Create sub layouts for groups of nodes
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

    private boolean passwordCheck(String username, String password, List<Person> people)
    {
        boolean checkPassed = false;

        for (Person p: people)
        {
            if (Objects.equals(username, p.getUsername()) && Objects.equals(password, p.getPassword()))
            {
                checkPassed = true;
                break;
            }
            else
            {
                checkPassed = false;
            }
        }

        return checkPassed;
    }

    Person getPersonInfo(String username, List<Person> people)
    {
        Person person1 = null;

        for (Person p: people)
        {
            if (Objects.equals(username, p.getUsername()))
            {
                person1 = p;
            }
        }


        return person1;
    }
}

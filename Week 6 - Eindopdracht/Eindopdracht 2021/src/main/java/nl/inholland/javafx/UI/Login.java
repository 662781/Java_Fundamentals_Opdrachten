package nl.inholland.javafx.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.javafx.Database.Database;
import nl.inholland.javafx.Models.User;

import java.util.List;
import java.util.Objects;

public class Login extends Window{

    private PurchaseTickets main;

    public Login(Database db){

        window = new Stage();

        //Set window size and title
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("Fabulous Cinema | Login");

        //Create layout from method setLayout()
        VBox layout = setLayout();

        //Create scene and add stylesheet
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("css/style.css");

        //Set the scene and show window
        window.setScene(scene);
        window.show();


        //Retrieve all the nodes needed from the layout
        HBox usernameBox = (HBox) layout.getChildren().get(1);
        HBox passwordBox = (HBox) layout.getChildren().get(2);
        HBox loginButtonBox = (HBox) layout.getChildren().get(3);

        TextField usernameInputText = (TextField) usernameBox.getChildren().get(0);
        TextField passwordInputText = (TextField) passwordBox.getChildren().get(0);
        Button loginButton = (Button) loginButtonBox.getChildren().get(0);

        //Load the list from the DB class
        List<User> users = db.getUsers();

        //Executes code if login button is clicked
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Checks if field are empty, if not, it checks the username and password
                if (!Objects.equals(usernameInputText.getText(), "") && !Objects.equals(passwordInputText.getText(), "")){

                    if(passwordCheck(usernameInputText.getText(), passwordInputText.getText(), users)){

                        //Show alert when logged in successfully
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Login");
                        //Gets the info of the person logging in
                        User u = getPersonInfo(usernameInputText.getText(), users);
                        //Sets the header text to welcome that person
                        alert.setHeaderText("Welcome, " + u.getUsername());
                        alert.showAndWait();

                        //Create new MainWindow
                        main = new PurchaseTickets(window, db, u);

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


        //Create nodes

        //Create label with logo and title
        Label lbl_LogoTitle = new Label("Fabulous Cinema");
        lbl_LogoTitle.setId("TitleLabel");
        lbl_LogoTitle.setPrefSize(800, 120);
        lbl_LogoTitle.setPadding(new Insets(10,10,10,10));

        //Create empty label
        Label lbl_BlueDetail = new Label();
        lbl_BlueDetail.setPrefSize(800, 20);
        lbl_BlueDetail.getStyleClass().add("detailLine");

        //Create TextFields
        TextField txt_UsernameInput = new TextField();
        txt_UsernameInput.setPromptText("Username");
        txt_UsernameInput.setPrefSize(400, 20);

        PasswordField txt_PasswordInput = new PasswordField();
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

    private boolean passwordCheck(String username, String password, List<User> people)
    {
        boolean checkPassed = false;

        for (User u: people)
        {
            if (Objects.equals(username, u.getUsername()) && Objects.equals(password, u.getPassword()))
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

    User getPersonInfo(String username, List<User> users)
    {
        User user = null;

        for (User u: users)
        {
            if (Objects.equals(username, u.getUsername()))
            {
                user = u;
            }
        }


        return user;
    }

}

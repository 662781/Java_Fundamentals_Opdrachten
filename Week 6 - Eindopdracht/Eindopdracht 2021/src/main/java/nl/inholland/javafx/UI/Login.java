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
import javafx.stage.WindowEvent;
import nl.inholland.javafx.Database.Database;
import nl.inholland.javafx.Models.User;

import java.util.List;
import java.util.Objects;

public class Login extends Window {

    private PurchaseTickets main;

    public Login(Database db) {

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
                if (!Objects.equals(usernameInputText.getText(), "") && !Objects.equals(passwordInputText.getText(), "")) {

                    if (passwordCheck(usernameInputText.getText(), passwordInputText.getText(), users)) {

                        //Show alert when logged in successfully
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Login");
                        //Gets the info of the person logging in
                        User u = getUserInfo(usernameInputText.getText(), users);
                        //Sets the header text to welcome that person
                        alert.setHeaderText("Welcome, " + u.getUsername());
                        alert.showAndWait();

                        //Close this window and clear text fields
                        usernameInputText.clear();
                        passwordInputText.clear();
                        window.close();

                        //Create new MainWindow
                        main = new PurchaseTickets(window, db, u);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("System couldn't find a match; wrong username or password");
                        alert.show();
                    }


                }
            }
        });

        //Shows a new ExitConfirm window when the mainWindow is requested to close
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                new ExitConfirm(window);
            }
        });


    }

    public VBox setLayout() {

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
        lbl_LogoTitle.setId("TitleLabelLogin");
        lbl_LogoTitle.setPrefSize(window.getWidth(), 120);
        lbl_LogoTitle.setPadding(new Insets(10, 10, 10, 10));

        //Create empty label
        Label lbl_Stripe = new Label();
        lbl_Stripe.setPrefSize(window.getWidth(), 20);
        lbl_Stripe.setId("detailLine");

        //Create TextFields
        TextField txt_UsernameInput = new TextField();
        txt_UsernameInput.setPromptText("Username");
        txt_UsernameInput.setPrefSize(400, 20);
        txt_UsernameInput.getStyleClass().add("textFieldLogin");

        PasswordField txt_PasswordInput = new PasswordField();
        txt_PasswordInput.setPromptText("Password");
        txt_PasswordInput.setPrefSize(400, 20);
        txt_PasswordInput.getStyleClass().add("textFieldLogin");

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
        vbox_Title.getChildren().addAll(lbl_LogoTitle, lbl_Stripe);

        //Add nodes to main layout
        layout.getChildren().addAll(vbox_Title, hbox_UsernameInput, hbox_PasswordInput, hbox_Login);

        return layout;
    }

    private boolean passwordCheck(String username, String password, List<User> people) {
        boolean checkPassed = false;

        for (User u : people) {
            if (Objects.equals(username, u.getUsername()) && Objects.equals(password, u.getPassword())) {
                checkPassed = true;
                break;
            } else {
                checkPassed = false;
            }
        }

        return checkPassed;
    }

    User getUserInfo(String username, List<User> users) {
        User user = null;

        for (User u : users) {
            if (Objects.equals(username, u.getUsername())) {
                user = u;
            }
        }


        return user;
    }

}

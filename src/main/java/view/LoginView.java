package view;

import model.YellowHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

public class LoginView extends ViewParent {

    @FXML private JFXTextField usernameField;
    @FXML private JFXPasswordField passwordField;

    @FXML private JFXButton toSignUpButton;
    @FXML private JFXButton loginButton;

    private YellowHandler yh;

    public void hej () {
        System.out.println("FUNKAR");
    }

    public void login (EventHandler<ActionEvent> clicked) {
        loginButton.setOnAction(clicked);
    }

    public void toSignupScreen(EventHandler<ActionEvent> clicked) {
        toSignUpButton.setOnAction(clicked);
    }

    public String getUsername(){
        return usernameField.getText();
    }

    public String getPassword(){
        return passwordField.getText();
    }
}

package view;

import model.YellowHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SignUpView extends ViewParent {
    
    @FXML private JFXTextField newUsernameField;
    @FXML private JFXPasswordField newPasswordField;
    @FXML private JFXTextField newFirstNameField;
    @FXML private JFXTextField newLastNameField;
    @FXML private JFXTextField newEmailTextField;

    @FXML private Button signUpExitButton;
    @FXML private JFXButton signUpButton;

    private YellowHandler yh;

    public void initialize(){

    }

    public void signUp (EventHandler<ActionEvent> clicked) {
        signUpButton.setOnAction(clicked);
    }

    public void toLoginScreen (EventHandler<ActionEvent> clicked) {
        signUpExitButton.setOnAction(clicked);
    }

    public String getUsername(){
        return newUsernameField.getText();
    }

    public String getFirstName(){
        return newFirstNameField.getText();
    }

    public String getLastName(){
        return newLastNameField.getText();
    }

    public String getEmail(){
        return newEmailTextField.getText();
    }

    public String getPassword(){
        return newPasswordField.getText();
    }
}

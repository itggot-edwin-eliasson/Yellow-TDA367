package View;

import Model.UserInterface;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class UserSettingsView {

    @FXML private JFXTextField usernameTextField;
    @FXML private JFXPasswordField passwordTextField;
    @FXML private JFXTextField emailTextField;
    @FXML private JFXTextField firstNameTextField;
    @FXML private JFXTextField lastNameTextField;

    @FXML private JFXButton saveButton;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;




    public void initialize(){

    }

    public void setFields (UserInterface activeUser) {
        firstNameTextField.setText(activeUser.getFirstName());
        lastNameTextField.setText(activeUser.getLastName());
        usernameTextField.setText(activeUser.getUsername());
        passwordTextField.setText(activeUser.getPassword());
        emailTextField.setText(activeUser.getEmail());

    }


    public void changeUserSettings(EventHandler<ActionEvent> clicked) {
        saveButton.setOnAction(clicked);
    }

    public String getFirstName(){
        return firstNameTextField.getText();
    }

    public String getLastName(){
        return lastNameTextField.getText();
    }

    public String getEmail(){
        return emailTextField.getText();
    }

    public String getUsername(){
        return usernameTextField.getText();
    }

    public String getPassword(){
        return passwordTextField.getText();
    }

    public void setUsernameTextField (String username) {
        usernameTextField.setText(username);
    }


    public void setEmailTextField (String email)  {
        emailTextField.setText(email);

    }


}

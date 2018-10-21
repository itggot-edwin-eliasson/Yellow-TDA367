package View;

import Model.UserInterface;
import Model.YellowHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserSettingsView extends ViewParent{

    @FXML private JFXTextField usernameTextField;
    @FXML private JFXPasswordField passwordTextField;
    @FXML private JFXTextField emailTextField;
    @FXML private JFXTextField firstNameTextField;
    @FXML private JFXTextField lastNameTextField;

    @FXML private JFXButton saveButton;

    Stage dialogStage;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;



    public void setFields () {
        firstNameTextField.setText(super.yh.getActiveUser().getFirstName());
        lastNameTextField.setText(super.yh.getActiveUser().getLastName());
        usernameTextField.setText(super.yh.getActiveUser().getUsername());
        passwordTextField.setText(super.yh.getActiveUser().getPassword());
        emailTextField.setText(super.yh.getActiveUser().getEmail());

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

    public void setDialogStage (Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setEmailTextField (String email)  {
        emailTextField.setText(email);
    }

}

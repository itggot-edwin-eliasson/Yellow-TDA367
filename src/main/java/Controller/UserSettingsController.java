package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class UserSettingsController {

    @FXML JFXTextField usernameTextField;
    @FXML JFXPasswordField passwordTextField;
    @FXML JFXTextField emailTextField;
    @FXML JFXTextField firstNameTextField;
    @FXML JFXTextField lastNameTextField;

    @FXML JFXButton saveButton;

    MainController mainController;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

    @FXML public void changeUserSettings () {

        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String name = firstName + " " + lastName;
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String username = usernameTextField.getText();

        mainController.changeUserSettings(name, username, email, password);

    }

    public void setUsernameTextField (String username) {
        usernameTextField.setText(username);
    }


    public void setEmailTextField (String email)  {
        emailTextField.setText(email);

    }

    public void initialize(){

    }

}

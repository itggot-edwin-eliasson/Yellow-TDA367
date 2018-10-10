package Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class UserSettingsController {

    @FXML JFXTextField usernameTextField;
    @FXML JFXTextField passwordTextField;
    @FXML JFXTextField emailTextField;
    @FXML JFXTextField firstNameTextField;
    @FXML JFXTextField lastNameTextField;

    MainController mainController;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

    @FXML public void changeUsettings () {

        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String name = firstName + " " + lastName;
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String username = usernameTextField.getText();

        mainController.changeUserSettings(name, username, email, password);

    }

    public void initialize(){

    }

}

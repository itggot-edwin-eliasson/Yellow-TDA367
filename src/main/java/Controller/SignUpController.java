package Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class SignUpController {
    MainController mainController;

    @FXML JFXTextField newUsernameField;
    @FXML JFXTextField newPasswordField;
    @FXML JFXTextField newFirstNameField;
    @FXML JFXTextField newLastNameField;
    @FXML JFXTextField newEmailTextField;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void initialize(){

    }

    @FXML
    public void signUp () {
        String username = newUsernameField.getText();
        String firstName = newFirstNameField.getText();
        String lastName = newLastNameField.getText();

        String name = firstName + " " + lastName;

        String password = newPasswordField.getText();
        String email = newEmailTextField.getText();

        mainController.createUser(username, name, email, password);
        mainController.goToMainWindow();

    }
}

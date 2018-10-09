package Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SignUpController {

    private MainController mainController;

    @FXML private JFXTextField newUsernameField;
    @FXML private JFXTextField newPasswordField;
    @FXML private JFXTextField newFirstNameField;
    @FXML private JFXTextField newLastNameField;
    @FXML private JFXTextField newEmailTextField;

    @FXML Button signUpExitButton;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void initialize(){

    }

    @FXML
    private void signUp () {
        String username = newUsernameField.getText();
        String firstName = newFirstNameField.getText();
        String lastName = newLastNameField.getText();

        String name = firstName + " " + lastName;

        String password = newPasswordField.getText();
        String email = newEmailTextField.getText();

        if(!username.isEmpty() && !password.isEmpty()) {

            mainController.createUser(username, name, email, password);
            mainController.goToMainWindow();
        }

    }

    @FXML private void toLoginScreen () {
        mainController.toLoginScreen();
    }
}

package Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class LoginController {

    MainController mainController;

    @FXML JFXTextField usernameField;
    @FXML JFXPasswordField passwordField;



    public void injectMainController (MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void login () {
        String username = usernameField.getText();
        String password = passwordField.getText();

        mainController.login(username, password);

        System.out.println("hej");

        // SHOW NEXT SCREEN
    }

    @FXML
    public void toSignupScreen() {
        mainController.goToSignUp();
    }


}
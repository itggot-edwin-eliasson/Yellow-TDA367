package Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class LoginController {

    private MainController mainController;

    @FXML private JFXTextField usernameField;
    @FXML private JFXPasswordField passwordField;

    public void injectMainController (MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void login () {
        String username = usernameField.getText();
        String password = passwordField.getText();

        mainController.login(username, password);

        System.out.println("hej");

        // SHOW NEXT SCREEN
    }

    @FXML
    private void toSignupScreen() {
        mainController.goToSignUp();
    }


}

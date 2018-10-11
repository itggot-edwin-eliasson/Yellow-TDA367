package Controller;

import Model.UserInterface;
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

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;

    MainController mainController;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void initialize(){

    }

    public void setFields (UserInterface activeUser) {
        firstNameTextField.setText(activeUser.getFirstName());
        lastNameTextField.setText(activeUser.getLastName());
        usernameTextField.setText(activeUser.getUsername());
        passwordTextField.setText(activeUser.getPassword());
        emailTextField.setText(activeUser.getEmail());

    }


    @FXML public void changeUserSettings () {
        this.firstName = firstNameTextField.getText();
        this.lastName = lastNameTextField.getText();
        this.email = emailTextField.getText();
        this.password = passwordTextField.getText();
        this.username = usernameTextField.getText();


        mainController.changeUserSettings(firstName, lastName, username, email, password);



    }

    public void setUsernameTextField (String username) {
        usernameTextField.setText(username);
    }


    public void setEmailTextField (String email)  {
        emailTextField.setText(email);

    }


}

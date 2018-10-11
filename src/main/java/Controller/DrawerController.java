package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class DrawerController {

    private MainController mainController;

    @FXML JFXButton userSettingsButton;
    @FXML JFXButton editGroupsButton;
    @FXML JFXButton inviteCodesButton;
    @FXML JFXButton logOut;


    public void injectMainController (MainController mainController) {
        this.mainController = mainController;
    }

    public void initialize () {

    }

    private void toUserSettings () {
        mainController.goToUserSettings();
    }

    private void logOut () {
        mainController.logOut();
    }










}

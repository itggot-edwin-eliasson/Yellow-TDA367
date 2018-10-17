package View;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

public class DrawerController {


    @FXML JFXButton userSettingsDrawerButton;
    @FXML JFXButton manageMyYellowDrawerButton;
    @FXML JFXButton inviteCodesDrawerButton;
    @FXML JFXButton joinGroupDrawerButton;
    @FXML JFXButton logOutDrawerButton;

    public void initialize () { }

    public void logOut (EventHandler<ActionEvent> clicked) {
        logOutDrawerButton.setOnAction(clicked);
    }


    private void toUserSettings (EventHandler<ActionEvent> clicked) {

    }












}

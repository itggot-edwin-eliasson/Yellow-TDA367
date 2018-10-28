package View;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

public class DrawerView {


    @FXML JFXButton userSettingsDrawerButton;
    @FXML JFXButton manageMyYellowDrawerButton;
    @FXML JFXButton inviteCodesDrawerButton;
    @FXML JFXButton joinGroupDrawerButton;
    @FXML JFXButton logOutDrawerButton;

    public void initialize () { }

    public void logOut (EventHandler<ActionEvent> clicked) {
        logOutDrawerButton.setOnAction(clicked);
    }

    public void toUserSettings (EventHandler<ActionEvent> clicked) {
        userSettingsDrawerButton.setOnAction(clicked);
    }

    public void showGroupInviteCodes(EventHandler<ActionEvent> clicked){
        inviteCodesDrawerButton.setOnAction(clicked);
    }
}

package view;

import model.YellowHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class MenuScreenView extends ViewParent {

    @FXML private JFXButton toMyYellowButton;
    @FXML private JFXButton toManageMyYellowButton;
    @FXML private JFXButton createGroupButton;
    @FXML private JFXButton joinGroupButton;
    @FXML private JFXButton signOutButton;

    private YellowHandler yh;

    public void toMyYellow(EventHandler<ActionEvent> clicked){
        toMyYellowButton.setOnAction(clicked);
    }

    public void toManageMyYellow(EventHandler<ActionEvent> clicked){
        toManageMyYellowButton.setOnAction(clicked);
    }

    public void toCreateGroupPopUp (EventHandler<ActionEvent> clicked){
        createGroupButton.setOnAction(clicked);
    }

    public void joinGroup (EventHandler<ActionEvent> clicked){
        joinGroupButton.setOnAction(clicked);
    }

    public void signOut (EventHandler <ActionEvent> clicked) {signOutButton.setOnAction(clicked);}






}

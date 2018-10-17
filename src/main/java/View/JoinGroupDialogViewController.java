
package View;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class JoinGroupDialogViewController {


    @FXML private JFXButton joinGroupButton;
    @FXML private JFXTextField joinGroupTextField;

    private boolean okCklicked = false;
    private Stage dialogStage;


    public boolean isOkClicked(){
        return okCklicked;
    }

    public void close () {
        dialogStage.close();
    }

    public String getJoinGroupTextField() {
        return joinGroupTextField.getText();
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    @FXML
    private void joinGroup(){
            okCklicked = true;
            dialogStage.close();

    }

    public void handleJoinGroup (EventHandler <ActionEvent> clicked) {
        joinGroupButton.setOnAction(clicked);
    }
}


package View;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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

    public JFXTextField getJoinGroupTextField() {
        return joinGroupTextField;
    }

    public void setDialogStage(Stage dialogStage){
        dialogStage.close();
    }

    @FXML
    private void joinGroup(){
        okCklicked = true;
        dialogStage.close();
    }
}

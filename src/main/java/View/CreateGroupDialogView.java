package View;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class CreateGroupDialogView {

    private Stage dialogStage;
    private boolean okClicked = false;

    @FXML
    private JFXTextField groupName;

    @FXML
    private JFXColorPicker colorPicker;

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    public String getGroupName(){
        return groupName.getText();
    }

    public String getGroupColor(){
        return "#" + Integer.toHexString(colorPicker.getValue().hashCode());
    }

    @FXML
    private void handleOk(){
        okClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

}

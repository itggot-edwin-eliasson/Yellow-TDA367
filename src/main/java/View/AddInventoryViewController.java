package View;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddInventoryViewController {

    private Stage dialogStage;

    private boolean okClicked = false;

    @FXML
    JFXButton addButton;

    @FXML JFXButton cancelButton;

    @FXML
    TextField nameTextField;


    public String getInventoryName () {
        return nameTextField.getText();
    }


    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void saveInventory () {
        okClicked = true;
        dialogStage.close();
    }

    @FXML
    private void cancelInventory(){
        dialogStage.close();
    }

    public void setDialogStage (Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


}

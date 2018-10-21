package View;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddItemView {

    private Stage dialogStage;

    private boolean okClicked = false;

    @FXML private JFXButton cancelButton;
    @FXML private TextField nameTextField;
    @FXML private TextArea descriptionTextArea;
    @FXML private TextField amountTextField;
    @FXML private JFXButton uploadImageButton;



    public String getItemName () {
        return nameTextField.getText();
    }

    public String getItemDescription (){
        return descriptionTextArea.getText();
    }

    /*public int getAmount () {
        try {
            int amount = Integer.parseInt(amountTextField.getText());
            return amount;

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(e.toString());

            alert.showAndWait();
            return null;
        }
    }*/

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void saveItem () {
        okClicked = true;
        dialogStage.close();
    }

    @FXML
    private void cancelItem(){
        dialogStage.close();
    }

    public void setDialogStage (Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void uploadImage(EventHandler<ActionEvent> clicked){
        uploadImageButton.setOnAction(clicked);
    }

    public void itemAmount(EventHandler<ActionEvent> clicked){
        amountTextField.setOnAction(clicked);
    }

}

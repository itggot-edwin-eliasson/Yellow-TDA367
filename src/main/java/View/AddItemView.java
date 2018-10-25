package View;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddItemView {

    private Stage dialogStage;

    private boolean okClicked = false;

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

    public int getAmount(){
        String amount = amountTextField.getText();
        for (char c : amount.toCharArray()){
            if(!Character.isDigit(c)){
                return 0;
            }
        }
        if(!amount.isEmpty()){
            return Integer.parseInt(amount);
        }else {
            return 1;
        }
    }

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

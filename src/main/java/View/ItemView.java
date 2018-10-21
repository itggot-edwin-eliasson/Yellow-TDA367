package View;

import Model.ItemInterface;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.awt.event.MouseEvent;

public class ItemView {


    private String itemId;
    private int amount;
    private boolean okClicked = false;
    private Stage dialogStage;

    @FXML private JFXSlider amountSlider;

    @FXML private Label itemName;
    @FXML private JFXTextField descriptionTextField;
    @FXML private AnchorPane itemCalendarAnchorPane;

    @FXML
    public void setAmount(MouseEvent e){
         this.amount = (int) amountSlider.getValue();
    }

    public void initialize(){

    }

    public String getId(){
        return itemId;
    }

    @FXML
    public void addToOrder() {
        okClicked = true;
        dialogStage.close();
    }

    public void setItem (ItemInterface item) {
        this.itemName.setText(item.getName());
        this.descriptionTextField.setText(item.getDescription());
        this.itemId = item.getId();
    }

    public AnchorPane getitemCalendarAnchorPane(){
        return itemCalendarAnchorPane;
    }

    public void setDialogStage(Stage stage){
        this.dialogStage = stage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }
}

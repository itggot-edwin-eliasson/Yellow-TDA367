package View;

import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


import java.awt.event.MouseEvent;

public class ItemViewController {


    String itemId;
    int amount;

    @FXML private JFXSlider amountSlider;

    @FXML private Label itemName;
    @FXML private Label itemDescription;

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
    public void addToOrder () {

    }

    public void setNameAndDescription (String itemName, String itemDescription) {
        this.itemName.setText(itemName);
        this.itemDescription.setText(itemDescription);
    }

}

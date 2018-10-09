package Controller;

import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


import java.awt.event.MouseEvent;

public class ItemViewController {

    MainController mainController;

    String itemId;
    int amount;

    @FXML JFXSlider amountSlider;

    @FXML Label itemName;
    @FXML Label itemDescription;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

    @FXML
    public void setAmount(MouseEvent e){
         this.amount = (int) amountSlider.getValue();
    }

    public void initialize(){

    }

    public String getId(){
        return itemId;
    }

    @FXML public void addToOrder () {


        mainController.addToOrder(amount, itemId);
    }

    public void setNameAndDescription (String itemName, String itemDescription) {
        this.itemName.setText(itemName);
        this.itemDescription.setText(itemDescription);
    }

}

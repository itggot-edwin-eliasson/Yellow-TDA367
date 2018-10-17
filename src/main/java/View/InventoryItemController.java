package View;

import Model.InventoryInterface;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InventoryItemController extends AnchorPane {

    String name;
    InventoryInterface inventory;

    @FXML private Label inventoryName;
    @FXML private AnchorPane inventoryBackground;

    public InventoryItemController(InventoryInterface inventory) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../inventoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.inventory = inventory;

        inventoryName.setText(inventory.getName());
    }

    public void selectInventory(EventHandler<MouseEvent> event){
        inventoryBackground.setOnMouseClicked(event);
    }

    public InventoryInterface getInventory(){
        return inventory;
    }
}
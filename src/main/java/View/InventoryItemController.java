package View;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InventoryItemController extends AnchorPane {

    String name;

    @FXML private Label inventoryName;
    @FXML private AnchorPane inventoryBackground;

    public InventoryItemController(String name) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../inventoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.name = name;

        inventoryName.setText(name);
    }

    public void selectInventory(EventHandler<MouseEvent> event){
        inventoryBackground.setOnMouseClicked(event);
    }
}

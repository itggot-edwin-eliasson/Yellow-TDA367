package View;

import Model.InventoryInterface;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ManageInventoryItemView extends AnchorPane {
    private InventoryInterface inventory;

    @FXML
    private Label manageInventoryItem;
    @FXML
    private AnchorPane background;

    public ManageInventoryItemView(InventoryInterface inventory) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../manageInventoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.inventory = inventory;

        manageInventoryItem.setText(inventory.getName());
    }

    public void selectInventory(EventHandler<MouseEvent> clicked) {
        background.setOnMouseClicked(clicked);

    }

    public InventoryInterface getInventory(){
        return inventory;
    }

    public void setHighlighted() {
        background.setOpacity(0.75);
    }

}

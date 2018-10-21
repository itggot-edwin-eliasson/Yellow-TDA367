package View;

import Model.InventoryInterface;
import Model.ItemInterface;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ManageItemItemView extends AnchorPane {

    ItemInterface item;

    @FXML private Label manageItemItem;
    @FXML private AnchorPane background;

    public ManageItemItemView(ItemInterface item) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../manageItemItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.item = item;

        manageItemItem.setText(item.getName());
    }

    public ItemInterface getInventory(){
        return item;
    }
}

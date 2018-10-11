package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InventoryItemController extends AnchorPane {

    String name;
    MainController parentController;

    @FXML private Label inventoryName;

    public InventoryItemController(String name, MainController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../inventoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.name = name;
        this.parentController = controller;

        inventoryName.setText(name);
    }
}

package view;

import model.ItemInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ActiveOrderItemView extends AnchorPane {

    private ItemInterface item;

    @FXML
    private Label orderItemName;

    @FXML private Button removeItemButton;

    public ActiveOrderItemView(ItemInterface item) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../activeOrderItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.item = item;

        orderItemName.setText(item.getName());
    }

    public ItemInterface getItem(){
        return item;
    }

    public void setRemoveItemButton(EventHandler<ActionEvent> clicked){
        removeItemButton.setOnAction(clicked);
    }
}

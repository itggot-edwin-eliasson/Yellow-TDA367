package View;

import Model.ItemInterface;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ItemItemController extends AnchorPane {

    private ItemInterface item;

    @FXML private Label itemName;
    @FXML private ImageView itemImage;
    @FXML private AnchorPane background;

    public ItemItemController(ItemInterface item) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../itemItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.item = item;

        itemName.setText(item.getName());
    }

    public ItemInterface getItem(){
        return this.item;
    }

    public void selectItem(EventHandler<MouseEvent> event){
        background.setOnMouseClicked(event);
    }
}

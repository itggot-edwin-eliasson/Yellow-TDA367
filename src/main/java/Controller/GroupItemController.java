package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GroupItemController extends AnchorPane {

    String name;
    MainController parentController;

    @FXML private Label groupName;
    @FXML private AnchorPane background;

    public GroupItemController(String name, String color, MainController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("groupItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.name = name;
        this.parentController = controller;

        groupName.setText(name);
        background.setStyle("-fx-background-color:" + color);
    }
}

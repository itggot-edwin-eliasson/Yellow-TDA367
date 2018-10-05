package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GroupItem extends AnchorPane {

    String name;
    Controller parentController;

    @FXML private Label groupName;
    @FXML private AnchorPane background;

    public GroupItem(String name, String color, Controller controller) {
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

package View;

import Model.GroupInterface;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ManageGroupItemView extends AnchorPane {
    private GroupInterface group;

    @FXML
    private Label manageGroupItem;
    @FXML
    private AnchorPane background;

    public ManageGroupItemView(GroupInterface group) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../manageGroupItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.group = group;

        manageGroupItem.setText(group.getName());
        background.setStyle("-fx-background-color: " + group.getColor());
    }

    public void selectGroup(EventHandler<MouseEvent> clicked){
        background.setOnMouseClicked(clicked);

    }

    public GroupInterface getGroup(){
        return group;
    }
}

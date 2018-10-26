package View;

import Model.GroupInterface;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.io.IOException;

public class GroupItemView extends AnchorPane {

    private GroupInterface group;

    @FXML private Label groupName;
    @FXML private AnchorPane background;

    public GroupItemView(GroupInterface group) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../groupItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.group = group;
        
        groupName.setText(group.getName());
        background.setStyle("-fx-background-color: " + group.getColor());
    }

    public void selectGroup(EventHandler<MouseEvent> clicked){
        background.setOnMouseClicked(clicked);

    }
    @FXML
    public void onMouseEntered(){
        background.setOpacity(0.8);
    }
    @FXML
    public void onMouseExited(){
        background.setOpacity(1);
    }

    public GroupInterface getGroup(){
        return group;
    }
}

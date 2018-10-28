package view;

import model.GroupInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class ViewInviteCodesItemView extends AnchorPane {

    private GroupInterface group;

    @FXML private Label groupNameLabel;
    @FXML private Label inviteCodeLabel;

    public ViewInviteCodesItemView(GroupInterface group){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../viewInviteCodesItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.group = group;

        groupNameLabel.setText(group.getName());
        inviteCodeLabel.setText(group.getInviteCode() + "");
    }
}

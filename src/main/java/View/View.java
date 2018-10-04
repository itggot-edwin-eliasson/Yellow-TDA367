package View;

import Controller.Controller;
import Model.Observable;
import Model.YellowHandler;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXHamburger;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.util.*;

public class View implements Observer {

    private Controller c;
    private YellowHandler yellowHandler;

    @FXML private Button addGroupButton;
    @FXML private FlowPane groupMenu;

    private Map<String, GroupItem> groupItemMap = new HashMap<>();

    public View (Observable t, Controller c, YellowHandler yellowHandler) {
        t.addObserver((this));
        this.c = c;
        this.yellowHandler = yellowHandler;

        for(String id: yellowHandler.getGroupInfo().keySet()){
            GroupItem groupItem = new GroupItem(yellowHandler.getGroupInfo().get(id).get(0),
                                                yellowHandler.getGroupInfo().get(id).get(0), this);
            groupItemMap.put(id,groupItem);
        }
    }

    private void updateGroups() {
        groupMenu.getChildren().clear();
        for(GroupItem groupItem: groupItemMap.values()){
            groupMenu.getChildren().add(groupItem);
        }
    }

    @FXML
    public void addGroup() {

    }







    @Override
    public void update() {

    }
}

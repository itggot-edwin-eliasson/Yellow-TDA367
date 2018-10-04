package View;

import Controller.Controller;
import Model.Observable;
import Model.YellowHandler;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXHamburger;
import javafx.scene.control.Button;
import java.util.*;

public class View implements Observer {

    private Controller c;
    private YellowHandler yellowHandler;

    @FXML private Button addGroupButton;

    private Map<String, GroupItem> groupItemMap = new HashMap<>();

    public View (Observable t, Controller c, YellowHandler yellowHandler) {
        t.addObserver((this));
        this.c = c;
        this.yellowHandler = yellowHandler;
    }

    private void updateGroups() {

    }

    @FXML
    public void addGroup() {

    }







    @Override
    public void update() {

    }
}

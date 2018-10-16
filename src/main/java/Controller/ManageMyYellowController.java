package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ManageMyYellowController {

    @FXML private Button addGroupButton;
    @FXML private Button addInventoryButton;
    @FXML private Button addItemButton;

    @FXML private Button backButton;
    
    public void initialize(){
    }

    public void addItem (EventHandler<ActionEvent> clicked) { addItemButton.setOnAction(clicked);
    }

    public void addGroup (EventHandler<ActionEvent> clicked) { addGroupButton.setOnAction(clicked);
    }

    public void addInventory (EventHandler<ActionEvent> clicked) { addInventoryButton.setOnAction(clicked);
    }

}

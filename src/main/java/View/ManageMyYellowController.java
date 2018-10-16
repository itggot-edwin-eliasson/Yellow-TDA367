package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class ManageMyYellowController extends ViewController {

    @FXML private Button addGroupButton;
    @FXML private Button addInventoryButton;
    @FXML private Button addItemButton;
    @FXML private Button backButton;

    @FXML private FlowPane manageGroupFlowPane;
    @FXML private FlowPane manageInventoryFlowPane;
    @FXML private FlowPane manageItemFlowPane;


    public void initialize(){
    }

    public void backToManageMyYellow(EventHandler<ActionEvent> clicked){
        backButton.setOnAction(clicked);
    }

    public void addItem (EventHandler<ActionEvent> clicked) {
        addItemButton.setOnAction(clicked);
    }

    public void addGroup (EventHandler<ActionEvent> clicked) {
        addGroupButton.setOnAction(clicked);
    }

    public void addInventory (EventHandler<ActionEvent> clicked) {
        addInventoryButton.setOnAction(clicked);
    }

}

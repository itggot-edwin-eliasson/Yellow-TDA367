package view;

import model.GroupInterface;
import model.InventoryInterface;
import model.ItemInterface;
import model.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class ManageMyYellow extends ViewParent implements Observer {

    @FXML private Button addGroupButton;
    @FXML private Button addInventoryButton;
    @FXML private Button addItemButton;
    @FXML private Button backButton;
    @FXML private Button toMyYellowButton;


    @FXML private FlowPane manageGroupFlowPane;
    @FXML private FlowPane manageInventoryFlowPane;
    @FXML private FlowPane manageItemFlowPane;

    private EventHandler<MouseEvent> groupItemClick;
    private EventHandler<MouseEvent> inventoryItemClick;

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

    public void goToMyYellow(EventHandler<ActionEvent> clicked){
        toMyYellowButton.setOnAction(clicked);
    }

    public void updateGroupList(){
        List<GroupInterface> groups = super.yh.getGroups();
        manageGroupFlowPane.getChildren().clear();
        for(GroupInterface group: groups){
            ManageGroupItemView groupItem = new ManageGroupItemView(group);
            groupItem.selectGroup(groupItemClick);
            if(group == super.yh.getActiveGroup()){
                groupItem.setHighlighted();
            }
            manageGroupFlowPane.getChildren().add(groupItem);
        }
    }

    public void updateInventoryList(){
        List<InventoryInterface> inventories = super.yh.getInventories();
        manageInventoryFlowPane.getChildren().clear();
        for(InventoryInterface inventory: inventories){
            ManageInventoryItemView inventoryItem = new ManageInventoryItemView(inventory);
            inventoryItem.selectInventory(inventoryItemClick);
            if(inventory == super.yh.getActiveGroup().getSelectedInventory()){
                inventoryItem.setHighlighted();
            }
            manageInventoryFlowPane.getChildren().add(inventoryItem);
        }
    }

    public void updateItemList(){
        List<ItemInterface> items = super.yh.getItems();
        manageItemFlowPane.getChildren().clear();
        for(ItemInterface item: items){
            ManageItemItemView itemItem = new ManageItemItemView(item);
            manageItemFlowPane.getChildren().add(itemItem);
        }
    }

    public void injectGroupItemListener(EventHandler<MouseEvent> clicked) {
        groupItemClick = clicked;
    }

    public void injectInventoryItemListener(EventHandler<MouseEvent> clicked) {
        inventoryItemClick = clicked;
    }

    @Override
    public void update () {
        updateGroupList();
        updateInventoryList();
        updateItemList();
    }

}

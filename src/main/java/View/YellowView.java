package View;

import Model.*;
import Model.Observer;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;

import javax.swing.text.html.ImageView;
import java.io.*;
import java.util.*;

public class YellowView extends ViewParent implements Observer {

    private List<GroupInterface> allGroups = new ArrayList<>();
    private List<UserInterface> allUsers = new ArrayList<>();
    private List<InventoryInterface> allInventories = new ArrayList<>();
    private Map<ItemInterface, ItemItemView> itemItemControllerMap = new HashMap<>();

    private EventHandler<MouseEvent> groupItemClick;
    private EventHandler<MouseEvent> inventoryItemClick;
    private EventHandler<MouseEvent> itemItemClick;

    @FXML private SignUpView signUpController;
    @FXML private LoginView loginController;
    @FXML private UserSettingsView userSettingsController;
    @FXML private ManageMyYellow manageMyYellowController;

    @FXML private JFXDrawer drawer;

    @FXML private JFXHamburger hamburger;

    @FXML private AnchorPane signUp;
    @FXML private AnchorPane login;
    @FXML private AnchorPane userSettings;
    @FXML private AnchorPane menuScreen;
    @FXML private AnchorPane manageMyYellow;
    @FXML private StackPane mainWindow;
    @FXML private FlowPane groupListFlowPane;
    @FXML private FlowPane listFlowPane;
    @FXML private BorderPane orderBorderPane;


    @FXML private JFXButton userSettingsButton;
    @FXML private Label title;
    @FXML private Button addGroupButton;
    @FXML private Button backButton;
    @FXML private Button addItemButton;
    @FXML private Button backToMenuButton;
    @FXML private JFXButton orderButton;
    @FXML private Button backToItemsButton;

    public void initialize() {

    }

    public void selectGroup(){
        title.setText(super.yh.getActiveGroup().getName());
        backButton.setVisible(true);
        backToMenuButton.setVisible(false);
        orderButton.setVisible(true);
        listFlowPane.toFront();
        hamburger.toFront();
        updateInventoryList();
        updateItemList();
    }

    public void backToGroups(){
        backButton.setVisible(false);
        backToMenuButton.setVisible(true);
        backToItemsButton.setVisible(false);
        orderButton.setVisible(false);
        orderBorderPane.toBack();
        title.setText("Groups");
        updateGroupList();
    }

    public void setBackToGroupsListener(EventHandler<ActionEvent> event){
        backButton.setOnAction(event);
    }

    public void setBackToMenuListener(EventHandler<ActionEvent> event){
        backToMenuButton.setOnAction(event);
    }

    public void setBackToItemsButton(EventHandler<ActionEvent> event){
        backToItemsButton.setOnAction(event);
    }

    public void setOrderButton(EventHandler<ActionEvent> event){
        orderButton.setOnAction(event);
    }


    public void updateGroupList(){
        List<GroupInterface> groups = super.yh.getGroups();
        groupListFlowPane.getChildren().clear();
        for(GroupInterface group: groups){
            GroupItemView item = new GroupItemView(group);
            item.selectGroup(groupItemClick);
            groupListFlowPane.getChildren().add(item);
        }
    }

    public void updateInventoryList(){
        List<InventoryInterface> inventories = super.yh.getInventories();
        groupListFlowPane.getChildren().clear();
        for(InventoryInterface inventory: inventories){
            InventoryItemView item = new InventoryItemView(inventory);
            item.selectInventory(inventoryItemClick);
            if(super.yh.getActiveGroup().getSelectedInventory()==inventory){
                item.setHighlighted();
            }else{
                item.setNotHighlighted();
            }
            groupListFlowPane.getChildren().add(item);
        }
    }

    public void updateItemList(){
        List<ItemInterface> items = super.yh.getItems();
        listFlowPane.getChildren().clear();
        for(ItemInterface item: items){
            ItemItemView itemItem = new ItemItemView(item);
            itemItem.selectItem(itemItemClick);
            listFlowPane.getChildren().add(itemItem);
            if(item.getImageURL()!= ""){
                itemItem.setImage(new Image(item.getImageURL()));
            }
        }
    }

    public void showOrderPane(){
        orderBorderPane.toFront();
        hamburger.toFront();
        backToItemsButton.setVisible(true);
    }

    public void hideOrderPane(){
        orderBorderPane.toBack();
        backToItemsButton.setVisible(false);
    }

    public void setOrderPane(Node node){
        orderBorderPane.setCenter(node);
    }

    public void hamburgerSetup(VBox drawerContent){

        drawer.setSidePane(drawerContent);

        HamburgerBasicCloseTransition hamTask = new HamburgerBasicCloseTransition(hamburger);
        hamTask.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            hamTask.setRate(hamTask.getRate() * -1);
            hamTask.play();


            if (drawer.isOpened()) {
                drawer.close();
                drawer.setOnDrawerClosed(event -> {
                    drawer.toBack();
                    event.consume();
                });
            } else {
                drawer.toFront();
                hamburger.toFront();
                drawer.open();
            }
        });
    }

    @Override
    public void update() {

        updateInventoryList();
        updateItemList();
        updateGroupList();
    }

    public void injectGroupItemListener(EventHandler<MouseEvent> clicked) {
        groupItemClick = clicked;
    }

    public void injectInventoryItemListener(EventHandler<MouseEvent> clicked) {
        inventoryItemClick = clicked;
    }

    public void injectItemItemListener(EventHandler<MouseEvent> clicked){
        itemItemClick = clicked;
    }
}

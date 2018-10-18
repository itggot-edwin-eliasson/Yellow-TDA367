package View;

import Model.*;
import Model.Observer;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.*;
import java.util.*;

/**
 * @date 2018-10-02
 * ---
 * 05/10 Modified by Viktor. Added methods to save and load groups, inventories and users. Also getters/setters.
 *
 *
 *@author Viktor
 *@date 2018-10-04
 *
 */
public class MainController extends ViewController implements Observer {

    private List<GroupInterface> allGroups = new ArrayList<>();
    private List<UserInterface> allUsers = new ArrayList<>();
    private List<InventoryInterface> allInventories = new ArrayList<>();
    private Map<ItemInterface, ItemItemController> itemItemControllerMap = new HashMap<>();

    private EventHandler<MouseEvent> groupItemClick;
    private EventHandler<MouseEvent> inventoryItemClick;

    @FXML private SignUpViewController signUpController;
    @FXML private LoginViewController loginController;
    @FXML private UserSettingsController userSettingsController;
    @FXML private ManageMyYellowController manageMyYellowController;
    @FXML private MenuScreenController menuScreenController;

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
        hamburgerSetup();

        //deseralize("groups");
        //deseralize("users");
        //deseralize("inventories");

    }

    /*private void start(){
        signUpController.toLoginScreen(event -> {
            toLoginScreen();
            event.consume();
        });
        signUpController.signUp(event -> {
            createUser(signUpController.getUsername(),signUpController.getFirstName(), signUpController.getLastName(),
                        signUpController.getEmail(), signUpController.getPassword());
            goToMenuScreen();
            event.consume();
        });
        loginController.toSignupScreen(event -> {
            goToSignUp();
            event.consume();
        });
        loginController.login(event -> {
            login(loginController.getUsername(),loginController.getPassword());
            event.consume();
        });
        userSettingsController.changeUserSettings(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeUserSettings(userSettingsController.getFirstName(), userSettingsController.getLastName(),
                        userSettingsController.getUsername(), userSettingsController.getEmail(),
                        userSettingsController.getPassword());
                goToMainWindow();
            }
        });
        manageMyYellowController.backToManageMyYellow(event -> {
            menuScreen.toFront();
        });
        menuScreenController.toMyYellow(event ->{
            mainWindow.toFront();
        });

        menuScreenController.toManageMyYellow(event -> {
            manageMyYellow.toFront();
        });

        menuScreenController.toCreateGroupPopUp(event -> {
            createGroup();
        });

        manageMyYellowController.addGroup(event -> {
            createGroup();
        });


    }*/

    public List<String> getSignUpInfo(){
        List<String> list = new ArrayList<>();
        list.add(userSettingsController.getFirstName());
        list.add(userSettingsController.getLastName());
        list.add(userSettingsController.getUsername());
        list.add(userSettingsController.getEmail());
        list.add(userSettingsController.getPassword());
        return list;
    }

    private void login(String username, String password)  {
        if (yh.logIn(username, password)) {
            goToMenuScreen();
        }

    }

    /*private void createUser (String username, String firstName, String lastName, String email, String password) {
        UserInterface user = yh.createUser(username, firstName, lastName, email, password);
        saveUser(user);
    }*/

    public void addToOrder (int amount, String itemId) {
        yh.addItemToOrder(itemId);
    }

    private void changeUserSettings (String firstName, String lastName, String username, String email, String password) {
        yh.changeUserSettings(firstName, lastName, username, email, password);
    }

    @FXML
    public void goToUserSettings () {
        userSettings.toFront();
        userSettingsController.setFields(super.yh.getActiveUser());


    }

    @FXML
    public void createGroup(){
        JFXDialogLayout content = new JFXDialogLayout();
        Label text = new Label("Create a group");
        text.setStyle("-fx-text-fill: white");
        content.setHeading(text);

        GridPane g = new GridPane();
        JFXTextField textField = new JFXTextField();
        textField.setPromptText("Group name");
        textField.setStyle("-fx-text-fill: white; -fx-prompt-text-fill: rgba(255,255,255,0.75)");
        textField.setFocusColor(Paint.valueOf("#ffcc00"));
        textField.setUnFocusColor(Paint.valueOf("#fffdfd"));
        g.setHgap(5);
        g.setVgap(5);
        g.add(textField, 1, 1);
        Label label = new Label("Pick a color:");
        label.setStyle("-fx-text-fill: white");
        g.add(label, 1,2);
        JFXColorPicker colorPicker = new JFXColorPicker(Color.web("#1f1e19"));
        g.add(colorPicker, 2,2);
        content.setBody(g);
        JFXDialog dialog = new JFXDialog(mainWindow, content, JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("Create group");
        button.setStyle("-fx-background-color: #ffcc00");
        button.setOnAction(event -> {
            String hex = "#" + Integer.toHexString(colorPicker.getValue().hashCode());
            super.yh.createGroup(textField.getText(), hex);
            dialog.close();
        });
        content.setActions(button);
        dialog.show();

    }


    private void selectGroup(GroupItemController groupItem, Map<GroupInterface, GroupItemController> groupItemControllerMap){
        for(GroupInterface tmpGroup: groupItemControllerMap.keySet()) {
            if (groupItemControllerMap.get(tmpGroup).equals(groupItem)) {
                yh.setActiveGroup(tmpGroup);
                title.setText(tmpGroup.getName());
                backButton.setVisible(true);
                listFlowPane.toFront();
                drawer.toFront();
                hamburger.toFront();
                updateItemList();
                //updateInventoryList();
            }
        }

    }

    public void selectGroup(){
        title.setText(super.yh.getActiveGroup().getName());
        backButton.setVisible(true);
        backToMenuButton.setVisible(false);
        orderButton.setVisible(true);
        listFlowPane.toFront();
        drawer.toFront();
        hamburger.toFront();
        updateInventoryList();
        updateItemList();
    }

    public void backToGroups(){
        backButton.setVisible(false);
        backToMenuButton.setVisible(true);
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

    @FXML
    private void createItem(){
        JFXDialogLayout content = new JFXDialogLayout();
        Label text = new Label("Add an item");
        text.setStyle("-fx-text-fill: white");
        content.setHeading(text);

        GridPane g = new GridPane();
        JFXTextField nameTextField = new JFXTextField();
        nameTextField.setPromptText("Item name");
        nameTextField.setStyle("-fx-text-fill: white; -fx-prompt-text-fill: rgba(255,255,255,0.75)");
        nameTextField.setFocusColor(Paint.valueOf("#ffcc00"));
        nameTextField.setUnFocusColor(Paint.valueOf("#fffdfd"));
        JFXTextField descriptionTextField = new JFXTextField();
        descriptionTextField.setPromptText("Item description");
        descriptionTextField.setStyle("-fx-text-fill: white; -fx-prompt-text-fill: rgba(255,255,255,0.75)");
        descriptionTextField.setFocusColor(Paint.valueOf("#ffcc00"));
        descriptionTextField.setUnFocusColor(Paint.valueOf("#fffdfd"));
        g.setHgap(5);
        g.setVgap(5);
        g.add(nameTextField, 1, 1);
        g.add(descriptionTextField, 1,2);
        content.setBody(g);
        JFXDialog dialog = new JFXDialog(mainWindow, content, JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("Add item");
        button.setStyle("-fx-background-color: #ffcc00");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                yh.addItem(nameTextField.getText(), descriptionTextField.getText(), yh.getActiveGroup().getSelectedInventory().getID(), 1);
                updateItemList();
                dialog.close();
            }
        });
        content.setActions(button);
        dialog.show();
    }


    public void updateGroupList(){
        List<GroupInterface> groups = super.yh.getGroups();
        groupListFlowPane.getChildren().clear();
        for(GroupInterface group: groups){
            GroupItemController item = new GroupItemController(group);
            item.selectGroup(groupItemClick);
            groupListFlowPane.getChildren().add(item);
        }
    }

    public void updateInventoryList(){
        List<InventoryInterface> inventories = super.yh.getInventories();
        groupListFlowPane.getChildren().clear();
        for(InventoryInterface inventory: inventories){
            InventoryItemController item = new InventoryItemController(inventory);
            item.selectInventory(event -> {
                event.consume();
            });
            groupListFlowPane.getChildren().add(item);
        }
    }

    private void updateItemList(){
        List<ItemInterface> items = super.yh.getItems();
        listFlowPane.getChildren().clear();
        for(ItemInterface item: items){
            ItemItemController itemItem = new ItemItemController(item);
            listFlowPane.getChildren().add(itemItem);
        }
    }


    private void goToSignUp () { signUp.toFront(); }

    public void goToMainWindow () {
        mainWindow.toFront();
    }
    public void goToMenuScreen(){menuScreen.toFront();}
    public void goToManageMyYellow(){manageMyYellow.toFront();}

    private void toLoginScreen () { login.toFront(); }


    public void saveGroup (GroupInterface group){
        allGroups.add(group);
    }
    public void saveInventory (InventoryInterface inventory){
        allInventories.add(inventory);
    }
    public void saveUser (UserInterface user){
        allUsers.add(user);
    }
    public List<GroupInterface> getAllGroups (){
        return allGroups;
    }
    public List<InventoryInterface> getAllInventories(){
        return allInventories;
    }
    public List <UserInterface> getAllUsers (){
        return allUsers;
    }



    public void showOrderPane(){
        orderBorderPane.toFront();
        drawer.toFront();
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

    private void hamburgerSetup(){

        VBox box;
        try {
            box = FXMLLoader.load(getClass().getResource("../drawer.fxml"));
            drawer.setSidePane(box);

            drawer.setSidePane(box);

            HamburgerBasicCloseTransition hamTask = new HamburgerBasicCloseTransition(hamburger);
            hamTask.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                hamTask.setRate(hamTask.getRate() * -1);
                hamTask.play();


                if (drawer.isOpened()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

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

    }
}

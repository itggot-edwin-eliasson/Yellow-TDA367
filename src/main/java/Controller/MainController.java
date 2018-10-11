package Controller;

import Model.*;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

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
public class MainController implements Initializable {



    private List<GroupInterface> allGroups = new ArrayList<>();
    private List<UserInterface> allUsers = new ArrayList<>();
    private List<Inventory> allInventories = new ArrayList<>();
    private YellowHandlerInterface yh;

    private Map<GroupInterface, GroupItemController> groupItemControllerMap = new HashMap<>();
    private Map<ItemInterface, ItemItemController> itemItemControllerMap = new HashMap<>();
    private Map<InventoryInterface, InventoryItemController> inventoryItemControllerMap = new HashMap<>();


    @FXML private SignUpController signUpController;
    @FXML private LoginController loginController;
    @FXML private UserSettingsController userSettingsController;

    @FXML private JFXDrawer drawer;

    @FXML private JFXHamburger hamburger;

    @FXML private AnchorPane signUp;
    @FXML private AnchorPane login;
    @FXML private AnchorPane userSettings;
    @FXML private StackPane mainWindow;
    @FXML private FlowPane groupListFlowPane;
    @FXML private FlowPane listFlowPane;

    @FXML private JFXButton userSettingsButton;
    @FXML private Label title;
    @FXML private Button addGroupButton;
    @FXML private Button backButton;
    @FXML private Button addItemButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hamburgerSetup();
        signUpController.injectMainController(this);
        loginController.injectMainController(this);
        userSettingsController.injectMainController(this);
        deseralize("groups");
        deseralize("users");
        deseralize("inventories");
        this.yh = new YellowHandler(allUsers, allGroups);

        updateGroupItemMap();
        updateGroupList();
    }

    public void login(String username, String password)  {
        if (yh.logIn(username, password)) {
            goToMainWindow();
        }

    }

    public void createUser (String username, String firstName, String lastName, String email, String password) {
        UserInterface user = yh.createUser(username, firstName, lastName, email, password);
        saveUser(user);
    }

    public void addToOrder (int amount, String itemId) {
        yh.addItemToOrder(amount, itemId);
    }

    public void changeUserSettings (String firstName, String lastName, String username, String email, String password) {
        yh.changeUserSettings(firstName, lastName, username, email, password);
    }

    @FXML
    public void goToUserSettings () {
        userSettings.toFront();
        userSettingsController.setFields(yh.getActiveUser());


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
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String hex = "#" + Integer.toHexString(colorPicker.getValue().hashCode());
                yh.createGroup(textField.getText(), hex);
                updateGroupItemMap();
                updateGroupList();
                dialog.close();
            }
        });
        content.setActions(button);
        dialog.show();

    }

    public void selectGroup(GroupItemController groupItem){
        for(GroupInterface tmpGroup: groupItemControllerMap.keySet()) {
            if (groupItemControllerMap.get(tmpGroup).equals(groupItem)) {
                yh.setActiveGroup(tmpGroup);
                title.setText(tmpGroup.getName());
                backButton.setVisible(true);
                listFlowPane.toFront();
                drawer.toFront();
                hamburger.toFront();
                updateItemList();
                updateItemItemMap();
                updateInventoryItemMap();
                updateInventoryList();
            }
        }

    }

    @FXML
    private void backToGroups(){
        backButton.setVisible(false);
        title.setText("Groups");
        updateGroupList();
    }

    @FXML
    private void createItem(){
        JFXDialogLayout content = new JFXDialogLayout();
        Label text = new Label("Create an item");
        text.setStyle("-fx-text-fill: white");
        content.setHeading(text);

        GridPane g = new GridPane();
        JFXTextField nameTextField = new JFXTextField();
        nameTextField.setPromptText("Item name");
        nameTextField.setStyle("-fx-text-fill: white; -fx-prompt-text-fill: rgba(255,255,255,0.75)");
        nameTextField.setFocusColor(Paint.valueOf("#ffcc00"));
        nameTextField.setUnFocusColor(Paint.valueOf("#fffdfd"));
        g.setHgap(5);
        g.setVgap(5);
        g.add(nameTextField, 1, 1);
        content.setBody(g);
        JFXDialog dialog = new JFXDialog(mainWindow, content, JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("Create group");
        button.setStyle("-fx-background-color: #ffcc00");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
           //     yh.addItem();
                updateGroupItemMap();
                updateGroupList();
                dialog.close();
            }
        });
        content.setActions(button);
        dialog.show();
    }

    @FXML
    public void exitApplication (ActionEvent event) {
        seralize("groups");
        seralize("inventories");
        seralize("users");
        Platform.exit();
    }

    private void updateGroupItemMap(){
        List<GroupInterface> groups = yh.getGroups();
        for(GroupInterface group: groups){
            GroupItemController item = new GroupItemController(group.getName(), group.getColor(), this);
            groupItemControllerMap.put(group, item);
        }
    }

    private void updateGroupList(){
        groupListFlowPane.getChildren().clear();
        GroupItemController item;
        for(GroupInterface group: groupItemControllerMap.keySet()){
            item = groupItemControllerMap.get(group);
            groupListFlowPane.getChildren().add(item);
        }
    }

    private void updateInventoryItemMap(){
        List<InventoryInterface> inventories = yh.getInventories();
        inventoryItemControllerMap.clear();
        for(InventoryInterface inventory: inventories){
            InventoryItemController item = new InventoryItemController(inventory.getName(), this);
            inventoryItemControllerMap.put(inventory, item);
        }
    }

    private void updateInventoryList(){
        groupListFlowPane.getChildren().clear();
        InventoryItemController item;
        for(InventoryInterface inventory: inventoryItemControllerMap.keySet()){
            item = inventoryItemControllerMap.get(inventory);
            groupListFlowPane.getChildren().add(item);
        }
    }

    private void updateItemItemMap(){
        List<ItemInterface> items = yh.getItems();
        for(ItemInterface item: items){
            ItemItemController itemItem = new ItemItemController(item.getName(), item.getImage(), this);
            itemItemControllerMap.put(item,itemItem);
        }
    }

    private void updateItemList(){
        listFlowPane.getChildren().clear();
        ItemItemController itemItem;
        for(ItemInterface item: itemItemControllerMap.keySet()){
            itemItem = itemItemControllerMap.get(item);
            listFlowPane.getChildren().add(itemItem);
        }
    }


    public void goToSignUp () { signUp.toFront(); }

    public void goToMainWindow () {
        mainWindow.toFront();
    }

    public void toLoginScreen () { login.toFront(); }


    public void saveGroup (Group group){
        allGroups.add(group);
    }
    public void saveInventory (Inventory inventory){
        allInventories.add(inventory);
    }
    public void saveUser (UserInterface user){
        allUsers.add(user);
    }
    public List<GroupInterface> getAllGroups (){
        return allGroups;
    }
    public List<Inventory> getAllInventories(){
        return allInventories;
    }
    public List <UserInterface> getAllUsers (){
        return allUsers;
    }


    /**
     * Saves the lists allGroups, allInventories or allUsers to three different .ser files. What files that
     * will get saved is based on the @param.
     * @param whatToSeralize should be groups, inventories or users, based on what is to be saved.
     */
    public void seralize(String whatToSeralize){
        try {
            FileOutputStream fileOut;
            switch (whatToSeralize) {
                case "groups":
                    fileOut =
                            new FileOutputStream("src/main/resources/Database/allGroups.ser");
                            System.out.println("fetching groups");
                    break;
                case "inventories":
                    fileOut =
                            new FileOutputStream("src/main/resources/Database/allInventories.ser");
                            System.out.println("fetching inventories");
                    break;
                case "users":
                    fileOut =
                            new FileOutputStream("src/main/resources/Database/allUsers.ser");
                            System.out.println("fetching users");
                    break;
                default:
                    fileOut = new FileOutputStream("src/main/resources/Database/errorSave.ser");
                    break;
            }
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            if(whatToSeralize.equals("groups")) {
                out.writeObject(allGroups);
            }
            if(whatToSeralize.equals("inventories")){
                out.writeObject(allInventories);
            }
            if(whatToSeralize.equals("users")){
                out.writeObject(allUsers);
            }
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /"+ whatToSeralize + ".ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * This method will fetch the allUsers, allGroups and allInventories lists from the local database.
     *
     * @param whatToDeseralize should be groups, inventories or users depending on what is to be fetched.
     */
    public void deseralize(String whatToDeseralize){
        try {
            FileInputStream fileIn;
            switch (whatToDeseralize) {
                case "groups":
                    fileIn =
                            new FileInputStream("src/main/resources/Database/allGroups.ser");
                            System.out.println("fetching groups");
                    break;
                case "inventories":
                    fileIn =
                            new FileInputStream("src/main/resources/Database/allInventories.ser");
                            System.out.println("fetching inventories");
                    break;
                case "users":
                    fileIn =
                            new FileInputStream("src/main/resources/Database/allUsers.ser");
                            System.out.println("fetching users");
                    break;
                default:
                    fileIn = new FileInputStream("src/main/resources/Database/errorSave.ser");
                    break;
            }
            ObjectInputStream in = new ObjectInputStream(fileIn);
            if(whatToDeseralize.equals("groups")) {
                allGroups = (List<GroupInterface>) in.readObject();
            }
            if(whatToDeseralize.equals("inventories")){
                allInventories = (List<Inventory>) in.readObject();
            }
            if(whatToDeseralize.equals("users")){
                allUsers = (List<UserInterface>) in.readObject();
            }
            in.close();
            fileIn.close();
        } catch (IOException i) {
            seralize(whatToDeseralize);
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }

    }

    private void hamburgerSetup(){

        VBox box = null;
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



}

package Controller;

import Model.*;
import View.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private YellowHandlerInterface yh;

    private List<GroupInterface> groups;
    private List<UserInterface> users;

    private Map<GroupInterface, GroupItemController> groupItemControllerMap = new HashMap<>();
    private Map<InventoryInterface, InventoryItemController> inventoryItemControllerMap = new HashMap<>();


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        users = new ArrayList<>();
        groups = new ArrayList<>();
        this.yh = new YellowHandler(users,groups);

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("Yellow");

        stage.setTitle(bundle.getString("application.name"));
        initRoot();

        showLogin();
    }

    private void initRoot(){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../rootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showYellow(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../yellow.fxml"));
            AnchorPane yellow = (AnchorPane) loader.load();

            MainController mainController = loader.getController();
            mainController.injectYellowHandler(yh);
            updateGroupItemMap(mainController);
            mainController.updateGroupList(groupItemControllerMap);

            mainController.setBackToGroupsListener(event -> {
                mainController.backToGroups(groupItemControllerMap);
                event.consume();
            });

            rootLayout.setCenter(yellow);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showLogin(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../logIn.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            LoginViewController controller = loader.getController();
            controller.injectYellowHandler(yh);
            controller.toSignupScreen(event -> {
                showSignUp();
            });
            controller.login(event -> {
                if(yh.logIn(controller.getUsername(),controller.getPassword())){
                    showYellow();
                }
                else {
                    //show error message
                    System.out.println("Finns ingen");
                }
                event.consume();
            });


            rootLayout.setCenter(login);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showSignUp(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../signUp.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            SignUpViewController controller = loader.getController();
            controller.injectYellowHandler(yh);
            controller.signUp(event -> {
                if(yh.createUser(controller.getUsername(), controller.getFirstName(), controller.getLastName(),
                        controller.getEmail(), controller.getPassword())) {
                    showMenuScreen();
                }
                else {
                    //TO DO:error message
                }
                event.consume();
            });

            rootLayout.setCenter(login);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showMenuScreen(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../firstYellowScreen.fxml"));
            AnchorPane menuScreen = (AnchorPane) loader.load();

            MenuScreenController controller = loader.getController();
            controller.injectYellowHandler(yh);
            controller.toMyYellow(event -> {
                showYellow();
                event.consume();
            });
            controller.joinGroup(event -> {
                showJoinGroupDialog();
                event.consume();
            });
            controller.toManageMyYellow(event -> {
                showManageMyYellow();
                event.consume();
            });
            controller.toCreateGroupPopUp(event -> {
                showCreateGroupDialog();
                event.consume();
            });

            rootLayout.setCenter(menuScreen);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showManageMyYellow(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../manageMyYellow.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            ManageMyYellowController controller = loader.getController();
            controller.injectYellowHandler(yh);
            controller.addGroup(event -> {
                showCreateGroupDialog();
                event.consume();
            });
            controller.addInventory(event -> {
                showAddInventoryDialog();
                event.consume();
            });
            controller.addItem(event -> {
                showCreateItemDialog();
                event.consume();
            });
            controller.backToManageMyYellow(event -> {
                showMenuScreen();
                event.consume();
            });

            rootLayout.setCenter(login);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showCreateItemDialog () {

        // Load the fxml file and create a new stage for the popup dialog.

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../addItem.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AddItemViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                yh.addItem(controller.getItemName(), controller.getItemDescription(),
                        yh.getActiveGroup().getSelectedInventory().getID(),1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showJoinGroupDialog(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../joinGroupDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Join group");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            JoinGroupDialogViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it

            dialogStage.show();

            controller.handleJoinGroup(event -> {
                int inviteCode = 0;
                if(controller.getJoinGroupTextField().length() == 4)
                    inviteCode = Integer.valueOf(controller.getJoinGroupTextField());
                if (yh.joinGroup(inviteCode)) {
                    dialogStage.close();
                }
                else {
                    System.out.println("No group found");
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showAddInventoryDialog(){
        // Load the fxml file and create a new stage for the popup dialog.

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../addInventory.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AddInventoryViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                yh.createInventory(controller.getInventoryName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateGroupDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../createGroupDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CreateGroupDialogViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


            if(controller.isOkClicked())
                yh.createGroup(controller.getGroupName(), controller.getGroupColor());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    private ViewController loader(String fileName, ViewController controller){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../" + fileName + ".fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            rootLayout.setCenter(login);

            controller = loader.getController();
            controller.injectYellowHandler(yh);
            return controller;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    */

    private void updateGroupItemMap(MainController mainController){
        List<GroupInterface> groups = yh.getGroups();
        groupItemControllerMap.clear();
        for(GroupInterface group: groups){
            GroupItemController item = new GroupItemController(group.getName(), group.getColor());
            item.selectGroup(event -> {
                yh.setActiveGroup(group);
                mainController.selectGroup();
                updateInventoryItemMap();
                mainController.updateInventoryList(inventoryItemControllerMap);
                event.consume();
            });
            groupItemControllerMap.put(group, item);
        }
    }

    private void updateInventoryItemMap(){
        List<InventoryInterface> inventories = yh.getInventories();
        inventoryItemControllerMap.clear();
        for(InventoryInterface inventory: inventories){
            InventoryItemController item = new InventoryItemController(inventory.getName());
            inventoryItemControllerMap.put(inventory, item);
        }
    }

    @Override
    public void stop() {
        System.out.println("Hejdå");

    }

}

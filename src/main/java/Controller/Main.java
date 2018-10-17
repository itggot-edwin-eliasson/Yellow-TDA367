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
    private AnchorPane menuScreen;
    private AnchorPane yellow;
    private AnchorPane loginScreen;
    private AnchorPane signUpScreen;
    private AnchorPane manageMyYellowScreen;

    private FXMLLoader menuScreenLoader;
    private FXMLLoader yellowLoader;
    private FXMLLoader loginLoader;
    private FXMLLoader signUpLoader;
    private FXMLLoader manageMyYellowLoader;

    private YellowHandler yh;

    private List<GroupInterface> groups;
    private List<UserInterface> users;

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

        menuScreenSetup();
        yellowSetup();
        menuScreenSetup();
        loginSetup();
        signUpSetup();
        manageMyYellowSetup();

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

        rootLayout.setCenter(yellow);

    }

    private void yellowSetup(){
        try {
            yellowLoader = new FXMLLoader();
            yellowLoader.setLocation(Main.class.getResource("../yellow.fxml"));
            yellow = (AnchorPane) yellowLoader.load();

            MainController mainController = yellowLoader.getController();
            yh.addObserver(mainController);
            mainController.injectYellowHandler(yh);
            mainController.injectGroupItemListener(event -> {
                GroupItemController item = (GroupItemController)event.getSource();
                yh.setActiveGroup(item.getGroup());
                mainController.selectGroup();
                event.consume();
            });
            mainController.updateGroupList();

            mainController.setBackToGroupsListener(event -> {
                mainController.backToGroups();
                event.consume();
            });
            mainController.setBackToMenuListener(event -> {
                showMenuScreen();
                event.consume();
            });

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showLogin(){
        rootLayout.setCenter(loginScreen);
    }

    private void loginSetup(){
        try {
            loginLoader = new FXMLLoader();
            loginLoader.setLocation(Main.class.getResource("../logIn.fxml"));
            loginScreen = (AnchorPane) loginLoader.load();

            LoginViewController controller = loginLoader.getController();
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

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showSignUp(){
        rootLayout.setCenter(signUpScreen);
    }

    private void signUpSetup(){
        try {
            signUpLoader = new FXMLLoader();
            signUpLoader.setLocation(Main.class.getResource("../signUp.fxml"));
            signUpScreen = (AnchorPane) signUpLoader.load();

            SignUpViewController controller = signUpLoader.getController();
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

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showMenuScreen(){
            MenuScreenController controller = menuScreenLoader.getController();

            rootLayout.setCenter(menuScreen);

    }

    private void menuScreenSetup(){
        try {
            menuScreenLoader = new FXMLLoader();
            menuScreenLoader.setLocation(Main.class.getResource("../firstYellowScreen.fxml"));
            menuScreen = menuScreenLoader.load();

            MenuScreenController controller = menuScreenLoader.getController();
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

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showManageMyYellow(){
        rootLayout.setCenter(manageMyYellowScreen);
    }

    private void manageMyYellowSetup(){
        try {
            manageMyYellowLoader = new FXMLLoader();
            manageMyYellowLoader.setLocation(Main.class.getResource("../manageMyYellow.fxml"));
            manageMyYellowScreen = (AnchorPane) manageMyYellowLoader.load();

            ManageMyYellowController controller = manageMyYellowLoader.getController();
            yh.addObserver(controller);
            controller.injectYellowHandler(yh);
            controller.injectGroupItemListener(event -> {
                ManageGroupItemViewController item = (ManageGroupItemViewController) event.getSource();
                yh.setActiveGroup(item.getGroup());
                event.consume();
            });
            controller.injectInventoryItemListener(event -> {
                ManageInventoryItemViewController item = (ManageInventoryItemViewController) event.getSource();
                yh.selectInventory(item.getInventory().getID());
                event.consume();
            });
            controller.updateGroupList();

            controller.addGroup(event -> {
                showCreateGroupDialog();
                event.consume();
            });
            controller.addInventory(event -> {
                if(yh.getActiveGroup() != null)
                    showAddInventoryDialog();
                event.consume();
            });
            controller.addItem(event -> {
                if(yh.getActiveGroup().getSelectedInventory() != null)
                    showCreateItemDialog();
                event.consume();
            });
            controller.backToManageMyYellow(event -> {
                showMenuScreen();
                event.consume();
            });

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

    @Override
    public void stop() {
        System.out.println("Hejdå");

    }

}

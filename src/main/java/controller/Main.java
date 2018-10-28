package controller;

import model.*;
import utils.DataHandler;
import view.*;
import com.jfoenix.controls.JFXSnackbar;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;

import javafx.stage.FileChooser;
import javafx.scene.image.Image;


import javax.imageio.ImageIO;
import java.io.*;
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
    private YellowHandlerInterface yh;
    private DataHandler dataHandler;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.yh = new YellowHandler(); //(users,groups);
        this.dataHandler = new DataHandler();

        dataHandler.deserialize("users", this.yh);
        dataHandler.deserialize("groups", this.yh);

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
        yh.setActiveGroup(null);
        rootLayout.setCenter(yellow);
    }

    private void yellowSetup(){
        try {
            yellowLoader = new FXMLLoader();
            yellowLoader.setLocation(Main.class.getResource("../yellow.fxml"));
            yellow = (AnchorPane) yellowLoader.load();

            YellowView yellowView = yellowLoader.getController();
            yh.addObserver(yellowView);
            yellowView.injectYellowHandler(yh);
            yellowView.injectGroupItemListener(event -> {
                GroupItemView item = (GroupItemView)event.getSource();
                yh.setActiveGroup(item.getGroup());
                yellowView.selectGroup();
                event.consume();
            });
            yellowView.injectInventoryItemListener(event -> {
                InventoryItemView item = (InventoryItemView) event.getSource();
                yh.selectInventory(item.getInventory().getID());
                yellowView.hideOrderPane();
                yellowView.updateItemList();
                yellowView.updateInventoryList();
                event.consume();
            });

            FXMLLoader orderLoader = new FXMLLoader();
            orderLoader.setLocation(Main.class.getResource("../orderView.fxml"));
            AnchorPane order = (AnchorPane) orderLoader.load();

            OrderView orderController = orderLoader.getController();

            FXMLLoader drawerContentLoader = new FXMLLoader();
            drawerContentLoader.setLocation(Main.class.getResource("../drawer.fxml"));
            VBox drawerContent = (VBox) drawerContentLoader.load();
            yellowView.hamburgerSetup(drawerContent);

            DrawerView drawerView = drawerContentLoader.getController();


            FXMLLoader activeOrderLoader = new FXMLLoader();
            activeOrderLoader.setLocation(Main.class.getResource("../activeOrder.fxml"));
            AnchorPane activeOrder = (AnchorPane) activeOrderLoader.load();

            ActiveOrderView activeOrderController = activeOrderLoader.getController();

            activeOrderController.injectYellowHandler(yh);
            activeOrderController.injectOrderItemListener(event -> {
                Button button = (Button) event.getSource();
                ActiveOrderItemView orderItem = (ActiveOrderItemView) button.getParent();
                yh.removeItemFromOrder(orderItem.getItem());
                activeOrderController.updateActiveOrders();
                event.consume();
            });
            yh.addObserver(activeOrderController);

            FXMLLoader ongoingOrdersLoader = new FXMLLoader();
            ongoingOrdersLoader.setLocation(Main.class.getResource("../ongoingOrders.fxml"));
            AnchorPane ongoingOrders = (AnchorPane) ongoingOrdersLoader.load();

            OngoingOrdersView ongoingOrdersController = ongoingOrdersLoader.getController();

            ongoingOrdersController.injectYellowHandler(yh);

            yh.addObserver(ongoingOrdersController);

            FXMLLoader oldOrdersLoader = new FXMLLoader();
            oldOrdersLoader.setLocation(Main.class.getResource("../oldOrdersView.fxml"));
            AnchorPane oldOrders = (AnchorPane) oldOrdersLoader.load();

            OldOrdersView oldOrdersController = oldOrdersLoader.getController();

            oldOrdersController.injectYellowHandler(yh);

            yh.addObserver(oldOrdersController);

            yellowView.injectItemItemListener(event -> {
                ItemItemView item = (ItemItemView) event.getSource();
                showItemViewDialog(item.getItem());
                event.consume();
            });

            yellowView.setOrderPane(order);
            orderController.setOrderScrollPane(activeOrder);

            orderController.setActiveOrderButton(event -> {
                orderController.setOrderScrollPane(activeOrder);
                event.consume();
            });
            orderController.setOngoingOrderButton(event -> {
                orderController.setOrderScrollPane(ongoingOrders);
                event.consume();
            });
            orderController.setOldOrderButton(event -> {
                orderController.setOrderScrollPane(oldOrders);
                event.consume();
            });
            activeOrderController.confirmOrderButton(event -> {
                if(!yh.completeOrder(activeOrderController.getStartDate(), activeOrderController.getReturnDate(),
                        activeOrderController.getRenterName(),activeOrderController.getRenterPhoneNumber())) {
                    yh.completeOrderFailed();
                    JFXSnackbar snackbar = new JFXSnackbar(yellow);
                    snackbar.show(yh.completeOrderFailed(),5000);
                }
            });

            yellowView.updateGroupList();

            yellowView.setOrderButton(event -> {
                yellowView.showOrderPane();
                activeOrderController.updateActiveOrders();
                event.consume();
            });
            yellowView.setBackToGroupsListener(event -> {
                yh.setActiveGroup(null);
                yellowView.backToGroups();
                event.consume();
            });
            yellowView.setBackToMenuListener(event -> {
                showMenuScreen();
                event.consume();
            });
            yellowView.setBackToItemsButton(event -> {
                yellowView.hideOrderPane();
                event.consume();
            });
            drawerView.logOut(event -> {
                yh.logOut();
                yh.setActiveUserToNull();
                showLogin();
            });

            drawerView.toUserSettings(event -> {
            showUserSettingsView();
            });
            drawerView.showGroupInviteCodes(event -> {
                showGroupInviteCodesDialog();
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

            LoginView controller = loginLoader.getController();
            controller.injectYellowHandler(yh);
            controller.toSignupScreen(event -> {
                showSignUp();
            });
            controller.login(event -> {
                if(yh.logIn(controller.getUsername(),controller.getPassword())){
                    showMenuScreen();
                }
                else {
                    JFXSnackbar snackbar = new JFXSnackbar(loginScreen);
                    snackbar.show("No user found with that password",3000);
                }
                event.consume();
            });

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private Callback<DatePicker, DateCell> getDayCellFactoryItem(ItemInterface theItem) {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        //disables a date
                        if (!theItem.checkDateIsNotInRentedPeriod(item.toString())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }

                };
            }
        };
        return dayCellFactory;
    }
    private Callback<DatePicker, DateCell> getDayCellFactoryOrder(OrderInterface order) {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        //disables a date
                        if(order.getOrderList().size()>0){
                            if (!order.getOrderList().get(0).checkDateIsNotInRentedPeriod(item.toString())) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        }

                    }

                };
            }
        };
        return dayCellFactory;
    }

    private void showSignUp(){
        rootLayout.setCenter(signUpScreen);
    }

    private void signUpSetup(){
        try {
            signUpLoader = new FXMLLoader();
            signUpLoader.setLocation(Main.class.getResource("../signUp.fxml"));
            signUpScreen = (AnchorPane) signUpLoader.load();
            yh.logOut();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../signUp.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            SignUpView controller = signUpLoader.getController();
            controller.injectYellowHandler(yh);
            controller.signUp(event -> {
                if(controller.getUsername().length()<5){
                    JFXSnackbar snackbar = new JFXSnackbar(signUpScreen);
                    snackbar.show("Username must be 5 characters or more",3000);
                    return;
                }if(controller.getPassword().length()<5){
                    JFXSnackbar snackbar = new JFXSnackbar(signUpScreen);
                    snackbar.show("Password must be 5 characters or more",3000);
                    return;
                }
                if(yh.createUser(controller.getUsername(), controller.getFirstName(), controller.getLastName(),
                        controller.getEmail(), controller.getPassword())) {
                    showMenuScreen();
                }
                else {
                    JFXSnackbar snackbar = new JFXSnackbar(signUpScreen);
                    snackbar.show("A user with that name already exist.",3000);
                }
                event.consume();
            });
            controller.toLoginScreen(event -> {
                showLogin();
                event.consume();
            });

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showMenuScreen(){
            MenuScreenView controller = menuScreenLoader.getController();

            rootLayout.setCenter(menuScreen);

    }

    private void menuScreenSetup(){
        try {
            menuScreenLoader = new FXMLLoader();
            menuScreenLoader.setLocation(Main.class.getResource("../menuScreen.fxml"));
            menuScreen = menuScreenLoader.load();

            MenuScreenView controller = menuScreenLoader.getController();
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
            controller.signOut(event -> {
                showLogin();
                yh.logOut();
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

            ManageMyYellow controller = manageMyYellowLoader.getController();
            yh.addObserver(controller);
            controller.injectYellowHandler(yh);
            controller.injectGroupItemListener(event -> {
                ManageGroupItemView item = (ManageGroupItemView) event.getSource();
                item.setHighlighted();
                yh.setActiveGroup(item.getGroup());
                event.consume();
            });
            controller.injectInventoryItemListener(event -> {
                ManageInventoryItemView item = (ManageInventoryItemView) event.getSource();
                item.setHighlighted();
                yh.selectInventory(item.getInventory().getID());
                controller.update();
                controller.updateItemList();
                event.consume();
            });
            controller.updateGroupList();

            controller.addGroup(event -> {
                showCreateGroupDialog();
                event.consume();
            });
            controller.addInventory(event -> {
                if(yh.getActiveGroup() != null)
                    showCreateInventoryDialog();
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
            controller.goToMyYellow(event -> {
                showYellow();
                event.consume();
            });

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void showItemViewDialog (ItemInterface item) {

        // Load the fxml file and create a new stage for the popup dialog.

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../itemView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ItemView controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setItem(item);
            //controller.setDialogStage(dialogStage);
            Stage stage = (Stage) controller.getitemCalendarAnchorPane().getScene().getWindow();
            Callback<DatePicker, DateCell> callback =getDayCellFactoryItem(item);
            CalendarView ccontrller = new CalendarView();
            ccontrller.start(stage,callback,controller.getitemCalendarAnchorPane());
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            if(controller.isOkClicked()){
                yh.addItemToOrder(controller.getId());
                JFXSnackbar snackbar = new JFXSnackbar(yellow);
                snackbar.show("Item added to order",3000);
            }

        } catch (IOException e) {
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
            AddItemView controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it


            yh.addObserver(controller);
            controller.injectYellowHandler(yh);

            controller.uploadImage(event -> {
                System.out.println("loading image....");
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png"));
                File selectedFile = fileChooser.showOpenDialog(null);
                if(selectedFile != null){
                    String imagePath = selectedFile.toURI().toString();
                    controller.setImage(new Image(imagePath));
                }
                event.consume();
            });


            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                if(controller.getAmount() != 0){
                    String imageURL = saveToFile(controller.getImage(),controller.getItemName(), 0);
                    yh.addItem(controller.getItemName(), controller.getItemDescription(), imageURL,
                            controller.getGroup(), controller.getInventory().getID(), controller.getAmount());
                }else{
                    JFXSnackbar snackbar = new JFXSnackbar(manageMyYellowScreen);
                    snackbar.show("You must add a proper amount to add item",3000);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String saveToFile(Image image, String itemName, int num) {
        String imageUrl = "";
        File outputFile = new File("src/main/resources/img/" + itemName + num + ".png");
        if(outputFile.exists()) {
            num++;
            return saveToFile(image, itemName, num);
        } else {
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            try {
                ImageIO.write(bImage, "png", outputFile);
                imageUrl = outputFile.toURI().toURL().toString();
                return imageUrl;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showUserSettingsView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../userSettingsView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("User settings");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            UserSettingsView userSettingsView = loader.getController();
            userSettingsView.setDialogStage(dialogStage);
            userSettingsView.injectYellowHandler(yh);
            userSettingsView.setFields();

            // Show the dialog and wait until the user closes it

            dialogStage.show();

            userSettingsView.changeUserSettings(event -> {
                String firstname = userSettingsView.getFirstName();
                String lastName = userSettingsView.getLastName();
                String username = userSettingsView.getUsername();
                String email = userSettingsView.getEmail();
                String password = userSettingsView.getPassword();

                yh.changeUserSettings(firstname, lastName, username, email, password);
                dialogStage.close();

            });

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
            JoinGroupDialogView controller = loader.getController();
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

    private void showGroupInviteCodesDialog(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../viewInviteCodesDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Join group");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ViewInviteCodesDialogView controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.injectYellowHandler(yh);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateInventoryDialog(){
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
            AddInventoryView controller = loader.getController();
            controller.injectYellowHandler(yh);
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                yh.createInventory(controller.getInventoryName(), controller.getSelectedGroup());
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
            CreateGroupDialogView controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


            if(controller.isOkClicked()) {
                yh.createGroup(controller.getGroupName(), controller.getGroupColor());
                JFXSnackbar snackbar = new JFXSnackbar(menuScreen);
                snackbar.show("Group created", 3000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.out.println("Hejdå");
        dataHandler.serialize("users", this.yh);
        dataHandler.serialize("groups", this.yh);
    }

}

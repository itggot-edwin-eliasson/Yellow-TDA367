package Controller;

import Model.*;
import View.*;
import com.jfoenix.controls.JFXSnackbar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;


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

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.yh = new YellowHandler(); //(users,groups);
        deseralize("users");
        deseralize("groups");

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
                GroupItemView item = (GroupItemView)event.getSource();
                yh.setActiveGroup(item.getGroup());
                mainController.selectGroup();
                event.consume();
            });
            mainController.injectInventoryItemListener(event -> {
                InventoryItemView item = (InventoryItemView) event.getSource();
                yh.selectInventory(item.getInventory().getID());
                mainController.hideOrderPane();
                mainController.updateItemList();
                event.consume();
            });

            FXMLLoader orderLoader = new FXMLLoader();
            orderLoader.setLocation(Main.class.getResource("../orderView.fxml"));
            AnchorPane order = (AnchorPane) orderLoader.load();

            OrderView orderController = orderLoader.getController();

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

            mainController.injectItemItemListener(event -> {
                ItemItemView item = (ItemItemView) event.getSource();
                showItemViewDialog(item.getItem());
                event.consume();
            });

            mainController.setOrderPane(order);
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
                activeOrderController.getStartDate();
                activeOrderController.getReturnDate();
            });

            mainController.updateGroupList();

            mainController.setOrderButton(event -> {
                mainController.showOrderPane();
                activeOrderController.updateActiveOrders();
                event.consume();
            });
            mainController.setBackToGroupsListener(event -> {
                yh.setActiveGroup(null);
                mainController.backToGroups();
                event.consume();
            });
            mainController.setBackToMenuListener(event -> {
                showMenuScreen();
                event.consume();
            });
            mainController.setBackToItemsButton(event -> {
                mainController.hideOrderPane();
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

    private void createCalendarOrder(Stage stage){

    }

    private void createCalendarItem(Stage stage, ItemInterface item){
        Callback<DatePicker, DateCell> callback = getDayCellFactoryItem(item);

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
            MenuScreen controller = menuScreenLoader.getController();

            rootLayout.setCenter(menuScreen);

    }

    private void menuScreenSetup(){
        try {
            menuScreenLoader = new FXMLLoader();
            menuScreenLoader.setLocation(Main.class.getResource("../menuScreen.fxml"));
            menuScreen = menuScreenLoader.load();

            MenuScreen controller = menuScreenLoader.getController();
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
                yh.setActiveGroup(item.getGroup());
                event.consume();
            });
            controller.injectInventoryItemListener(event -> {
                ManageInventoryItemView item = (ManageInventoryItemView) event.getSource();
                yh.selectInventory(item.getInventory().getID());
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
            CreateGroupDialogView controller = loader.getController();
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
        seralize("users");
        seralize("groups");

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
                    System.out.println("saving groups");
                    break;
                case "users":
                    fileOut =
                            new FileOutputStream("src/main/resources/Database/allUsers.ser");
                    System.out.println("saving users");
                    break;
                default:
                    fileOut = new FileOutputStream("src/main/resources/Database/errorSave.ser");
                    break;
            }
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            if(whatToSeralize.equals("groups")) {
                out.writeObject(yh.getAllGroups());
            }
            if(whatToSeralize.equals("users")){
                out.writeObject(yh.getUsers());
            }
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in /"+ whatToSeralize + ".ser");
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
                yh.setGroups((List<GroupInterface>) in.readObject()); //= (List<GroupInterface>) in.readObject();
            }
            if(whatToDeseralize.equals("users")){
                yh.setAllUsers((List<UserInterface>) in.readObject());
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

}

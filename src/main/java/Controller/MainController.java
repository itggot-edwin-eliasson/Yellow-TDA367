package Controller;

import Model.*;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
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
public class MainController implements Initializable {

    private List<GroupInterface> allGroups = new ArrayList<>();
    private List<UserInterface> allUsers = new ArrayList<>();
    private List<Inventory> allInventories = new ArrayList<>();
    private YellowHandler yh;

    private Map<String, GroupItemController> groupItemControllerMap = new HashMap<>();


    @FXML private SignUpController signUpController;
    @FXML private LoginController loginController;
    @FXML private UserSettingsController userSettingsController;

    @FXML private JFXDrawer drawer;

    @FXML private JFXHamburger hamburger;

    @FXML private AnchorPane signUp;
    @FXML private AnchorPane login;
    @FXML private StackPane mainWindow;
    @FXML private FlowPane groupListFlowPane;
    @FXML private FlowPane listFlowPane;

    @FXML Button addGroupButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hamburgerSetup();
        signUpController.injectMainController(this);
        loginController.injectMainController(this);
        userSettingsController.injectMainController(this);
        //seralize("");

        this.yh = new YellowHandler(allUsers, allGroups);

        updateGroupItemMap();
        updateGroupList();
    }

    public void login(String username, String password)  { yh.logIn(username, password); }

    public void createUser (String username, String name, String email, String password) {
        UserInterface user = yh.createUser(username, name, email, password);
        saveUser(user);
    }

    public void addToOrder (int amount, String itemId) {
        yh.addItemToOrder(amount, itemId);
    }

    public void changeUserSettings (String name, String username, String email, String password) {
        yh.changeUserSettings(name, username, email, password);
    }

    @FXML
    public void createGroup(){
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Create a group"));
        GridPane g = new GridPane();
        JFXTextField textField = new JFXTextField();
        textField.setPromptText("Group name");
        g.add(textField, 1, 1);
        g.add(new Label("Pick a color"), 1,2);
        JFXColorPicker colorPicker = new JFXColorPicker(Color.web("#1f1e19"));
        g.add(colorPicker, 2,2);
        content.setBody(g);
        JFXDialog dialog = new JFXDialog(mainWindow, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Create group");
        button.setStyle("-fx-background-color: #ffcc00");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                yh.createGroup(textField.getText(), colorPicker.getValue().toString());
                updateGroupItemMap();
                updateGroupList();
                dialog.close();
            }
        });
        content.setActions(button);
        dialog.show();

    }

    private void updateGroupItemMap(){
        List<GroupInterface> groups = yh.getGroups();
        for(GroupInterface group: groups){
            GroupItemController item = new GroupItemController(group.getName(), group.getColor(), this);
            groupItemControllerMap.put(group.getId(), item);
        }
    }

    private void updateGroupList(){
        groupListFlowPane.getChildren().clear();
        GroupItemController item;
        for(String id: groupItemControllerMap.keySet()){
            item = groupItemControllerMap.get(id);
            groupListFlowPane.getChildren().add(item);
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
                            new FileOutputStream("src/main/res/allGroups.ser");
                    break;
                case "inventories":
                    fileOut =
                            new FileOutputStream("src/main/res/allInventories.ser");
                    break;
                case "users":
                    fileOut =
                            new FileOutputStream("src/main/res/allUsers.ser");
                    break;
                default:
                    fileOut = new FileOutputStream("src/main/res/errorSave.ser");
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
            System.out.printf("Serialized data is saved in /tmp/"+ whatToSeralize + ".ser");
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
                            new FileInputStream("src/main/res/allGroups.ser");
                    break;
                case "inventories":
                    fileIn =
                            new FileInputStream("src/main/res/allInventories.ser");
                    break;
                case "users":
                    fileIn =
                            new FileInputStream("src/main/res/allUsers.ser");
                    break;
                default:
                    fileIn = new FileInputStream("src/main/res/errorSave.ser");
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
        HamburgerBasicCloseTransition hamTask = new HamburgerBasicCloseTransition(hamburger);
        hamTask.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            hamTask.setRate(hamTask.getRate() * -1);
            hamTask.play();

            if(drawer.isOpened()){
                drawer.close();
            }else{
                drawer.open();
            }
        });
    }



}

package Controller;

import Model.Group;
import Model.Inventory;
import Model.User;
import Model.YellowHandler;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
public class Controller implements Initializable {

    private List<Group> allGroups = new ArrayList<>();
    private List<User> allUsers = new ArrayList<>();
    private List<Inventory> allInventories = new ArrayList<>();
    private YellowHandler yh = new YellowHandler(allUsers, allGroups);

    @FXML private SignUp signUp;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hamburgerSetup();
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
            System.out.printf("Serialized data is saved in /tmp/whatToSeralise.ser");
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
                allGroups = (List<Group>) in.readObject();
            }
            if(whatToDeseralize.equals("inventories")){
                allInventories = (List<Inventory>) in.readObject();
            }
            if(whatToDeseralize.equals("users")){
                allUsers = (List<User>) in.readObject();
            }
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }

    }

    public void hamburgerSetup(){
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

    public void saveGroup (Group group){
        allGroups.add(group);
    }
    public void saveInventory (Inventory inventory){
        allInventories.add(inventory);
    }
    public void saveUser (User user){
        allUsers.add(user);
    }
    public List<Group> getAllGroups (){
        return allGroups;
    }
    public List<Inventory> getAllInventories(){
        return allInventories;
    }
    public List <User> getAllUsers (){
        return allUsers;
    }


    @FXML
    public void addGroup(){

    }

}

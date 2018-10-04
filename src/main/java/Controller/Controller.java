package Controller;

import Model.Group;
import Model.Inventory;
import Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<Group> allGroups = new ArrayList<>();
    private List<Inventory> allInventories = new ArrayList<>();
    private List<User> allUsers = new ArrayList<>();

    public Controller (){

    }

    private void initilize(){

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
                            new FileOutputStream("tmp/allGroups.ser");
                    break;
                case "inventories":
                    fileOut =
                            new FileOutputStream("tmp/allInventories.ser");
                    break;
                case "users":
                    fileOut =
                            new FileOutputStream("tmp/allUsers.ser");
                    break;
                default:
                    fileOut = new FileOutputStream("tmp/errorSave.ser");
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
                            new FileInputStream("tmp/allGroups.ser");
                    break;
                case "inventories":
                    fileIn =
                            new FileInputStream("tmp/allInventories.ser");
                    break;
                case "users":
                    fileIn =
                            new FileInputStream("tmp/allUsers.ser");
                    break;
                default:
                    fileIn = new FileInputStream("tmp/errorSave.ser");
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

}

package utils;

import model.GroupInterface;
import model.UserInterface;
import model.YellowHandlerInterface;

import java.io.*;
import java.util.List;

public class DataHandler {

    /**
     * Saves the lists allGroups, allInventories or allUsers to three different .ser files. What files that
     * will get saved is based on the @param.
     * @param whatToSerialize should be groups, inventories or users, based on what is to be saved.
     */
    public void serialize(String whatToSerialize, YellowHandlerInterface yh){
        try {
            FileOutputStream fileOut;
            switch (whatToSerialize) {
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
            if(whatToSerialize.equals("groups")) {
                out.writeObject(yh.getAllGroups());
            }
            if(whatToSerialize.equals("users")){
                out.writeObject(yh.getUsers());
            }
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in /"+ whatToSerialize + ".ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * This method will fetch the allUsers, allGroups and allInventories lists from the local database.
     *
     * @param whatToDeserialize should be groups, inventories or users depending on what is to be fetched.
     */
    public void deserialize(String whatToDeserialize, YellowHandlerInterface yh){
        try {
            FileInputStream fileIn;
            switch (whatToDeserialize) {
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
            if(whatToDeserialize.equals("groups")) {
                yh.setGroups((List<GroupInterface>) in.readObject()); //= (List<GroupInterface>) in.readObject();
            }
            if(whatToDeserialize.equals("users")){
                yh.setAllUsers((List<UserInterface>) in.readObject());
            }
            in.close();
            fileIn.close();
        } catch (IOException i) {
            serialize(whatToDeserialize, yh);
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }

    }
}

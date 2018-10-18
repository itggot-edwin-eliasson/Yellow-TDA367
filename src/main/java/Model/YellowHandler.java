package Model;
import java.util.*;


/**
 * @author Mona Kilsgård
 * @date 2018.09-28
 *
 * ---
 * 05/10 Modified by Joakim. Adds complexity and functionality to remove and add items.
 * 05/10 Modified by Joakim. Login functionality added.
 *
 */
public class YellowHandler extends Observable {

    private UserInterface activeUser;
    private GroupInterface activegroup;
    public List<GroupInterface> groups = new ArrayList<>();
    public List<UserInterface> users = new ArrayList<>();
    private List<Integer> groupInviteCodes = new ArrayList<>();

    /**
     * Creates a handler of the model
     * @param users List of all users in the application
     * @param groups List of all groups in the application
     */


 /*   public YellowHandler (List <UserInterface> users, List <GroupInterface> groups){
        this.users = users;
        this.groups = groups;
    }*/

    /**
     * Creates a group and adds it to the list with groups.
     * @param groupName The name of the new group.
     */
    public void createGroup(String groupName, String color){
        GroupInterface g = new Group(groupName, color, generateUniqueKeyUsingUUID(), groupInviteCodes);
        groupInviteCodes.add(g.getInviteCode());
        String id = generateUniqueKeyUsingUUID();
        g.createInventory("All items", id);
        g.selectInventory(id);
        groups.add(g);
        activeUser.addGroup(g.getId());
        notifyObservers();
    }

    public void changeUserSettings (String firstName, String lastName, String username, String email, String password) {
        activeUser.setName(firstName);
        activeUser.setLastName(lastName);
        activeUser.setUsername(username);
        activeUser.setEmail(email);
        activeUser.setPassword(password);
    }


    public List<GroupInterface> getGroups(){
        List<GroupInterface> tmpGroups = new ArrayList<>();
        if(activeUser != null) {
            for (int i = 0; i < activeUser.getGroupIds().size(); i++) {
                for (GroupInterface group : groups) {
                    if (group.getId().equals(activeUser.getGroupIds().get(i))) {
                        tmpGroups.add(group);
                    }
                }
            }
        }
        return tmpGroups;
    }

    /**
     * Creates a user and adds it to the list with users. The new user is also set to activeUser.
     * @param username The username of the new user.
     * @param firstName The name of the user
     * @param lastName The first name of the user
     * @param email The email of the user
     * @param password The users password
     */
    public boolean createUser(String username, String firstName, String lastName, String email, String password){
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                return false;
                // ERROR MESSAGE
            }
        }
        activeUser = new User(username, firstName, lastName, email, generateUniqueKeyUsingUUID(), password);
        users.add(activeUser);
        return true;
    }

    /**
     * The user logs in, checks if username exists and if password matches user
     * @param username
     * @param password
     */

    public boolean logIn (String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                if (users.get(i).comparePassword(password)) {
                    activeUser = users.get(i);
                    notifyObservers();
                    return true;
                }
            }
        }
        notifyObservers();
        return false;
    }

    public void logOut (){
        activeUser = null;
        activegroup = null;
    }


    public void setActiveUserToNull () {
        activeUser = null;
    }

    /**
     * Adds an item into an inventory
     * @param name Name of item
     * @param description Description of item
     * @param inventoryId Id of inventory
     * @param amount amount of items to add
     */

    public void addItem (String name, String description, String inventoryId, int amount) {
        for(int i = 0; i < amount; i++) {
            String id = generateUniqueKeyUsingUUID();
            activegroup.addItem(name, description, id, inventoryId);
        }
        notifyObservers();
    }

    /**
     * Removes an item from inventory
     * @param id id of the item to be removed
     */

    public void removeItem (String id) {
        activegroup.removeItem(id);
    }

    /**
     * Adds user to group
     * @param inviteCode The invitecode the user gets
     */


    public boolean joinGroup(int inviteCode){
    for (GroupInterface group : groups) {
            if(inviteCode == group.getInviteCode()){
                activeUser.addGroup(group.getId());
                return true;
            }
        }
        return false;
    }

    /**
     * Removes user from group
     * @param id id of the group to be removed
     */
    public void removeGroup (String id) {
        activeUser.removeGroup(id);
    }

    /**
     * Generates a unique id
     * @return The unique id
     */
    public String generateUniqueKeyUsingUUID() {
        // Static factory to retrieve a type 4 (pseudo randomly generated) UUID
        String crunchifyUUID = UUID.randomUUID().toString();
        return crunchifyUUID;
    }


    public void addItemToOrder(String itemID) {
         if (activegroup.getActiveOrder() == null){
             activegroup.createOrder(generateUniqueKeyUsingUUID());
         }
         activegroup.addItemToOrder(itemID);

    }

    public UserInterface getActiveUser () {
         return this.activeUser;
    }

    /**
     * This tries to complete an order. Returns true if the order can be completed for said date and false
     * if a item is already rented on that date.
     * @param startDate startDate when the order wants to be made.
     * @param endDate endDate when the order wants to be made.
     * @return read description.
     */
    public Boolean completeOrder(String startDate, String endDate) {
        if (activegroup.orderIsCompleted(startDate, endDate)) {
            return true;
        }
        return false;
    }

    public GroupInterface getActiveGroup() {
        return this.activegroup;
    }

    public List<ItemInterface> getItems() {
        if(activegroup != null) {
            if (activegroup.getSelectedInventory() != null)
                return activegroup.getSelectedInventory().getItemList();
        }
        return new ArrayList<>();
    }

    public List<InventoryInterface> getInventories(){
        if(activegroup != null)
            return activegroup.getInventories();
        return new ArrayList<>();
    }

    public void setActiveGroup(GroupInterface group) {
        activegroup = group;
        notifyObservers();
    }

    public void createInventory(String name) {
        if(activegroup != null)
            activegroup.createInventory(name, generateUniqueKeyUsingUUID());
        notifyObservers();
    }

    /**
     * This should be used when an order could not be completed.
     * @return A list of booleans that matches the order list in index and says if the item is rentable or not.
     */
    public List<Boolean> completeOrderFailed(){
         return activegroup.getActiveOrder().getIsRentable();
    }

    /**
     * This sets the isRented boolean on every item in the active inventory.
     * Should be called when an inventory is opened.
     */
    public void updateInventory() {
        activegroup.updateInventory();
    }

    /**
     * Sets activeInventory in the group.
     * @param id of the inventory that is selected
     */
    public void selectInventory(String id) {
        activegroup.selectInventory(id);
        notifyObservers();
    }
    public void orderIsReturned(String orderID){
        activegroup.orderIsReturned(orderID);
    }

    public List<UserInterface> getUsers() {
        return users;
    }

    public List<GroupInterface> getAllGroups () {
        return groups;
    }

    public void setGroups (List <GroupInterface> groups) {this.groups = groups;}

    public void setAllUsers (List <UserInterface> users) {this.users = users;}
}

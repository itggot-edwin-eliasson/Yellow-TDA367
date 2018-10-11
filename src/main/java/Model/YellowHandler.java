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
public class YellowHandler implements YellowHandlerInterface {

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


    public YellowHandler (List <UserInterface> users, List <GroupInterface> groups){
        this.users = users;
        this.groups = groups;
    }

    /**
     * Creates a group and adds it to the list with groups.
     * @param groupName The name of the new group.
     */
    @Override
    public void createGroup(String groupName, String color){
        GroupInterface g = new Group(groupName, color, generateUniqueKeyUsingUUID(), groupInviteCodes);
        groupInviteCodes.add(g.getInviteCode());
        String id = generateUniqueKeyUsingUUID();
        g.createInventory("All items", id);
        g.selectInventory(id);
        groups.add(g);
        activeUser.addGroup(g.getId());
    }

    @Override
    public void changeUserSettings (String firstName, String lastName, String username, String email, String password) {
        activeUser.setName(firstName);
        activeUser.setLastName(lastName);
        activeUser.setUsername(username);
        activeUser.setEmail(email);
        activeUser.setPassword(password);
    }

    public String[] getUserInfo () {

        return null;
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

    public Map<String, List<String>> getGroupInfo(){
        Map<String, List<String>> groupsMap = new HashMap<>();
        for(int i = 0; i < groups.size(); i++){
            List<String> tmpList = new ArrayList<>();
            tmpList.add(groups.get(i).getName());
            tmpList.add(groups.get(i).getColor());
            groupsMap.put(groups.get(i).getId(), tmpList);
        }

        return groupsMap;
    }
    /**
     * Creates a user and adds it to the list with users. The new user is also set to activeUser.
     * @param username The username of the new user.
     * @param firstName The name of the user
     * @param lastName The first name of the user
     * @param email The email of the user
     * @param password The users password
     */
    @Override
    public UserInterface createUser(String username, String firstName, String lastName, String email, String password){
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                return null;
                // ERROR MESSAGE
            }
        }
        activeUser = new User(username, firstName, lastName, email, generateUniqueKeyUsingUUID(), password);
        users.add(activeUser);
        return activeUser;
    }

    /**
     * The user logs in, checks if username exists and if password matches user
     * @param username
     * @param password
     */

    @Override
    public boolean logIn (String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                if (password.equals(users.get(i).comparePassword(password))) {
                    activeUser = users.get(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
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

    @Override
    public void addItem (String name, String description, String inventoryId, int amount) {
        String id = generateUniqueKeyUsingUUID();
        activegroup.addItem(name, description, id, inventoryId, amount);
    }

    /**
     * Removes an item from inventory
     * @param id id of the item to be removed
     */

    @Override
    public void removeItem (String id) {
        activegroup.removeItem(id);
    }

    /**
     * Adds user to group
     * @param inviteCode The invitecode the user gets
     */

    @Override
    public void joinGroup(String inviteCode){
        activeUser.addGroup(inviteCode);

    }

    /**
     * Removes user from group
     * @param id id of the group to be removed
     */

    @Override
    public void removeGroup (String id) {
        activeUser.removeGroup(id);
    }

    /**
     * Generates a unique id
     * @return The unique id
     */
     @Override
    public String generateUniqueKeyUsingUUID() {
        // Static factory to retrieve a type 4 (pseudo randomly generated) UUID
        String crunchifyUUID = UUID.randomUUID().toString();
        return crunchifyUUID;
    }


    @Override
    public void addItemToOrder(int amount, String itemID) {
         if (activegroup.getActiveOrder() == null){
             activegroup.createOrder(generateUniqueKeyUsingUUID());
         }

         activegroup.addItemToOrder(amount, itemID);
    }

    @Override
    public UserInterface getActiveUser () {
         return this.activeUser;
    }

    public Boolean completeOrder(String startDate, String endDate) {
        if (activegroup.orderIsCompleted(startDate, endDate)) {
            return true;
        }
        return false;
    }

    @Override
    public GroupInterface getActiveGroup() {
        return this.activegroup;
    }

    @Override
    public List<ItemInterface> getItems() {
         return activegroup.getSelectedInventory().getItemList();
    }

    @Override
    public List<InventoryInterface> getInventories(){
         return activegroup.getInventories();
    }

    @Override
    public void setActiveGroup(GroupInterface group) {
        activegroup = group;
    }

    public void updateInventory() {
        activegroup.updateInventory();
    }

    public void selectInventory(String id) {
        activegroup.selectInventory(id);
    }
}

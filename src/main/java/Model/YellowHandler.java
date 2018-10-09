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
        groups.add(g);

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
     * @param name The name of the user
     * @param email The email of the user
     * @param password The users password
     */
    @Override
    public UserInterface createUser(String username, String name, String email, String password){
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                return null;
                // ERROR MESSAGE
            }
        }
        activeUser = new User(username, name, email, generateUniqueKeyUsingUUID(), password);
        users.add(activeUser);
        return activeUser;
    }

    /**
     * The user logs in, checks if username exists and if password matches user
     * @param username
     * @param password
     */

    @Override
    public void logIn (String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                if (password.equals(users.get(i).comparePassword(password))) {
                    activeUser = users.get(i);
                }
            }
        }
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


    public void addItemToOrder(String itemID) {
         if(activegroup.getActiveOrder() == null){
             activegroup.createOrder(generateUniqueKeyUsingUUID());
         }
         activegroup.addItemToOrder(itemID);
    }

    public void completeOrder() {
         activegroup.orderIsCompleted();
    }

    public void updateInventory() {
        activegroup.updateInventory();
    }

    public void selectInventory(String id) {
         activegroup.selectInventory(id);
    }
}

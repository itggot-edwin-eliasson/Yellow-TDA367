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

    private User activeUser;
    private Group activegroup;
    public List<Group> groups = new ArrayList<>();
    public List<User> users = new ArrayList<>();
    private List<Integer> groupInviteCodes = new ArrayList<>();


    public YellowHandler (List <User> users, List <Group> groups){
        this.users = users;
        this.groups = groups;
    }

    /**
     * Creates a group and adds it to the list with groups.
     * @param groupName The name of the new group.
     */
    @Override
    public void createGroup(String groupName, String color){
        Group g = new Group(groupName, color, generateUniqueKeyUsingUUID(), groupInviteCodes);
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
    public User createUser(String username, String name, String email, String password){
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

    /*@Override
    public void createUser (String username) {
        User u = new User(username);
        activeUser = u;
        users.add(u);

    }*/

    @Override
    public void addItem (String name, String description, String inventoryId, int amount) {
        String id = generateUniqueKeyUsingUUID();
        activegroup.addItem(name, description, id, inventoryId, amount);
    }

    @Override
    public void removeItem (String id) {
        activegroup.removeItem(id);
    }


    @Override
    public void joinGroup(String inviteCode){
        activeUser.addGroup(inviteCode);

    }

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



}

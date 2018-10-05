package Model;
import java.util.*;

/**
 * @author Mona Kilsgård
 * @date 2018.09-28
 */
public class YellowHandler implements YellowHandlerInterface {

    private User activeUser;
    private Group activegroup;
    public List<Group> groups = new ArrayList<>();
    public List<User> users = new ArrayList<>();
    private List<Integer> groupInviteCodes = new ArrayList<>();


    public YellowHandler (){
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
     */
    @Override
    public void createUser(String username, String name, String email){
        activeUser = new User(username, name, email, generateUniqueKeyUsingUUID());
        users.add(activeUser);

    }

    @Override
    public void createUser (String username) {
        User u = new User(username);
        activeUser = u;
        users.add(u);

    }

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

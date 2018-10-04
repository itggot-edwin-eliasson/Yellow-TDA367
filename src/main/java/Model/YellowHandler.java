package Model;
import java.util.*;


/**
 * @author Mona Kilsgård
 * @date 2018.09-28
 */
public class YellowHandler {

    private User activeUser;
    public List<Group> groups = new ArrayList<>();
    public List<User> users = new ArrayList<>();
    private List<Integer> groupInviteCodes = new ArrayList<>();
    private Observable observable;

    public YellowHandler (){
        observable = new Observable();
    }

    /**
     * Creates a group and adds it to the list with groups.
     * @param groupName The name of the new group.
     */
    public void createGroup(String groupName, String color){
        Group g = new Group(groupName, color, generateUniqueKeyUsingUUID(), groupInviteCodes);
        groupInviteCodes.add(g.getInviteCode())
        groups.add(g);
        observable.notifyObserver();
    }

    /**
     * Creates a user and adds it to the list with users. The new user is also set to activeUser.
     * @param username The username of the new user.
     */
    public void createUser(String username, String name, String email){
        activeUser = new User(username, name, email);
        users.add(activeUser);
        observable.notifyObserver();
    }

    public void createUser (String username) {
        User u = new User(username);
        activeUser = u;
        users.add(u);
        observable.notifyObserver();
    }

    

    public void joinGroup(String inviteCode){
        activeUser.addGroup(inviteCode);

        observable.notifyObserver();

    }

    public String generateUniqueKeyUsingUUID() {
        // Static factory to retrieve a type 4 (pseudo randomly generated) UUID
        String crunchifyUUID = UUID.randomUUID().toString();
        return crunchifyUUID;

    }


}

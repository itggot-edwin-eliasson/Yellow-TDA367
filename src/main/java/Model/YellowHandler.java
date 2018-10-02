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

    public YellowHandler (){}

    /**
     * Creates a group and adds it to the list with groups.
     * @param groupName The name of the new group.
     */
    public void createGroup(String groupName){
        Group g = new Group(groupName);
        g.generateInviteCode(groupInviteCodes);
        groups.add(g);
    }

    /**
     * Creates a user and adds it to the list with users. The new user is also set to activeUser.
     * @param username The username of the new user.
     */
    public void createUser(String username){
        activeUser = new User(username);
        users.add(activeUser);
    }

    public void joinGroup(String inviteCode){
        activeUser.addGroup(inviteCode);

    }


}

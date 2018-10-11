package Model;

import java.util.List;

public interface YellowHandlerInterface {

    /**
     * Creates a group and adds it to the list with groups.
     * @param groupName The name of the new group.
     */
    public void createGroup(String groupName, String color);

    /**
     * Creates a user and adds it to the list with users. The new user is also set to activeUser.
     * @param username The username of the new user.
     */
    public UserInterface createUser(String username, String firstName, String lastName, String email, String password);

    public boolean logIn (String username, String password);

    //public User createUser (String username);

    public void joinGroup(String inviteCode);

    public void removeGroup (String id);

    public void addItem(String name, String description, String inventoryId, int amount);

    public void removeItem(String id);

    public void setActiveUserToNull();

    public void changeUserSettings(String firstName, String lastName, String username, String email, String password);

    /**
     * Generates a unique id
     * @return The unique id
     */
    public String generateUniqueKeyUsingUUID();

    public void addItemToOrder(int amount, String id);

    public UserInterface getActiveUser();

    public GroupInterface getActiveGroup();

    public List<GroupInterface> getGroups();

    public List<ItemInterface> getItems();

    public List<InventoryInterface> getInventories();

    public void setActiveGroup(GroupInterface group);

}

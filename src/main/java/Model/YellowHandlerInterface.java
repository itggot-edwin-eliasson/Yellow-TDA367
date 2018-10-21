package Model;

import java.util.List;

public interface YellowHandlerInterface extends ObservableInterface{

    /**
     * Creates a group and adds it to the list with groups.
     * @param groupName The name of the new group.
     */
    void createGroup(String groupName, String color);

    /**
     * Creates a user and adds it to the list with users. The new user is also set to activeUser.
     * @param username The username of the new user.
     */
    boolean createUser(String username, String firstName, String lastName, String email, String password);

    boolean logIn (String username, String password);

    boolean joinGroup(int inviteCode);

    void removeGroup (String id);

    void logOut();

    void addItem(String name, String description, String inventoryId, int amount);

    void removeItem(String id);

    void setActiveUserToNull();

    void changeUserSettings(String firstName, String lastName, String username, String email, String password);

    /**
     * Generates a unique id
     * @return The unique id
     */
    String generateUniqueKeyUsingUUID();

    void addItemToOrder(String id);

    UserInterface getActiveUser();

    GroupInterface getActiveGroup();

    List<GroupInterface> getGroups();

    List<ItemInterface> getItems();

    List<InventoryInterface> getInventories();

    void setActiveGroup(GroupInterface group);

    void createInventory(String name);

    List<Boolean> completeOrderFailed();

    void selectInventory(String id);

    void orderIsReturned(String orderID);

    List<UserInterface> getUsers();

    List<GroupInterface> getAllGroups();

    void setGroups(List<GroupInterface> groups);

    void setAllUsers(List<UserInterface> users);
}

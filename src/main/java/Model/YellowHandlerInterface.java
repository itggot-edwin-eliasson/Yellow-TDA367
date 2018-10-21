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
     * @param firstName The name of the user
     * @param lastName The first name of the user
     * @param email The email of the user
     * @param password The users password
     */
    boolean createUser(String username, String firstName, String lastName, String email, String password);

    /**
     * Sets a user to the active user if any of the users matches the username and password entered.
     * @param username the username entered.
     * @param password the password entered.
     * @return true if login was done, else false.
     */
    boolean logIn (String username, String password);

    /**
     * Joins a group through the invite code that group has.
     * @param inviteCode the invite code of the group you want to join.
     * @return true if a group was joined, else false.
     */
    boolean joinGroup(int inviteCode);

    /**
     * Removes a group from the active user.
     * @param id id of the group that will be removed.
     */
    void removeGroup (String id);

    /**
     * Sets the active user and active group to null.
     */
    void logOut();

    /**
     * Creates and adds a new item to an inventory
     * @param name name of the item you want to create.
     * @param description description of the item you want to create.
     * @param inventoryId ID of the inventory the item should be placed in.
     * @param amount how many of the same items that should be created.
     */
    void addItem(String name, String description, String inventoryId, int amount);

    /**
     * Removes an item from the selected inventory.
     * @param id ID of the item that will be removed.
     */
    void removeItem(String id);

    void setActiveUserToNull();

    /**
     * Changes the information of the active user to whats entered.
     * @param firstName
     * @param lastName
     * @param username
     * @param email
     * @param password
     */
    void changeUserSettings(String firstName, String lastName, String username, String email, String password);

    /**
     * Generates a unique id
     * @return The unique id
     */
    String generateUniqueKeyUsingUUID();

    /**
     * Adds an item to the active order.
     * @param id ID of the item that should be added.
     */
    void addItemToOrder(String id);

    UserInterface getActiveUser();

    /**
     * Tries to complete the active order for a certain date. If all items are available for that date
     * the order will be added to the onGoing orders list.
     * @param startDate the date when the order will start.
     * @param endDate the date when the order will end.
     * @return true if the order was completed, else false.
     */
    Boolean completeOrder(String startDate, String endDate);

    GroupInterface getActiveGroup();

    List<GroupInterface> getGroups();

    List<ItemInterface> getItems();

    List<InventoryInterface> getInventories();

    void setActiveGroup(GroupInterface group);

    /**
     * Creates a new inventory into the active group.
     * @param name name of the inventory that will be created.
     */
    void createInventory(String name);

    /**
     * Use this function when the order could not be completed.
     * @return a list of true or false that matches the active order. Used to list next to the active
     * order to see what items were available and not.
     */
    List<Boolean> completeOrderFailed();

    /**
     * Select an inventory and set it to the selected inventory.
     * @param id ID of the inventory you want to select.
     */
    void selectInventory(String id);

    /**
     * Used when an order is returned to add it from the onGoing orders list to the oldOrders list.
     * @param orderID
     */
    void orderIsReturned(String orderID);

    List<UserInterface> getUsers();

    List<GroupInterface> getAllGroups();

    void setGroups(List<GroupInterface> groups);

    void setAllUsers(List<UserInterface> users);
}

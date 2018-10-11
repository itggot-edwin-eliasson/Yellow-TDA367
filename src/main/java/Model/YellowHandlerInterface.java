package Model;

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

    public void logIn (String username, String password);

    //public User createUser (String username);

    public void joinGroup(String inviteCode);

    public void removeGroup (String id);

    public void addItem(String name, String description, String inventoryId, int amount);

    public void removeItem(String id);

    public void changeUserSettings(String name, String username, String email, String password);

    /**
     * Generates a unique id
     * @return The unique id
     */
    public String generateUniqueKeyUsingUUID();

}

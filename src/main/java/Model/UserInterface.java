package Model;

import java.io.Serializable;
import java.util.List;

public interface UserInterface extends Serializable {

    public void addGroup(String groupId);

    public List<String> getGroupIds();

    public String getEmail();

    public String getFirstName();

    public String getLastName();

    public String getPassword();

    public void setLastName(String name);

    public String getUsername();

    public void setName(String name);

    public void setEmail(String email);

    public String getId();

    /**
     * Compares if entered string matches the password of the user.
     * @param password the password you want to check if matches.
     * @return True if it matched, else false.
     */
    public boolean comparePassword(String password);

    /**
     * Removes a group from the list of groups in the user.
     * @param id id of the group you want to remove.
     */
    public void removeGroup(String id);

    public void setPassword (String password);

    public void setUsername (String username);
}

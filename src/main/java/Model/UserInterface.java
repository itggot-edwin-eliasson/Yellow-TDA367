package Model;

import java.io.Serializable;
import java.util.List;

public interface UserInterface extends Serializable {

    public void addGroup(String groupId);

    public List<String> getGroupIds();

    public String getEmail();

    public void setLastName(String name);

    public String getUsername();

    public void setName(String name);

    public void setEmail(String email);

    public String getId();

    public boolean comparePassword(String password);

    public void removeGroup(String id);

    public void setPassword (String password);

    public void setUsername (String username);
}

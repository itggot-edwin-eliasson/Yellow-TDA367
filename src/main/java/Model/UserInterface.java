package Model;

import java.io.Serializable;
import java.util.List;

public interface UserInterface extends Serializable {

    public void addGroup(String groupId);

    public List<String> getGroupIds();

    public String getUsername();

    public void setName(String name);

    public void setEmail(String email);

    public String getId();
}

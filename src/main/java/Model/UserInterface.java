package Model;

import java.util.List;

public interface UserInterface {

    public void addGroup(String groupId);

    public List<String> getGroupIds();

    public String getUsername();

    public void setName(String name);

    public void setEmail(String email);

    public String getId();
}

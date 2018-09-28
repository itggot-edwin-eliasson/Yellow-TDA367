package Model;

import java.util.*;

public class User {
    private String username;
    private String name;
    private String email;
    private List<String> groupIds = new ArrayList<>();


    public User(String username) {
        this.username = username;
    }

    public User(String username, String name, String email) {

        this.username = username;
        this.name = name;
        this.email = email;
    }

    public void addGroup(String groupId){
        groupIds.add(groupId);
    }

    public List<String> getGroupIds(){
        return groupIds;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

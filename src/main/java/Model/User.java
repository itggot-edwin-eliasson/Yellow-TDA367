package Model;

import java.util.*;

public class User implements UserInterface {

    private String username;
    private String name;
    private String email;
    private String id;
    private String password;
    private List<String> groupIds = new ArrayList<>();

    public User (String username, String name, String email, String id, String password){
        this.username = username;
        this.name = name;
        this.email = email;
        this.id = id;
        this.password = password;
    }

    public User (String username) {
        this.username = username;
    }

   @Override
    public void addGroup(String groupId){
        groupIds.add(groupId);
    }

    public void removeGroup (String groupId) {
        for (int i = 0 ; i < groupIds.size() ; i++) {
            if (groupId.equals(groupIds.get(i))) {
                groupIds.remove(i);
            }
        }
    }

    @Override
    public List<String> getGroupIds(){
        return groupIds;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getId() {
        return id;
    }

    public boolean comparePassword (String newPassword) {
        if (newPassword.equals(password)) {
            return true;
        }
        return false;
    }

}




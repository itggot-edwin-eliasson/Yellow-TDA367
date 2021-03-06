package model;

import java.util.*;

public class User implements UserInterface {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String id;
    private String password;
    private List<String> groupIds = new ArrayList<>();

    /**
     * Creates a user.
     * @param username Username
     * @param firstName First name of the user
     * @param lastName Last name of the user
     * @param email Email of the user
     * @param id generated from YellowHandler class.
     * @param password Password for the user.
     */
    public User (String username, String firstName, String lastName, String email, String id, String password){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.password = password;
    }

   @Override
    public void addGroup(String groupId){
        groupIds.add(groupId);
    }

    @Override
    public void removeGroup (String groupId) {
        for (int i = 0 ; i < groupIds.size() ; i++) {
            if (groupId.equals(groupIds.get(i))) {
                groupIds.remove(i);
            }
        }
    }

    public boolean comparePassword (String newPassword) {
        if (newPassword.equals(password)) {
            return true;
        }
        return false;
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
    public String getEmail () {return email;}

    @Override
    public void setName(String name) {
        this.firstName = name;
    }

    @Override
    public void setLastName(String name) {this.lastName = name;}

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword (String password) {this.password = password;}

    @Override
    public void setUsername (String username) {this.username = username;}

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getPassword () {return password;}

    @Override
    public String getFirstName() {return firstName; }

    @Override
    public String getLastName () { return lastName; }



}




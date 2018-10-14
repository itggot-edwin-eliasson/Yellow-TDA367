package Model;

import Model.YellowHandler;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class YellowHandlerTest {
    @Test
    public void getCorrectGroupsForUser() throws Exception {
        List<GroupInterface> allGroups = new ArrayList<>();
        List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createUser("Viktor","hej","hej", "hej", "hej");
        yh.createGroup("group1","hej");
        yh.createGroup("group2","hej");
        yh.createUser("Edwin","hej","hej", "hej", "hej");
        yh.createGroup("group3","hej");
        yh.createGroup("group4","hej");
        assertEquals("group3",yh.getGroups().get(0).getName());
        assertEquals("group4",yh.getGroups().get(1).getName());
    }

    @Test
    public void logIn() throws Exception {
        List<GroupInterface> allGroups = new ArrayList<>();
        List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createUser("Viktor","hej","hej", "hej", "hej");
        yh.setActiveUserToNull();
        yh.logIn("hej","hej");
        assertEquals(null,yh.getActiveUser());
        yh.logIn("Viktor","hej");
        assertEquals("Viktor", yh.getActiveUser().getUsername());
    }

    @Test
    public void addItem() throws Exception {
        List<GroupInterface> allGroups = new ArrayList<>();
        List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createUser("Viktor","hej","hej", "hej", "hej");
        yh.createGroup("group","hej");
        yh.setActiveGroup(yh.getGroups().get(0));
        yh.getActiveGroup().createInventory("inventory","inventory");
        yh.selectInventory("inventory");
        yh.addItem("boll","en boll","inventory",1);
        assertEquals(1,yh.getItems().size());
        yh.addItem("boll","en boll","inventory",2);
        assertEquals(3,yh.getItems().size());
    }

    @Test
    public void removeItem() throws Exception {
        List<GroupInterface> allGroups = new ArrayList<>();
        List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createUser("Viktor","hej","hej", "hej", "hej");
        yh.createGroup("group","hej");
        yh.setActiveGroup(yh.getGroups().get(0));
        yh.getActiveGroup().createInventory("inventory","inventory");
        yh.selectInventory("inventory");
        yh.addItem("boll","en boll","inventory",1);
        assertEquals(1,yh.getItems().size());
        yh.addItem("boll","en boll","inventory",2);
        assertEquals(3,yh.getItems().size());
    }

    @Test
    public void joinGroup() throws Exception {
        List<GroupInterface> allGroups = new ArrayList<>();
        List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createUser("Viktor","hej","hej", "hej", "hej");
        yh.createGroup("group1","hej");
        yh.createGroup("group2","hej");
        String tmp = yh.getGroups().get(1).getId();
        yh.createUser("Edwin","hej","hej", "hej", "hej");
        yh.createGroup("group3","hej");
        yh.createGroup("group4","hej");
        assertEquals("group3",yh.getGroups().get(0).getName());
        assertEquals("group4",yh.getGroups().get(1).getName());
        assertEquals(2,yh.getGroups().size());
        yh.joinGroup(tmp);
        assertEquals(3,yh.getGroups().size());
        assertEquals("group2",yh.getGroups().get(2).getName());
    }

    @Test
    public void removeGroup() throws Exception {
        List<GroupInterface> allGroups = new ArrayList<>();
        List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createUser("Viktor","hej","hej", "hej", "hej");
        yh.createGroup("group1","hej");
        yh.createGroup("group2","hej");
        String tmp = yh.getGroups().get(1).getId();
        assertEquals(2,yh.getGroups().size());
        yh.removeGroup(tmp);
        assertEquals(1,yh.getGroups().size());
    }

    @Test
    public void generateUniqueKeyUsingUUID() throws Exception {
    }

    @Test
    public void createGroup(){
        List<GroupInterface> allGroups = new ArrayList<>();
        List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createUser("test", "test","test","test","test");
        yh.createGroup("test", "red");
        assertTrue(yh.groups.size() == 1 || yh.users.size() == 1);
    }

    @Test
    public void createUser(){
         List<GroupInterface> allGroups = new ArrayList<>();
         List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createUser("Moki","hej","hej", "hej", "hej");
        assertEquals(1, yh.users.size());
    }
}

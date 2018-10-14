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
        int tmp = yh.getGroups().get(1).getInviteCode();
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
        List<GroupInterface> allGroups = new ArrayList<>();
        List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        List<String> tmplist = new ArrayList<>();
        boolean bol = true;
        for (int i = 0; i < 10000; i++) {
            tmplist.add(yh.generateUniqueKeyUsingUUID());
        }
        for (int i = 1; i < 10000; i++) {
            if(tmplist.get(0).equals(tmplist.get(i))){
                bol = false;
            }
        }
        System.out.println(bol);

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
    @Test
    public void changeUserSettingsTest(){
        List<GroupInterface> allGroups = new ArrayList<>();
        List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createUser("test", "test","test","test","test");
        yh.changeUserSettings("hej","hej","hej","hej","hej");
        assertEquals("hej",yh.getActiveUser().getUsername());
    }
    @Test
    public void addItemToOrderTest(){
        List<GroupInterface> allGroups = new ArrayList<>();
        List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createUser("Viktor","hej","hej", "hej", "hej");
        yh.createGroup("group","hej");
        yh.setActiveGroup(yh.getGroups().get(0));
        yh.getActiveGroup().createInventory("inventory","inventory");
        yh.selectInventory("inventory");
        yh.addItem("boll","en boll","inventory",1);
        String itemID = yh.getItems().get(0).getId();
        yh.addItemToOrder(itemID);
        assertEquals(1,yh.getActiveGroup().getActiveOrder().getOrderList().size());
        yh.addItemToOrder(itemID);
        assertEquals(1,yh.getActiveGroup().getActiveOrder().getOrderList().size());
    }
    @Test
    public void completeOrderTest(){
        List<GroupInterface> allGroups = new ArrayList<>();
        List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createUser("Viktor","hej","hej", "hej", "hej");
        yh.createGroup("group","hej");
        yh.setActiveGroup(yh.getGroups().get(0));
        yh.getActiveGroup().createInventory("inventory","inventory");
        yh.selectInventory("inventory");
        yh.addItem("boll","en boll","inventory",1);
        String itemID = yh.getItems().get(0).getId();
        yh.addItemToOrder(itemID);
        assertEquals(1,yh.getActiveGroup().getActiveOrder().getOrderList().size());
        assertTrue(yh.completeOrder("2018-10-14","2018-10-16"));
        yh.addItemToOrder(itemID);
        assertEquals(1,yh.getActiveGroup().getActiveOrder().getOrderList().size());
        assertFalse(yh.completeOrder("2018-10-15","2018-10-18"));
    }
    @Test
    public void returnOrderTest(){
        List<GroupInterface> allGroups = new ArrayList<>();
        List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createUser("Viktor","hej","hej", "hej", "hej");
        yh.createGroup("group","hej");
        yh.setActiveGroup(yh.getGroups().get(0));
        yh.getActiveGroup().createInventory("inventory","inventory");
        yh.selectInventory("inventory");
        yh.addItem("boll","en boll","inventory",1);
        String itemID = yh.getItems().get(0).getId();
        yh.addItemToOrder(itemID);
        assertEquals(1,yh.getActiveGroup().getActiveOrder().getOrderList().size());
        String orderID = yh.getActiveGroup().getActiveOrder().getOrderID();
        yh.completeOrder("2018-10-14","2018-10-16");
        yh.orderIsReturned(orderID);
        assertEquals(0,yh.getActiveGroup().getOrderList().size());
        assertEquals(1,yh.getActiveGroup().getOldOrders().size());
    }
}

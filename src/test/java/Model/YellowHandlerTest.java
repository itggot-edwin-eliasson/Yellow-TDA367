package Model;

import Model.YellowHandler;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class YellowHandlerTest {
    @Test
    public void getGroupInfo() throws Exception {
    }

    @Test
    public void logIn() throws Exception {
    }

    @Test
    public void addItem() throws Exception {
    }

    @Test
    public void removeItem() throws Exception {
    }

    @Test
    public void joinGroup() throws Exception {
    }

    @Test
    public void removeGroup() throws Exception {
    }

    @Test
    public void generateUniqueKeyUsingUUID() throws Exception {
    }

    @Test
    public void createGroup(){
        List<GroupInterface> allGroups = new ArrayList<>();
        List<UserInterface> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createGroup("test", "red");
        assertTrue(yh.getGroups().size() == 1 || yh.users.size() == 1);
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

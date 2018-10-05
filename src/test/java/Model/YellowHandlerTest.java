package Model;

import Model.YellowHandler;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class YellowHandlerTest {

    @Test
    public void createGroup(){
        List<Group> allGroups = new ArrayList<>();
        List<User> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createGroup("test", "red");
        assertTrue(yh.groups.size() == 1 || yh.users.size() == 1);
    }

    @Test
    public void createUser(){
         List<Group> allGroups = new ArrayList<>();
         List<User> allUsers = new ArrayList<>();
        YellowHandler yh = new YellowHandler(allUsers, allGroups);
        yh.createUser("Moki","hej","hej", "hej");
        assertEquals(1, yh.users.size());
    }
}

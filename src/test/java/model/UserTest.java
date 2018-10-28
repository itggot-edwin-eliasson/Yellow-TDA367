package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void addGroup(){
        User user = new User("Moki", "hej", "hej", "hej" , "hej", "hej");
        user.addGroup("12345");
        assertTrue(user.getGroupIds().contains("12345"));
    }
}

package Model;

import Model.YellowHandler;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class YellowHandlerTest {

    @Test
    public void createGroup(){
        YellowHandler yh = new YellowHandler();
        yh.createGroup("test", "red");
        assertTrue(yh.groups.size() == 1 || yh.users.size() == 1);
    }

    @Test
    public void createUser(){
        YellowHandler yh = new YellowHandler();
        yh.createUser("Moki");
        assertEquals(1, yh.users.size());
    }
}

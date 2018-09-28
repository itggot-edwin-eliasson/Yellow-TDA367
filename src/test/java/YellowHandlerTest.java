import Model.YellowHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class YellowHandlerTest {

    @Test
    public void createGroup(){
        YellowHandler yh = new YellowHandler();
        yh.createGroup("Test");
        assertTrue(yh.groups.size() == 1 || yh.users.size() == 1);
    }

    @Test
    public void createUser(){
        YellowHandler yh = new YellowHandler();
        yh.createUser("Moki");
        assertTrue(yh.users.size() == 1);
    }
}

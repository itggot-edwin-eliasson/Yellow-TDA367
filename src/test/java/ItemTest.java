import Model.Item;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void addCategory(){
        Item item = new Item("Boll", "En boll");
        item.addCategory("Sport");
        assertTrue(item.getCategories().contains("Sport"));
    }
}

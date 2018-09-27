import Model.Inventory;
import Model.Item;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryTest {
    @Test
    public void addItem() {
        Inventory i = new Inventory("Testinv");
        i.addItem("Boll", "En boll", 3);
        assertEquals(3, i.getItemList().size());
    }

    @Test
    public void addOneItem(){
        Inventory i = new Inventory("Testinc");
        i.addItem("Boll", "Rund boll",1);
        assertEquals(1, i.getItemList().size());
    }

}
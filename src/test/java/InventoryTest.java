import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryTest {
    @Test
    public void addItem() {
        Inventory i = new Inventory("Testinv");
        Item item = new Item ("Ball", "A round ball");
        i.addItem(item);
        assertEquals(1, i.getItemList().size());
    }

}
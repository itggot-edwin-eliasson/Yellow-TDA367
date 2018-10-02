import Model.Inventory;
import Model.Item;
import Model.Observable;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Joakim Agnemyr
 * @date 2018-09-28
 *
 *
 */

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

    @Test
    public void removeItem () {
        Inventory i = new Inventory("Testing");
        i.addItem("Boll", "Rund boll");
        if (i.getItemList().size() == 1) {
            i.removeItem(i.getItemList().get(0).getId());
            assertEquals(0, i.getItemList().size());
        }
    }

    @Test
    public void searchItem(){
        Inventory i = new Inventory("Testing");
        i.addItem("Grill", "En vanlig grill för bruk utomhus");
        assertEquals("Grill", i.searchItem("Grill").get(0).getName());
    }

    @Test
    public void searchCategory(){
        Inventory i = new Inventory("Testinv");
        i.addItem("Boll", "Rund boll");
        i.getItemList().get(0).addCategory("Sport");
        assertTrue(i.getItemList().get(0).getCategories().contains("Sport"));
    }

}
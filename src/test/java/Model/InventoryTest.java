package Model;

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
        Inventory i = new Inventory("Testinv", "ID");
        i.addItem("Boll", "En boll","ID", "");
        assertEquals(1, i.getItemList().size());
    }

    @Test
    public void addOneItem(){
        Inventory i = new Inventory("Testinc", "ID");
        i.addItem("Boll", "Rund boll","ID", "");
        assertEquals(1, i.getItemList().size());
    }

    @Test
    public void removeItem () {
        Inventory i = new Inventory("Testing", "ID");
        i.addItem("Boll", "Rund boll", "ID", "");
        if (i.getItemList().size() == 1) {
            i.removeItem(i.getItemList().get(0).getId());
            assertEquals(0, i.getItemList().size());
        }
    }

    @Test
    public void searchItem(){
        Inventory i = new Inventory("Testing", "ID");
        i.addItem("Grill", "En vanlig grill för bruk utomhus", "ID", "");
        assertEquals("Grill", i.searchItem("Grill").get(0).getName());
    }

    @Test
    public void searchCategory(){
        Inventory i = new Inventory("Testinv", "ID");
        i.addItem("Boll", "Rund boll", "ID", "");
        i.getItemList().get(0).addCategory("Sport");
        assertTrue(i.getItemList().get(0).getCategories().contains("Sport"));
    }

}
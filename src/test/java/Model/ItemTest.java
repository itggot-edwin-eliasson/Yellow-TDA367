package Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void addCategory(){
        Item item = new Item("Boll", "En boll", "ID");
        item.addCategory("Sport");
        assertTrue(item.getCategories().contains("Sport"));
    }
    @Test
    public void testRentedFunction(){
        Item item = new Item ("Boll","En boll", "ID");
        assertTrue(item.setRentedDate("2018-10-03","2018-10-05"));
        assertFalse(item.setRentedDate("2018-10-04","2018-10-06"));
        assertTrue(item.setRentedDate("2018-10-06","2018-10-08"));
    }
}

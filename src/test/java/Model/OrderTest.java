package Model;

import Model.Item;
import Model.Order;
import org.junit.Test;
import java.text.SimpleDateFormat;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class OrderTest {

    @Test // tests if the date is correct when you create an order
    public void createOrderDateTest(){

        Order o = new Order("id");
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        System.out.print(o.getOrderDate());
        assertEquals(s.format(d), o.getOrderDate());
    }

    @Test // tests if a new item can be added to the item list in the order.
    public void addItemTest(){

        Order o = new Order("id");
        Item tmp = new Item("test","test","ID");
        o.addItem(tmp);
        assertEquals(1,o.getOrderList().size());
    }

    @Test // tests the removeitem function
     public void removeItemTest(){

        Order o = new Order ("id");
        Item tmp = new Item("test","test","ID");
        o.addItem(tmp);
        assertEquals(1,o.getOrderList().size());
        Item tmpItem = o.removeItem("WrongId");
        assertEquals(1,o.getOrderList().size());
        Item tmpItem2 = o.removeItem("RandomId");
        assertEquals(0,o.getOrderList().size());
    }

}

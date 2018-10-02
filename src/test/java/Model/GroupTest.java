package Model;

import Model.Group;
import Model.Inventory;
import Model.Item;
import Model.Order;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void generateInviteCode(){
        Group g = new Group("Test");
        List<Integer> l = new ArrayList<>();
        g.generateInviteCode(l);
        assertTrue(g.getInviteCode() > 999 || g.getInviteCode() < 10000);
    }

    @Test
    public void generateUniqueInviteCode(){
        Group g = new Group("Test");
        List<Integer> l = new ArrayList<>();
        g.generateInviteCode(l);
        int tmp = g.getInviteCode();
        g.generateInviteCode(l);
        assertTrue(tmp != g.getInviteCode());
    }
    @Test
    public void findItemByIdTest(){
        Group g = new Group("test");
        g.createInventory("inventory", "ID123");
        g.selectInventory("ID123");
        g.getSelectedInventory().addItem("boll","en boll", "itemID123");
        Item tmp = g.findItemByID("itemID123");
        assertEquals("boll", tmp.getName());
        tmp.setName("bollen");
        Item tmp2 = g.findItemByID("itemID123");
        assertEquals("bollen",tmp2.getName());
    }
    @Test
    public void findInventoryByIdTest(){
        Group g = new Group("test");
        g.createInventory("inventory123", "ID123");
        Inventory tmp = g.findInventory("ID123");
        assertEquals("inventory123",tmp.getName());
    }

    @Test
    public void findOrderByIdTest(){
        Group g = new Group("test");
        g.createOrder("orderId");
        Order tmp = g.findOrder("orderId");
        tmp.setRenter("hej","123");
        Order tmp2 = g.findOrder("orderId");
        assertEquals("hej",tmp2.getRenter().getName());
    }
    @Test
    public void addItemToOrderTest(){
        Group g = new Group("test");
        g.createInventory("inventory", "ID123");
        g.selectInventory("ID123");
        g.getSelectedInventory().addItem("boll","en boll", "itemID123");
        g.createOrder("orderID");
        g.addItemToOrder("itemID123","orderID","ID123");
        assertEquals(0,g.getSelectedInventory().getItemList().size());
        assertEquals(1,g.findOrder("orderID").getOrderList().size());
    }
}
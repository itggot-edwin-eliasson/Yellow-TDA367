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
        List<Integer> l = new ArrayList<>();
        Group g = new Group("Test", "red", "ID", l);
        assertTrue(g.getInviteCode() > 999 || g.getInviteCode() < 10000);
    }

    @Test
    public void generateUniqueInviteCode(){
        List<Integer> l = new ArrayList<>();
        Group g = new Group("Test","red","ID",l);
        int tmp = g.getInviteCode();
        assertTrue(tmp == g.getInviteCode());
    }
    @Test
    public void findItemByIdTest(){
        List<Integer> l = new ArrayList<>();
        Group g = new Group("test","red","ID",l);
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
        List<Integer> l = new ArrayList<>();
        Group g = new Group("test","red","ID",l);
        g.createInventory("inventory123", "ID123");
        Inventory tmp = g.findInventory("ID123");
        assertEquals("inventory123",tmp.getName());
    }

    @Test
    public void findOrderByIdTest(){
        List<Integer> l = new ArrayList<>();
        Group g = new Group("test","red","ID",l);
        g.createOrder("orderId");
        g.orderIsCompleted();
        Order tmp = g.findOrder("orderId");
        tmp.setRenter("hej","123");
        Order tmp2 = g.findOrder("orderId");
        assertEquals("hej",tmp2.getRenter().getName());
    }
    @Test
    public void addItemToOrderTest(){
        List<Integer> l = new ArrayList<>();
        Group g = new Group("test","red","ID",l);
        g.createInventory("inventory", "ID123");
        g.selectInventory("ID123");
        g.getSelectedInventory().addItem("boll","en boll", "itemID123");
        g.createOrder("orderID");
        g.addItemToOrder("itemID123");
        g.orderIsCompleted();
        assertEquals(1,g.findOrder("orderID").getOrderList().size());
    }
    @Test
    public void removeItemFromOrder(){
        List<Integer> l = new ArrayList<>();
        Group g = new Group("test","red","ID",l);
        g.createInventory("inventory", "ID123");
        g.selectInventory("ID123");
        g.getSelectedInventory().addItem("boll","en boll", "itemID123");
        g.createOrder("orderID");
        g.addItemToOrder("itemID123");
        g.orderIsCompleted();
        assertEquals(1,g.findOrder("orderID").getOrderList().size());
        g.selectOrder("orderID");
        g.removeItemFromOrder("itemID123");
        assertEquals(0,g.getActiveOrder().getOrderList().size());
    }
    @Test
    public void removeOrderTest(){
        List<Integer> l = new ArrayList<>();
        Group g = new Group("test","red","ID",l);
        g.createInventory("inventory", "ID123");
        g.selectInventory("ID123");
        g.getSelectedInventory().addItem("boll","en boll", "itemID123");
        g.createOrder("orderID");
        g.addItemToOrder("itemID123");
        g.orderIsCompleted();
        assertEquals(1,g.getOrderList().size());
        g.orderIsReturned("orderID");
        assertEquals(0,g.getOrderList().size());
        assertEquals(1,g.getOldOrders().size());
    }

}
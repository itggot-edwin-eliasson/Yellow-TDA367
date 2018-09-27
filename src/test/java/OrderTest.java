import Model.Item;
import Model.Order;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class OrderTest {

    @Test // tests if the date is correct when you create an order
    public void createOrderDateTest(){
        Order o = new Order("id");
        System.out.print(o.getOrderDate());
        assertEquals(getCurrentDate(),o.getOrderDate());
    }

    @Test // tests if a new item can be added to the item list in the order.
    public void AddItemTest(){
        Order o = new Order("id");
        Item tmp = new Item("test","test");
        o.addItem(tmp);
        assertEquals(1,o.getOrderList().size());
    }


    private String getCurrentDate(){
        Date now = new Date();
        StringBuilder tmp = new StringBuilder();
        tmp.append((now.getYear()+1900)+"");
        if (now.getMonth() < 10){
            tmp.append("-0"+(now.getMonth()+1));
        }else {
            tmp.append("-"+(now.getMonth()+1));
        }
        if (now.getDate() < 10){
            tmp.append("-0"+now.getDate());
        }else {
            tmp.append("-"+now.getDate());
        }
        return tmp.toString();
    }

}

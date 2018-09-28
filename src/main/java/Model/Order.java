package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String orderDate;
    private String dateOfReturn;
    private List <Item> itemList = new ArrayList<>();
    private String orderID;

    //Creates a new Order with today's date as the date.
    public Order (String orderID){
        this.orderDate = getCurrentTimeStamp();
        this.orderID = orderID;
    }

    //Adds a new item to the order from said inventory.
    public void addItem (Item item){
        itemList.add(item);
    }

    //TO DO: This should remove the said item and add it back to the inventory list.
    public Item removeItem(String ID){
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId() == ID){
                Item tmpItem = itemList.get(i);
                itemList.remove(i);
                return tmpItem;
            }
        }
        return null;
    }

    //Sets date of return from the GUI when the order is sent.
    public void setDateOfReturn (String dateOfReturn){
        this.dateOfReturn = dateOfReturn;
    }

    //returns the date the order should be returned.
    public String getDateOfReturn(){
        return dateOfReturn;
    }

    //returns the Date in the Date format
    public String getOrderDate(){
        return orderDate;
    }

    //returns the list of items the order has
    public List<Item> getOrderList(){
        return itemList;
    }




    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}

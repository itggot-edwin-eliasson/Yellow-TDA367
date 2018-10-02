package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** @author Viktor Valadi
 * @date 2018-09-27
 *
 * A class representing an order made on the inventory.
 *
 * --
 * 26/09 Modified by Viktor. Makes it possible to create an order and add items
 * to it. Also added timestamps to the order.
 * 28/09 Modified by Viktor. Made it possible to remove items from the order.
 * 02/10 Modified by Viktor. Added javadoc documentation.
 * 02/10 Modified by Viktor. Added renter to the order.
 *
 */

public class Order {
    private String orderDate;
    private String dateOfReturn;
    private List <Item> itemList = new ArrayList<>();
    private String orderID;
    private Renter renter;

    /**
     * Creates a new Order
     * @param orderID the generated ID assigned to the order.
     */
    public Order (String orderID){
        this.orderDate = getCurrentTimeStamp();
        this.orderID = orderID;
    }

    /**
     * Adds an item to the list of items in the order.
     * @param item the item that should be added.
     */
    public void addItem (Item item){
        itemList.add(item);
    }

    /**
     * Sets the renter for the order.
     * @param name name of the renter from the GUI.
     * @param phoneNr phone number from the renter from the GUI.
     */
    public void setRenter(String name, String phoneNr){
        this.renter = new Renter(name, phoneNr);
    }
    public Renter getRenter(){
        return renter;
    }

    /**
     * Removes an item from the inventory
     * @param ID the ID of the item that should be removed.
     * @return returns the item back to the inventory.
     */
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

    /**
     * Sets the date the order should be returned.
     * @param dateOfReturn generated from the GUI to address when the order is to be returned.
     */
    public void setDateOfReturn (String dateOfReturn){
        this.dateOfReturn = dateOfReturn;
    }

    public String getDateOfReturn(){
        return dateOfReturn;
    }


    public String getOrderDate(){
        return orderDate;
    }

    public List<Item> getOrderList(){
        return itemList;
    }
    public String getOrderID(){
        return orderID;
    }


    /**
     * Gets the current date for the GUI
     * @return the date as a string.
     */
    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }


}

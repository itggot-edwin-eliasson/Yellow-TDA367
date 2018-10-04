package Model;

import java.util.List;

public interface OrderInterface {

    public void addItem (Item item);

    /**
     * Sets the renter for the order.
     * @param name name of the renter from the GUI.
     * @param phoneNr phone number from the renter from the GUI.
     */
    public void setRenter(String name, String phoneNr);

    public Renter getRenter();

    /**
     * Removes an item from the inventory
     * @param ID the ID of the item that should be removed.
     * @return returns the item back to the inventory.
     */
    public Item removeItem(String ID);

    /**
     * Sets the date the order should be returned.
     * @param dateOfReturn generated from the GUI to address when the order is to be returned.
     */
    public void setDateOfReturn (String dateOfReturn);

    public String getDateOfReturn();

    public String getOrderDate();

    public List<Item> getOrderList();

    public String getOrderID();


}

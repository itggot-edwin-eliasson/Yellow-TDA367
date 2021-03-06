package model;

import java.io.Serializable;
import java.util.List;

public interface OrderInterface extends Serializable {

    /**
     * Adds an item to the order
     * @param item the item that should be added.
     */
    public void addItem (ItemInterface item);

    /**
     * Sets the renter for the order.
     * @param name name of the renter from the GUI.
     * @param phoneNr phone number from the renter from the GUI.
     */
    public void setRenter(String name, String phoneNr);

    public RenterInterface getRenter();

    /**
     * Removes an item from the inventory
     * @param ID the ID of the item that should be removed.
     * @return returns the item back to the inventory.
     */
    public ItemInterface removeItem(String ID);

    /**
     * Sets the date the order should be returned.
     * @param dateOfReturn generated from the GUI to address when the order is to be returned.
     */
    public void setDateOfReturn (String dateOfReturn);

    public String getDateOfReturn();

    public String getOrderDate();

    void setOrderDate(String orderDate);

    public List<ItemInterface> getOrderList();

    public String getOrderID();

    List<Boolean> getIsRentable();

    public void setIsRentable(int i, boolean b);
}

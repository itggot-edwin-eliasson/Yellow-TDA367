package Model;

import java.io.Serializable;
import java.util.List;

public interface GroupInterface extends Serializable {

    /**
     * Get the invite code
     * @return The invite code
     */

    public int getInviteCode();

    /**
     * Creates and adds a new inventory int the List with inventories.
     * @param name The name of the new inventory
     */
    public void createInventory (String name, String ID);

    /**
     * Creates a new order.
     * @param ID the generated ID for the order.
     */
    public void createOrder (String ID);

    /**
     * Finds a certain order by its ID.
     * @param ID the ID of the order you want to find.
     * @return the Order.
     */
    public OrderInterface findOrder (String ID);

    /**
     * Finds a certain inventory by its ID.
     * @param ID the ID of the inventory to find.
     * @return the inventory.
     */
    public InventoryInterface findInventory (String ID);

    /**
     * Finds an item with a certain ID in said inventory.
     * @param ID ID of the item to find.
     * @return returns the Item.
     */
    public ItemInterface findItemByID (String ID);

    /**
     * Adds a item to an order and removes it from its inventory.
     * @param itemID The ID of the item that should be moved.
     *
     *
     */
    public void addItemToOrder (String itemID);

    /**
     * Removes an item from the active order.
     * @param itemID ID of the item that should be removed.
     */
    public void removeItemFromOrder(String itemID);


    public InventoryInterface getSelectedInventory ();

    /**
     * selects an inventory as the active inventory.
     * @param inventoryID the ID of the inventory that is to be selected.
     */
    public void selectInventory (String inventoryID);

    public String getName();

    public String getColor();

    public String getId();

    /**
     * Creates a new item and adds it to an inventory.
     * @param name Name of the item you want to create.
     * @param description Description of the item you want to create.
     * @param id ID generated in YellowHandler class.
     * @param inventoryId ID of the inventory where the item should be placed.
     */
    public void addItem(String name, String description, String id, String inventoryId, String imageURL);

    /**
     * Removes an item with a certain ID from the selected inventory.
     * @param id ID of the item.
     */
    public void removeItem(String id);

    public List<InventoryInterface> getInventories();

    /**
     * Moves the order from the onGoing orders list to the oldOrders list.
     * @param orderID
     */
    void orderIsReturned(String orderID);

    void setName(String name);

    void setColor(String color);

    /**
     * Updates the isRented variable for every item in the selected inventory compared to today.
     */
    public void updateInventory();

    /**
     * Checks if all the dates in the active order are available for rent in the said period. If they are
     * the order will be added to the onGoing orders list. Else it will return false.
     * @param startDate start date of when you want to make the order.
     * @param endDate end date of when you want to make the order.
     * @return true if it worked, false if it did not work.
     */
    public Boolean orderIsCompleted(String startDate, String endDate, String renterName, String renterPhoneNumber);

    public OrderInterface getActiveOrder();

    public List<OrderInterface> getOldOrders();

    public List<OrderInterface> getOrderList();
}




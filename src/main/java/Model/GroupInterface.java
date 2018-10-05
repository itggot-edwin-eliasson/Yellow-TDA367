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
    public Order findOrder (String ID);

    /**
     * Finds a certain inventory by its ID.
     * @param ID the ID of the inventory to find.
     * @return the inventory.
     */
    public Inventory findInventory (String ID);

    /**
     * Finds an item with a certain ID in said inventory.
     * @param ID ID of the item to find.
     * @return returns the Item.
     */
    public Item findItemByID (String ID);

    /**
     * Adds a item to an order and removes it from its inventory.
     * @param itemID The ID of the item that should be moved.
     * @param orderID The ID of the order that it should be moved to.
     * @param inventoryID The ID of the inventory that the item should be moved from.
     *
     */
    public void addItemToOrder (String itemID, String orderID, String inventoryID);

    public void removeItemFromOrder();


    public Inventory getSelectedInventory ();

    /**
     * selects an inventory as the active inventory.
     * @param inventoryID the ID of the inventory that is to be selected.
     */
    public void selectInventory (String inventoryID);
}



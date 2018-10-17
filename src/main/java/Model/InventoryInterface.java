package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface InventoryInterface extends Serializable {

    /**
     * Creates several items of the same type
     *
     * @param itemName The name of the item
     * @param itemDescription The description of item
     */
    public ItemInterface addItem (String itemName, String itemDescription, String itemID);


    public void addItemToList(ItemInterface item);
    /**
     * Removes item, if the list is empty a message will be displayed
     *
     * @param id The selected Id of the item chooses which item that
     *           should be removed
     */
    public void removeItem (String id);

    public List<ItemInterface> searchItem(String search);


    public List <String> getCategories();


    /**
     * Get the inventory name
     * @return The name of the inventory
     */
    public String getName();

    /**
     * Get the complete list of items in inventory
     * @return The complete list of items in inventory
     */
    public List<ItemInterface> getItemList ();

    public String getID ();

    /**
     * Change the name of the inventory
     * @param name the new name of the inventory
     */
    public void setName (String name);

}

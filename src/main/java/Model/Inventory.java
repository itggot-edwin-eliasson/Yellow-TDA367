package Model;

import java.util.ArrayList;
import java.util.List;

/** @author Joakim Agnemyr
 * @date 2018-09-26
 *
 * A class representing the inventory
 *
 *---
 * 26/09 Modified by Joakim. Makes it possible to add items.
 * 28/09 Modified by Joakim. Makes it possible to remove items.
 * 02/10 Modified by Viktor. Added ID to the constructor.
 */

public class Inventory implements InventoryInterface{


    private String name;
    private List<ItemInterface> itemlist;
    private List<String> categories;
    private String ID;
    private ItemFactory ifa = new ItemFactory();

    /**
     * Create a new inventory
     * @param name Name of the inventory
     */

    public Inventory (String name, String ID) {
        this.name = name;
        this.itemlist = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.ID = ID;
    }

    /**
     * Creates several items of the same type
     *
     * @param itemName The name of the item
     * @param itemDescription The description of item
     */
    @Override
    public ItemInterface addItem (String itemName, String itemDescription, String itemID) {
        ItemInterface item;
        item = ifa.createItem(itemName, itemDescription, itemID);
        itemlist.add(item);
        return item;
    }

    @Override
    public void addItemToList(ItemInterface item) {
        itemlist.add(item);
    }

    /**
     * Removes item, if the list is empty a message will be displayed
     *
     * @param id The selected Id of the item chooses which item that
     *           should be removed
     */
    @Override
    public void removeItem (String id) {
        if (itemlist.size() > 0) {
            ItemInterface item;
            for (int i = 0; i < itemlist.size(); i++) {
                item = itemlist.get(i);

                if (item.getId().equals(id)) {
                    itemlist.remove(i);
                }
            }
        }
        else {
            System.out.print ("Nothing to remove, the list is empty");
        }
    }
    @Override
    public List<ItemInterface> searchItem(String search){
        List<ItemInterface> results = new ArrayList<>();
        for(int i = 0; i < itemlist.size(); i++){
            ItemInterface item = itemlist.get(i);
            if(item.getName().contains(search)){
                results.add(itemlist.get(i));
            }
            for(int j = 0; j < item.getCategories().size(); j++){
                String category = categories.get(j);
                if(category.contains(search)){
                    results.add(item);
                }
            }
        }
        return results;
    }

    @Override
    public List <String> getCategories() {
        return categories;
    }


    /**
     * Get the inventory name
     * @return The name of the inventory
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Get the complete list of items in inventory
     * @return The complete list of items in inventory
     */
    @Override
    public List<ItemInterface> getItemList ()  {
        return itemlist;
    }

    @Override
    public String getID (){return ID;}

    /**
     * Change the name of the inventory
     * @param name the new name of the inventory
     */
    @Override
    public void setName (String name) {
        this.name = name;
    }


}

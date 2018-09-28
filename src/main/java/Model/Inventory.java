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
 */

public class Inventory {


    private String name;
    private List <Item> itemlist;
    private List <String> categories;

    /**
     * Create a new inventory
     * @param name Name of the inventory
     */

    public Inventory (String name) {
        this.name = name;
        this.itemlist = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    /**
     * Create a new item
     *
     * @param itemName The name of the item
     * @param itemDescription The description of the item
     */


    public void addItem(String itemName, String itemDescription){ //Add Item to list if amount =1
        Item item = new Item(itemName,itemDescription);
        itemlist.add(item);
    }

    /**
     * Creates several items of the same type
     *
     * @param itemName The name of the item
     * @param itemDescription The description of item
     * @param amount Amount of item you want to create
     */

    public void addItem (String itemName, String itemDescription, int amount) { //Adds Items to list if amout > 1
        if(amount > 1){
            for(int i = 0; i < amount; i++){
                Item item = new Item(itemName, itemDescription);
                itemlist.add(item);
            }
        }else{
            addItem(itemName, itemDescription);
        }
    }

    /**
     * Removes item, if the list is empty a message will be displayed
     *
     * @param id The selected Id of the item chooses which item that
     *           should be removed
     */

    public void removeItem (String id) {
        if (itemlist.size() > 0) {
            Item item;
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

    public List<Item> searchItem(String search){
        List<Item> results = new ArrayList<>();
        for(int i = 0; i < itemlist.size(); i++){
            Item item = itemlist.get(i);
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


    public List <String> getCategories() {
        return categories;
    }


    /**
     * Get the inventory name
     * @return The name of the inventory
     */

    public String getName() {
        return this.name;
    }

    /**
     * Get the complete list of items in inventory
     * @return The complete list of items in inventory
     */

    public List <Item> getItemList ()  {
        return itemlist;
    }

    /**
     * Change the name of the inventory
     * @param name the new name of the inventory
     */

    public void setName (String name) {
        this.name = name;
    }

}

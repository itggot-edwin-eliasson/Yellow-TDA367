package model;

import java.util.ArrayList;
import java.util.List;

public class Inventory implements InventoryInterface{

    private String name;
    private List<ItemInterface> itemlist;
    private List<String> categories;
    private String ID;
    private ItemFactory itemFactory = new ItemFactory();

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

    @Override
    public ItemInterface addItem (String itemName, String itemDescription, String itemID, String imageURL) {
        ItemInterface item;
        item = itemFactory.createItem(itemName, itemDescription, itemID, imageURL);
        itemlist.add(item);
        return item;
    }

    @Override
    public void addItemToList(ItemInterface item) {
        itemlist.add(item);
    }

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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<ItemInterface> getItemList ()  {
        return itemlist;
    }

    @Override
    public String getID (){return ID;}

    @Override
    public void setName (String name) {
        this.name = name;
    }

}

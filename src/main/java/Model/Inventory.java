package Model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private String name;
    private List <Item> itemlist;
    private List <String> categories;


    public Inventory (String name) {
        this.name = name;
        this.itemlist = new ArrayList<>();
        this.categories = new ArrayList<>();
    }


    public void addItem(String itemName, String itemDescription){ //Add Item to list if amount =1
        Item item = new Item(itemName,itemDescription);
        itemlist.add(item);
    }

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
            if(itemlist.get(i).getName().contains(search)){
                results.add(itemlist.get(i));
            }
        }
        return results;
    }


    public List <String> getCategories() {
        return categories;
    }

    public String getName() {
        return this.name;
    }

    public List <Item> getItemList ()  {
        return itemlist;
    }

    public void setName (String name) {
        this.name = name;
    }




    public void setCategories () {

    }
}

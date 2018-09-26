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


    private void addItem(String itemName, String itemDescription){
        Item item = new Item(itemName,itemDescription);
        itemlist.add(item);
    }

    public void addItem (String itemName, String itemDescription, int amount) {
        if(amount > 1){
            for(int i = 0; i < amount; i++){
                Item item = new Item(itemName, itemDescription);
                itemlist.add(item);
            }
        }else{
            addItem(itemName, itemDescription);
        }
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

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


    public List <String> getCategories() { return categories; }

    public void addItem (Item item) {
        itemlist.add(item);
    }

    public String getName() {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public List <Item> getItemList ()  {
        return itemlist;
    }


    public void setCategories () {

    }
}

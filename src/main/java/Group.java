import java.util.List;

public class Group {

    String name;
    Inventory selectedInventory;

    public Group (String name) {
        this.name = name;
    }

    List <Inventory> inventories;


    public void createInventory (String name) {
        Inventory i = new Inventory(name);
        inventories.add(i);

    }

    public Inventory getInventory () {

        return null;
    }

    public void selectInventory (Inventory i) {
        selectedInventory = i;

    }
}

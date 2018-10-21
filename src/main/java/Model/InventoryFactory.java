package Model;

public class InventoryFactory {

    public InventoryInterface createInventory (String name, String id) {
        return new Inventory(name, id);
    }
}

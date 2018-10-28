package model;

import java.io.Serializable;

public class InventoryFactory implements Serializable {

    public InventoryInterface createInventory (String name, String id) {
        return new Inventory(name, id);
    }
}

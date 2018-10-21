package Model;

import java.io.Serializable;

public class ItemFactory implements Serializable {

    public ItemInterface createItem (String name, String description, String id) {
        return new Item(name, description, id);
    }
}

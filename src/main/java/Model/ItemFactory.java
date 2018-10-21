package Model;

public class ItemFactory {

    public ItemInterface createItem (String name, String description, String id) {
        return new Item(name, description, id);
    }
}

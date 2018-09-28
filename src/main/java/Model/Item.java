package Model;

public class Item {

    private String name;
    private String description;
    private String id;

    public Item (String name, String description) {
        this.name = name;
        this.description = description;
        this.id = "RandomId";

    }

    public String getId() {
        return this.id;
    }
}

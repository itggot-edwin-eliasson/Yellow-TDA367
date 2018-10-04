package Model;

import java.util.ArrayList;
import java.util.List;

public class Item implements ItemInterface{

    /**
     * @author Joakim Agnemyr
     * @date 2018-26-09
     *
     * A class representing an item
     *
     * ---
     *
     * 28/09 Created a method for getting Id's.
     */
    private String name;
    private String description;
    private String id;
    private List<String> categories = new ArrayList<>();

    /**
     *
     * @param name The name of the item
     * @param description The description of the item
     */
    public Item (String name, String description, String ID) {
        this.name = name;
        this.description = description;
        this.id = ID;

    }

    @Override
    public void setName (String name){
        this.name = name;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public String getDescription(){
        return this.description;
    }

    @Override
    public List<String> getCategories(){
        return this.categories;
    }

    @Override
    public void addCategory(String category){
        categories.add(category);
    }


}

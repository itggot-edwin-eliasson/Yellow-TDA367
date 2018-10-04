package Model;

import java.util.List;

public interface ItemInterface {

    public void setName (String name);

    public String getId();

    public String getName();

    public String getDescription();

    public List<String> getCategories();

    public void addCategory(String category);
}

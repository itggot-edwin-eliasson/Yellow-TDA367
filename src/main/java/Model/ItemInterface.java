package Model;

import javafx.scene.image.Image;

import java.util.List;

public interface ItemInterface {

    public void setName (String name);

    public String getId();

    public String getName();

    public String getDescription();

    public List<String> getCategories();

    public void addCategory(String category);

    public Image getImage();

    public void setIsRented(Boolean isRented);

    public Boolean checkDateIsNotInRentedPeriod (String date);

    public Boolean setRentedDate(String startDate, String endDate);

    public Boolean getIsRented();
}

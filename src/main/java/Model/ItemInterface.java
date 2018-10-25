package Model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.List;

public interface ItemInterface extends Serializable {

    public void setName (String name);

    public String getId();

    public String getName();

    public String getDescription();

    public List<String> getCategories();

    public void addCategory(String category);


    public void setIsRented(Boolean isRented);

    /**
     * Checks if a date does not conflict with any period that the item is already rented in.
     * @param date date that should be checked against.
     * @return true if no conflict, else false.
     */
    public Boolean checkDateIsNotInRentedPeriod (String date);

    /**
     *
     * @param startDate in the format "2018-10-21"
     * @param endDate in the format "2018-10-21"
     * @return true if no conflict and the rented period was safe to rent in. Else false.
     */
    public Boolean setRentedDate(String startDate, String endDate);

    public Boolean getIsRented();
}

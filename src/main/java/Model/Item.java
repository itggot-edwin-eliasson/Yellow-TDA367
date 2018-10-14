package Model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String , String> rentedDates = new HashMap<>();
    private int orderCount = 0;
    private Boolean isRented = false;
    private Image image;

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

    /**
     * Checks if a date conflicts with another date that the item is rented in.
     * @param date the date that will be checked.
     * @return True if no conflict. False if conflict.
     */
    @Override
    public Boolean checkDateIsNotInRentedPeriod (String date){
        for (int i = 0; i < orderCount; i++) {
            int startDate = getDateAsInt(rentedDates.get("startDate"+(i+1)+""));
            int endDate = getDateAsInt(rentedDates.get("endDate"+(i+1)+""));
            int dateToBeChecked = getDateAsInt(date);
            if(dateToBeChecked <= endDate && dateToBeChecked >= startDate){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a start and end of an order is available and then sets the item to rented during this period.
     *
     * @param startDate
     * @param endDate
     * @return
     */

    @Override
    public Boolean setRentedDate(String startDate, String endDate){
        if (checkDateIsNotInRentedPeriod(startDate) && checkDateIsNotInRentedPeriod(endDate)){
            String keyStart = "startDate"+(orderCount+1)+"";
            String keyEnd = "endDate"+(orderCount+1)+"";
            rentedDates.put(keyStart, startDate);
            rentedDates.put(keyEnd, endDate);
            orderCount++;
            return true;
        }
        return false;
    }


    private int getDateAsInt (String date){
        String tmp = date.charAt(0)+date.charAt(1)+date.charAt(2)+date.charAt(3)+date.charAt(5)+date.charAt(6)+date.charAt(8)+date.charAt(9)+"";
        return Integer.parseInt(tmp);
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

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setIsRented(Boolean isRented) {
        this.isRented = isRented;
    }

    @Override
    public Boolean getIsRented(){
        return isRented;
    }
}

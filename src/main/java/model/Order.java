package model;

import java.util.ArrayList;
import java.util.List;

public class Order implements OrderInterface{
    private String orderDate;
    private String dateOfReturn;
    private List <ItemInterface> itemList = new ArrayList<>();
    private String orderID;
    private RenterInterface renter;
    private List <Boolean> isRentable = new ArrayList<>();
    private RenterFactory renterFactory = new RenterFactory();

    /**
     * Creates a new Order
     * @param orderID the generated ID assigned to the order.
     */
    public Order (String orderID){
        this.orderID = orderID;
    }

    @Override
    public void addItem (ItemInterface item){
        if(!alreadyInList(item)){
            itemList.add(item);
            isRentable.add(null);
        }
    }

    private boolean alreadyInList(ItemInterface itemToCheck) {
        boolean isAdded = false;
        for (ItemInterface item:itemList) {
            if(item.getId().equals(itemToCheck.getId())){
                isAdded = true;
            }
        }
        return isAdded;
    }

    @Override
    public void setRenter(String name, String phoneNr){
        this.renter = renterFactory.createRenter(name, phoneNr);
    }

    @Override
    public RenterInterface getRenter(){
        return renter;
    }

    @Override
    public ItemInterface removeItem(String ID){
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId() == ID){
                ItemInterface tmpItem = itemList.get(i);
                itemList.remove(i);
                return tmpItem;
            }
        }
        return null;
    }

    @Override
    public void setDateOfReturn (String dateOfReturn){
        this.dateOfReturn = dateOfReturn;
    }

    @Override
    public String getDateOfReturn(){
        System.out.println(dateOfReturn);
        return dateOfReturn;
    }

    @Override
    public String getOrderDate(){
        return orderDate;
    }

    @Override
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public List<ItemInterface> getOrderList(){
        return itemList;
    }

    @Override
    public String getOrderID(){
        return orderID;
    }


    @Override
    public void setIsRentable(int index, boolean canBeRented) {
        isRentable.remove(index);
        isRentable.add(index,canBeRented);
    }

    @Override
    public List<Boolean> getIsRentable() {
        return isRentable;
    }
}

package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Group implements GroupInterface{

   private String name;
   private InventoryInterface selectedInventory;
   private int inviteCode;
   private List <InventoryInterface> inventories = new ArrayList<>();
   private List <OrderInterface> orderList = new ArrayList<>();
   private List <OrderInterface> oldOrders = new ArrayList<>();
   private OrderInterface activeOrder;
   private String color;
   private String id;
   private InventoryFactory ifa = new InventoryFactory();

    /**
     * Creates a group
     * @param name The name of the group
     * @param color The GUI color for the group.
     *
     */
    public Group (String name, String color, String id, List<Integer> groupInviteCodes) {
        this.name = name;
        this.color = color;
        this.id = id;
        this.inviteCode = generateInviteCode(groupInviteCodes);
    }

    private int generateInviteCode(List<Integer> groupInviteCodes){
        inviteCode = (int)(Math.random()*9000)+1000;
        if (groupInviteCodes.contains(inviteCode)){
            generateInviteCode(groupInviteCodes);
        } else {
            groupInviteCodes.add(inviteCode);
        }
        return inviteCode;
    }

    @Override
    public void addItem(String name, String description, String id, String inventoryId) {
        ItemInterface item = selectedInventory.addItem(name, description, id);
        if(!selectedInventory.getName().equals("All items"))
            inventories.get(0).addItemToList(item);
    }

    @Override
    public void removeItem (String id) {
        selectedInventory.removeItem(id);
    }

    @Override
    public int getInviteCode(){
        return inviteCode;
    }

    @Override
    public String getId(){
        return id;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getColor(){
        return color;
    }

    @Override
    public void createInventory (String name, String ID) {
        InventoryInterface i = ifa.createInventory(name, ID);
        inventories.add(i);
    }

    @Override
    public void createOrder (String ID){
        if(activeOrder == null) {
            OrderInterface order = new Order(ID);
            activeOrder = order;
        }
    }

    @Override
    public OrderInterface findOrder (String ID){
        for (OrderInterface order: orderList) {
            if (order.getOrderID().equals(ID)){
                return order;
            }
        }
        return null;
    }

    @Override
    public InventoryInterface findInventory (String ID){
        for (InventoryInterface inventory: inventories){
            if (inventory.getID().equals(ID)){
                return inventory;
            }
        }return null;
    }

    @Override
    public ItemInterface findItemByID (String ID){
        for (ItemInterface item: selectedInventory.getItemList()){
            if (item.getId().equals(ID)){
                return item;
            }
        }
        return null;
    }

    @Override
    public void addItemToOrder (String itemId){
        ItemInterface item = findItemByID(itemId);
        if(item != null)
            activeOrder.addItem(item);
    }

    @Override
    public void removeItemFromOrder(String itemID){
        activeOrder.removeItem(itemID);
    }

    @Override
    public InventoryInterface getSelectedInventory () {
        if(selectedInventory != null)
            return selectedInventory;
        return null;
    }

    @Override
    public void selectInventory (String inventoryID) {
        selectedInventory = findInventory(inventoryID);
    }
    public void selectOrder (String orderID){
        activeOrder = findOrder(orderID);
    }

    @Override
    public Boolean orderIsCompleted(String startDate, String endDate, String renterName, String renterPhoneNumber){
        if (allDatesAreOkay(startDate,endDate)){
            setItemsToOrdered(startDate,endDate);
            activeOrder.setRenter(renterName,renterPhoneNumber);
            orderList.add(activeOrder);
            return true;
        }
        return false;
    }

    private void setItemsToOrdered(String startDate, String endDate) {
        for (ItemInterface item: activeOrder.getOrderList()) {
            item.setRentedDate(startDate,endDate);
        }
    }

    private boolean allDatesAreOkay(String startDate, String endDate) {
        boolean works = true;
        for (int i = 0; i < activeOrder.getOrderList().size(); i++) {
            if(!activeOrder.getOrderList().get(i).checkDateIsNotInRentedPeriod(startDate) || !activeOrder.getOrderList().get(i).checkDateIsNotInRentedPeriod(endDate)){
                works = false;
                activeOrder.setIsRentable(i,false);
            }else{
                activeOrder.setIsRentable(i,true);
            }

        }
        return works;
    }

    @Override
    public OrderInterface getActiveOrder(){
        if(activeOrder != null)
            return activeOrder;
        return null;
    }

    @Override
    public void orderIsReturned(String orderID){
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderID().equals(orderID)){
                oldOrders.add(orderList.get(i));
                orderList.remove(i);
            }
        }
    }

    @Override
    public List<OrderInterface> getOldOrders(){return oldOrders;}

    @Override
    public List<OrderInterface> getOrderList(){return orderList;}

    @Override
    public void updateInventory() {
        for (ItemInterface item: selectedInventory.getItemList()){
            item.setIsRented(item.checkDateIsNotInRentedPeriod(getCurrentTimeStamp()));
        }
    }

    @Override
    public List<InventoryInterface> getInventories(){
        return inventories;
    }

    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}

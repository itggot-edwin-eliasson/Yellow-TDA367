package Model;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @date 2018-09-26
 * ---
 * 02/10 Modified by Viktor. Added methods to find Inventory, Item and Order by ID. Also added addItemToOrder
 * method that removes items from said inventory and adds them to said order.
 *
 * 05/10 Modified the order handling by adding "activeOrder" to the class. Fixed the functions and tests.
 * also added removeItemFromOrder method.
 *
 *
 *@author Mona Kilsgård
 *@date 2018-10-04
 *
 */
public class Group implements GroupInterface{

   private String name;
   private Inventory selectedInventory;
   private int inviteCode;
   private List <Inventory> inventories = new ArrayList<>();
   private List <Order> orderList = new ArrayList<>();
   private List <Order> oldOrders = new ArrayList<>();
   private Order activeOrder;
   private String color;
   private String id;

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

    /**
     * Generates an invitecode and checks if it is not taken
     * @param groupInviteCodes A list with current invite codes
     */
    private int generateInviteCode(List<Integer> groupInviteCodes){
        inviteCode = (int)(Math.random()*9000)+1000;
        if (groupInviteCodes.contains(inviteCode)){
            generateInviteCode(groupInviteCodes);
        } else {
            groupInviteCodes.add(inviteCode);
        }
        return inviteCode;
    }

    public void addItem(String name, String description, String id, String inventoryId, int amount) {
        selectedInventory.addItem(name, description, id, amount);
    }

    public void removeItem (String id) {
        selectedInventory.removeItem(id);
    }

    /**
     * Get the invite code
     * @return The invite code
     */
    @Override
    public int getInviteCode(){
        return inviteCode;
    }

    /**
     * Get the group id
     * @return The group id
     */
    public String getId(){
        return id;
    }

    /**
     * Get the group name
     * @return The group name
     */
    public String getName(){
        return name;
    }

    /**
     * Get the color
     * @return The color
     */
    public String getColor(){
        return color;
    }

    /**
     * Creates and adds a new inventory int the List with inventories.
     * @param name The name of the new inventory
     */
    @Override
    public void createInventory (String name, String ID) {
        Inventory i = new Inventory(name, ID);
        inventories.add(i);
    }

    /**
     * Creates a new order.
     * @param ID the generated ID for the order.
     */
    @Override
    public void createOrder (String ID){
        if(activeOrder == null) {
            Order order = new Order(ID);
            activeOrder = order;
        }
    }

    /**
     * Finds a certain order by its ID.
     * @param ID the ID of the order you want to find.
     * @return the Order.
     */
    @Override
    public Order findOrder (String ID){
        for (Order order: orderList) {
            if (order.getOrderID().equals(ID)){
                return order;
            }
        }
        return null;
    }

    /**
     * Finds a certain inventory by its ID.
     * @param ID the ID of the inventory to find.
     * @return the inventory.
     */
    @Override
    public Inventory findInventory (String ID){
        for (Inventory inventory: inventories){
            if (inventory.getID().equals(ID)){
                return inventory;
            }
        }return null;
    }

    /**
     * Finds an item with a certain ID in said inventory.
     * @param ID ID of the item to find.
     * @return returns the Item.
     */
    @Override
    public Item findItemByID (String ID){
        for (Item item: selectedInventory.getItemList()){
            if (item.getId().equals(ID)){
                return item;
            }
        }
        return null;
    }

    /**
     * Adds a item to an order and changes its state to "Rented".
     * @param itemId The ID of the item that should be handled.
     *
     */
    @Override
    public void addItemToOrder (int amount, String itemId){
        Item item = findItemByID(itemId);
        activeOrder.addItem(item);
    }

    /**
     * Removes an item from the active order and sets its state to not rented.
     * @param itemID the ID of the item that should be handled.
     */
    @Override
    public void removeItemFromOrder(String itemID){
        activeOrder.removeItem(itemID);
    }

    @Override
    public Inventory getSelectedInventory () {
        return selectedInventory;
    }

    /**
     * selects an inventory as the active inventory.
     * @param inventoryID the ID of the inventory that is to be selected.
     */
    @Override
    public void selectInventory (String inventoryID) {
        selectedInventory = findInventory(inventoryID);
    }
    public void selectOrder (String orderID){
        activeOrder = findOrder(orderID);
    }

    @Override
    public Boolean orderIsCompleted(String startDate, String endDate){
        if (allDatesAreOkay(activeOrder.getOrderList(),startDate,endDate)){
            orderList.add(activeOrder);
            activeOrder = null;
            return true;
        }
        return false;
    }

    private boolean allDatesAreOkay(List<Item> itemList, String startDate, String endDate) {
        for (Item item: itemList) {
            if(!item.checkDateIsNotInRentedPeriod(startDate) || !item.checkDateIsNotInRentedPeriod(endDate)){
                return false;
            }
        }
        return true;
    }

    public Order getActiveOrder(){
        return activeOrder;
    }

    /**
     * returns the order and moves it from the order list to the old order list.
     * @param orderID ID of the order that is returned.
     */
    public void orderIsReturned(String orderID){
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderID().equals(orderID)){
                oldOrders.add(orderList.get(i));
                orderList.remove(i);
            }
        }
    }
    public List getOldOrders(){return oldOrders;}
    public List getOrderList(){return orderList;}

    public void updateInventory() {
        for (Item item: selectedInventory.getItemList()){
            item.setIsRented(item.checkDateIsNotInRentedPeriod(getCurrentTimeStamp()));
        }
    }
    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}

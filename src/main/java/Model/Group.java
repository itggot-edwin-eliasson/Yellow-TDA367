package Model;



import java.util.ArrayList;
import java.util.List;


/**
 * @date 2018-09-26
 * ---
 * 02/10 Modified by Viktor. Added methods to find Inventory, Item and Order by ID. Also added addItemToOrder
 * Method that removes items from said inventory and adds them to said order.
 *
 *@author Mona Kilsgård
 *@date 2018-10-04
 *
 */

public class Group {

   private String name;
   private Inventory selectedInventory;
   private int inviteCode;
   private List <Inventory> inventories = new ArrayList<>();
   private List <Order> orderList = new ArrayList<>();
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

    /**
     * Get the invite code
     * @return The invite code
     */

    public int getInviteCode(){
        return inviteCode;
    }

    /**
     * Creates and adds a new inventory int the List with inventories.
     * @param name The name of the new inventory
     */
    public void createInventory (String name, String ID) {
        Inventory i = new Inventory(name, ID);
        inventories.add(i);
    }

    /**
     * Creates a new order.
     * @param ID the generated ID for the order.
     */
    public void createOrder (String ID){
        Order order = new Order(ID);
        orderList.add(order);
    }

    /**
     * Finds a certain order by its ID.
     * @param ID the ID of the order you want to find.
     * @return the Order.
     */
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
    public Item findItemByID (String ID){
        for (Item item: selectedInventory.getItemList()){
            if (item.getId().equals(ID)){
                return item;
            }
        }
        return null;
    }

    /**
     * Adds a item to an order and removes it from its inventory.
     * @param itemID The ID of the item that should be moved.
     * @param orderID The ID of the order that it should be moved to.
     * @param inventoryID The ID of the inventory that the item should be moved from.
     *
     */
    public void addItemToOrder (String itemID, String orderID, String inventoryID){
        Order order = findOrder(orderID);
        Inventory inventory = findInventory(inventoryID);
        Item item = findItemByID(itemID);
        order.addItem(item);
        inventory.removeItem(item.getId());
    }

    public void removeItemFromOrder(){

    }


    public Inventory getSelectedInventory () {
        return selectedInventory;
    }

    /**
     * selects an inventory as the active inventory.
     * @param inventoryID the ID of the inventory that is to be selected.
     */
    public void selectInventory (String inventoryID) {
        selectedInventory = findInventory(inventoryID);
    }
}

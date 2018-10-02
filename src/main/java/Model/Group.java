package Model;



import java.util.List;


/**
 * @author Mona Kilsgård
 * @date 2018-09-26
 *
 * ---
 *
 *
 *
 */

public class Group {

   private String name;
   private Inventory selectedInventory;
   private int inviteCode;
   private List <Inventory> inventories;
   private List <Order> orderList;

    /**
     * Creates a group
     * @param name The name of the group
     *
     */


    public Group (String name) {
        this.name = name;
    }
    /**
     * Generates an invitecode and checks if it is not taken
     * @param groupInviteCodes A list with current invite codes
     */

    public void generateInviteCode(List<Integer> groupInviteCodes){
        inviteCode = (int)(Math.random()*9000)+1000;
        if (groupInviteCodes.contains(inviteCode)){
            generateInviteCode(groupInviteCodes);
        } else {
            groupInviteCodes.add(inviteCode);
        }
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
    public void createInventory (String name) {
        Inventory i = new Inventory(name, "ID");
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
    public void findOrder (String ID){

    }
    public void addItemToOrder (String ItemID){

    }
    public void findItemByID (){

    }
    public void removeItemFromOrder(){

    }


    public Inventory getInventory () {
        return null;
    }

    public void selectInventory (Inventory i) {
        selectedInventory = i;
    }



}

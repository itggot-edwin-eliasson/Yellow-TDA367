package model;
import java.util.*;

public class YellowHandler extends Observable implements YellowHandlerInterface {

    private UserInterface activeUser;
    private GroupInterface activegroup;
    private List<GroupInterface> groups = new ArrayList<>();
    private List<UserInterface> users = new ArrayList<>();
    private List<Integer> groupInviteCodes = new ArrayList<>();
    private GroupFactory groupFactory = new GroupFactory();
    private UserFactory userFactory = new UserFactory();


    @Override
    public void createGroup(String groupName, String color){
        GroupInterface g = groupFactory.createGroup(groupName, color, generateUniqueKeyUsingUUID(), groupInviteCodes);
        groupInviteCodes.add(g.getInviteCode());
        String id = generateUniqueKeyUsingUUID();
        g.createInventory("All items", id);
        g.selectInventory(id);
        g.createOrder(generateUniqueKeyUsingUUID());
        groups.add(g);
        activeUser.addGroup(g.getId());
        notifyObservers();
        activegroup = g;
    }

    @Override
    public void changeUserSettings (String firstName, String lastName, String username, String email, String password) {
        activeUser.setName(firstName);
        activeUser.setLastName(lastName);
        activeUser.setUsername(username);
        activeUser.setEmail(email);
        activeUser.setPassword(password);
    }

    @Override
    public List<GroupInterface> getGroups(){
        List<GroupInterface> tmpGroups = new ArrayList<>();
        if(activeUser != null) {
            for (int i = 0; i < activeUser.getGroupIds().size(); i++) {
                for (GroupInterface group : groups) {
                    if (group.getId().equals(activeUser.getGroupIds().get(i))) {
                        tmpGroups.add(group);
                    }
                }
            }
        }
        return tmpGroups;
    }

    @Override
    public boolean createUser(String username, String firstName, String lastName, String email, String password){
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                return false;
                // ERROR MESSAGE
            }
        }
        activeUser = userFactory.createUser(username, firstName, lastName, email, generateUniqueKeyUsingUUID(), password);
        users.add(activeUser);
        return true;
    }

    @Override
    public boolean logIn (String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                if (users.get(i).comparePassword(password)) {
                    activeUser = users.get(i);
                    notifyObservers();
                    return true;
                }
            }
        }
        notifyObservers();
        return false;
    }

    @Override
    public void logOut (){
        activeUser = null;
        activegroup = null;
        notifyObservers();
    }

    @Override
    public void addItem(String name, String description, String imageUrl, GroupInterface group, String inventoryId, int amount) {
        for(int i = 0; i < amount; i++) {
            String id = generateUniqueKeyUsingUUID();
            group.addItem(name, description, id, inventoryId, imageUrl);
        }
        notifyObservers();
    }

    @Override
    public void removeItem (String id) {
        activegroup.removeItem(id);
    }

    @Override
    public boolean joinGroup(int inviteCode){
    for (GroupInterface group : groups) {
            if(inviteCode == group.getInviteCode()){
                if(!activeUser.getGroupIds().contains(group.getId()))
                    activeUser.addGroup(group.getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeGroup (String id) {
        activeUser.removeGroup(id);
    }

    @Override
    public String generateUniqueKeyUsingUUID() {
        // Static factory to retrieve a type 4 (pseudo randomly generated) UUID
        String crunchifyUUID = UUID.randomUUID().toString();
        return crunchifyUUID;
    }

    @Override
    public void addItemToOrder(String itemID) {
         if (activegroup.getActiveOrder() == null){
             activegroup.createOrder(generateUniqueKeyUsingUUID());
         }
         activegroup.addItemToOrder(itemID);
    }

    @Override
    public void removeItemFromOrder(ItemInterface item){
        activegroup.removeItemFromOrder(item.getId());
    }

    @Override
    public UserInterface getActiveUser () {
         return this.activeUser;
    }

    @Override
    public Boolean completeOrder(String startDate, String endDate, String renterName, String renterPhoneNumber) {
        if(activegroup.orderIsCompleted(startDate, endDate, renterName, renterPhoneNumber)) {
            activegroup.createOrder(generateUniqueKeyUsingUUID());
            notifyObservers();
            return true;
        }
        notifyObservers();
        return false;
    }

    @Override
    public GroupInterface getActiveGroup() {
        return this.activegroup;
    }

    @Override
    public List<ItemInterface> getItems() {
        if(activegroup != null) {
            if (activegroup.getSelectedInventory() != null)
                return activegroup.getSelectedInventory().getItemList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<InventoryInterface> getInventories(){
        if(activegroup != null)
            return activegroup.getInventories();
        return new ArrayList<>();
    }

    @Override
    public void setActiveGroup(GroupInterface group) {
        activegroup = group;
        notifyObservers();
    }

    @Override
    public void createInventory(String name, GroupInterface group) {
        group.createInventory(name, generateUniqueKeyUsingUUID());
        notifyObservers();
    }

    @Override
    public String completeOrderFailed(){
        List<Boolean> whoFailedList = activegroup.getActiveOrder().getIsRentable();
        StringBuilder whoFailedMessage = new StringBuilder();
        int count = 0;
        whoFailedMessage.append("The item(s) at place ");
        for (int i = 0; i < whoFailedList.size(); i++) {
            if(!whoFailedList.get(i)){
                if(count>0){
                    whoFailedMessage.append(",");
                }
                whoFailedMessage.append(i+1+" ");
                count++;
            }
        }
        whoFailedMessage.append("are not available for rent during this period");
        return whoFailedMessage.toString();
    }

    @Override
    public void selectInventory(String id) {
        activegroup.selectInventory(id);
    }

    @Override
    public void orderIsReturned(String orderID){
        activegroup.orderIsReturned(orderID);
    }

    @Override
    public List<UserInterface> getUsers() {
        return users;
    }

    @Override
    public List<GroupInterface> getAllGroups() {
        return groups;
    }

    @Override
    public void setGroups(List<GroupInterface> groups) {this.groups = groups;}

    @Override
    public void setAllUsers(List<UserInterface> users) {this.users = users;}

    @Override
    public List<ItemInterface> getActiveOrderItems(){
        if(activegroup != null) {
            return activegroup.getActiveOrder().getOrderList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<OrderInterface> getOldOrders(){
        if(activegroup != null)
            return activegroup.getOldOrders();
        return new ArrayList<>();
    }

    @Override
    public List<OrderInterface> getOngoingOrders(){
        if(activegroup != null)
            return activegroup.getOrderList();
        return new ArrayList<>();
    }
}

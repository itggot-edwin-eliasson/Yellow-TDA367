import static junit.framework.TestCase.assertEquals;

public class ControllerTest {
  /*  @Test
    public void saveAndFetchTestUsers(){
        Main c = new MainController();
        User user = new User ("test","hej","hej","ID", "password", "hej");
        User user2 = new User ("test2","hej","hej","ID2", "password", "hej");
        User user3 = new User ("test3","hej","hej","ID3", "password", "Hej");
        c.saveUser(user);
        c.saveUser(user2);
        c.serialize("users");
        c.saveUser(user3);
        assertEquals(3, c.getAllUsers().size());
        c.deserialize("users");
        assertEquals(2, c.getAllUsers().size());
        assertEquals("test",c.getAllUsers().get(0).getUsername());
    }
    @Test
    public void saveAndFetchTestGroups(){
        MainController c = new MainController();
        List<Integer> tmp = new ArrayList();
        Group group = new Group ("test","red","ID",tmp);
        Group group2 = new Group ("test1","red","ID",tmp);
        Group group3 = new Group ("test2","red","ID",tmp);
        c.saveGroup(group);
        c.saveGroup(group2);
        c.serialize("groups");
        c.saveGroup(group3);
        assertEquals(3, c.getAllGroups().size());
        c.deserialize("groups");
        assertEquals(2, c.getAllGroups().size());
        assertEquals("test",c.getAllGroups().get(0).getName());
    }
    @Test
    public void saveAndFetchTestInventories(){
        MainController c = new MainController();
        Inventory inventory = new Inventory("test","ID");
        Inventory inventory1 = new Inventory("test2","ID");
        Inventory inventory2 = new Inventory("test3", "ID");
        c.saveInventory(inventory);
        c.saveInventory(inventory1);
        c.serialize("inventories");
        c.saveInventory(inventory2);
        assertEquals(3,c.getAllInventories().size());
        c.deserialize("inventories");
        assertEquals(2,c.getAllInventories().size());
        assertEquals("test", c.getAllInventories().get(0).getName());
    }
    @Test
    public void DeserializeNothingTest(){
        MainController c = new MainController();
        c.deserialize("inventories");
        c.serialize("inventories");

    }*/
}

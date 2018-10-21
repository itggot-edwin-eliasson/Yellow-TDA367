package Model;

public class UserFactory {

    public UserInterface createUser (String username, String firstName, String lastName, String email, String id, String password) {
        return new User(username, firstName, lastName, email, id, password);
    }
}

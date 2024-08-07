
import java.util.TreeSet;

public class UserRegistration {

    private TreeSet<String> users;

    public UserRegistration() {
        users = new TreeSet<>();
    }

    public boolean registerUser(String userName) {
        return users.add(userName);
    }

    public boolean removeUser(String userName) {
        return users.remove(userName);
    }

    public void displayUsers() {
        System.out.println("\nRegistered users: ");
        for (String u : users) {
            System.out.println(u);
        }
    }

    public static void main(String[] args) {
        UserRegistration r = new UserRegistration();

        r.registerUser("Sagar");
        r.registerUser("Jyotin");
        r.registerUser("Pradhyumn");
        r.registerUser("Aditya");

        r.displayUsers();

        boolean added = r.registerUser("Sagar");
        System.out.println(added + ": Sagar is already added");

        boolean removed = r.removeUser("Pradhyumn");
        if (removed) {
            System.out.println("\nPradhyumn is removed from the registration.");
        } else {
            System.out.println("\nPradhyumn is already not present in the registration.");
        }

        removed = r.removeUser("Abhiraj");
        if (removed) {
            System.out.println("Abhiraj is removed from the registration.");
        } else {
            System.out.println("Abhiraj is already not present in the registration.");
        }

        r.displayUsers();
        System.out.println("\nUsers are in alphabetical order.");
    }
}

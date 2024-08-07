
import java.util.Map;
import java.util.TreeMap;

class Customer {

    public int id;
    public String name;
    public String email;

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + "\'" + '}';
    }
}

class CustomerAccounts {

    private final TreeMap<Integer, Customer> customerAccounts;

    public CustomerAccounts() {
        customerAccounts = new TreeMap<>();
    }

    public void addCustomer(Customer customer) {
        customerAccounts.put(customer.id, customer);
    }

    public boolean removeCustomer(int customerId) {
        if (customerAccounts.containsKey(customerId)) {
            customerAccounts.remove(customerId);
            return true;
        }
        return false;
    }

    public void displayCustomers() {
        System.out.println("\nCustomer Accounts: ");
        for (Map.Entry<Integer, Customer> entry : customerAccounts.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public static void main(String[] args) {
        CustomerAccounts c = new CustomerAccounts();

        c.addCustomer(new Customer(101, "Sagar", "sagar@gmail.com"));
        c.addCustomer(new Customer(102, "Jyotin", "jyotin@outlook.com"));
        c.addCustomer(new Customer(103, "Pradhyumn", "psingh@yahoo.com"));

        c.displayCustomers();

        boolean removed = c.removeCustomer(102);
        if (removed) {
            System.out.println("\nJyotin removed from the system.");
        } else {
            System.out.println("\nJyotin not found.");
        }

        c.displayCustomers();
    }
}

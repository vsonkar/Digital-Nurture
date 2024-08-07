
import java.util.Hashtable;

class Contact {

    public int id;
    public String name;
    public String phoneNumber;

    public Contact(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", name='" + name + '\'' + ", phoneNumber='" + phoneNumber + '\'' + '}';
    }
}

class ContactManagement {

    private final Hashtable<Integer, Contact> contacts;

    public ContactManagement() {
        contacts = new Hashtable<>();
    }

    public void addContact(Contact contact) {
        contacts.put(contact.id, contact);
    }

    public boolean removeContact(int contactId) {
        if (contacts.containsKey(contactId)) {
            contacts.remove(contactId);
            return true;
        }
        return false;
    }

    public void displayContacts() {
        System.out.println("\nContacts: ");
        for (Contact contact : contacts.values()) {
            System.out.println(contact);
        }
    }

    public static void main(String[] args) {
        ContactManagement cm = new ContactManagement();

        cm.addContact(new Contact(1, "Sagar", "9786765786"));
        cm.addContact(new Contact(2, "Jyotin", "8967584756"));
        cm.addContact(new Contact(3, "Pradhyumn", "9879867543"));

        cm.displayContacts();

        boolean removed = cm.removeContact(2);
        if (removed) {
            System.out.println("\nJyotin removed from the system.");
        } else {
            System.out.println("\nJyotin not found.");
        }

        cm.displayContacts();
    }
}

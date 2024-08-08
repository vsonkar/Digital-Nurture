
import java.io.*;

class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
}

class ContactWriter {

    public void saveContact(String fileName, Contact contact) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(contact);
            System.out.println("Contact saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the contact: " + e.getMessage());
        }
    }
}

class ContactReader {

    public Contact readContact(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Contact) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the contact: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Contact class not found: " + e.getMessage());
        }
        return null;
    }
}

public class ContactApp {

    public static void main(String[] args) {
        ContactWriter writer = new ContactWriter();
        ContactReader reader = new ContactReader();

        String fileName = "contact.txt";

        Contact contact = new Contact("Jyotin", "9876789855", "jyotin@gmail.com");
        writer.saveContact(fileName, contact);

        Contact savedContact = reader.readContact(fileName);
        if (savedContact != null) {
            System.out.println("\nContact details read from file:");
            System.out.println(savedContact);
        }

        System.out.println("\nAttempting to read from a non-existent file:");
        reader.readContact("non_existent_file.ser");
    }
}

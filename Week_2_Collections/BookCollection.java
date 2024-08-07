
import java.util.LinkedHashSet;

public class BookCollection {

    private LinkedHashSet<String> books;

    public BookCollection() {
        books = new LinkedHashSet<>();
    }

    public boolean addBook(String bookTitle) {
        return books.add(bookTitle);
    }

    public boolean removeBook(String bookTitle) {
        return books.remove(bookTitle);
    }

    public void displayBooks() {
        System.out.println("\nBook Collection:");
        for (String book : books) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        BookCollection b = new BookCollection();
        b.addBook("Design and Ananysis of Algorithms");
        b.addBook("Computer Organization and Architecture");
        b.addBook("Digital Forensics");
        b.addBook("Web Application Security");
        b.displayBooks();

        boolean added = b.addBook("Digital Forensics");
        System.out.println(added + ": Digital Forensics is already added");

        boolean removed = b.removeBook("Design and Analysis of Algorithms");
        if (removed) {
            System.out.println("\nDesign and Ananysis of Algorithms is removed from the registration.");
        } else {
            System.out.println("\nDesign and Ananysis of Algorithms is already not present in the registration.");
        }

        removed = b.removeBook("Operating Systems");
        if (removed) {
            System.out.println("Operating Systems is removed from the registration.");
        } else {
            System.out.println("Operating Systems is already not present in the registration.");
        }

        b.displayBooks();
        System.out.println("\nBooks are displayed in the order they were added.");
    }
}

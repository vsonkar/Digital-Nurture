
import java.util.HashSet;

public class ProductCatalog {

    private HashSet<String> products;

    public ProductCatalog() {
        products = new HashSet<>();
    }

    public boolean addProduct(String productName) {
        return products.add(productName);
    }

    public boolean removeProduct(String productName) {
        return products.remove(productName);
    }

    public boolean searchProduct(String productName) {
        return products.contains(productName);
    }

    public void displayProducts() {
        System.out.println("\nProduct Catalog:");
        for (String product : products) {
            System.out.println(product);
        }
    }

    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();

        // Add Products
        catalog.addProduct("Charger");
        catalog.addProduct("Mouse");
        catalog.addProduct("Earphone");
        catalog.addProduct("Headset");

        // Display Products
        catalog.displayProducts();

        boolean added = catalog.addProduct("Mouse");
        System.out.println(added + ": Mouse can't be added again");

        // Search Products
        boolean found = catalog.searchProduct("Charger");
        if (found) {
            System.out.println("\nCharger is present in the catalog");
        } else {
            System.out.println("Charger is not present in the catalog");
        }

        found = catalog.searchProduct("Camera");
        if (found) {
            System.out.println("Mobile is present in the catalog");
        } else {
            System.out.println("Mobile is not present in the catalog");
        }

        // Remove Products
        boolean removed = catalog.removeProduct("Earphone");
        if (removed) {
            System.out.println("Earphone is removed");
        } else {
            System.out.println("Earphone is already not present in the catalog");
        }

        removed = catalog.removeProduct("Camera");
        if (removed) {
            System.out.println("Camera is removed");
        } else {
            System.out.println("Camera is already not present in the catalog");
        }

        // Display Products again to verify removal
        catalog.displayProducts();
    }
}

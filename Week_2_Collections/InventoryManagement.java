
import java.util.HashMap;
import java.util.Map;

class Product {

    public int id;
    public String name;
    public int quantity;

    public Product(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", quantity=" + quantity + '}';
    }
}

class InventoryManagement {

    private final HashMap<Integer, Product> inventory;

    public InventoryManagement() {
        inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        inventory.put(product.id, product);
    }

    public boolean removeProduct(int productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
            return true;
        }
        return false;
    }

    public boolean updateProductQuantity(int productId, int newQuantity) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.quantity = newQuantity;
            return true;
        }
        return false;
    }

    public void displayProducts() {
        System.out.println("\nInventory: ");
        for (Map.Entry<Integer, Product> entry : inventory.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public static void main(String[] args) {
        InventoryManagement i = new InventoryManagement();

        i.addProduct(new Product(1, "Laptop", 10));
        i.addProduct(new Product(2, "Smartphone", 20));
        i.addProduct(new Product(3, "Tablet", 15));

        i.displayProducts();

        boolean updated = i.updateProductQuantity(2, 25);
        if (updated) {
            System.out.println("\nQuantity updated for Smartphone");
        } else {
            System.out.println("\nSmartphone not found");
        }

        boolean removed = i.removeProduct(3);
        if (removed) {
            System.out.println("\nTablet removed from the inventory.");
        } else {
            System.out.println("\nTablet not found.");
        }

        i.displayProducts();
    }
}

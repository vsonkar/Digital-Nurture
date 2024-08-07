
import java.util.LinkedList;

class Order {

    public int orderId;
    public String orderDetails;

    public Order(int orderId, String orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderDetails='" + orderDetails + '\'' + '}';
    }
}

class OrderTracking {

    private final LinkedList<Order> orders;

    public OrderTracking() {
        orders = new LinkedList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order processOrder() {
        return orders.pollFirst();
    }

    public void displayOrders() {
        System.out.println("\nOrders: ");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public static void main(String[] args) {
        OrderTracking o = new OrderTracking();

        o.addOrder(new Order(1, "Burger"));
        o.addOrder(new Order(2, "Pizza"));
        o.addOrder(new Order(3, "Pasta"));

        o.displayOrders();

        Order processedOrder = o.processOrder();
        if (processedOrder != null) {
            System.out.println("\nProcessed Order: " + processedOrder);
        } else {
            System.out.println("\nNo orders to process.");
        }

        o.displayOrders();

        processedOrder = o.processOrder();
        if (processedOrder != null) {
            System.out.println("\nProcessed Order: " + processedOrder);
        } else {
            System.out.println("\nNo orders to process.");
        }

        o.displayOrders();
    }
}

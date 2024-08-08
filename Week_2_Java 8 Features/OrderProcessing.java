
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface OrderFilter {

    boolean filter(Order order);
}

@FunctionalInterface
interface OrderProcessor {

    void process(Order order);
}

class Order {

    private int orderId;
    private String customerName;
    private double orderAmount;
    private String status;

    public Order(int orderId, String customerName, double orderAmount, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderAmount = orderAmount;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{id=" + orderId + ", customerName='" + customerName + "', orderAmount=" + orderAmount + ", status='" + status + "'}";
    }
}

public class OrderProcessing {

    public static void filterOrders(List<Order> orders, OrderFilter filter) {
        for (Order order : orders) {
            if (filter.filter(order)) {
                System.out.println(order);
            }
        }
    }

    public static void processOrders(List<Order> orders, OrderProcessor processor) {
        for (Order order : orders) {
            processor.process(order);
        }
    }

    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, "Aditya", 250.0, "Pending"));
        orders.add(new Order(2, "Jyotin", 150.0, "Shipped"));
        orders.add(new Order(3, "Sagar", 350.0, "Delivered"));
        orders.add(new Order(4, "Pradhyumn", 450.0, "Pending"));

        System.out.println("Orders with amount greater than 200:");
        filterOrders(orders, order -> order.getOrderAmount() > 200.0);

        System.out.println("\nProcessing orders by updating status to 'Processed':");
        processOrders(orders, order -> order.setStatus("Processed"));

        System.out.println("\nUpdated orders:");
        filterOrders(orders, order -> true); // Display all orders
    }
}

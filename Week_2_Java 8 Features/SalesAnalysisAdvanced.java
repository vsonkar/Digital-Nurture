
import java.util.*;

class SalesRecord {

    private int recordId;
    private String salesPerson;
    private String region;
    private String productCategory;
    private double amount;
    private int quantity;
    private Date date;

    public SalesRecord(int recordId, String salesPerson, String region, String productCategory, double amount, int quantity, Date date) {
        this.recordId = recordId;
        this.salesPerson = salesPerson;
        this.region = region;
        this.productCategory = productCategory;
        this.amount = amount;
        this.quantity = quantity;
        this.date = date;
    }

    public int getRecordId() {
        return recordId;
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public String getRegion() {
        return region;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public double getAmount() {
        return amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "SalesRecord{" + "recordId=" + recordId + ", salesPerson='" + salesPerson + '\'' + ", region='" + region + '\'' + ", productCategory='" + productCategory + '\'' + ", amount=" + amount + ", quantity=" + quantity + ", date=" + date + '}';
    }
}

public class SalesAnalysisAdvanced {

    public static void main(String[] args) {
        List<SalesRecord> salesRecords = new ArrayList<>();
        salesRecords.add(new SalesRecord(1, "Sagar", "North", "Electronics", 1200.0, 3, new GregorianCalendar(2024, Calendar.JANUARY, 15).getTime()));
        salesRecords.add(new SalesRecord(2, "Jyotin", "South", "Furniture", 1500.0, 2, new GregorianCalendar(2024, Calendar.FEBRUARY, 20).getTime()));
        salesRecords.add(new SalesRecord(3, "Aditya", "North", "Electronics", 1000.0, 5, new GregorianCalendar(2024, Calendar.MARCH, 10).getTime()));
        salesRecords.add(new SalesRecord(4, "Pradhyumn", "East", "Clothing", 1700.0, 4, new GregorianCalendar(2024, Calendar.APRIL, 5).getTime()));
        salesRecords.add(new SalesRecord(5, "Abhiraj", "North", "Electronics", 1300.0, 6, new GregorianCalendar(2024, Calendar.MAY, 25).getTime()));

        System.out.println("Sorted sales records for 'Electronics':");
        salesRecords.stream()
                .filter(record -> "Electronics".equals(record.getProductCategory()))
                .sorted(Comparator.comparing(SalesRecord::getDate))
                .forEach(System.out::println);

        double averageSales = salesRecords.stream()
                .filter(record -> "North".equals(record.getRegion()))
                .mapToDouble(SalesRecord::getAmount)
                .average()
                .orElse(0.0);
        System.out.println("\nAverage sales amount for 'North' region: " + averageSales);

        SalesRecord topSalesRecord = salesRecords.stream()
                .max(Comparator.comparing(SalesRecord::getAmount))
                .orElse(null);
        System.out.println("\nTop sales record:");
        System.out.println(topSalesRecord);

        long startTime = System.nanoTime();
        salesRecords.stream()
                .filter(record -> "Electronics".equals(record.getProductCategory()))
                .sorted(Comparator.comparing(SalesRecord::getDate))
                .forEach(record -> {
                }); // No-op for timing
        long endTime = System.nanoTime();
        System.out.println("\nSequential stream operation took " + (endTime - startTime) + " nanoseconds.");

        startTime = System.nanoTime();
        salesRecords.parallelStream()
                .filter(record -> "Electronics".equals(record.getProductCategory()))
                .sorted(Comparator.comparing(SalesRecord::getDate))
                .forEach(record -> {
                });
        endTime = System.nanoTime();
        System.out.println("Parallel stream operation took " + (endTime - startTime) + " nanoseconds.");
    }
}

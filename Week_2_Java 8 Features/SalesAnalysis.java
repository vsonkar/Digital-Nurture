
import java.util.*;
import java.util.stream.Collectors;

class SalesRecord {

    private int recordId;
    private String salesPerson;
    private String region;
    private double amount;
    private Date date;

    public SalesRecord(int recordId, String salesPerson, String region, double amount, Date date) {
        this.recordId = recordId;
        this.salesPerson = salesPerson;
        this.region = region;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "SalesRecord{" + "recordId=" + recordId + ", salesPerson='" + salesPerson + '\'' + ", region='" + region + '\'' + ", amount=" + amount + ", date=" + date + '}';
    }
}

public class SalesAnalysis {

    public static void main(String[] args) {
        List<SalesRecord> salesRecords = new ArrayList<>();
        salesRecords.add(new SalesRecord(1, "Sagar", "North", 1200.0, new GregorianCalendar(2024, Calendar.JANUARY, 15).getTime()));
        salesRecords.add(new SalesRecord(2, "Jyotin", "South", 1500.0, new GregorianCalendar(2024, Calendar.FEBRUARY, 20).getTime()));
        salesRecords.add(new SalesRecord(3, "Aditya", "North", 1000.0, new GregorianCalendar(2024, Calendar.MARCH, 10).getTime()));
        salesRecords.add(new SalesRecord(4, "Pradhyumn", "East", 1700.0, new GregorianCalendar(2024, Calendar.APRIL, 5).getTime()));
        salesRecords.add(new SalesRecord(5, "Abhiraj", "North", 1300.0, new GregorianCalendar(2024, Calendar.MAY, 25).getTime()));

        System.out.println("Sales records for region 'North':");
        salesRecords.stream()
                .filter(record -> "North".equals(record.getRegion()))
                .forEach(System.out::println);

        System.out.println("\nSales amounts for region 'North':");
        salesRecords.stream()
                .filter(record -> "North".equals(record.getRegion()))
                .map(SalesRecord::getAmount)
                .forEach(System.out::println);

        double totalSales = salesRecords.stream()
                .filter(record -> "North".equals(record.getRegion()))
                .mapToDouble(SalesRecord::getAmount)
                .sum();
        System.out.println("\nTotal sales amount for region 'North': " + totalSales);

        System.out.println("\nSales records grouped by salesperson:");
        Map<String, List<SalesRecord>> groupedBySalesPerson = salesRecords.stream()
                .collect(Collectors.groupingBy(SalesRecord::getSalesPerson));
        groupedBySalesPerson.forEach((salesPerson, records) -> {
            System.out.println(salesPerson + ": " + records);
        });

        System.out.println("\nSales report (total sales amount for each salesperson):");
        Map<String, Double> salesReport = salesRecords.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getSalesPerson,
                        Collectors.summingDouble(SalesRecord::getAmount)
                ));
        salesReport.forEach((salesPerson, totalAmount) -> {
            System.out.println(salesPerson + ": $" + totalAmount);
        });
    }
}

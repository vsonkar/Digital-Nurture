
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCApplication {

    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password_here";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void getEmployees() {
        String query = "SELECT * FROM employees";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Employee List:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                double salary = resultSet.getDouble("salary");
                System.out.printf("ID: %d, Name: %s, Position: %s, Salary: %.2f%n", id, name, position, salary);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving employee data: " + e.getMessage());
        }
    }

    public static void addEmployee(String name, String position, double salary) {
        String query = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setString(2, position);
            statement.setDouble(3, salary);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " employee(s) added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
        }
    }

    public static void updateEmployee(int id, String name, String position, double salary) {
        String query = "UPDATE employees SET name = ?, position = ?, salary = ? WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setString(2, position);
            statement.setDouble(3, salary);
            statement.setInt(4, id);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " employee(s) updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
        }
    }

    public static void deleteEmployee(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " employee(s) deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. View Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter employee name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter employee position: ");
                    String position = scanner.nextLine();
                    System.out.print("Enter employee salary: ");
                    double salary = scanner.nextDouble();
                    scanner.nextLine();
                    addEmployee(name, position, salary);
                    break;

                case 2:
                    System.out.print("Enter employee ID to update: ");
                    int idUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new employee name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new employee position: ");
                    String newPosition = scanner.nextLine();
                    System.out.print("Enter new employee salary: ");
                    double newSalary = scanner.nextDouble();
                    scanner.nextLine();
                    updateEmployee(idUpdate, newName, newPosition, newSalary);
                    break;

                case 3:
                    System.out.print("Enter employee ID to delete: ");
                    int idDelete = scanner.nextInt();
                    scanner.nextLine();
                    deleteEmployee(idDelete);
                    break;

                case 4:
                    getEmployees();
                    break;

                case 5:
                    System.out.println("Exiting the application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        scanner.close();
    }
}

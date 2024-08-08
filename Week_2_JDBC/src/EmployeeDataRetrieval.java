
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDataRetrieval {

    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASSWORD = "sonkar";

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
        // Add a few employee records
        addEmployee("Alice Smith", "Developer", 70000);
        addEmployee("Bob Johnson", "Manager", 85000);

        System.out.println("Initial Employee Records:");
        getEmployees();

        updateEmployee(1, "Alice Smith", "Senior Developer", 75000);

        deleteEmployee(2);

        System.out.println("Employee Records After Updates:");
        getEmployees();
    }
}

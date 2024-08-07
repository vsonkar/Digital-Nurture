
import java.util.*;

class Employee {

    public int id;
    public String name;
    public String address;

    public Employee(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", address='" + address + '\'' + '}';
    }

}

public class EmployeeManagement {

    private final ArrayList<Employee> employees;

    public EmployeeManagement() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public boolean removeEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
                return true;
            }
        }
        return false;
    }

    public boolean updateEmployee(int id, String newAddress) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setAddress(newAddress);
                return true;
            }
        }
        return false;
    }

    public void displayEmployees() {
        System.out.println("\nEmployees: ");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public static void main(String[] args) {
        EmployeeManagement e = new EmployeeManagement();
        e.addEmployee(new Employee(1, "Sagar", "123 Street Road"));
        e.addEmployee(new Employee(2, "Jyotin", "234 MG Road"));
        e.addEmployee(new Employee(3, "Pradhyumn", "345 Angel Road"));

        e.displayEmployees();

        boolean updated = e.updateEmployee(2, "456 Main Street");
        if (updated) {
            System.out.println("\nAddress updated for Jyotin");
        } else {
            System.out.println("\nJyotin not found");
        }

        boolean removed = e.removeEmployee(3);
        if (removed) {
            System.out.println("\nPradhyumn removed from the system.");
        } else {
            System.out.println("\nPradhyumn not found.");
        }

        e.displayEmployees();

    }
}

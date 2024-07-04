import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJDBC {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/EmployeeDB";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Hello@world21"; // replace with your MySQL password

    // Create
    public void addEmployee(Employee employee) {
        String query = "INSERT INTO Employee (id, name, department) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getDepartment());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee added: " + employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public Employee getEmployeeById(int id) {
        String query = "SELECT * FROM Employee WHERE id = ?";
        Employee employee = null;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String department = resultSet.getString("department");
                    employee = new Employee(id, name, department);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    public List<Employee> getAllEmployees() {
        String query = "SELECT * FROM Employee";
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                employees.add(new Employee(id, name, department));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    // Update
    public void updateEmployee(Employee employee) {
        String query = "UPDATE Employee SET name = ?, department = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getDepartment());
            preparedStatement.setInt(3, employee.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee updated: " + employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteEmployee(int id) {
        String query = "DELETE FROM Employee WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EmployeeJDBC employeeJDBC = new EmployeeJDBC();

        // Creating employees
        Employee emp1 = new Employee(1, "Employee 1", "Maketing");
        Employee emp2 = new Employee(2, "Employee 2", "Software");
        Employee emp3 = new Employee(3, "Employee 3", "Sales");

        // Adding employees
        employeeJDBC.addEmployee(emp1);
        employeeJDBC.addEmployee(emp2);
        employeeJDBC.addEmployee(emp3);

        // Reading employees
        System.out.println("All Employees: " + employeeJDBC.getAllEmployees());
        System.out.println("Get Employee with ID 2: " + employeeJDBC.getEmployeeById(2));

        // Updating an employee
        Employee updatedEmp2 = new Employee(2, "Employee 4", "Marketing");
        employeeJDBC.updateEmployee(updatedEmp2);

        // Reading employees after update
        System.out.println("All Employees after update: " + employeeJDBC.getAllEmployees());

        // Deleting an employee
        employeeJDBC.deleteEmployee(1);

        // Reading employees after deletion
        System.out.println("All Employees after deletion: " + employeeJDBC.getAllEmployees());
    }
}


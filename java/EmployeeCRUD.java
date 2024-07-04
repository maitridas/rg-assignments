import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class EmployeeCRUD{
    private List<Employee> employeeList = new ArrayList<>();

    // Create
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        System.out.println("Employee added: " + employee);
    }

    // Read
    public Employee getEmployeeById(int id) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    // Update
    public boolean updateEmployee(Employee updatedEmployee) {
        for (Employee employee : employeeList) {
            if (employee.getId() == updatedEmployee.getId()) {
                employee.setName(updatedEmployee.getName());
                employee.setDepartment(updatedEmployee.getDepartment());
                System.out.println("Employee updated: " + employee);
                return true;
            }
        }
        return false;
    }

    // Delete
    public boolean deleteEmployee(int id) {
        Iterator<Employee> iterator = employeeList.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getId() == id) {
                iterator.remove();
                System.out.println("Employee removed: " + employee);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        EmployeeCRUD employeeCRUD = new EmployeeCRUD();

        Employee emp1 = new Employee(1, "Employee 1", "Marketing");
        Employee emp2 = new Employee(2, "Employee 2", "Sales");
        Employee emp3 = new Employee(3, "Employee 3", "Finance");

        employeeCRUD.addEmployee(emp1);
        employeeCRUD.addEmployee(emp2);
        employeeCRUD.addEmployee(emp3);

        System.out.println("All Employees: " + employeeCRUD.getAllEmployees());
        System.out.println("Get Employee with ID 2: " + employeeCRUD.getEmployeeById(2));

        Employee updatedEmp2 = new Employee(2, "Employee 4", "Marketing");
        employeeCRUD.updateEmployee(updatedEmp2);

        System.out.println("All Employees after update: " + employeeCRUD.getAllEmployees());

        employeeCRUD.deleteEmployee(1);

        System.out.println("All Employees after deletion: " + employeeCRUD.getAllEmployees());
    }
}
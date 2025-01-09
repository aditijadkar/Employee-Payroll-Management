import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // Add new employee to the database
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (employee_id, name, department, position, email, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employee.getEmployeeId()); 
            stmt.setString(2, employee.getName());
            stmt.setString(3, employee.getDepartment());
            stmt.setString(4, employee.getPosition());
            stmt.setString(5, employee.getEmail());
            stmt.setString(6, employee.getPhoneNumber());
            stmt.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    // View details of a specific employee using employeeId
    public Employee viewEmployee(int employeeId) {
        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        Employee employee = null;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                employee = new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getString("position"),
                    rs.getString("email"),
                    rs.getString("phone_number")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error viewing employee: " + e.getMessage());
        }
        return employee;
    }

    // Update an employee's details
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, department = ?, position = ?, email = ?, phone_number = ? WHERE employee_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getDepartment());
            stmt.setString(3, employee.getPosition());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPhoneNumber());
            stmt.setInt(6, employee.getEmployeeId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    // Delete an employee by employeeId
    public void deleteEmployee(int employeeId) {
        String deleteSalarySql = "DELETE FROM salary WHERE employee_id = ?";
        String deleteEmployeeSql = "DELETE FROM employees WHERE employee_id = ?";
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // Delete from the salary table first
            try (PreparedStatement salaryStmt = conn.prepareStatement(deleteSalarySql)) {
                salaryStmt.setInt(1, employeeId);
                salaryStmt.executeUpdate();
            }

            // Delete from the employees table
            try (PreparedStatement employeeStmt = conn.prepareStatement(deleteEmployeeSql)) {
                employeeStmt.setInt(1, employeeId);
                int rowsDeleted = employeeStmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Employee deleted successfully.");
                } else {
                    System.out.println("Employee ID not found.");
                }
            }

            // Commit the transaction
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback if error occurs
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error resetting auto-commit: " + ex.getMessage());
            }
        }
    }

    // List all employees
    public List<Employee> listAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Employee employee = new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getString("position"),
                    rs.getString("email"),
                    rs.getString("phone_number")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println("Error listing employees: " + e.getMessage());
        }
        return employees;
    }
}

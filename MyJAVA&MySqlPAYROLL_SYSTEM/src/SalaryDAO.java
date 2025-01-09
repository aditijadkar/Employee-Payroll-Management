import java.sql.*;

public class SalaryDAO {

    // Add Salary
    public void addSalary(Salary salary) {
        String sql = "INSERT INTO salary (employee_id, base_salary, bonus, deductions) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salary.getEmployeeId());
            stmt.setDouble(2, salary.getBaseSalary());
            stmt.setDouble(3, salary.getBonus());
            stmt.setDouble(4, salary.getDeductions());
            stmt.executeUpdate();
            System.out.println("Salary added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding salary: " + e.getMessage());
        }
    }

    // Calculate Salary
    public void calculateSalary(int employeeId) {
        String sql = "SELECT base_salary, bonus, deductions FROM salary WHERE employee_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double baseSalary = rs.getDouble("base_salary");
                double bonus = rs.getDouble("bonus");
                double deductions = rs.getDouble("deductions");
                double totalSalary = baseSalary + bonus - deductions;
                System.out.println("Total Salary for Employee ID " + employeeId + ": " + totalSalary);
            } else {
                System.out.println("No salary details found for employee ID: " + employeeId);
            }
        } catch (SQLException e) {
            System.out.println("Error calculating salary: " + e.getMessage());
        }
    }

    // Display Salary
    public void displaySalary(int employeeId) {
        String sql = "SELECT base_salary, bonus, deductions FROM salary WHERE employee_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("=== Salary Details for Employee ID: " + employeeId + " ===");
                System.out.println("Base Salary: " + rs.getDouble("base_salary"));
                System.out.println("Bonus: " + rs.getDouble("bonus"));
                System.out.println("Deductions: " + rs.getDouble("deductions"));
            } else {
                System.out.println("No salary details found for employee ID: " + employeeId);
            }
        } catch (SQLException e) {
            System.out.println("Error displaying salary: " + e.getMessage());
        }
    }

    // Update Salary
    public void updateSalary(Salary salary) {
        String sql = "UPDATE salary SET base_salary = ?, bonus = ?, deductions = ? WHERE employee_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, salary.getBaseSalary());
            stmt.setDouble(2, salary.getBonus());
            stmt.setDouble(3, salary.getDeductions());
            stmt.setInt(4, salary.getEmployeeId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Salary updated successfully.");
            } else {
                System.out.println("No salary details found for employee ID: " + salary.getEmployeeId());
            }
        } catch (SQLException e) {
            System.out.println("Error updating salary: " + e.getMessage());
        }
    }
}

import java.util.List;
import java.util.Scanner;

public class PayrollSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDAO employeeDAO = new EmployeeDAO();
        SalaryDAO salaryDAO = new SalaryDAO();

        while (true) {
            System.out.println("=== Employee Payroll Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. List All Employees");
            System.out.println("6. Add Salary");
            System.out.println("7. Calculate Salary");
            System.out.println("8. Display Salary");
            System.out.println("9. Update Salary");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add Employee
                    System.out.print("Enter Employee ID: ");
                    int EId = scanner.nextInt(); 
                    scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter position: ");
                    String position = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    Employee employee = new Employee(EId, name, department, position, email, phoneNumber);
                    employeeDAO.addEmployee(employee);
                    break;

                case 2:
                    // View Employee
                    System.out.print("Enter employee ID: ");
                    int employeeId = scanner.nextInt();
                    Employee emp = employeeDAO.viewEmployee(employeeId);
                    if (emp != null) {
                        System.out.println("Employee ID: " + emp.getEmployeeId());
                        System.out.println("Name: " + emp.getName());
                        System.out.println("Department: " + emp.getDepartment());
                        System.out.println("Position: " + emp.getPosition());
                        System.out.println("Email: " + emp.getEmail());
                        System.out.println("Phone: " + emp.getPhoneNumber());
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 3:
                    // Update Employee
                    System.out.print("Enter employee ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new department: ");
                    String newDepartment = scanner.nextLine();
                    System.out.print("Enter new position: ");
                    String newPosition = scanner.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Enter new phone number: ");
                    String newPhoneNumber = scanner.nextLine();
                    Employee updatedEmployee = new Employee(updateId, newName, newDepartment, newPosition, newEmail, newPhoneNumber);
                    employeeDAO.updateEmployee(updatedEmployee);
                    break;

                case 4:
                    // Delete Employee
                    System.out.print("Enter employee ID to delete: ");
                    int deleteId = scanner.nextInt();
                    employeeDAO.deleteEmployee(deleteId);
                    break;

                case 5:
                    // List All Employees
                    List<Employee> employees = employeeDAO.listAllEmployees();
                    System.out.println("=== List of All Employees ===");
                    for (Employee e : employees) {
                        System.out.println("Employee ID: " + e.getEmployeeId() + ", Name: " + e.getName());
                    }
                    break;

                case 6:
                    // Add Salary
                    System.out.print("Enter employee ID: ");
                    int empId = scanner.nextInt();
                    System.out.print("Enter base salary: ");
                    double baseSalary = scanner.nextDouble();
                    System.out.print("Enter bonus: ");
                    double bonus = scanner.nextDouble();
                    System.out.print("Enter deductions: ");
                    double deductions = scanner.nextDouble();
                    Salary salary = new Salary(empId, baseSalary, bonus, deductions);
                    salaryDAO.addSalary(salary);
                    break;

                case 7:
                    // Calculate Salary
                    System.out.print("Enter employee ID to calculate salary: ");
                    int empSalaryId = scanner.nextInt();
                    salaryDAO.calculateSalary(empSalaryId);
                    break;

                case 8:
                    // Display Salary
                    System.out.print("Enter employee ID to display salary: ");
                    int displaySalaryId = scanner.nextInt();
                    salaryDAO.displaySalary(displaySalaryId);
                    break;

                case 9:
                    // Update Salary
                    System.out.print("Enter employee ID to update salary: ");
                    int updateSalaryId = scanner.nextInt();
                    System.out.print("Enter new base salary: ");
                    double newBaseSalary = scanner.nextDouble();
                    System.out.print("Enter new bonus: ");
                    double newBonus = scanner.nextDouble();
                    System.out.print("Enter new deductions: ");
                    double newDeductions = scanner.nextDouble();
                    Salary updatedSalary = new Salary(updateSalaryId, newBaseSalary, newBonus, newDeductions);
                    salaryDAO.updateSalary(updatedSalary);
                    break;

                case 10:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

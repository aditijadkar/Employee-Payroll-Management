public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private String position;
    private String email;
    private String phoneNumber;

   

    // Constructor 
    public Employee(int employeeId, String name, String department, String position, String email, String phoneNumber) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

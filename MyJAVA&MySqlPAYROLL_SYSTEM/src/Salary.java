public class Salary {
    private int employeeId;
    private double baseSalary;
    private double bonus;
    private double deductions;

    public Salary(int employeeId, double baseSalary, double bonus, double deductions) {
        this.employeeId = employeeId;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.deductions = deductions;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double getBonus() {
        return bonus;
    }

    public double getDeductions() {
        return deductions;
    }
}

package nl.inholland;

public class Employee implements Payable
{
    final double tax = 0.7;

    double grossSalary;

    public double getPayout()
    {
        return grossSalary * tax;
    }

    public Employee(double grossSalary)
    {
        this.grossSalary = grossSalary;
    }
}

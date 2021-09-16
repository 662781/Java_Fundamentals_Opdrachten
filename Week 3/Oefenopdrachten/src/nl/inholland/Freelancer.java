package nl.inholland;

public class Freelancer implements Payable
{
    double hourlyRate;

    double workedHours;

    public double getPayout()
    {
        return workedHours * hourlyRate;
    }

    public Freelancer(double workedHours, double hourlyRate)
    {
        this.workedHours = workedHours;
        this.hourlyRate = hourlyRate;
    }

}

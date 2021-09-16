package nl.inholland;

public class Main {

    public static void main(String[] args) {
	Main main = new Main();
    main.start();
    }

    void start()
    {
        Payable employee = new Employee(3000);
        Payable freelancer = new Freelancer(140, 60);

        displayPayment(employee);
        displayPayment(freelancer);
    }

    void displayPayment(Payable payable)
    {
        System.out.println(payable.getPayout());
    }

}

public class Employee extends Person {
    private int paycheck;

    public Employee(String firstName, String lastName) {
        this(0, firstName, lastName);
    }

    public Employee(int paycheck, String firstName, String lastName) {
        super(firstName, lastName);
        this.paycheck = paycheck;
    }

    public int getPaycheck(){
        return paycheck;
    }

    public void setPaycheck(int paycheck) {
        this.paycheck = paycheck;
    }

    @Override
    public void displayInfo() {
        System.out.println("First name: " + getFirstName());
        System.out.println("Last name: " + getLastName());
        System.out.println("Paycheck: " + paycheck);
    }
}

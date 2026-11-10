public class Reader extends Person {

    private int id;

    public Reader(int id, String firstName, String lastName) {
        super(firstName, lastName);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("First name: " + getFirstName());
        System.out.println("Last name: " + getLastName());
    }
}
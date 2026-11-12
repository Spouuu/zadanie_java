import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    static ArrayList<Book> books = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Reader> readers = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static int nextReaderId = 1;

    public static void main(String[] args) {

        int choice = -1;

        while (choice != 0) {

            System.out.println("\n===== LIBRARY MANAGEMENT =====");
            System.out.println("1. Add book");
            System.out.println("2. Display all books");
            System.out.println("3. Search book");
            System.out.println("4. Borrow book");
            System.out.println("5. Save book to file");
            System.out.println("6. Load books from file");
            System.out.println("7. Add reader");
            System.out.println("8. Display readers");
            System.out.println("9. Find reader by ID");
            System.out.println("10. Add employee");
            System.out.println("11. Make an employee a bankrupt");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        addBook();
                        break;

                    case 2:
                        displayBooks();
                        break;

                    case 3:
                        searchBook();
                        break;

                    case 4:
                        borrowBook();
                        break;

                    case 5:
                        saveBooksToFile();
                        break;

                    case 6:
                        loadBooksFromFile();
                        break;

                    case 7:
                        addReader();
                        break;

                    case 8:
                        displayReaders();
                        break;

                    case 9:
                        findReaderById();
                        break;

                    case 10:
                        addEmployee();
                        break;

                    case 11:
                        bankruptEmployee();
                        break;

                    case 0:
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        }
    }

    public static void addBook() {

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter publication year: ");

        try {
            int year = Integer.parseInt(scanner.nextLine());

            Book book = new Book(title, author, year);
            books.add(book);

            System.out.println("Book added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid publication year!");
        }
    }

    public static void displayBooks(){
        if (books.isEmpty()) {
            System.out.println("The library is empty!");
            return;
        }

        for (int i = 0; i < books.size(); i++) {

            System.out.println("\nBook number: " + (i + 1));
            books.get(i).displayInfo();
        }
    }

    public static void searchBook() {

        System.out.print("Enter title to search: ");
        String search = scanner.nextLine();

        boolean found = false;

        for (Book book : books) {

            if (book.getTitle().toLowerCase().contains(search.toLowerCase())) {
                book.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found!");
        }
    }

    public static void borrowBook() {

        displayBooks();

        System.out.print("Enter book number: ");
        int number = scanner.nextInt();
        scanner.nextLine();

        Book book = books.get(number - 1);

        if (book.isBorrowed()) {
            System.out.println("Book is already borrowed!");
        } else {
            book.borrowBook();
            System.out.println("Book borrowed!");
        }
    }

    public static void saveBooksToFile() {

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("books.txt"));

            for (Book book : books) {

                writer.write(book.getTitle() + ";" +
                        book.getAuthor() + ";" +
                        book.getPublicationYear() + ";" +
                        book.isBorrowed());

                writer.newLine();
            }

            writer.close();

            System.out.println("Books saved successfully!");

        } catch (IOException e) {
            System.out.println("Error while saving books!");
        }
    }

    public static void loadBooksFromFile() {

        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get("books.txt"));

            books.clear();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(";");

                String title = data[0];
                String author = data[1];
                int year = Integer.parseInt(data[2]);
                boolean borrowed = Boolean.parseBoolean(data[3]);

                Book book = new Book(title, author, year);

                if (borrowed) {
                    book.borrowBook();
                }

                books.add(book);
            }

            reader.close();

            System.out.println("Books loaded successfully!");

        } catch (IOException e) {
            System.out.println("File not found or error while reading!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid data in file!");
        }
    }
    public static void addReader() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        Reader reader = new Reader(nextReaderId, firstName, lastName);

        readers.add(reader);
        nextReaderId++;

        System.out.println("Reader added!");
    }
    public static void displayReaders() {
        if (readers.isEmpty()) {
            System.out.println("No readers!");
        } else {
            for (Reader reader : readers) {
                reader.displayInfo();
                System.out.println();
            }
        }
    }
    public static void findReaderById() {
        System.out.print("Enter reader ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Reader reader : readers) {
            if (reader.getId() == id) {
                reader.displayInfo();
                return;
            }
        }

        System.out.println("Reader not found!");
    }

    public static void addEmployee() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter paycheck: ");
        int paycheck = scanner.nextInt();
        scanner.nextLine();

        Employee employee = new Employee(paycheck, firstName, lastName);

        employees.add(employee);

        System.out.println("Employee added!");
    }

    public static void bankruptEmployee() {
        System.out.print("Enter employee number: ");
        int number = scanner.nextInt();
        scanner.nextLine();

        if (number > 0 && number <= employees.size()) {
            Employee employee = employees.get(number - 1);

            employee.setPaycheck(0);

            System.out.println("Employee paycheck set to 0!");
        } else {
            System.out.println("Employee not found!");
        }
    }

}

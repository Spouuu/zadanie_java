import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Book> books = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice = -1;

        while (choice != 0) {

            System.out.println("\n===== LIBRARY MANAGEMENT =====");
            System.out.println("1. Add book");
            System.out.println("2. Display all books");
            System.out.println("3. Search book");
            System.out.println("4. Borrow book");
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
}

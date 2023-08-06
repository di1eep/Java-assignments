import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class librarymanagement {

    static String[][] books = {
            {"101", "HTML and CSS", "Jon Duckett", "Available", "Null"},
            {"102", "JavaScript: The Good Parts", "Douglas C", "Available", "Null"},
            {"103", "Learning Web Design", "Jennifer N", "CP2014", "23-May-2023"},
            {"104", "Responsive Web Design", "Ben Frain", "EC3142", "17-May-2023"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;

        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to the Unique Library");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        do {
            System.out.println("1. View the complete list of Books");
            System.out.println("2. Issue a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit");
            System.out.print("Please choose an option from the above menu: ");
            choice = scanner.next().charAt(0);

            switch (choice) {
                case '1':
                    displayAllBooks();
                    break;
                case '2':
                    issueBook();
                    break;
                case '3':
                    returnBook();
                    break;
                case '4':
                    System.out.println("Thank you for visiting  Unique Library!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != '4');

        scanner.close();
    }

    public static void displayAllBooks() {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("List of all Books");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-30s %-20s %-15s %-15s\n", "Book ID", "Book Title", "Author", "Availability", "Issue Date");

        for (String[] book : books) {
            System.out.printf("%-8s %-30s %-20s %-15s %-15s\n", book[0], book[1], book[2], book[3], book[4]);   /// format string used for properspacing 
        }

        System.out.println("Enter “Y” to return to the main menu or “N” to Exit: Y");
        Scanner scanner = new Scanner(System.in);
        char choice = scanner.next().charAt(0);
        if (Character.toLowerCase(choice) == 'y') {
            main(null);
        } else {
            System.out.println("Thank you for visiting Unique Library!");
            System.exit(0);
        }
    }

    public static void issueBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("Issue the Book");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        System.out.print("Enter the Book ID: ");
        String bookId = scanner.nextLine();
        int bookIndex = findBookIndex(bookId);

        if (bookIndex == -1) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }

        String[] book = books[bookIndex];
        if (!book[3].equals("Available")) {
            System.out.println("Book " + book[0] + " - " + book[1] + " is not available.");
            return;
        }

        System.out.println(book[0] + " " + book[1] + " - " + book[2] + ": " + book[3]);
        System.out.print("Enter StudentID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter “C” to confirm: ");
        char confirm = scanner.next().charAt(0);

        if (Character.toUpperCase(confirm) == 'C') {
            book[3] = studentId;
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            book[4] = dateFormat.format(new Date());
            System.out.println("BookID: " + bookId + " is Issued to " + studentId);
        } else {
            System.out.println("Book issue process cancelled.");
        }

        System.out.println("\nEnter “Y” to return to the main menu or “N” to Exit: ");
        choicePrompt(scanner);
    }

    public static void returnBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("Return the Book");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        System.out.print("Enter the Book ID: ");
        String bookId = scanner.nextLine();
        int bookIndex = findBookIndex(bookId);

        if (bookIndex == -1) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }

        String[] book = books[bookIndex];
        if (book[3].equals("Available")) {
            System.out.println("Book " + book[0] + " - " + book[1] + " is not issued to any student.");
            return;
        }

        String studentId = book[3];
        String issueDate = book[4];

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date currentDate = new Date();
        Date issuedDate;
        try {
            issuedDate = dateFormat.parse(issueDate);
        } catch (Exception e) {
            System.out.println("Error parsing date. Book return process failed.");
            return;
        }

        long differenceInMillis = currentDate.getTime() - issuedDate.getTime();
        long daysDifference = differenceInMillis / (24 * 60 * 60 * 1000);

        double chargePerDay = 10.0;
        double delayedCharges = 0;

        if (daysDifference > 7) {
            delayedCharges = (daysDifference - 7) * chargePerDay;
        }

        System.out.println(book[0] + " - " + book[1] + " by " + book[2]);
        System.out.println("StudentID - " + studentId);
        System.out.println("Issue Date - " + issueDate);
        System.out.println("Delayed by - " + (daysDifference > 7 ? daysDifference - 7 : 0) + " days");
        System.out.println("Delayed Charges - Rs. " + delayedCharges);

        System.out.print("Enter “R” to confirm the return: ");
        char confirm = scanner.next().charAt(0);

        if (Character.toUpperCase(confirm) == 'R') {
            book[3] = "Available";
            book[4] = "Null";
            System.out.println("BookID: " + bookId + " is returned back");
        } else {
            System.out.println("Book return process cancelled.");
        }

        System.out.println("\nEnter “Y” to return to the main menu or “N” to Exit: ");
        choicePrompt(scanner);
    }

    public static int findBookIndex(String bookId) {
        for (int i = 0; i < books.length; i++) {
            if (books[i][0].equals(bookId)) {
                return i;
            }
        }
        return -1;
    }

    public static void choicePrompt(Scanner scanner) {
        char choice = scanner.next().charAt(0);
        if (Character.toLowerCase(choice) == 'y') {
            main(null);
        } else {
            System.out.println("Thank you for visiting Unique Library!");
            System.exit(0);
        }
    }
}

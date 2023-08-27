import java.util.*;

class Visitor {
    private String name;
    private int age;
    private String phoneNumber;
    private String appointmentDate;
    private String appointmentTime;

    public Visitor(String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

   
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}

class VisitorsList {
    private List<Visitor> visitors = new ArrayList<>();

    public void addVisitor(Visitor visitor) {
        visitors.add(visitor);
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }
}

class AppointmentScheduler {
    private VisitorsList visitorsList;
    private Map<String, List<String>> appointmentSchedule;

    public AppointmentScheduler(VisitorsList visitorsList) {
        this.visitorsList = visitorsList;
        this.appointmentSchedule = new HashMap<>();
    }

    public void bookAppointment(String date, String timeSlot, Visitor visitor) {
        if (!appointmentSchedule.containsKey(date)) {
            appointmentSchedule.put(date, new ArrayList<>());
        }
        appointmentSchedule.get(date).add(timeSlot);
        visitor.setAppointmentDate(date);
        visitor.setAppointmentTime(timeSlot);
    }

    public List<String> getAvailableTimeSlots(String date) {
        List<String> timeSlots = new ArrayList<>();
        timeSlots.add("09:00 AM - 10:00 AM");
        timeSlots.add("11:00 AM - 12:00 PM");
        return timeSlots;
    }
}

class ViewVisitorsList {
    private VisitorsList visitorsList;

    public ViewVisitorsList(VisitorsList visitorsList) {
        this.visitorsList = visitorsList;
    }

    public void execute() {
        List<Visitor> allVisitors = visitorsList.getVisitors();
        System.out.println("Visitors List:");
        for (Visitor visitor : allVisitors) {
            System.out.println("Name: " + visitor.getName());
            System.out.println("Age: " + visitor.getAge());
            System.out.println("Phone Number: " + visitor.getPhoneNumber());
            System.out.println("Appointment Date: " + visitor.getAppointmentDate());
            System.out.println("Appointment Time: " + visitor.getAppointmentTime());
            System.out.println("--------------------");
        }
    }
}

class AddNewVisitor {
    private VisitorsList visitorsList;
    private AppointmentScheduler appointmentScheduler;
    private Scanner scanner;

    public AddNewVisitor(VisitorsList visitorsList, AppointmentScheduler appointmentScheduler, Scanner scanner) {
        this.visitorsList = visitorsList;
        this.appointmentScheduler = appointmentScheduler;
        this.scanner = scanner;
    }

    public void execute() {
        System.out.print("Enter Visitor's Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Visitor's Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Visitor's Phone Number: ");
        String phoneNumber = scanner.nextLine();

        Visitor newVisitor = new Visitor(name, age, phoneNumber);
        visitorsList.addVisitor(newVisitor);


        System.out.print("Select Appointment Date (DD-MM-YYYY): ");
        String appointmentDate = scanner.nextLine();


        List<String> availableTimeSlots = appointmentScheduler.getAvailableTimeSlots(appointmentDate);
        System.out.println("Available Time Slots:");
        for (int i = 0; i < availableTimeSlots.size(); i++) {
            System.out.println((i + 1) + ". " + availableTimeSlots.get(i));
        }

        System.out.print("Select Time Slot: ");
        int timeSlotChoice = scanner.nextInt();
        scanner.nextLine(); 

        String selectedTimeSlot = availableTimeSlots.get(timeSlotChoice - 1);
        appointmentScheduler.bookAppointment(appointmentDate, selectedTimeSlot, newVisitor);

        System.out.println("Visitor " + newVisitor.getName() + "'s appointment on " +
                newVisitor.getAppointmentDate() + " at " + newVisitor.getAppointmentTime() +
                " has been booked.\n");
    }
}

public class AppointmentManAgementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VisitorsList visitorsList = new VisitorsList();
        AppointmentScheduler appointmentScheduler = new AppointmentScheduler(visitorsList);

        while (true) {
            System.out.println("Welcome to the Small Clinic Appointment Management System\n");
            System.out.println("1. View Visitors List");
            System.out.println("2. Add New Visitor");
            System.out.println("3. View Appointment Schedule for a Day");
            System.out.println("4. Book an Appointment");
            System.out.println("5. Exit");
            System.out.print("\nPlease enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    ViewVisitorsList viewVisitorsList = new ViewVisitorsList(visitorsList);
                    viewVisitorsList.execute();
                    break;

                case 2:
                    AddNewVisitor addNewVisitor = new AddNewVisitor(visitorsList, appointmentScheduler, scanner);
                    addNewVisitor.execute();
                    break;

                case 3:
                    EditVisitorDetails editVisitorDetails = new EditVisitorDetails();
                    EditVisitorDetails();
                    break;


                case 4:
                AppointmentSchedule appointmentSchedule = new AppointmentSchedule();
                    AppointmentSchedule();
                    break;

                
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid choice.\n");
            }
        }
    }

    private static void AppointmentSchedule() {
    }

    private static void EditVisitorDetails() {
    }
}

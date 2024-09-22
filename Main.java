import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

   
    static List<Staff> staffList = new ArrayList<>();
    static List<Member> membersList = new ArrayList<>();

    
    static Member loggedInMember = null;
    static Staff loggedInStaff = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Product product = new Product();
        Payment payment = new Payment();
        Stock stock = new Stock();
        loadStaffData();

        boolean exit = false;

        
        while (loggedInMember == null && loggedInStaff == null) {
            System.out.println("\n|       Authentication Menu       |");
            System.out.println("===================================");
            System.out.println("| 1. Sign Up as User              |");
            System.out.println("| 2. User Login                   |");
            System.out.println("| 3. Staff Login                  |");
            System.out.println("| 4. Exit                         |");
            System.out.println("===================================");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        handleSignup(scanner);
                        break;
                    case 2:
                        handleUserLogin(scanner);
                        break;
                    case 3:
                        handleStaffLogin(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
            }
        }

        
        while (!exit) {
            if (loggedInMember != null) {
                
                System.out.println("\n|       Member Menu        |");
                System.out.println("============================");
                System.out.println("| 1. View Profile          |");
                System.out.println("| 2. Update Profile        |");
                System.out.println("| 3. Logout                |");
                System.out.println("============================");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        viewProfile(loggedInMember);
                        break;
                    case 2:
                        updateProfile(scanner, loggedInMember);
                        break;
                    case 3:
                        loggedInMember = null;
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else if (loggedInStaff != null) {
                
                System.out.println("\n|        Staff Menu         |");
                System.out.println("=============================");
                System.out.println("| 1. Main Menu              |");
                System.out.println("| 2. View All Members       |");
                System.out.println("| 3. Update Member          |");
                System.out.println("| 4. Delete Member          |");
                System.out.println("| 5. Logout                 |");
                System.out.println("=============================");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        mainApplicationMenu(scanner, product, payment, stock);
                        break;
                    case 2:
                        viewAllMembers();
                        break;
                    case 3:
                        updateMember(scanner);
                        break;
                    case 4:
                        deleteMember(scanner);
                        break;
                    case 5:
                        loggedInStaff = null; 
                        System.out.println("Staff logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

            
            if (loggedInMember == null && loggedInStaff == null) {
                System.out.println("Returning to authentication menu...");
                break; 
            }
        }

    
        main(args); 
    }

    private static void loadStaffData() {
        try (Scanner fileScanner = new Scanner(new File("staff.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Staff staff = new Staff(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim());
                    staffList.add(staff);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Staff file not found: " + e.getMessage());
        }
    }

    // ------------------- Authentication Methods ----------------------

    private static void handleSignup(Scanner scanner) {
        System.out.println("\n|       Sign Up       |");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();

       
        for (Member member : membersList) {
            if (member.getEmail().equals(email)) {
                System.out.println("A user with this email already exists.");
                return;
            }
        }

        
        Member newMember = new Member(email, password, name);
        membersList.add(newMember);
        System.out.println("Sign up successful! You can now log in as a user.");
    }

    private static void handleUserLogin(Scanner scanner) {
        System.out.println("\n|       User Login       |");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        
        for (Member member : membersList) {
            if (member.getEmail().equals(email) && member.getPassword().equals(password)) {
                loggedInMember = member;
                System.out.println("Login successful! Welcome, " + member.getName());
                return;
            }
        }
        System.out.println("Invalid email or password.");
    }

    private static void handleStaffLogin(Scanner scanner) {
        System.out.println("\n|       Staff Login       |");

        int attempt = 0;
        while (attempt < 3 && loggedInStaff == null) {
            System.out.print("Staff ID: ");
            String staffID = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            
            for (Staff staff : staffList) {
                if (staff.getStaffID().equals(staffID) && staff.getPassword().equals(password)) {
                    loggedInStaff = staff;
                    System.out.println("Staff login successful! Welcome, " + staff.getStaffName());
                    return;
                }
            }

            attempt++;
            System.out.println("Invalid staff ID or password. Attempt " + attempt + " of 3.");
        }

        if (attempt == 3) {
            System.out.println("Too many failed attempts. Returning to the main menu.");
        }
    }

    // ------------------- Member Methods ----------------------
    private static void viewProfile(Member member) {
        System.out.println("\n|       Your Profile       |");
        System.out.println("Name: " + member.getName());
        System.out.println("Email: " + member.getEmail());
        System.out.println("Password: " + member.getPassword()); // Optionally hide passwords
    }

    private static void updateProfile(Scanner scanner, Member member) {
        System.out.println("\n|       Update Profile       |");
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        member.setName(newName);
        member.setPassword(newPassword);
        System.out.println("Profile updated successfully.");
    }

    // ------------------- Staff Methods ----------------------
    private static void viewAllMembers() {
        System.out.println("\n|       All Members       |");
        if (membersList.isEmpty()) {
            System.out.println("No members registered.");
        } else {
            for (Member member : membersList) {
                System.out.println(member.toString());
            }
        }
    }

    private static void updateMember(Scanner scanner) {
        System.out.print("Enter Member's Email to update: ");
        String email = scanner.nextLine();

        Member memberToUpdate = null;
        for (Member member : membersList) {
            if (member.getEmail().equals(email)) {
                memberToUpdate = member;
                break;
            }
        }

        if (memberToUpdate != null) {
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            memberToUpdate.setName(newName);
            memberToUpdate.setPassword(newPassword);
            System.out.println("Member updated successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }

    private static void deleteMember(Scanner scanner) {
        System.out.print("Enter Member's Email to delete: ");
        String email = scanner.nextLine();

        Member memberToDelete = null;
        for (Member member : membersList) {
            if (member.getEmail().equals(email)) {
                memberToDelete = member;
                break;
            }
        }

        if (memberToDelete != null) {
            membersList.remove(memberToDelete);
            System.out.println("Member deleted successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }

    // ------------------- Main Menu Methods (for Staff) ----------------------
    private static void mainApplicationMenu(Scanner scanner, Product product, Payment payment, Stock stock) {
        boolean exitMainMenu = false;

        while (!exitMainMenu) {
            System.out.println("\n|        Main Menu        |");
            System.out.println("===========================");
            System.out.println("|1. Supplier Management   |");
            System.out.println("|2. Product Management    |");
            System.out.println("|3. Order Management      |");
            System.out.println("|4. Transaction Management|");
            System.out.println("|5. Staff Management      |");
            System.out.println("|6. Display Stock         |");
            System.out.println("|7. Exit                  |");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        Supplier.userInterface(scanner);
                        break;
                    case 2:
                        product.productMenu();
                        break;
                    case 3:
                        Order.userInterface(scanner);
                        break;
                    case 4:
                        payment.paymentMenu();
                        break;
                    case 5:
                        Staff.staffMenu(scanner, staffList);
                        break;
                    case 6:
                        stock.displayStock();
                        System.out.println("Press Enter to return to the main menu...");
                        scanner.nextLine();
                        break;
                    case 7:
                        exitMainMenu = true;
                        System.out.println("Returning to staff menu...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StaffLogin {

    private static final String STAFF_FILE = "staff.txt"; 
    public static boolean login(Scanner scanner) {
        System.out.println("\n--- Staff Login ---");
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter Staff ID: ");
            String staffID = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (authenticate(staffID, password)) {
                System.out.println("Staff login successful. Welcome, " + staffID + "!");
                return true;
            } else {
                attempts++;
                System.out.println("Invalid Staff ID or Password. Attempt " + attempts + " of 3.");
            }
        }
        System.out.println("Too many failed attempts. Please try again later.");
        return false;
    }

    private static boolean authenticate(String staffID, String password) {
        try (Scanner fileScanner = new Scanner(new File(STAFF_FILE))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(","); 

                if (parts.length == 6) {
                    String storedStaffID = parts[0].trim();
                    String storedPassword = parts[5].trim();

                    if (storedStaffID.equals(staffID) && storedPassword.equals(password)) {
                        return true; 
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Staff file not found: " + e.getMessage());
            return false;
        }
        return false;
    }
}

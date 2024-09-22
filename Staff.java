import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Staff {
    private String staffID;
    private String staffName;
    private String department;
    private String phoneNumber;
    private String email;
    private String password;
    private List<Shift> assignedShifts;

    public Staff(String staffID, String staffName, String department, String phoneNumber, String email, String password) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.assignedShifts = new ArrayList<>();
        this.password = password;
        
    }



    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

    public void assignShift(Shift shift) {
        assignedShifts.add(shift);
    }

    public void removeShift(Shift shift) {
        assignedShifts.remove(shift);
    }

    public List<Shift> getAssignedShift() {
        return assignedShifts;
    }
    
    public static void staffMenu(Scanner scanner, List<Staff> staffList) {
    boolean back = false;

    while (!back) {
        System.out.println("\nStaff Management Menu:");
        System.out.println("1. Add Staff");
        System.out.println("2. Assign Shift to Staff");
        System.out.println("3. Display Staff");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        switch (choice) {
            case 1:
                System.out.print("Enter Staff ID: ");
                String staffID = scanner.nextLine(); 
                System.out.print("Enter Staff Name: ");
                String staffName = scanner.nextLine();
                System.out.print("Enter Department: ");
                String department = scanner.nextLine();
                System.out.print("Enter Phone Number: ");
                String phoneNumber = scanner.nextLine();    
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter Password: ");
				String password = scanner.nextLine(); 
				Staff staff = new Staff(staffID, staffName, department, phoneNumber, email, password); 


                
                staffList.add(staff); 
                System.out.println("Staff added successfully!");
                break;

            case 2:
                System.out.print("Enter Staff ID: ");
                staffID = scanner.nextLine();
                boolean staffFound = false;
                for (Staff s : staffList) { 
                    if (s.getStaffID().equals(staffID)) {
                        staffFound = true;
                        System.out.print("Enter Shift ID: ");
                        String shiftID = scanner.nextLine();
                        System.out.print("Enter Shift Name: ");
                        String shiftName = scanner.nextLine();
                        System.out.print("Enter Start Time: ");
                        int startTime = scanner.nextInt();
                        System.out.print("Enter End Time: ");
                        int endTime = scanner.nextInt();
                        scanner.nextLine(); // Clear the buffer

                        Shift shift = new Shift(shiftID, shiftName, startTime, endTime);
                        s.assignShift(shift);
                        System.out.println("Shift assigned to staff.");
                        break;
                    }
                }
                if (!staffFound) {
                    System.out.println("Staff ID not found.");
                }
                break;

            case 3:
                System.out.println("All Staff Details:");
                for (Staff s : staffList) {
                    System.out.println(s);
                }
                break;

            case 4:
                back = true;
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}

    
    @Override
    public String toString() {
        return "Staff ID: " + staffID +
                ", Name: " + staffName +
                ", Department: " + department +
                ", Phone Number: " + phoneNumber +
                ", Email: " + email +
                ", Assigned Shifts: " + assignedShifts;
    }
}

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Supplier {
    private String supplierID;
    private String supplierName;
    private String supplierAddress;
    private String supplierContactNum;
    private String supplierEmail;

    
    private static final String FILE_NAME = "supplier.txt";

    
    public Supplier() {
        this(" ", " ", " ", " ", " ");
    }

    
    public Supplier(String supplierID, String supplierName, String supplierAddress, String supplierContactNum, String supplierEmail) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierContactNum = supplierContactNum;
        this.supplierEmail = supplierEmail;
    }

  
    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        if (isValidID(supplierID)) {
            this.supplierID = supplierID;
        } else {
            System.out.println("Invalid Supplier ID. Please enter a valid alphanumeric ID.");
        }
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        if (isValidName(supplierName)) {
            this.supplierName = supplierName;
        } else {
            System.out.println("Invalid Supplier Name. Please enter alphabetic characters only.");
        }
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        if (supplierAddress != null) {
            this.supplierAddress = supplierAddress;
        } else {
            System.out.println("Supplier address cannot be empty.");
        }
    }

    public String getSupplierContactNum() {
        return supplierContactNum;
    }

    public void setSupplierContactNum(String supplierContactNum) {
        if (isValidContactNumber(supplierContactNum)) {
            this.supplierContactNum = supplierContactNum;
        } else {
            System.out.println("Invalid contact number. Please enter a valid contact number.");
        }
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        if (isValidEmail(supplierEmail)) {
            this.supplierEmail = supplierEmail;
        } else {
            System.out.println("Invalid Email. Please enter a valid email address.");
        }
    }

    private boolean isValidID(String id) {
        return id != null && !id.trim().isEmpty() && id.matches("[S]\\d{3}");
    }

    private boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-Z\\s]+");
    }

    private boolean isValidContactNumber(String contactNum) {
    	return contactNum != null && contactNum.matches("01\\d{8,9}"); 
	}

	private boolean isValidAddress(String address) {
    	return address != null && !address.trim().isEmpty();
	}

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        return email != null && Pattern.compile(emailRegex).matcher(email).matches();
    }

    public boolean isValid() {
    	return isValidID(this.supplierID) &&
           isValidName(this.supplierName) &&
           isValidAddress(this.supplierAddress) &&
           isValidContactNumber(this.supplierContactNum) &&
           isValidEmail(this.supplierEmail);
  	}

    
    public static void addSupplier(Supplier supplier) {
        if (supplier.isValid()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                writer.write(supplier.getSupplierID() + "," + supplier.getSupplierName() + "," +
                             supplier.getSupplierAddress() + "," + supplier.getSupplierContactNum() + "," +
                             supplier.getSupplierEmail());
                writer.newLine();
                System.out.println("Supplier added successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while adding the supplier.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Supplier data is invalid. Please check the input.");
        }
    }

    public static void readSuppliers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.printf("\n%-10s %-20s %-60s %-20s %-20s\n", "ID", "Name", "Address", "Contact", "Email");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] details = line.split(",");
                if (details.length == 5) {
                    System.out.printf("%-10s %-20s %-60s %-20s %-20s\n", details[0].trim(), details[1].trim(), details[2].trim(), details[3].trim(), details[4].trim());
                } else {
                    System.out.println("Invalid supplier data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the suppliers.");
            e.printStackTrace();
        }
    }

   
    public static void updateSupplier(String supplierID, String newName, String newAddress, String newContactNum, String newEmail) {
        String filePath = FILE_NAME;
        StringBuilder content = new StringBuilder();
        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(supplierID)) {
                    line = supplierID + "," + newName + "," + newAddress + "," + newContactNum + "," + newEmail;
                    updated = true;
                    System.out.println("Supplier updated successfully.");
                }
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!updated) {
            System.out.println("Supplier not found.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    public static void deleteSupplier(String supplierID) {
        String filePath = FILE_NAME;
        StringBuilder content = new StringBuilder();
        boolean deleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(supplierID)) {
                    deleted = true;
                    System.out.println("Supplier deleted successfully.");
                    continue;  
                }
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!deleted) {
            System.out.println("Supplier not found.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void userInterface(Scanner scanner) {
        while (true) {
        
            System.out.println("\n|  Supplier Management |");
            System.out.println("========================");
            System.out.println("|1. Add Supplier       |");
            System.out.println("|2. Read Suppliers     |");
            System.out.println("|3. Update Supplier    |");
            System.out.println("|4. Delete Supplier    |");
            System.out.println("|5. Back to Main Menu  |");
            System.out.println("========================");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addSupplierUI(scanner);
                        break;
                    case 2:
                        readSuppliers();
                        break;
                    case 3:
                        updateSupplierUI(scanner);
                        break;
                    case 4:
                        deleteSupplierUI(scanner);
                        break;
                    case 5:
                        return; 
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
            }
        }
    }

    private static void addSupplierUI(Scanner scanner) {
        System.out.print("Enter Supplier ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Supplier Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Supplier Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Supplier Contact Number: ");
        String contactNum = scanner.nextLine();
        System.out.print("Enter Supplier Email: ");
        String email = scanner.nextLine();
        addSupplier(new Supplier(id, name, address, contactNum, email));
    }

    private static void updateSupplierUI(Scanner scanner) {
        System.out.print("Enter Supplier ID to update: ");
        String id = scanner.nextLine();
        System.out.print("Enter new Supplier Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new Supplier Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter new Supplier Contact Number: ");
        String contactNum = scanner.nextLine();
        System.out.print("Enter new Supplier Email: ");
        String email = scanner.nextLine();
        updateSupplier(id, name, address, contactNum, email);
    }

    private static void deleteSupplierUI(Scanner scanner) {
        System.out.print("Enter Supplier ID to delete: ");
        String id = scanner.nextLine();
        deleteSupplier(id);
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    private List<Product> products;

    public Stock() {
        products = new ArrayList<>();
        loadProductsFromFile(); 
    }

    private void loadProductsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Product ID: ")) {
                    String productID = line.substring(12).trim();
                    String category = reader.readLine().substring(10).trim();
                    String productName = reader.readLine().substring(13).trim();
                    String description = reader.readLine().substring(13).trim();
                    String priceString = reader.readLine().substring(7).trim().replace("RM", "").replace(",", "");
                    double price = Double.parseDouble(priceString);
                    int quantity = Integer.parseInt(reader.readLine().substring(10).trim());
                    reader.readLine();
                    
                    Product product = new Product(category, productID, productName, description, price, quantity);
                    products.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing product information: " + e.getMessage());
        }
    }

    public void displayStock() {
    if (products.isEmpty()) {
        System.out.println("No products in stock.");
        return;
    }
    
    System.out.printf("\n%-12s %-20s %-30s %-50s %-10s %-10s\n", "Product ID", "Category", "Product Name", "Description", "Price", "Quantity");
    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
    
    for (Product product : products) {
        System.out.printf("%-12s %-20s %-30s %-50s RM%-9.2f %-10d\n",
                product.getProductID(),
                product.getCategory(),
                product.getProductName(),
                product.getDescription(),
                product.getProductPrice(),
                product.getProductQuantity());
    }
    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
    
    
}

}

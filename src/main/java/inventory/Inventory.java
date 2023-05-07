package inventory;

import javafx.collections.ObservableList;
import parts.Part;
import products.Product;

public class Inventory {
    static ObservableList<Part> allParts;
    static ObservableList<Product> allProducts;

    public static void addPart(Part newPart) {}
    public static void addProduct(Product newProduct) {}
    public static void lookupPart(int partId) {}
    public static void lookupProduct(int productId) {}

    public static void lookupPart(String partName) {}
    public static void lookupProduct(String productName) {}

    public static void updatePart(int index, Part selectedProduct) {}

    public static void updateProduct(int index, Product newProduct) {}

    public static boolean deletePart(Part selectedPart) { return false; }
    public static boolean deleteProduct(Product selectedProduct) { return false; }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}

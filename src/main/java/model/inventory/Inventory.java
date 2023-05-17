package model.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.parts.Part;
import model.products.Product;

/**
 * Class to hold Inventory
 */
public class Inventory {
    /**
     * A collection of all the Inventory Parts
     */
    static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /**
     * A collection of all the Inventory Products
     */
    static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Variable to keep track of part IDs
     */
    private static int currentPartId = 0;
    /**
     * Variable to keep track of product IDs
     */
    private static int currentProductId = 0;

    /**
     * Gets a new part ID by incrementing previous id
     * @return part id
     */
    public static int getNewPartID() {
        currentPartId += 1;
        return currentPartId;
    };

    /**
     * Gets a new product ID by incrementing previous id
     * @return product id
     */
    public static int getNewProductID() {
        currentProductId += 1;
        return currentProductId;
    };

    /**
     * adds part to part collection
     * @param newPart
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * add product to product collection
     * @param newProduct
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Looks up a part by ID
     * @param partId
     * @return Part
     */
    public static Part lookupPart(int partId) {
        Part foundPart = null;

        for (Part part : allParts) {
            if (part.getId() == partId) {
                foundPart = part;
            }
        }

        return foundPart;
    }

    /**
     * Looks up a product by ID
     * @param productId
     * @return Product
     */
    public static Product lookupProduct(int productId) {
        Product foundProduct = null;

        for (Product product : allProducts) {
            if (product.getId() == productId) {
                foundProduct = product;
            }
        }

        return foundProduct;
    }

    /**
     * Looks up a part by name
     * @param partName
     * @return Collection of matching parts
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> foundPart = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName() == partName) {
                foundPart.add(part);
            }
        }

        return foundPart;
    }

    /**
     * Looks up a product by name
     * @param productName
     * @return Collection of matching products
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> foundProduct = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName() == productName) {
                foundProduct.add(product);
            }
        }

        return foundProduct;
    }

    /**
     * Updates a part at given index
     * @param index
     * @param selectedPart
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Updates a product at a given index
     * @param index
     * @param newProduct
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * Deletes a selected part
     * @param selectedPart
     * @return boolean
     */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        return false;
    }

    /**
     * Deletes a selected product
     * @param selectedProduct
     * @return boolean
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        return false;
    }

    /**
     * Getter for all parts
     * @return collection of parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Getter for all products
     * @return collection of products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}

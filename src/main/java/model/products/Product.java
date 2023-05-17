package model.products;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.parts.Part;

/**
 * Class for Products
 */
public class Product {
    /**
     * A list of associated parts
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    /**
     * A unique identifier
     */
    private int id;
    /**
     * Product name
     */
    private String name;
    /**
     * Product price
     */
    private double price;
    /**
     * Product inventory/stock
     */
    private int stock;
    /**
     * Product inventory minimum
     */
    private int min;

    /**
     * Product inventory max
     */
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     *
     * @return product id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return product price
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @return product stock/inventory
     */
    public int getStock() {
        return stock;
    }

    /**
     *
     * @return inventory min
     */
    public int getMin() {
        return min;
    }

    /**
     *
     * @return inventory max
     */
    public int getMax() {
        return max;
    }

    /**
     *
     * @return all associated parts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * Adds a new part to associated parts
     * @param associatedPart
     */
    public void addAssociatedParts(Part associatedPart) {
        associatedParts.add(associatedPart);
    }

    /**
     * Deletes the associated parts
     * @param selectedAssociatedPart
     * @return boolean
     */
    public boolean deleteAssociatedParts(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        return false;
    }

    /**
     * Sets id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * sets name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * sets stock/inventory
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * sets min
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * sets max
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }
}

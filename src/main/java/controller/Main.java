package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import model.inventory.Inventory;
import model.parts.Part;
import model.products.Product;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller to handle Main screen of application
 */
public class Main implements Initializable {
    /**
     * Button to add a new part
     */
    @FXML
    private Button root_addPart;

    /**
     * Button to add a new product
     */
    @FXML
    private Button root_addProduct;

    /**
     * Button to delete a part
     */
    @FXML
    private Button root_deletePart;

    /**
     * Button to delete a product
     */
    @FXML
    private Button root_deleteProduct;

    /**
     * Button to exit application
     */
    @FXML
    private Button root_exit;

    /**
     * Button to modify part
     */
    @FXML
    private Button root_modifyPart;

    /**
     * Button to modify product
     */
    @FXML
    private Button root_modifyProduct;

    /**
     * Table view for ALL parts in inventory
     */
    @FXML
    private TableView<Part> root_parts;

    /**
     * Column for All Parts table, part ID
     */
    @FXML
    private TableColumn<Part, Integer> root_partIdColumn;

    /**
     * Column for All Parts table, part name
     */
    @FXML
    private TableColumn<Part, String>  root_partNameColumn;

    /**
     * Column for All Parts table, part inventory
     */
    @FXML
    private TableColumn<Part, Integer>  root_partInventoryColumn;

    /**
     * Column for All Parts table, part price
     */
    @FXML
    private TableColumn<Part, Double>  root_partPriceColumn;

    /**
     * Table view for ALL products in inventory
     */
    @FXML
    private TableView<Product> root_products;

    /**
     * Column for All Products table, product ID
     */
    @FXML
    private TableColumn<Product, Integer>  root_productIdColumn;

    /**
     * Column for All Products table, product name
     */
    @FXML
    private TableColumn<Product, String> root_productNameColumn;

    /**
     * Column for All Products table, product inventory
     */
    @FXML
    private TableColumn<Product, Integer> root_productInventoryColumn;

    /**
     * Column for All Products table, product cost
     */
    @FXML
    private TableColumn<Product, Double> root_productCostColumn;

    /**
     * Text field to search for Parts
     */
    @FXML
    private TextField root_searchPart;

    /**
     * Text field to search for Products
     */
    @FXML
    private TextField root_searchProduct;

    /**
     * Static variable for selected parts to be accessed by part modification screen
     */
    static Part selectedPart;

    /**
     * Getter to get the currently selected part for modification
     * @return part
     */
    static Part getSelectedPart() {
        return selectedPart;
    }

    /**
     * Static variable for selected products to be accessed by part modification screen
     */
    static Product selectedProduct;

    /**
     * Getter to get the currently selected product for modification
     * @return product
     */
    static Product getSelectedProduct() {
        return selectedProduct;
    }

    /**
     * Populates the tables with the corresponding part and product information upon initialization of window
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        root_products.setItems(Inventory.getAllProducts());
        root_productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        root_productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        root_productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        root_productCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        root_parts.setItems(Inventory.getAllParts());
        root_partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        root_partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        root_partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        root_partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Opens the screen to Add Products
     * @param event
     * @throws IOException
     */
    @FXML
    void openAddProductScreen(MouseEvent event) throws IOException {
        URL url = Paths.get("./src/main/java/view/add-product.fxml").toUri().toURL();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the screen to Modify Products
     * Throws an alert if no product is selected
     * RUNTIME ERROR:
     * In the modify Product function, the selected part could be null
     * if a user has not selected anything. To fix this, there is now a
     * null check, and if it is null, an alert is presented to the user
     * advising them to select a product to modify first.
     * @param event
     * @throws IOException
     */
    @FXML
    void openModifyProductScreen(MouseEvent event) throws IOException {
        URL url = Paths.get("./src/main/java/view/modify-product.fxml").toUri().toURL();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        selectedProduct = root_products.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            setAlertDialogue("Product must be selected");
            return;
        }

        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the screen to Add Parts
     * @param event
     * @throws IOException
     */
    @FXML
    void openAddPartScreen(MouseEvent event) throws IOException {
        URL url = Paths.get("./src/main/java/view/add-part.fxml").toUri().toURL();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the screen to Modify Parts
     * Throws an alert if no part is selected
     * @param event
     * @throws IOException
     */
    @FXML
    void openModifyPartScreen(MouseEvent event) throws IOException {
        URL url = Paths.get("./src/main/java/view/modify-part.fxml").toUri().toURL();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        selectedPart = root_parts.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            setAlertDialogue("Part must be selected");
            return;
        }

        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Deletes a part after user confirmation
     * @param event mouse click event
     */
    @FXML
    void deletePart(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Alert");
        alert.setContentText("Do you wish to delete the selected part?");
        Optional<ButtonType> answer = alert.showAndWait();

        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            Inventory.deletePart(root_parts.getSelectionModel().getSelectedItem());
        } else {
            setAlertDialogue("Part not deleted");
        }
    }

    /**
     * Deletes a product after user confirmation
     * @param event mouse click event
     */
    @FXML
    void deleteProduct(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Alert");
        alert.setContentText("Do you wish to delete the selected product?");
        Optional<ButtonType> answer = alert.showAndWait();

        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            if (root_products.getSelectionModel().getSelectedItem().getAllAssociatedParts().size() > 0) {
                setAlertDialogue("Unable to delete product with associated parts. Please remove the parts first.");
                return;
            }
            Inventory.deleteProduct(root_products.getSelectionModel().getSelectedItem());
        } else {
            setAlertDialogue("Product not deleted");
        }
    }

    /**
     * Searches Parts by ID or full or partial name matches
     * Displays in parts table view
     * TRIGGERED BY ENTER KEY PRESS
     * @param event ENTER KEY PRESS
     */
    @FXML
    void searchAllParts(ActionEvent event) {
        ObservableList<Part> parts = Inventory.getAllParts();
        ObservableList<Part> matchedParts = FXCollections.observableArrayList();
        String search = root_searchPart.getText();

        for (Part part : parts) {
            if (String.valueOf(part.getId()).contains(search) || part.getName().contains(search)) {
                matchedParts.add(part);
            }
        }

        root_parts.setItems(matchedParts);

        if (matchedParts.size() < 1) {
            setAlertDialogue("No matching parts found");
        }
    }

    /**
     * Searches Products by ID or full or partial name matches
     * Displays in product table view
     * TRIGGERED BY ENTER KEY PRESS
     * @param event ENTER KEY PRESS
     */
    @FXML
    void searchAllProducts(ActionEvent event) {
        ObservableList<Product> products = Inventory.getAllProducts();
        ObservableList<Product> matchedProducts = FXCollections.observableArrayList();
        String search = root_searchProduct.getText();

        for (Product product : products) {
            if (String.valueOf(product.getId()).contains(search) || product.getName().contains(search)) {
                matchedProducts.add(product);
            }
        }

        root_products.setItems(matchedProducts);

        if (matchedProducts.size() < 1) {
            setAlertDialogue("No matching products found");
        }
    }

    /**
     * Exits out of the application
     * @param event mouse click event
     */
    @FXML
    void exit(MouseEvent event) {
        System.out.println("Terminating");
        System.exit(0);
    }

    /**
     * When Part Search field is empty, refreshes the table view to show all parts
     */
    @FXML
    public void refreshPartSearch() {
        if (root_searchPart.getText().isEmpty()) {
            root_parts.setItems(Inventory.getAllParts());
        }
    }

    /**
     * When Product Search field is empty, refreshes the table view to show all products
     */
    @FXML
    public void refreshProductSearch() {
        if (root_searchProduct.getText().isEmpty()) {
            root_products.setItems(Inventory.getAllProducts());
        }
    }

    /**
     * Generates an error alert with a user provided string
     * @param alertInfo string with alert message
     */
    private void setAlertDialogue(String alertInfo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An Error has occurred");
        alert.setHeaderText("Alert");
        alert.setContentText(alertInfo);
        alert.showAndWait();
    }

}

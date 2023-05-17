package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.inventory.Inventory;
import model.parts.Part;
import model.products.Product;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * Controller to handle Modifying an existing Product
 */
public class ModifyProduct implements Initializable {

    /**
     * An observable list/array that holds the parts to be associated
     * with this product
     */
    private ObservableList<Part> modifyProduct_associatedPartsArray = FXCollections.observableArrayList();

    /**
     * Button to add part
     */
    @FXML
    private Button modifyProduct_addButton;

    /**
     * Button to cancel product modification
     */
    @FXML
    private Button modifyProduct_cancel;

    /**
     * Text field that holds the product ID
     */
    @FXML
    private TextField modifyProduct_id;

    /**
     * Text field that holds the product inventory
     */
    @FXML
    private TextField modifyProduct_inventory;

    /**
     * Text field that holds the product inventory max
     */
    @FXML
    private TextField modifyProduct_max;

    /**
     * Text field that holds the product inventory min
     */
    @FXML
    private TextField modifyProduct_min;

    /**
     * Text field that holds the product name
     */
    @FXML
    private TextField modifyProduct_name;

    /**
     * Table view for ALL parts in inventory
     */
    @FXML
    private TableView<Part> modifyProduct_allParts;

    /**
     * Column for All Parts table, part ID
     */
    @FXML
    private TableColumn<Part, Integer> modifyProduct_partIdColumn;

    /**
     * Column for All Parts table, part name
     */
    @FXML
    private TableColumn<Part, String>  modifyProduct_partNameColumn;

    /**
     * Column for All Parts table, part inventory
     */
    @FXML
    private TableColumn<Part, Integer>  modifyProduct_partInventoryColumn;

    /**
     * Column for All Parts table, part price
     */
    @FXML
    private TableColumn<Part, Double>  modifyProduct_partPriceColumn;

    /**
     * Table view for associated parts in inventory
     */
    @FXML
    private TableView<Part> modifyProduct_associatedParts;

    /**
     * Column for Associated Parts table, part id
     */
    @FXML
    private TableColumn<Part, Integer> modifyProduct_assoc_partIdColumn;

    /**
     * Column for Associated Parts table, part name
     */
    @FXML
    private TableColumn<Part, String>  modifyProduct_assoc_partNameColumn;

    /**
     * Column for Associated Parts table, part inventory
     */
    @FXML
    private TableColumn<Part, Integer>  modifyProduct_assoc_partInventoryColumn;

    /**
     * Column for Associated Parts table, part price
     */
    @FXML
    private TableColumn<Part, Double>  modifyProduct_assoc_partPriceColumn;

    /**
     * Text field for product Price
     */
    @FXML
    private TextField modifyProduct_price;

    /**
     * Button to remove associated part
     */
    @FXML
    private Button modifyProduct_removePart;

    /**
     * Button to save modified Product
     */
    @FXML
    private Button modifyProduct_save;

    /**
     * Text field to search for Parts
     */
    @FXML
    private TextField modifyProduct_search;

    /**
     * Int variable to hold current index of product being modified
     */
    private int productIndex;

    /**
     * Function called when addPart button is clicked
     * Will add part to associated list
     * Will error if no part is selected or if part is already associated
     * @param event
     */
    @FXML
    void addPart(MouseEvent event) {
        Part partSelected = modifyProduct_allParts.getSelectionModel().getSelectedItem();
        if (partSelected == null) {
            setAlertDialogue("No part selected");
            return;
        }
        if (modifyProduct_associatedPartsArray.contains(partSelected)) {
            setAlertDialogue("Part already associated with product");
            return;
        }
        modifyProduct_associatedPartsArray.add(partSelected);
        modifyProduct_associatedParts.setItems(modifyProduct_associatedPartsArray);
    }

    /**
     * Cancels the add product process
     * @param event the click event
     * @throws IOException
     */
    @FXML
    void cancel(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Alert");
        alert.setContentText("Do you wish to cancel product modification?");
        Optional<ButtonType> answer = alert.showAndWait();

        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            openMainScreen(event);
        }
    }

    /**
     * Searches Parts by ID or full or partial name matches
     * Displays in top part table view
     * TRIGGERED BY ENTER KEY PRESS
     * @param event ENTER KEY PRESS
     */
    @FXML
    void searchParts(ActionEvent event) {
        ObservableList<Part> parts = Inventory.getAllParts();
        ObservableList<Part> matchedParts = FXCollections.observableArrayList();
        String search = modifyProduct_search.getText();

        for (Part part : parts) {
            if (String.valueOf(part.getId()).contains(search) || part.getName().contains(search)) {
                matchedParts.add(part);
            }
        }

        modifyProduct_allParts.setItems(matchedParts);

        if (matchedParts.size() < 1) {
            setAlertDialogue("No matching parts found");
        }
    }

    /**
     * When Search field is empty, refreshes the table view to show all parts
     */
    @FXML
    public void refreshPartSearch() {
        if (modifyProduct_search.getText().isEmpty()) {
            modifyProduct_allParts.setItems(Inventory.getAllParts());
        }
    }

    /**
     * Removes part from associated parts list
     * @param event mouse click event
     */
    @FXML
    void removePart(MouseEvent event) {
        Part partSelected = modifyProduct_associatedParts.getSelectionModel().getSelectedItem();
        if (partSelected == null) {
            setAlertDialogue("No part selected");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Alert");
        alert.setContentText("Do you wish to remove this associated part?");
        Optional<ButtonType> answer = alert.showAndWait();

        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            if (modifyProduct_associatedPartsArray.contains(partSelected)) {
                modifyProduct_associatedPartsArray.remove(partSelected);
            }
            modifyProduct_associatedParts.setItems(modifyProduct_associatedPartsArray);
        }
    }

    /**
     * Saves new product information and updates product in inventory
     * @param event  mouse click event
     */
    @FXML
    void saveProduct(MouseEvent event) {
        try {
            int id = Main.selectedProduct.getId();
            String name = modifyProduct_name.getText();
            double price = parseDouble(modifyProduct_price.getText());
            int min = parseInt(modifyProduct_min.getText());
            int max = parseInt(modifyProduct_max.getText());
            int stock = parseInt(modifyProduct_inventory.getText());

            if (name.length() < 1) {
                setAlertDialogue("Name is a required field");
                return;
            }
            if (min > max) {
                setAlertDialogue("Minimum must be less than maximum");
                return;
            }
            if (stock < min || stock > max) {
                setAlertDialogue("Inventory must be between min and max");
                return;
            }
            Product product = new Product(id, name, price, stock, min, max);
            for (Part part : modifyProduct_associatedPartsArray) {
                product.addAssociatedParts(part);
            }
            Inventory.updateProduct(productIndex, product);
            openMainScreen(event);
        } catch (Exception e) {
            System.out.println(e);
            setAlertDialogue("Product form contains invalid data and/or blank fields");
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

    /**
     * Reopens the Main display
     * @param event the click event
     * @throws IOException
     */
    void openMainScreen(MouseEvent event) throws IOException {
        URL url = Paths.get("./src/main/java/view/main.fxml").toUri().toURL();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Populates the tables with the corresponding part information upon initialization of window
     * and populates existing product information into fields
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Product selectedProduct = Main.getSelectedProduct();
        productIndex = Inventory.getAllProducts().indexOf(selectedProduct);

        modifyProduct_associatedPartsArray = selectedProduct.getAllAssociatedParts();
        modifyProduct_allParts.setItems(Inventory.getAllParts());
        modifyProduct_partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProduct_partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProduct_partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProduct_partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        modifyProduct_associatedParts.setItems(modifyProduct_associatedPartsArray);
        modifyProduct_assoc_partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProduct_assoc_partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProduct_assoc_partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProduct_assoc_partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        modifyProduct_id.setText(String.valueOf(selectedProduct.getId()));
        modifyProduct_name.setText(selectedProduct.getName());
        modifyProduct_price.setText(String.valueOf(selectedProduct.getPrice()));
        modifyProduct_min.setText(String.valueOf(selectedProduct.getMin()));
        modifyProduct_max.setText(String.valueOf(selectedProduct.getMax()));
        modifyProduct_inventory.setText(String.valueOf(selectedProduct.getStock()));
    }

}

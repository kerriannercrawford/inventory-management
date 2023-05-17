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
 * Controller to handle Adding a new Product
 */
public class AddProduct implements Initializable {

    /**
     * An observable list/array that holds the parts to be associated
     * with this product
     */
    private ObservableList<Part> addProduct_associatedPartsArray = FXCollections.observableArrayList();

    /**
     * Button to add part
     */
    @FXML
    private Button addProduct_addButton;

    /**
     * Button to cancel product creation
     */
    @FXML
    private Button addProduct_cancel;

    /**
     * Text field that holds the product ID
     */
    @FXML
    private TextField addProduct_id;

    /**
     * Text field that holds the inventory/stock for the product
     */
    @FXML
    private TextField addProduct_inventory;

    /**
     * Text field that holds the inventory max
     */
    @FXML
    private TextField addProduct_max;

    /**
     * Text field that holds the inventory min
     */
    @FXML
    private TextField addProduct_min;

    /**
     * Text field that holds the product name
     */
    @FXML
    private TextField addProduct_name;

    /**
     * Table view for ALL parts in inventory
     */
    @FXML
    private TableView<Part> addProduct_allParts;

    /**
     * Column for All Parts table, part ID
     */
    @FXML
    private TableColumn<Part, Integer> addProduct_partIdColumn;

    /**
     * Column for All Parts table, part name
     */
    @FXML
    private TableColumn<Part, String>  addProduct_partNameColumn;

    /**
     * Column for All Parts table, part inventory
     */
    @FXML
    private TableColumn<Part, Integer>  addProduct_partInventoryColumn;

    /**
     * Column for All Parts table, part price
     */
    @FXML
    private TableColumn<Part, Double>  addProduct_partPriceColumn;

    /**
     * Table view for associated parts in inventory
     */
    @FXML
    private TableView<Part> addProduct_associatedParts;

    /**
     * Column for Associated Parts table, part id
     */
    @FXML
    private TableColumn<Part, Integer> addProduct_assoc_partIdColumn;

    /**
     * Column for Associated Parts table, part name
     */
    @FXML
    private TableColumn<Part, String>  addProduct_assoc_partNameColumn;

    /**
     * Column for Associated Parts table, part inventory
     */
    @FXML
    private TableColumn<Part, Integer>  addProduct_assoc_partInventoryColumn;

    /**
     * Column for Associated Parts table, part price
     */
    @FXML
    private TableColumn<Part, Double>  addProduct_assoc_partPriceColumn;

    /**
     * Text field for product Price
     */
    @FXML
    private TextField addProduct_price;

    /**
     * Button to remove associated part
     */
    @FXML
    private Button addProduct_removePart;

    /**
     * Button to save new Product
     */
    @FXML
    private Button addProduct_save;

    /**
     * Text field to search for Parts
     */
    @FXML
    private TextField addProduct_search;

    /**
     * Function called when addPart button is clicked
     * Will add part to associated list
     * Will error if no part is selected or if part is already associated
     * @param event
     */
    @FXML
    void addPart(MouseEvent event) {
        Part partSelected = addProduct_allParts.getSelectionModel().getSelectedItem();
        if (partSelected == null) {
            setAlertDialogue("No part selected");
            return;
        }
        if (addProduct_associatedPartsArray.contains(partSelected)) {
            setAlertDialogue("Part already associated with product");
            return;
        }
        addProduct_associatedPartsArray.add(partSelected);
        addProduct_associatedParts.setItems(addProduct_associatedPartsArray);
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
     * Cancels the add product process
     * @param event the click event
     * @throws IOException
     */
    @FXML
    void cancel(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Alert");
        alert.setContentText("Do you wish to cancel product creation?");
        Optional<ButtonType> answer = alert.showAndWait();

        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            openMainScreen(event);
        }
    }


    /**
     * Removes part from associated parts list
     * @param event mouse click event
     */
    @FXML
    void removePart(MouseEvent event) {
        Part partSelected = addProduct_associatedParts.getSelectionModel().getSelectedItem();
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
            if (addProduct_associatedPartsArray.contains(partSelected)) {
                addProduct_associatedPartsArray.remove(partSelected);
            }
            addProduct_associatedParts.setItems(addProduct_associatedPartsArray);
        }
    }

    /**
     * Saves new product information and adds product to inventory
     * @param event  mouse click event
     */
    @FXML
    void saveProduct(MouseEvent event) {
        try {
            int id = Inventory.getNewProductID();
            String name = addProduct_name.getText();
            double price = parseDouble(addProduct_price.getText());
            int min = parseInt(addProduct_min.getText());
            int max = parseInt(addProduct_max.getText());
            int stock = parseInt(addProduct_inventory.getText());

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
            for (Part part : addProduct_associatedPartsArray) {
                product.addAssociatedParts(part);
            }
            Inventory.addProduct(product);
            openMainScreen(event);
        } catch (Exception e) {
            setAlertDialogue("Product form contains invalid data and/or blank fields");
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
        String search = addProduct_search.getText();

        for (Part part : parts) {
            if (String.valueOf(part.getId()).contains(search) || part.getName().contains(search)) {
                matchedParts.add(part);
            }
        }

        addProduct_allParts.setItems(matchedParts);

        if (matchedParts.size() < 1) {
            setAlertDialogue("No matching parts found");
        }
    }

    /**
     * When Search field is empty, refreshes the table view to show all parts
     */
    @FXML
    public void refreshPartSearch() {
        if (addProduct_search.getText().isEmpty()) {
            addProduct_allParts.setItems(Inventory.getAllParts());
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
     * Populates the tables with the corresponding part information upon initialization of window
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProduct_allParts.setItems(Inventory.getAllParts());
        addProduct_partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProduct_partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProduct_partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProduct_partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        addProduct_assoc_partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProduct_assoc_partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProduct_assoc_partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProduct_assoc_partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}

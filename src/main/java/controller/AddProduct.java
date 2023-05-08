package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddProduct {

    @FXML
    private Button addProduct_addButton;

    @FXML
    private Button addProduct_cancel;

    @FXML
    private TextField addProduct_id;

    @FXML
    private TextField addProduct_inventory;

    @FXML
    private TextField addProduct_max;

    @FXML
    private TextField addProduct_min;

    @FXML
    private TextField addProduct_name;

    @FXML
    private TableView<?> addProduct_parts1;

    @FXML
    private TableView<?> addProduct_parts2;

    @FXML
    private TextField addProduct_price;

    @FXML
    private Button addProduct_removePart;

    @FXML
    private Button addProduct_save;

    @FXML
    private TextField addProduct_search;

    @FXML
    void addPart(MouseEvent event) {

    }

    @FXML
    void cancel(MouseEvent event) {

    }

    @FXML
    void removePart(MouseEvent event) {

    }

    @FXML
    void saveProduct(MouseEvent event) {

    }

    @FXML
    void searchParts(ActionEvent event) {

    }

}

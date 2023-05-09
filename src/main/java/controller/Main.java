package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class Main {

    @FXML
    private Button root_addPart;

    @FXML
    private Button root_addProduct;

    @FXML
    private Button root_deletePart;

    @FXML
    private Button root_deleteProduct;

    @FXML
    private Button root_exit;

    @FXML
    private Button root_modifyPart;

    @FXML
    private Button root_modifyProduct;

    @FXML
    private TableView<?> root_parts;

    @FXML
    private TableView<?> root_products;

    @FXML
    private TextField root_searchPart;

    @FXML
    private TextField root_searchProduct;

    @FXML
    void openAddProductScreen(MouseEvent event) throws IOException {
        URL url = Paths.get("./src/main/java/view/add-product.fxml").toUri().toURL();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openModifyProductScreen(MouseEvent event) throws IOException {
        URL url = Paths.get("./src/main/java/view/modify-product.fxml").toUri().toURL();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openAddPartScreen(MouseEvent event) throws IOException {
        URL url = Paths.get("./src/main/java/view/add-part.fxml").toUri().toURL();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openModifyPartScreen(MouseEvent event) throws IOException {
        URL url = Paths.get("./src/main/java/view/modify-part.fxml").toUri().toURL();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addPart(MouseEvent event) {

    }

    @FXML
    void addProduct(MouseEvent event) {
        System.out.println("successfully added a prod!");
    }

    @FXML
    void deletePart(MouseEvent event) {

    }

    @FXML
    void deleteProduct(MouseEvent event) {

    }

    @FXML
    void modifyPart(MouseEvent event) {

    }

    @FXML
    void modifyProduct(MouseEvent event) {

    }

    @FXML
    void searchAllParts(ActionEvent event) {
        System.out.println("yes! success! search all parts");
    }

    @FXML
    void searchAllProducts(ActionEvent event) {
        System.out.println("yes! success! search all products");
    }

    @FXML
    void exit(MouseEvent event) {
        System.out.println("Terminating");
        System.exit(0);
    }

}

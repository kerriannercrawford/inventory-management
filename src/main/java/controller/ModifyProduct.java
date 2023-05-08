package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class ModifyProduct {

    @FXML
    private Button modifyProduct_addButton;

    @FXML
    private Button modifyProduct_cancel;

    @FXML
    private TextField modifyProduct_id;

    @FXML
    private TextField modifyProduct_inventory;

    @FXML
    private TextField modifyProduct_max;

    @FXML
    private TextField modifyProduct_min;

    @FXML
    private TextField modifyProduct_name;

    @FXML
    private TableView<?> modifyProduct_parts1;

    @FXML
    private TableView<?> modifyProduct_parts2;

    @FXML
    private TextField modifyProduct_price;

    @FXML
    private Button modifyProduct_removePart;

    @FXML
    private Button modifyProduct_save;

    @FXML
    private TextField modifyProduct_search;

    @FXML
    void addPart(MouseEvent event) {
        System.out.println("success");
    }

    @FXML
    void cancel(MouseEvent event) throws IOException {
        URL url = Paths.get("./src/main/java/view/main.fxml").toUri().toURL();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
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

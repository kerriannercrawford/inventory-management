package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class ModifyPart {

    @FXML
    private AnchorPane modifyPart;

    @FXML
    private Button modifyPart_cancel;

    @FXML
    private TextField modifyPart_id;

    @FXML
    private RadioButton modifyPart_inhouseRadio;

    @FXML
    private TextField modifyPart_inventory;

    @FXML
    private TextField modifyPart_max;

    @FXML
    private TextField modifyPart_min;

    @FXML
    private TextField modifyPart_name;

    @FXML
    private RadioButton modifyPart_outsourcedRadio;

    @FXML
    private TextField modifyPart_price;

    @FXML
    private Button modifyPart_save;

    @FXML
    private TextField modifyPart_source;

    @FXML
    private Label modifyPart_sourceLabel;

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
    void savePart(MouseEvent event) {

    }

    @FXML
    void setPartSource(MouseEvent event) {

    }

}

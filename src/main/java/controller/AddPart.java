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
import model.parts.Part;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class AddPart {

    @FXML
    private AnchorPane addPart;

    @FXML
    private Button addPart_cancel;

    @FXML
    private TextField addPart_id;

    @FXML
    private RadioButton addPart_inhouseRadio;

    @FXML
    private TextField addPart_inventory;

    @FXML
    private TextField addPart_max;

    @FXML
    private TextField addPart_min;

    @FXML
    private TextField addPart_name;

    @FXML
    private RadioButton addPart_outsourcedRadio;

    @FXML
    private TextField addPart_price;

    @FXML
    private Button addPart_save;

    @FXML
    private TextField addPart_source;

    @FXML
    private Label addPart_sourceLabel;

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
        addPart_source.setVisible(false);
        System.out.println(addPart_name.getText());
        System.out.println(addPart_inhouseRadio.isSelected());
    }

    @FXML
    void setInhousePart() {
        addPart_outsourcedRadio.setSelected(false);
        addPart_sourceLabel.setText("Machine ID");
        addPart_source.setVisible(true);
        addPart_sourceLabel.setVisible(true);
    }

    @FXML
    void setOutsourcedPart() {
        addPart_inhouseRadio.setSelected(false);
        addPart_sourceLabel.setText("Company Name");
        addPart_source.setVisible(true);
        addPart_sourceLabel.setVisible(true);
    }

}

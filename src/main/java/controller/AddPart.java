package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    void cancel(MouseEvent event) {

    }

    @FXML
    void savePart(MouseEvent event) {

    }

    @FXML
    void setPartSource(MouseEvent event) {

    }

}

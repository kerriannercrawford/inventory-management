package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    void cancel(ActionEvent event) {

    }

    @FXML
    void savePart(MouseEvent event) {

    }

    @FXML
    void setPartSource(MouseEvent event) {

    }

}

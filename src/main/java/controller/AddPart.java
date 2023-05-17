package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.inventory.Inventory;
import model.parts.InHouse;
import model.parts.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 *
 * Add Part Controller
 * This controller manages the Add Part pane and functionality
 *
 */
public class AddPart {

    /**
     * The main Add Part window pane
     */
    @FXML
    private AnchorPane addPart;

    /**
     * The Cancel button
     */
    @FXML
    private Button addPart_cancel;

    /**
     * The text Field for the Part ID
     */
    @FXML
    private TextField addPart_id;

    /**
     * The radio selector for an In House part
     */
    @FXML
    private RadioButton addPart_inhouseRadio;

    /**
     * The radio selector for an Outsourced part
     */
    @FXML
    private RadioButton addPart_outsourcedRadio;

    /**
     * The text field for Inventory/Stock
     */
    @FXML
    private TextField addPart_inventory;

    /**
     * The text field for the max inventory
     */
    @FXML
    private TextField addPart_max;

    /**
     * The text field for min inventory
     */
    @FXML
    private TextField addPart_min;

    /**
     * The text field for the part name
     */
    @FXML
    private TextField addPart_name;

    /**
     * The text field for the part price
     */
    @FXML
    private TextField addPart_price;

    /**
     * The button to save the part information
     */
    @FXML
    private Button addPart_save;

    /**
     * The value for the part which is either Machine ID or Company Name
     * depending on which radio is selected
     */
    @FXML
    private TextField addPart_source;

    /**
     * The label for the part source which will read either Machine ID
     * or Company Name depending on which radio is selected
     */
    @FXML
    private Label addPart_sourceLabel;

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
     * Cancels the add part process
     * @param event the click event
     * @throws IOException
     */
    @FXML
    void cancel(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Alert");
        alert.setContentText("Do you wish to cancel part creation?");
        Optional<ButtonType> answer = alert.showAndWait();

        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            openMainScreen(event);
        }
    }

    /**
     * Saves the inputted part information and applies it to a new part
     * @param event the click event
     */
    @FXML
    void savePart(MouseEvent event) {
        try {
            int id = Inventory.getNewPartID();
            String name = addPart_name.getText();
            double price = parseDouble(addPart_price.getText());
            int min = parseInt(addPart_min.getText());
            int max = parseInt(addPart_max.getText());
            int stock = parseInt(addPart_inventory.getText());

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

            if (addPart_inhouseRadio.isSelected()) {
                try {
                    int machineId = parseInt(addPart_source.getText());
                    InHouse inhouse = new InHouse(id, name, price, stock, min, max, machineId);
                    System.out.println(inhouse);
                    Inventory.addPart(inhouse);
                    openMainScreen(event);
                } catch (Exception e) {
                    setAlertDialogue("Machine ID must be a number");
                }
            } else if (addPart_outsourcedRadio.isSelected()) {
                try {
                    String companyName = addPart_source.getText();
                    Outsourced outsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                    System.out.println(outsourced);
                    Inventory.addPart(outsourced);
                    openMainScreen(event);
                } catch (Exception e) {
                    setAlertDialogue("Error parsing company name");
                }
            } else {
                setAlertDialogue("Part source must be either In House or Outsourced");
            }
        } catch (Exception e) {
            setAlertDialogue("Part form contains invalid data and/or blank fields");
        }

    }

    /**
     * Sets the radio button to be In House and makes the fields visible
     */
    @FXML
    void setInhousePart() {
        addPart_outsourcedRadio.setSelected(false);
        addPart_sourceLabel.setText("Machine ID");
        addPart_source.setVisible(true);
        addPart_sourceLabel.setVisible(true);
    }

    /**
     * Sets the radio button to be Outsourced and makes the fields visible
     */
    @FXML
    void setOutsourcedPart() {
        addPart_inhouseRadio.setSelected(false);
        addPart_sourceLabel.setText("Company Name");
        addPart_source.setVisible(true);
        addPart_sourceLabel.setVisible(true);
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

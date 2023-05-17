package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.inventory.Inventory;
import model.parts.InHouse;
import model.parts.Outsourced;
import model.parts.Part;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 *
 * Modify Part Controller
 * This controller manages the Modify Part pane and functionality
 *
 */
public class ModifyPart implements Initializable {

    /**
     * The main Add Part window pane
     */
    @FXML
    private AnchorPane modifyPart;

    /**
     * The Cancel button
     */
    @FXML
    private Button modifyPart_cancel;

    /**
     * The text Field for the Part ID
     */
    @FXML
    private TextField modifyPart_id;

    /**
     * The radio selector for an In House part
     */
    @FXML
    private RadioButton modifyPart_inhouseRadio;

    /**
     * The radio selector for an Outsourced part
     */
    @FXML
    private RadioButton modifyPart_outsourcedRadio;

    /**
     * The text field for Inventory/Stock
     */
    @FXML
    private TextField modifyPart_inventory;

    /**
     * The text field for the max inventory
     */
    @FXML
    private TextField modifyPart_max;

    /**
     * The text field for min inventory
     */
    @FXML
    private TextField modifyPart_min;

    /**
     * The text field for the part name
     */
    @FXML
    private TextField modifyPart_name;

    /**
     * The text field for the part price
     */
    @FXML
    private TextField modifyPart_price;

    /**
     * The button to save the part information
     */
    @FXML
    private Button modifyPart_save;

    /**
     * The value for the part which is either Machine ID or Company Name
     * depending on which radio is selected
     */
    @FXML
    private TextField modifyPart_source;

    /**
     * The label for the part source which will read either Machine ID
     * or Company Name depending on which radio is selected
     */
    @FXML
    private Label modifyPart_sourceLabel;

    /**
     * Integer variable that holds the index of the currently being modified part
     */
    private int partIndex;

    /**
     * Cancels the modify part process
     * @param event the click event
     * @throws IOException
     */
    @FXML
    void cancel(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Alert");
        alert.setContentText("Do you wish to cancel part modification?");
        Optional<ButtonType> answer = alert.showAndWait();

        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            openMainScreen(event);
        }
    }

    /**
     * Saves the inputted part information and applies it to an existing part
     * @param event the click event
     */
    @FXML
    void savePart(MouseEvent event) {
        try {
            int id = parseInt(modifyPart_id.getText());
            String name = modifyPart_name.getText();
            double price = parseDouble(modifyPart_price.getText());
            int min = parseInt(modifyPart_min.getText());
            int max = parseInt(modifyPart_max.getText());
            int stock = parseInt(modifyPart_inventory.getText());

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

            if (modifyPart_inhouseRadio.isSelected()) {
                try {
                    int machineId = parseInt(modifyPart_source.getText());
                    InHouse inhouse = new InHouse(id, name, price, stock, min, max, machineId);
                    System.out.println(inhouse);
                    Inventory.updatePart(partIndex, inhouse);
                    openMainScreen(event);
                } catch (Exception e) {
                    setAlertDialogue("Machine ID must be a number");
                }
            } else if (modifyPart_outsourcedRadio.isSelected()) {
                try {
                    String companyName = modifyPart_source.getText();
                    Outsourced outsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                    System.out.println(outsourced);
                    Inventory.updatePart(partIndex, outsourced);
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
     * Populates the existing fields with the existing product information
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Part selectedPart = Main.getSelectedPart();
        partIndex = Inventory.getAllParts().indexOf(selectedPart);

        if (selectedPart instanceof InHouse) {
            modifyPart_inhouseRadio.setSelected(true);
            modifyPart_outsourcedRadio.setSelected(false);

            modifyPart_sourceLabel.setText("Machine ID");
            modifyPart_source.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }

        else if (selectedPart instanceof Outsourced) {
            modifyPart_outsourcedRadio.setSelected(true);
            modifyPart_inhouseRadio.setSelected(false);
            modifyPart_sourceLabel.setText("Company Name");
            modifyPart_source.setText(((Outsourced) selectedPart).getCompanyName());
        }

        modifyPart_sourceLabel.setVisible(true);
        modifyPart_source.setVisible(true);
        modifyPart_id.setText(String.valueOf(selectedPart.getId()));
        modifyPart_name.setText(selectedPart.getName());
        modifyPart_inventory.setText(String.valueOf(selectedPart.getStock()));
        modifyPart_price.setText(String.valueOf(selectedPart.getPrice()));
        modifyPart_min.setText(String.valueOf(selectedPart.getMin()));
        modifyPart_max.setText(String.valueOf(selectedPart.getMax()));
    }

    /**
     * Sets the radio button to be In House and makes the fields visible
     */
    @FXML
    void setInhousePart() {
        modifyPart_outsourcedRadio.setSelected(false);
        modifyPart_sourceLabel.setText("Machine ID");
        modifyPart_source.setVisible(true);
        modifyPart_sourceLabel.setVisible(true);
    }

    /**
     * Sets the radio button to be Outsourced and makes the fields visible
     */
    @FXML
    void setOutsourcedPart() {
        modifyPart_inhouseRadio.setSelected(false);
        modifyPart_sourceLabel.setText("Company Name");
        modifyPart_source.setVisible(true);
        modifyPart_sourceLabel.setVisible(true);
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
}

package kc_software_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Class for Overall Application
 *
 * Javadocs located in ./javadocs
 *
 *  *      * FUTURE ENHANCEMENT:
 *  *      * Currently, the top table on Product Modify/Add shows all the parts in inventory. There is a check to ensure
 *  *      * a part is not duplicated in the bottom table, but a potential enhancement for the
 *  *      * future of this application could be to remove any associated parts from the top table
 *  *      * to prevent a user from accidentally clicking it, even though there is a catch for it
 */
public class kc_software_1 extends Application {
    @Override
    /**
     * Starts the application
     */
    public void start(Stage stage) throws IOException {
        System.out.println("Started");
        URL url = Paths.get("./src/main/java/view/main.fxml").toUri().toURL();

        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    /**
     * Inits the program
     */
    public void init() {
        System.out.println("Initialized");
    }

    /**
     * Launches main program
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}
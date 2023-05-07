package kc_software_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class kc_software_1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("Started");
        URL url = Paths.get("./src/main/java/view/main.fxml").toUri().toURL();

        Scene scene = new Scene(FXMLLoader.load(url), 320, 240);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("Terminated");
    }

    @Override
    public void init() {
        System.out.println("Initialized");
    }


    public static void main(String[] args) {
        launch();
    }
}
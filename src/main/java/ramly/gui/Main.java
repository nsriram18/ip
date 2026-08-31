package ramly.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ramly.Ramly;

/** JavaFX entry point for the Ramly task manager. */
public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "./data/ramly.txt";

    /** Loads the FXML view and injects the shared Ramly command engine. */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            MainWindow controller = fxmlLoader.getController();
            controller.setRamly(new Ramly(DEFAULT_FILE_PATH));

            Scene scene = new Scene(root);
            stage.setTitle("Ramly · Your daily sidekick");
            stage.setMinWidth(520);
            stage.setMinHeight(680);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load the Ramly GUI.", e);
        }
    }
}

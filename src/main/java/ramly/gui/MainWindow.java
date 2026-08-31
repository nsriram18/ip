package ramly.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ramly.Ramly;
import ramly.ui.Ui;

/** Controller for the FXML-based Ramly chat window. */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Image userImage = createAvatar(Color.web("#ffb86c"), Color.web("#5b2c6f"));
    private final Image ramlyImage = createAvatar(Color.web("#7dd3fc"), Color.web("#12355b"));
    private Ramly ramly;

    /** Binds the scroll position to the growing conversation, as in the tutorial. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        addRamlyDialog("Hello! I’m Ramly, your tiny but mighty task sidekick.\n"
                + "Try `todo`, `deadline`, `event`, `list`, `find`, `mark`, `unmark`, `delete`, or `bye`.");
    }

    /** Injects the command engine used by both the GUI and text interfaces. */
    public void setRamly(Ramly ramly) {
        this.ramly = ramly;
    }

    /** Processes text entered by the user and appends the conversation to the window. */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty() || ramly == null) {
            return;
        }

        List<String> output = new ArrayList<>();
        Ui responseUi = new Ui(output::add);
        boolean isExit = ramly.executeCommand(input, responseUi);
        responseUi.close();

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        if (!output.isEmpty()) {
            addRamlyDialog(String.join("\n", output));
        }
        userInput.clear();

        if (isExit) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            userInput.setPromptText("Ramly has signed off for now");
        }
    }

    /** Appends a response bubble from Ramly to the conversation. */
    private void addRamlyDialog(String text) {
        dialogContainer.getChildren().add(DialogBox.getRamlyDialog(text, ramlyImage));
    }

    /** Creates a small geometric avatar image without requiring platform-specific image files. */
    private static Image createAvatar(Color background, Color foreground) {
        int size = 96;
        WritableImage image = new WritableImage(size, size);
        PixelWriter writer = image.getPixelWriter();
        double center = size / 2.0;
        double outerRadius = size / 2.0;
        double faceRadius = size * 0.29;

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                double distanceFromCenter = Math.hypot(x - center, y - center);
                if (distanceFromCenter <= outerRadius) {
                    writer.setColor(x, y, background);
                } else {
                    writer.setColor(x, y, Color.TRANSPARENT);
                }
                if (Math.hypot(x - center, y - size * 0.43) <= faceRadius) {
                    writer.setColor(x, y, foreground);
                }
                if (y > size * 0.59 && y < size * 0.84 && Math.abs(x - center) < size * 0.26) {
                    writer.setColor(x, y, foreground);
                }
            }
        }
        return image;
    }
}

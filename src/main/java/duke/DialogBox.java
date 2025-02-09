package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Generates the Dialog Box to represent user speech.
     *
     * @param text The text to be displayed.
     * @param img The image of the User.
     * @return A DialogBox with the required specifications.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userMessage = new DialogBox(text, img);
        userMessage.setBackground(new Background(new BackgroundFill(Color.LAVENDERBLUSH,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        return userMessage;
    }

    /**
     * Generates the Dialog Box to represent Duwuke speech.
     *
     * @param text The text to be displayed.
     * @param imgHappy The image of Duwuke.
     * @param imgAngry The image of Duwuke with an additional angry motif.
     * @return A DialogBox with the required specifications.
     */
    public static DialogBox getDukeDialog(String text, Image imgHappy, Image imgAngry) {
        var db = (text.substring(0, 6).equals("GRR!!!"))
                ? new DialogBox(text, imgAngry)
                : new DialogBox(text, imgHappy);
        db.flip();
        db.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        return db;
    }
}
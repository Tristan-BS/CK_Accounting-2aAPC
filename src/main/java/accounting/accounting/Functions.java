package accounting.accounting;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Functions {

    public static boolean ShowPopup(String Type, String Title, String Content) {
        Objects.requireNonNull(Type, "Type cannot be null");
        Objects.requireNonNull(Title, "Title cannot be null");
        Objects.requireNonNull(Content, "Content cannot be null");

        Alert alert;
        if (Type.equalsIgnoreCase("E")) {
            alert = new Alert(AlertType.ERROR);
        } else if(Type.equalsIgnoreCase("W")) {
            alert = new Alert(AlertType.WARNING);
        } else if(Type.equalsIgnoreCase("I")) {
            alert = new Alert(AlertType.INFORMATION);
        } else {
            alert = new Alert(AlertType.CONFIRMATION);
        }

        alert.setTitle(Title);
        alert.setHeaderText(null);

        Text text = new Text(Content);
        text.setTextAlignment(TextAlignment.CENTER);

        // To Center
        VBox vbox = new VBox(text);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        // Create custom buttons
        Button continueButton = new Button("Continue");
        Button exitButton = new Button("Cancel");

        continueButton.setOnAction(e -> alert.setResult(ButtonType.OK));
        exitButton.setOnAction(e -> {
            alert.setResult(ButtonType.CANCEL);
            alert.close();
        });

        HBox buttonBox = new HBox(continueButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        vbox.getChildren().add(buttonBox);

        alert.getDialogPane().setContent(vbox);
        alert.getButtonTypes().clear();

        //alert.getDialogPane().getStylesheets().add(
        //        Objects.requireNonNull(Functions.class.getResource("/Style/Style.css")).toExternalForm()
        //);

        if (Type.equalsIgnoreCase("E")) {
            continueButton.setVisible(false);
        }

        alert.showAndWait();

        // Check if the user clicked the continue button or the exit button
        return alert.getResult() == ButtonType.OK;
    }

    public static String formatNumber(double number) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMANY);
        return numberFormat.format(number);
    }
}
package accounting.accounting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CKAccounting.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1074, 638);
        scene.getStylesheets().add(getClass().getResource("/Style/CKAccounting_Style.css").toExternalForm());

        stage.setWidth(1074);
        stage.setHeight(638);

        stage.setMinHeight(638);
        stage.setMinWidth(1074);
        stage.setMaximized(true);

        stage.setTitle("CK Accounting System");

        stage.setScene(scene);
        stage.show();

        boolean ActivateDebugMode = false;

        if (ActivateDebugMode) {
            stage.widthProperty().addListener((obs, oldVal, newVal) -> {
                System.out.println("Width: " + newVal);
            });

            stage.heightProperty().addListener((obs, oldVal, newVal) -> {
                System.out.println("Height: " + newVal);
            });

            stage.setScene(scene);
            stage.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
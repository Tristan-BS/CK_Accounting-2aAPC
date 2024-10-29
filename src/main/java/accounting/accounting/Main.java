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
        stage.setMinHeight(638);
        stage.setMinWidth(1074);
        stage.setTitle("CK Accounting System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
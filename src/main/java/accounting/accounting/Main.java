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
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setMinHeight(620);
        stage.setMinWidth(920);
        stage.setTitle("CK Accounting System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
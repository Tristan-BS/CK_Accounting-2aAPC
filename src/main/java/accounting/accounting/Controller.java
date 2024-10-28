package accounting.accounting;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    // ImageViews
    @FXML
    private ImageView IV_Menu;
    @FXML
    private ImageView IV_Home;
    @FXML
    private ImageView IV_Invoices;
    @FXML
    private ImageView IV_Documents;
    @FXML
    private ImageView IV_Evaluation;
    @FXML
    private ImageView IV_Account;
    @FXML
    private ImageView IV_Exit;

    // Button for the ImageViews
    @FXML
    private Button B_Menu;
    @FXML
    private Button B_Home;
    @FXML
    private Button B_Invoices;
    @FXML
    private Button B_Documents;
    @FXML
    private Button B_Evaluation;
    @FXML
    private Button B_Account;
    @FXML
    private Button B_Exit;

    // AnchorPane
    @FXML
    private AnchorPane AP_SBText;
    @FXML
    private AnchorPane AP_Page;

    private boolean isMenuOpen = false;
    private int MenuWidth = 75;

    // Methods for Button Pressed

    // Menu Button Pressed with animation
    @FXML
    private void On_B_Menu_Pressed() {
        // Menu Animation
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(200));
        transition.setNode(AP_SBText);

        // Page Animation
        Timeline timeline = new Timeline();
        KeyFrame keyFrame;

        if (isMenuOpen) {
            keyFrame = new KeyFrame(
                    Duration.millis(200),
                    new KeyValue(AP_Page.prefWidthProperty(), AP_Page.getPrefWidth() - MenuWidth), // Set width to original width
                    new KeyValue(AP_Page.layoutXProperty(), AP_Page.getLayoutX() + MenuWidth) // Set x position the original x position
            );
            transition.setByX(125);
            isMenuOpen = false;
        } else {
            keyFrame = new KeyFrame(
                    Duration.millis(200),
                    new KeyValue(AP_Page.prefWidthProperty(), AP_Page.getPrefWidth() + MenuWidth), // Set width to original width + MenuWidth
                    new KeyValue(AP_Page.layoutXProperty(), AP_Page.getLayoutX() - MenuWidth) // Set x position to original x position - MenuWidth
            );
            transition.setByX(-125);
            isMenuOpen = true;
        }

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        transition.play();
    }


    @FXML
    private void On_B_Home_Pressed() {
        System.out.println("Home Button Pressed");
    }
    @FXML
    private void On_B_Invoices_Pressed() {
        System.out.println("Invoices Button Pressed");
    }
    @FXML
    private void On_B_Documents_Pressed() {
        System.out.println("Documents Button Pressed");
    }
    @FXML
    private void On_B_Evaluation_Pressed() {
        System.out.println("Evaluation Button Pressed");
    }
    @FXML
    private void On_B_Account_Pressed() {
        System.out.println("Account Button Pressed");
    }
    @FXML
    private void On_B_Exit_Pressed() {
        System.exit(0);
    }


}
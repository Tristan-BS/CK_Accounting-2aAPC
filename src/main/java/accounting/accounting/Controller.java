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
    // AnchorPane
    @FXML
    private AnchorPane AP_SBText;
    @FXML
    private AnchorPane AP_Page;

    // Tab from TabPane Pages
    @FXML
    private TabPane TP_Pages;
    @FXML
    private Tab TPP_Home;
    @FXML
    private Tab TPP_Invoices;
    @FXML
    private Tab TPP_Customer;
    @FXML
    private Tab TPP_Documents;
    @FXML
    private Tab TPP_Evaluations;
    @FXML
    private Tab TPP_Account;
    @FXML
    private Tab TPP_NewInvoice;
    @FXML
    private Tab TPP_NewCustomer;

    private boolean isMenuOpen = false;

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

        int menuWidth = 75;
        if (isMenuOpen) {
            keyFrame = new KeyFrame(
                    Duration.millis(200),
                    new KeyValue(AP_Page.prefWidthProperty(), AP_Page.getPrefWidth() - menuWidth), // Set width to original width
                    new KeyValue(AP_Page.layoutXProperty(), AP_Page.getLayoutX() + menuWidth) // Set x position the original x position
            );
            transition.setByX(125);
            isMenuOpen = false;
        } else {
            keyFrame = new KeyFrame(
                    Duration.millis(200),
                    new KeyValue(AP_Page.prefWidthProperty(), AP_Page.getPrefWidth() + menuWidth), // Set width to original width + MenuWidth
                    new KeyValue(AP_Page.layoutXProperty(), AP_Page.getLayoutX() - menuWidth) // Set x position to original x position - MenuWidth
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
        TP_Pages.getSelectionModel().select(TPP_Home);
    }
    @FXML
    private void On_B_Invoices_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_Invoices);
    }
    @FXML
    private void On_B_Customer_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_Customer);
    }
    @FXML
    private void On_B_Documents_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_Documents);
    }
    @FXML
    private void On_B_Evaluation_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_Evaluations);
    }
    @FXML
    private void On_B_Account_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_Account);
    }
    @FXML
    private void On_B_Exit_Pressed() {
        System.exit(0);
    }


    // HOMEPAGE CODE



    // INVOICES CODE
    @FXML
    private void On_B_NewInvoice_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_NewInvoice);
    }
    @FXML
    private void On_B_DeleteInvoice_Pressed() {
        System.out.println("Delete Invoice Pressed");
    }


    // CUSTOMER CODE

    // Add a new Customer
    @FXML
    private void On_B_NewCustomer_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_NewCustomer);
    }

    // Edit a existing Customer
    @FXML
    private void On_B_EditCustomer_Pressed() {
        System.out.println("Edit Customer Pressed");
    }

    // Delete a existing Customer
    @FXML
    private void On_B_DeleteCustomer_Pressed() {
        System.out.println("Delete Customer Pressed");
    }


}
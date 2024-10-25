package accounting.accounting;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

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

    // Methods for Button Pressed
    @FXML
    private void On_B_Menu_Pressed() {
        System.out.println("Home Button Pressed");
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
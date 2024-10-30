package accounting.accounting;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.awt.*;
import java.awt.TextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

    // Comboboxes
    @FXML
    private ComboBox<String> CB_Gender;
    @FXML
    private ComboBox<String> CB_Gender2;
    @FXML
    private ComboBox<String> CB_Title;
    @FXML
    private ComboBox<String> CB_Title2;
    @FXML
    private ComboBox<String> CB_Country;

    // Labels
    @FXML
    private Label L_DSGVO;
    @FXML
    private Label L_AcceptDSGVO;

    // Checkbox
    @FXML
    private CheckBox CB_DSGVO;

    // TextFields
    @FXML
    private TextField TF_Company;
    @FXML
    private TextField TF_Street;
    @FXML
    private TextField TF_HouseNumber;
    @FXML
    private TextField TF_Location;
    @FXML
    private TextField TF_PostalCode;
    @FXML
    private TextField TF_Firstname;
    @FXML
    private TextField TF_Lastname;
    @FXML
    private TextField TF_TelNr;
    @FXML
    private TextField TF_Email;
    @FXML
    private TextField TA_Others;
    @FXML
    private TextField TF_Firstname2;
    @FXML
    private TextField TF_Lastname2;
    @FXML
    private TextField TF_TelNr2;
    @FXML
    private TextField TF_Email2;

    // Other

    private boolean isMenuOpen = false;
    Database DB = new Database();
    Functions FC = new Functions();

    public Controller() {
        DB.ConnectToDatabase();
    }

    @FXML
    public void initialize() {
        // Tooltips
        L_AcceptDSGVO.setTooltip(new Tooltip("Open as PDF"));

        // Labels
        L_DSGVO.setText("GDPR Compliance Statement \n" +
                "By accepting this GDPR compliance statement, you agree to the following terms regarding the handling of your data");

        // Comboboxes

        // Insert all genderTypes into Combobox "CB_Gender" and "CB_Gender2"
        CB_Gender.getItems().add("Choose");
        CB_Gender.getSelectionModel().selectFirst();
        CB_Gender.getItems().addAll(DB.GetGenderTypes());

        CB_Gender2.getItems().add("Choose");
        CB_Gender2.getSelectionModel().selectFirst();
        CB_Gender2.getItems().addAll(DB.GetGenderTypes());

        // Insert all Titles into Combobox "CB_Title" and "CB_Title2"

        CB_Title.getItems().add("Choose");
        CB_Title.getSelectionModel().selectFirst();
        CB_Title.getItems().addAll(DB.GetTitles());

        CB_Title2.getItems().add("Choose");
        CB_Title2.getSelectionModel().selectFirst();
        CB_Title2.getItems().addAll(DB.GetTitles());

        // CB_Country
        CB_Country.getItems().add("Choose");
        CB_Country.getSelectionModel().selectFirst();
        CB_Country.getItems().addAll(DB.GetCountries());

    }

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
        // Change to New Customer Page
        TP_Pages.getSelectionModel().select(TPP_NewCustomer);
    }

    @FXML
    private void On_L_AcceptDSGVO_Pressed() throws IOException {
        File pdfFile = new File("C:\\APC\\KNAPP\\Berufsschule\\2LJ Berufsschule\\ITL 1-2 JRZ\\Aufgabe 9 - Buchhaltung\\Accounting\\documentation\\GDPR\\DSGVO.pdf");
        if (pdfFile.exists()) {
            Desktop.getDesktop().browse(pdfFile.toURI());
        } else {
            System.out.println("DSGVO.pdf not found");
        }
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

    // NEW CUSTOMER CODE
    @FXML
    private void On_B_SaveNewCustomer_Pressed() {
        System.out.println("Save New Customer Pressed");
    }
    @FXML
    private void On_B_CancelNewCustomer_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_Customer);
    }
    @FXML
    private void On_B_ClearAllNewCustomer_Pressed() {
        System.out.println("Save New Customer Pressed");
    }


}
package accounting.accounting;

import javafx.animation.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    private TextArea TA_Others;
    @FXML
    private TextField TF_Firstname2;
    @FXML
    private TextField TF_Lastname2;
    @FXML
    private TextField TF_TelNr2;
    @FXML
    private TextField TF_Email2;

    // TableView
    @FXML
    private TableView<String> TV_ShowCustomer;

    // Table View -> Table Columns
    @FXML
    private TableColumn<String, String> TC_Title;
    @FXML
    private TableColumn<String, String> TC_Firstname;
    @FXML
    private TableColumn<String, String> TC_Lastname;
    @FXML
    private TableColumn<String, String> TC_EMail;
    @FXML
    private TableColumn<String, String> TC_PhoneNr;
    @FXML
    private TableColumn<String, String> TC_Gender;
    @FXML
    private TableColumn<String, String> TC_CompanyName;
    @FXML
    private TableColumn<String, String> TC_Other;

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

        // Insert all Customers into TableView "TV_ShowCustomer"
        InsertTV_ShowCustomer();
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
        DB.InsertNewCompany(TF_Company.getText(), TF_Street.getText(), Integer.parseInt(TF_HouseNumber.getText()), TF_Location.getText(), Integer.parseInt(TF_PostalCode.getText()), CB_Country.getValue(), TA_Others.getText());
        DB.InsertNewContactPerson(1, CB_Title.getValue(), CB_Gender.getValue(), TF_Firstname.getText(), TF_Lastname.getText(), TF_Email.getText(), TF_TelNr.getText(), TF_Company.getText());
        if (!TF_Firstname2.getText().isEmpty() && !TF_Lastname2.getText().isEmpty()) {
            DB.InsertNewContactPerson(2, CB_Title2.getValue(), CB_Gender2.getValue(), TF_Firstname2.getText(), TF_Lastname2.getText(), TF_Email2.getText(), TF_TelNr2.getText(), TF_Company.getText());
        }
        // Refresh TableView TV_ShowCustomer
        InsertTV_ShowCustomer();
    }
    @FXML
    private void On_B_CancelNewCustomer_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_Customer);
    }
    @FXML
    private void On_B_ClearAllNewCustomer_Pressed() {
        System.out.println("Save New Customer Pressed");
    }



    // INITIALIZATION CODE
    private void InsertTV_ShowCustomer() {
        // Clear existing data
        TV_ShowCustomer.getItems().clear();

        // Set cell value factories for each column
        TC_Title.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 1 ? parts[1] : "");
        });
        TC_Firstname.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 2 ? parts[2] : "");
        });
        TC_Lastname.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 3 ? parts[3] : "");
        });
        TC_EMail.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 4 ? parts[4] : "");
        });
        TC_PhoneNr.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 5 ? parts[5] : "");
        });
        TC_Gender.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 6 ? parts[6] : "");
        });
        TC_CompanyName.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 7 ? parts[7] : "");
        });

        TC_Other.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 8 ? parts[8] : "");
        });

        // Fetch customer data from the database
        ArrayList<String> customers = DB.GetAllCustomers();
        System.out.println(customers);

        // Add data to the TableView
        TV_ShowCustomer.setItems(FXCollections.observableArrayList(customers));
    }


}
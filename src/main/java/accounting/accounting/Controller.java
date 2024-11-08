package accounting.accounting;

import javafx.animation.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.scene.control.Button;

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
    private Tab TPP_Categories;
    @FXML
    private Tab TPP_Evaluations;
    @FXML
    private Tab TPP_Account;
    @FXML
    private Tab TPP_NewInvoice;
    @FXML
    private Tab TPP_NewCustomer;
    @FXML
    private Tab TPP_NewCategory;

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

    // New Category Combobox
    @FXML
    private ComboBox<String> CB_CategoryType;

    // New Invoice Combobox
    @FXML
    private ComboBox<String> CB_NewInvoiceType;
    @FXML
    private ComboBox<String> CB_NewInvoiceCustomer;

    // Labels
    @FXML
    private Label L_DSGVO;
    @FXML
    private Label L_AcceptDSGVO;

    // Home Labels
    @FXML
    private Label L_ExpensesHome;
    @FXML
    private Label L_IncomeHome;
    @FXML
    private Label L_DifferenceHome;

    // Invoice Combobox
    @FXML
    private CheckBox CB_InvoicePaid;
    @FXML
    private CheckBox CB_ShowPaidInvoices;

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

    // New Category
    @FXML
    private TextField TF_CategoryName;

    // New Invoice
    @FXML
    private TextField TF_NewInvoiceDescription;
    @FXML
    private TextField TF_NewInvoiceAmount;
    @FXML
    private TextField TF_NewInvoiceName;

    // Date Picker
    // New Invoice
    @FXML
    private DatePicker DP_NewInvoiceDate;

    // New Category Text Area
    @FXML
    private TextArea TA_NewCG_Other;

    // TableView
    @FXML
    private TableView<String> TV_ShowCustomer;

    // Invoices
    @FXML
    private TableView<String> TV_ShowInvoices;

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

    // Invoices
    @FXML
    private TableColumn<String, String> TC_Category;
    @FXML
    private TableColumn<String, String> TC_Name;
    @FXML
    private TableColumn<String, String> TC_Description;
    @FXML
    private TableColumn<String, String> TC_Amount;
    @FXML
    private TableColumn<String, String> TC_Date;
    @FXML
    private TableColumn<String, String> TC_Timestamp;
    @FXML
    private TableColumn<String, String> TC_AlreadyPaid;
    @FXML
    private TableColumn<String, String> TC_CompanyNameInvoice;

    // Categories
    @FXML
    private TableView<String> TV_ShowCategories;
    @FXML
    private TableColumn<String, String> TC_CategoryType;
    @FXML
    private TableColumn<String, String> TC_CategoryName;
    @FXML
    private TableColumn<String, String> TC_CategoryOther;

    // Invoices - Vbox
    @FXML
    private VBox VB_ShowOpenInvoices;

    // TextField - Invoices
    @FXML
    private TextField TF_SearchTable;

    // BarChar - Home
    @FXML
    private BarChart<String, Number> BC_HomeChart;

    // DatePicker - Home
    @FXML
    private DatePicker DP_StartDate;
    @FXML
    private DatePicker DP_EndDate;

    // TextFields - Home
    @FXML
    private TextField TF_StartAmount;
    @FXML
    private TextField TF_EndAmount;

    // Label - Home
    @FXML
    private Label L_BestYearCategory;
    @FXML
    private Label L_BestYearAmount;
    @FXML
    private Label L_WorstYearCategory;
    @FXML
    private Label L_WorstYearAmount;
    @FXML
    private Label L_BestMonthCategory;
    @FXML
    private Label L_BestMonthAmount;
    @FXML
    private Label L_WorstMonthCategory;
    @FXML
    private Label L_WorstMonthAmount;
    @FXML
    private Label L_BestCustomerEntry;
    @FXML
    private Label L_MostUsedCategoryEntry;
    @FXML
    private Label L_LeastUsedCategoryEntry;

    // Combobox - Home
    @FXML
    private ComboBox<String> CB_WhatToShow_Settings;

    @FXML
    private CheckBox CB_DSGVO;

    // Other

    private boolean isMenuOpen = false;
    Database DB = new Database();

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

        // Insert into TableViews
        InsertTV_ShowCustomer();
        InsertTV_ShowInvoices(DB.GetAllInvoices());
        InsertTV_Categories();

        // Load CB_WhatToShow_Settings
        Initialize_CB_WhatToShow_Settings();

        // Insert into BarChart
        initializeBarChart(DB.GetAllInvoicesWithCategory(CB_ShowPaidInvoices.isSelected()));

        initializeOpenInvoices();

        // Overview Statistics
        InitializeOverViewStatistics();


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

        // Fill New Category Type Combobox with Income and Expenses
        CB_CategoryType.getItems().add("Choose");
        CB_CategoryType.getSelectionModel().selectFirst();
        CB_CategoryType.getItems().addAll("Income", "Expenses");

        // Get All Company Names from ck_companydetails and fill CB_NewInvoiceCustomer with them
        CB_NewInvoiceCustomer.getItems().add("Choose");
        CB_NewInvoiceCustomer.getSelectionModel().selectFirst();
        CB_NewInvoiceCustomer.getItems().addAll(DB.GetAllCompanyNames());

        // Get All Categories from ck_categories and fill CB_NewInvoiceType with them
        CB_NewInvoiceType.getItems().add("Choose");
        CB_NewInvoiceType.getSelectionModel().selectFirst();
        CB_NewInvoiceType.getItems().addAll(DB.GetNameFromCategories());
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
            transition.setByX(122);
            isMenuOpen = false;
        } else {
            keyFrame = new KeyFrame(
                    Duration.millis(200),
                    new KeyValue(AP_Page.prefWidthProperty(), AP_Page.getPrefWidth() + menuWidth), // Set width to original width + MenuWidth
                    new KeyValue(AP_Page.layoutXProperty(), AP_Page.getLayoutX() - menuWidth) // Set x position to original x position - MenuWidth
            );
            transition.setByX(-122);
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
    private void On_B_Categories_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_Categories);
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
    @FXML
    private void On_CB_ShowPaidInvoices_Pressed() {
        initializeBarChart(DB.GetAllInvoicesWithCategory(CB_ShowPaidInvoices.isSelected()));
    }

    @FXML
    private void On_B_FilterForDateRange_Pressed() {
        initializeBarChart(DB.GetInvoicesByDateRange(Date.valueOf(DP_StartDate.getValue()), Date.valueOf(DP_EndDate.getValue())));

        // Filter the TV_ShowInvoices as well with the same date range
        TV_ShowInvoices.getItems().clear();
        ArrayList<String> invoices = DB.GetInvoicesByDateRange_TABLE(Date.valueOf(DP_StartDate.getValue()), Date.valueOf(DP_EndDate.getValue()));
        TV_ShowInvoices.setItems(FXCollections.observableArrayList(invoices));
    }

    @FXML
    private void On_B_FilterForAmountRange_Pressed() {
        initializeBarChart(DB.GetInvoicesByAmountRange(Double.parseDouble(TF_StartAmount.getText()), Double.parseDouble(TF_EndAmount.getText())));

        // Filter the TV_ShowInvoices as well with the same amount range
        TV_ShowInvoices.getItems().clear();
        ArrayList<String> invoices = DB.GetInvoicesByAmountRange_TABLE(Double.parseDouble(TF_StartAmount.getText()), Double.parseDouble(TF_EndAmount.getText()));
        InsertTV_ShowInvoices(invoices);
        System.out.println(invoices);
        TV_ShowInvoices.setItems(FXCollections.observableArrayList(invoices));
    }

    @FXML
    private void On_B_ResetAllFilter_Pressed() {
        initializeBarChart(DB.GetAllInvoicesWithCategory(CB_ShowPaidInvoices.isSelected()));
        TV_ShowInvoices.getItems().clear();
        InsertTV_ShowInvoices(DB.GetAllInvoices());
    }

    @FXML
    private void On_CB_WhatToShow_Settings_TextChanged() {
        initializeBarChart(DB.GetInvoicesByYear(CB_WhatToShow_Settings.getValue()));
        System.out.println(CB_WhatToShow_Settings.getValue());
    }


    // INVOICES CODE
    @FXML
    private void On_B_NewInvoice_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_NewInvoice);
    }

    @FXML
    private void On_B_ResetFilter_Pressed() {
        initializeBarChart(DB.GetAllInvoicesWithCategory(CB_ShowPaidInvoices.isSelected()));
        TV_ShowInvoices.getItems().clear();
        InsertTV_ShowInvoices(DB.GetAllInvoices());
    }

    @FXML
    private void On_TF_SearchTable_TextChanged() {
        System.out.println("Hallo");
    }

    // DELETE AN INVOICE
    @FXML
    private void On_B_DeleteInvoice_Pressed() {

        // Check if the invoice is the same date as today
        String selectedInvoice = TV_ShowInvoices.getSelectionModel().getSelectedItem();
        String[] parts = selectedInvoice.split(" - ");
        String invoiceDate = parts[4];

        // Check if the invoice is from today
        if (!invoiceDate.equals(String.valueOf(LocalDate.now()))) {
            Functions.ShowPopup("E", "Delete Invoice", "You can't delete an invoice that is not from today");
            return;
        }

        // Show a Popup to confirm the deletion
        boolean PopupResponse = Functions.ShowPopup("W","Delete Invoice", "Are you sure you want to delete this invoice?");
        if (!PopupResponse) {
            return;
        }

        // Get The Category_ID, Name and Amount
        String invoiceID = parts[0];
        String invoiceName = parts[1];
        String invoiceAmount = parts[3];

        // Parse invoiceAmount to double
        double amount = Double.parseDouble(invoiceAmount);

        // Delete the invoice from the database
        DB.DeleteInvoice(invoiceID, invoiceName, amount);

        InsertTV_ShowInvoices(DB.GetAllInvoices());
        initializeOpenInvoices();
    }

    // NEW INVOICE CODE
    @FXML
    private void On_CB_NewInvoiceType_Actioned() {
        String Category = CB_NewInvoiceType.getValue();
        System.out.println(Category);
        System.out.println(DB.GetCategoryType(Category));

        CB_NewInvoiceCustomer.setDisable(DB.GetCategoryType(Category).equalsIgnoreCase("Expenses"));
    }

    // SAVE NEW INVOICE
    @FXML
    private void On_B_SaveNewInvoice_Pressed() {
        String CompanyNameToPass;
        if (CB_NewInvoiceCustomer.getValue().equalsIgnoreCase("Choose") || CB_NewInvoiceCustomer.getValue().isEmpty()) {
            CompanyNameToPass = "None";
        } else {
            CompanyNameToPass = CB_NewInvoiceCustomer.getValue();
        }

        if(TF_NewInvoiceAmount.getText().equals("0") || TF_NewInvoiceAmount.getText().contains("-")) {
            Functions.ShowPopup("E", "Error Creating New Invoice", "The Amount must be greater than 0");
        } else if(TF_NewInvoiceAmount.getText().matches("[0-9]+")) {
            Functions.ShowPopup("E", "Error Creating New Invoice", "The Amount must be a number");
        } else if(TF_NewInvoiceName.getText().isEmpty() || TF_NewInvoiceDescription.getText().isEmpty() || TF_NewInvoiceAmount.getText().isEmpty() || DP_NewInvoiceDate.getValue() == null || CB_NewInvoiceType.getValue().equalsIgnoreCase("Choose")) {
            Functions.ShowPopup("E", "Error Creating New Invoice", "Please fill in all necessary fields");
        } else {
            boolean ReturnValue = DB.InsertNewInvoice(CB_NewInvoiceType.getValue(), TF_NewInvoiceName.getText(), TF_NewInvoiceDescription.getText(), TF_NewInvoiceAmount.getText(), Date.valueOf(DP_NewInvoiceDate.getValue()), CB_InvoicePaid.isSelected(), CompanyNameToPass);

            if (ReturnValue) {
                InsertTV_ShowInvoices(DB.GetAllInvoices());
                Functions.ShowPopup("I", "Create New Invoice", "The new invoice has been saved successfully");
                // Reset Values
                CB_NewInvoiceType.getSelectionModel().selectFirst();
                CB_NewInvoiceCustomer.getSelectionModel().selectFirst();
                TF_NewInvoiceName.clear();
                TF_NewInvoiceDescription.clear();
                TF_NewInvoiceAmount.clear();
                DP_NewInvoiceDate.setValue(LocalDate.now());
                CB_InvoicePaid.setSelected(false);

                initializeBarChart(DB.GetAllInvoicesWithCategory(CB_ShowPaidInvoices.isSelected()));
                initializeOpenInvoices();
            } else {
                Functions.ShowPopup("E", "Error Creating New Invoice", "The new invoice could not be saved");
            }
        }

    }

    @FXML
    private void On_B_CancelNewInvoice_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_Invoices);
    }

    // NEW CATEGORY CODE
    @FXML
    private void On_B_SaveCategory_Pressed() {
        if(TF_CategoryName.getText().isEmpty() || CB_CategoryType.getValue().equalsIgnoreCase("Choose")) {
            Functions.ShowPopup("E", "Error Creating New Category", "Please fill in all necessary fields");
        } else {
            boolean ReturnValue = DB.InsertNewCategory(TF_CategoryName.getText(), CB_CategoryType.getValue(), TA_NewCG_Other.getText());
            if (!ReturnValue) {
                Functions.ShowPopup("E", "Error Creating New Category", "The Category could not be saved");
            } else {
                Functions.ShowPopup("I", "Create New Category", "The Category was saved successfully");
                InsertTV_Categories();
                TF_CategoryName.clear();
                CB_CategoryType.getSelectionModel().selectFirst();
                TA_NewCG_Other.clear();
            }
        }
    }
    @FXML
    private void On_B_CancelNewCategory_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_Categories);
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
        // Get Company Name from Selected Customer
        String Selected = TV_ShowCustomer.getSelectionModel().getSelectedItem();
        String[] parts = Selected.split(" - ");
        String CompanyName = parts[7];

        String ReturnValue = DB.DeleteCustomer(CompanyName);
        if (ReturnValue.contains("There are contact")) {
            Functions.ShowPopup("E", "Delete Customer", ReturnValue);
        } else {
            Functions.ShowPopup("I", "Delete Customer", ReturnValue);
        }
        InsertTV_ShowCustomer();
    }

    // NEW CUSTOMER CODE
    @FXML
    private void On_B_SaveNewCustomer_Pressed() {
        if (!CB_DSGVO.isSelected()) {
            Functions.ShowPopup("E", "Error Creating New Customer", "You have to accept the GDPR Compliance Statement");
            return;
            // Check if all necessary fields are filled (fields with *)
        } else if(TF_Company.getText().isEmpty() || TF_Street.getText().isEmpty() || TF_HouseNumber.getText().isEmpty() || TF_Location.getText().isEmpty() || TF_PostalCode.getText().isEmpty() || CB_Country.getValue().equalsIgnoreCase("Choose")){
            Functions.ShowPopup("E", "Error Creating New Customer", "Please fill in all necessary fields marked with *");
            return;
        } else if(TF_HouseNumber.getText().matches("[0-9]+") && TF_PostalCode.getText().matches("[0-9]+")) {
            Functions.ShowPopup("E", "Error Creating New Customer", "House Number and Postal Code must be a number");
            return;
        }

        boolean ReturnValue = DB.InsertNewCompany(TF_Company.getText(), TF_Street.getText(), Integer.parseInt(TF_HouseNumber.getText()), TF_Location.getText(), Integer.parseInt(TF_PostalCode.getText()), CB_Country.getValue(), TA_Others.getText());
        if (!ReturnValue) {
            Functions.ShowPopup("E", "Error Creating New Customer", "The Company could not be saved");
            return;
        }
        ReturnValue = DB.InsertNewContactPerson(1, CB_Title.getValue(), CB_Gender.getValue(), TF_Firstname.getText(), TF_Lastname.getText(), TF_Email.getText(), TF_TelNr.getText(), TF_Company.getText());

        if (!ReturnValue) {
            Functions.ShowPopup("E", "Error Creating New Customer", "The Contact Person could not be saved");
            return;
        }

        if (!TF_Firstname2.getText().isEmpty() && !TF_Lastname2.getText().isEmpty()) {
            ReturnValue = DB.InsertNewContactPerson(2, CB_Title2.getValue(), CB_Gender2.getValue(), TF_Firstname2.getText(), TF_Lastname2.getText(), TF_Email2.getText(), TF_TelNr2.getText(), TF_Company.getText());
            if (!ReturnValue) {
                Functions.ShowPopup("E", "Error Creating New Customer", "The Deputy Contact Person could not be saved");
                return;
            }
        }

        // Show Popup
        Functions.ShowPopup("I", "Create New Customer", "The Customer was saved successfully");
        // Clear all

        TF_Company.clear();
        TF_Street.clear();
        TF_HouseNumber.clear();
        TF_Location.clear();
        TF_PostalCode.clear();
        CB_Country.getSelectionModel().selectFirst();
        CB_DSGVO.setSelected(false);
        CB_Gender.getSelectionModel().selectFirst();
        CB_Title.getSelectionModel().selectFirst();
        CB_Gender2.getSelectionModel().selectFirst();
        CB_Title2.getSelectionModel().selectFirst();
        TF_Firstname.clear();
        TF_Lastname.clear();
        TF_Email.clear();
        TF_TelNr.clear();
        TF_Firstname2.clear();
        TF_Lastname2.clear();
        TF_Email2.clear();
        TF_TelNr2.clear();
        TA_Others.clear();

        // Refresh TableView TV_ShowCustomer
        InsertTV_ShowCustomer();
    }
    @FXML
    private void On_B_CancelNewCustomer_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_Customer);
    }
    @FXML
    private void On_B_ClearAllNewCustomer_Pressed() {
        // Clear ALL fields and reset Comboboxes to "Choose"
        TF_Company.clear();
        TF_Street.clear();
        TF_HouseNumber.clear();
        TF_Location.clear();
        TF_PostalCode.clear();
        CB_Country.getSelectionModel().selectFirst();
        CB_DSGVO.setSelected(false);

        CB_Gender.getSelectionModel().selectFirst();
        CB_Title.getSelectionModel().selectFirst();
        CB_Gender2.getSelectionModel().selectFirst();
        CB_Title2.getSelectionModel().selectFirst();

        TF_Firstname.clear();
        TF_Lastname.clear();
        TF_Email.clear();
        TF_TelNr.clear();
        TF_Firstname2.clear();
        TF_Lastname2.clear();
        TF_Email2.clear();
        TF_TelNr2.clear();
        TA_Others.clear();

    }

    // CATEGORY CODE
    @FXML
    private void On_B_NewCategory_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_NewCategory);
    }
    @FXML
    private void On_B_DeleteCategory_Pressed() {
        // Get Selected Category
        String selectedCategory = TV_ShowCategories.getSelectionModel().getSelectedItem();
        String[] parts = selectedCategory.split(" - ");
        String Name = parts[1];

        // Call Popup Window to check
        boolean PopupResponse = Functions.ShowPopup("W", "Delete Category", "Are you sure you want to delete this category?");
        if (!PopupResponse) {
            return;
        }

        String ReturnValue = DB.DeleteCategory(Name);

        // If "cannot" is in ReturnValue
        if (ReturnValue.contains("cannot")) {
            Functions.ShowPopup("E", "Delete Category", ReturnValue);
        } else {
            Functions.ShowPopup("I", "Delete Category", ReturnValue);
        }

        InsertTV_Categories();
    }

    @FXML
    private void On_B_EditCategory_Pressed() {
        System.out.println("Edit Category Pressed");
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

        // Add data to the TableView
        TV_ShowCustomer.setItems(FXCollections.observableArrayList(customers));
    }

    // HOME - OVERVIEW STATISTICS
    private void InitializeOverViewStatistics() {
        // Get GetBestYear as ArrayList<String>
        ArrayList<String> BestYear = DB.GetBestYear();
        ArrayList<String> WorstYear = DB.GetWorstYear();
        ArrayList<String> BestMonth = DB.GetBestMonth();
        ArrayList<String> WorstMonth = DB.GetWorstMonth();
        String BestCutomer = DB.GetBestCustomer();
        String MostUsedCategory = DB.Get_MostUsed_Category();
        String LeastUsedCategory = DB.Get_LeastUsed_Category();

        // Extract the year from the ArrayList
        String BestYearString = BestYear.getFirst();
        String WorstYearString = WorstYear.getFirst();

        String BestMonthString = BestMonth.getFirst();
        String WorstMonthString = WorstMonth.getFirst();

        // Extract the Amount from ArrayList
        String BestYearAmount = BestYear.get(1);
        String WorstYearAmount = WorstYear.get(1);

        String BestMonthAmount = BestMonth.get(1);
        String WorstMonthAmount = WorstMonth.get(1);

        // Dont show month: 2 or month 1: but the whole month NAME like august, december or somethin - Format month into month name
        BestMonthString = Functions.FormatMonth(BestMonthString);
        WorstMonthString = Functions.FormatMonth(WorstMonthString);

        // Format the Amounts
        String Formatted_BestYearAmount = Functions.formatNumber(Double.parseDouble(BestYearAmount));
        String Formatted_WorstYearAmount = Functions.formatNumber(Double.parseDouble(WorstYearAmount));

        String Formatted_BestMonthAmount = Functions.formatNumber(Double.parseDouble(BestMonthAmount));
        String Formatted_WorstMonthAmount = Functions.formatNumber(Double.parseDouble(WorstMonthAmount));

        // Set Labels
        L_BestYearCategory.setText("Best Year: " + BestYearString);
        L_BestYearAmount.setText(Formatted_BestYearAmount + " €");

        L_WorstYearCategory.setText("Worst Year: " + WorstYearString);
        L_WorstYearAmount.setText(Formatted_WorstYearAmount + " €");

        L_BestMonthCategory.setText("Best Month: " + BestMonthString);
        L_BestMonthAmount.setText(Formatted_BestMonthAmount + " €");

        L_WorstMonthCategory.setText("Worst Month: " + WorstMonthString);
        L_WorstMonthAmount.setText(Formatted_WorstMonthAmount + " €");

        L_BestCustomerEntry.setText(BestCutomer);
        L_MostUsedCategoryEntry.setText(MostUsedCategory);
        L_LeastUsedCategoryEntry.setText(LeastUsedCategory);
    }

    private void Initialize_CB_WhatToShow_Settings() {
        // Get All Years from Database
        ArrayList<String> years = DB.GetAllYearsFromInvoices();

        CB_WhatToShow_Settings.getItems().addAll(years);
        CB_WhatToShow_Settings.getSelectionModel().selectFirst();
    }


    // INSERT INTO TABLEVIEW
    private void InsertTV_ShowInvoices(ArrayList<String> invoices) {
        TV_ShowInvoices.getItems().clear();

        TC_Category.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 0 ? parts[0] : "");
        });

        TC_Name.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 1 ? parts[1] : "");
        });

        TC_Description.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 2 ? parts[2] : "");
        });

        TC_Amount.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 3 ? parts[3] : "");
        });

        TC_CompanyNameInvoice.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            String companyName = parts.length > 4 ? parts[4] : "";
            if ("-1".equals(companyName) || "null".equals(companyName)) {
                companyName = " ";
            }
            return new SimpleStringProperty(companyName);
        });

        TC_Date.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 5 ? parts[5] : "");
        });

        TC_Timestamp.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 6 ? parts[6] : "");
        });

        TC_AlreadyPaid.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 7 && parts[7].equals("1") ? "Yes" : "No");
        });

        TV_ShowInvoices.setItems(FXCollections.observableArrayList(invoices));
    }

    private void InsertTV_Categories() {
        TC_CategoryType.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 0 ? parts[0] : "");
        });

        TC_CategoryName.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 1 ? parts[1] : "");
        });

        TC_CategoryOther.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 2 ? parts[2] : "");
        });

        ArrayList<String> categories = DB.GetAllCategories();
        TV_ShowCategories.setItems(FXCollections.observableArrayList(categories));
    }

    private void initializeBarChart(ArrayList<String> records) {
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter monthDisplayFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        XYChart.Series<String, Number> incomeSeries = new XYChart.Series<>();
        XYChart.Series<String, Number> expenseSeries = new XYChart.Series<>();
        incomeSeries.setName("Income");
        expenseSeries.setName("Expenses");

        double totalIncome = 0;
        double totalExpenses = 0;

        boolean showByYear = "All".equals(CB_WhatToShow_Settings.getValue());
        int selectedYear = showByYear ? 0 : Integer.parseInt(CB_WhatToShow_Settings.getValue());

        // Hashmaps zur Speicherung von Daten pro Monat oder Jahr
        Map<String, Double> incomeData = new HashMap<>();
        Map<String, Double> expenseData = new HashMap<>();

        // Schritt 1: Daten verarbeiten und in HashMaps speichern
        for (String record : records) {
            String[] parts = record.split(" - ");
            if (parts.length == 4) {
                double amount = Double.parseDouble(parts[1].replace(",", "."));
                LocalDate date = LocalDate.parse(parts[2]);
                String category = parts[3];

                String key = showByYear ? String.valueOf(date.getYear()) : date.format(monthFormatter);

                if (showByYear || date.getYear() == selectedYear) {
                    if (category.equals("Income")) {
                        incomeData.put(key, incomeData.getOrDefault(key, 0.0) + amount);
                        totalIncome += amount;
                    } else if (category.equals("Expenses")) {
                        expenseData.put(key, expenseData.getOrDefault(key, 0.0) + amount);
                        totalExpenses += amount;
                    }
                }
            }
        }

        // Schritt 2: Sicherstellen, dass alle Monate oder Jahre enthalten sind
        if (showByYear) {
            // Jahre darstellen
            for (String year : incomeData.keySet()) {
                double income = incomeData.getOrDefault(year, 0.0);
                double expenses = expenseData.getOrDefault(year, 0.0);

                incomeSeries.getData().add(new XYChart.Data<>(year, income));
                expenseSeries.getData().add(new XYChart.Data<>(year, expenses));
            }
        } else {
            // Monate für das gewählte Jahr darstellen
            for (int month = 1; month <= 12; month++) {
                String monthKey = String.format("%02d", month);
                String monthDisplay = LocalDate.of(selectedYear, month, 1).format(monthDisplayFormatter);

                double income = incomeData.getOrDefault(monthKey, 0.0);
                double expenses = expenseData.getOrDefault(monthKey, 0.0);

                incomeSeries.getData().add(new XYChart.Data<>(monthDisplay, income));
                expenseSeries.getData().add(new XYChart.Data<>(monthDisplay, expenses));
            }
        }

        // Schritt 3: BarChart aktualisieren
        BC_HomeChart.getData().clear();
        BC_HomeChart.getData().addAll(incomeSeries, expenseSeries);

        // Schritt 4: Summen und Differenz berechnen und anzeigen
        double difference = totalIncome - totalExpenses;
        String FormattedTotalIncome = Functions.formatNumber(totalIncome);
        String FormattedTotalExpenses = Functions.formatNumber(totalExpenses);
        String FormattedDifference = Functions.formatNumber(difference);

        L_IncomeHome.setText(String.format("Total Income: %s €", FormattedTotalIncome));
        L_ExpensesHome.setText(String.format("Total Expenses: %s €", FormattedTotalExpenses));
        L_DifferenceHome.setText(String.format("Difference: %s €", FormattedDifference));
    }

    private void initializeOpenInvoices() {
        // Fill into open Invoices VBox
        VB_ShowOpenInvoices.getChildren().clear();
        ArrayList<String> openInvoices = DB.GetOpenInvoices();
        // If there are no open invoices, show a message
        if (openInvoices.isEmpty()) {
            Label label = new Label("There are no open invoices");
            label.setPrefWidth(300);
            label.setPrefHeight(25);
            label.setStyle("-fx-padding: 0 0 0 15px;");
            VB_ShowOpenInvoices.getChildren().add(label);
            return;
        }

        // I want to show all Invoices untereinander mit jeweils einem Button rechts daneben, dass wenn ich darauf drücke die richtige invoice auf der linken seite ausgeben wird

        for (String invoice : openInvoices) {
            String[] parts = invoice.split(" - ");
            String invoiceName = parts[0];
            String invoiceAmount = parts[1];
            String invoiceCompany = parts[2];

            // Create a new HBox
            HBox hbox = new HBox();
            hbox.setSpacing(5);
            hbox.setPrefWidth(150);

            // Create a new Button
            Button button = new Button("Pay");
            button.setOnAction(event -> {
                boolean ReturnValue = DB.SetInvoicePaid(invoice);
                if (ReturnValue) {
                    Functions.ShowPopup("I", "Pay Invoice", "The Invoice has been paid successfully");

                    // Remove this button and label from VBox
                    VB_ShowOpenInvoices.getChildren().remove(hbox);

                    InsertTV_ShowInvoices(DB.GetAllInvoices());
                    initializeBarChart(DB.GetAllInvoicesWithCategory(CB_ShowPaidInvoices.isSelected()));
                    initializeOpenInvoices();
                } else {
                    Functions.ShowPopup("E", "Pay Invoice", "The Invoice could not be paid");
                }
            });
            button.setPrefWidth(50);
            button.setPrefHeight(25);

            GridPane.setHgrow(button, Priority.ALWAYS);

            // Create a new Label
            Label label = new Label(invoiceName + " - " + invoiceAmount + "€ - " + invoiceCompany);
            label.setPrefWidth(300);
            label.setPrefHeight(25);
            // 15px Margin on left side
            label.setStyle("-fx-padding: 0 0 0 15px;");

            // Add the label and button to the HBox
            hbox.getChildren().addAll(label, button);

            // Add the HBox to the VBox
            VB_ShowOpenInvoices.getChildren().add(hbox);
            VB_ShowOpenInvoices.setSpacing(15);
        }
    }
}

/// TODO NEXT TIME:
// -> Search through Invoices
// -> Error handling ( Wrong Inputs - Check if all fields are filled out etc... )
// -> Logo as Icon!!!!


/// Optional TODO:
// -> Maybe new Page "Support" or "Settings"
// -> Export to Excel or PDF or CSV
// -> Dark / Lightmode?
// -> Support via Email?
package accounting.accounting;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
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
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.*;

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

    // Categories
    @FXML
    private TableView<String> TV_ShowCategories;
    @FXML
    private TableColumn<String, String> TC_CategoryType;
    @FXML
    private TableColumn<String, String> TC_CategoryName;
    @FXML
    private TableColumn<String, String> TC_CategoryOther;

    // BarChar - Home
    @FXML
    private BarChart<String, Number> BC_HomeChart;

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
        InsertTV_ShowInvoices();
        InsertTV_Categories();

        // Insert into BarChart
        initializeBarChart();

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



    // INVOICES CODE
    @FXML
    private void On_B_NewInvoice_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_NewInvoice);
    }
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

        InsertTV_ShowInvoices();
    }

    // SAVE NEW INVOICE
    @FXML
    private void On_B_SaveNewInvoice_Pressed() {
        DB.InsertNewInvoice(CB_NewInvoiceType.getValue(), TF_NewInvoiceName.getText(), TF_NewInvoiceDescription.getText(), TF_NewInvoiceAmount.getText(), Date.valueOf(DP_NewInvoiceDate.getValue()));
        InsertTV_ShowInvoices();
    }

    @FXML
    private void On_B_CancelNewInvoice_Pressed() {
        TP_Pages.getSelectionModel().select(TPP_Invoices);
    }

    // NEW CATEGORY CODE
    @FXML
    private void On_B_SaveCategory_Pressed() {
        DB.InsertNewCategory(TF_CategoryName.getText(), CB_CategoryType.getValue(), TA_NewCG_Other.getText());
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

    // Controller.java
    private void InsertTV_ShowInvoices() {
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

        TC_Date.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 4 ? parts[4] : "");
        });

        TC_Timestamp.setCellValueFactory(data -> {
            String[] parts = data.getValue().split(" - ");
            return new SimpleStringProperty(parts.length > 5 ? parts[5] : "");
        });

        ArrayList<String> invoices = DB.GetAllInvoices();
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

    private void initializeBarChart() {
        // Format für die Monatsachse
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter monthDisplayFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");

        // Listen für Einnahmen und Ausgaben pro Monat
        XYChart.Series<String, Number> incomeSeries = new XYChart.Series<>();
        XYChart.Series<String, Number> expenseSeries = new XYChart.Series<>();
        incomeSeries.setName("Einnahmen");
        expenseSeries.setName("Ausgaben");

        // Summen für Einnahmen, Ausgaben und Differenz
        double totalIncome = 0;
        double totalExpenses = 0;

        // Daten aus der Datenbank abrufen
        ArrayList<String> records = DB.GetAllInvoicesWithCategory();

        // HashMap für die monatliche Speicherung von Einnahmen und Ausgaben
        Map<String, Double> monthlyIncome = new HashMap<>();
        Map<String, Double> monthlyExpenses = new HashMap<>();

        // Daten verarbeiten
        for (String record : records) {
            String[] parts = record.split(" - ");
            if (parts.length == 4) {
                double amount = Double.parseDouble(parts[1].replace(",", "."));
                LocalDate date = LocalDate.parse(parts[2]);
                String category = parts[3];

                // Monat im Format MM extrahieren
                String monthKey = date.format(monthFormatter);

                // Daten je nach Kategorie zu Einnahmen oder Ausgaben hinzufügen
                if (category.equals("Income")) {
                    monthlyIncome.put(monthKey, monthlyIncome.getOrDefault(monthKey, 0.0) + amount);
                    totalIncome += amount;
                } else if (category.equals("Expenses")) {
                    monthlyExpenses.put(monthKey, monthlyExpenses.getOrDefault(monthKey, 0.0) + amount);
                    totalExpenses += amount;
                }
            }
        }

        // Alle Monate im Jahr generieren (Januar bis Dezember)
        for (int month = 1; month <= 12; month++) {
            String monthKey = String.format("%02d", month); // MM-Format
            String monthDisplay = LocalDate.of(2024, month, 1).format(monthDisplayFormatter); // Monat in gewünschtem Format

            // Füge Einnahmen hinzu (oder 0, wenn keine vorhanden)
            double income = monthlyIncome.getOrDefault(monthKey, 0.0);
            incomeSeries.getData().add(new XYChart.Data<>(monthDisplay, income));

            // Füge Ausgaben hinzu (oder 0, wenn keine vorhanden)
            double expenses = monthlyExpenses.getOrDefault(monthKey, 0.0);
            expenseSeries.getData().add(new XYChart.Data<>(monthDisplay, expenses));
        }

        // Werte dem BarChart hinzufügen
        BC_HomeChart.getData().clear();
        BC_HomeChart.getData().addAll(incomeSeries, expenseSeries);

        // Gesamteinnahmen, Gesamtausgaben und Differenz anzeigen
        //double difference = totalIncome - totalExpenses;
        //lblTotalIncome.setText(String.format("Einnahmen: %.2f €", totalIncome));
        //lblTotalExpenses.setText(String.format("Ausgaben: %.2f €", totalExpenses));
        //lblDifference.setText(String.format("Differenz: %.2f €", difference));
    }
}
package accounting.accounting;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Database {
    private final String DatabaseName;
    private final String DatabaseType;
    private final String DatabaseHost;
    private final String DatabasePort;
    private final String DatabaseUser;
    private final String DatabasePassword;

    public Database() {
        this.DatabaseName = "ckaccounting";
        this.DatabaseType = "mysql";
        this.DatabaseHost = "localhost";
        this.DatabasePort = "3306";
        this.DatabaseUser = "root";
        this.DatabasePassword = "";
    }

    // Connection to Database
    protected Connection ConnectToDatabase() {
        Connection connection = null;
        try {
            String url = "jdbc:" + DatabaseType + "://" + DatabaseHost + ":" + DatabasePort + "/" + DatabaseName;
            connection = DriverManager.getConnection(url, DatabaseUser, DatabasePassword);
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage() + "\n\n");
            return null;
        }
    }
    //                                                                                                            SETTER

    // Insert New Customer into ck_customers
    protected boolean InsertNewCompany(String Name, String Street, int HouseNumber, String Location, int PostalCode, String Country, String Other) {
        String selectQuery = "SELECT * FROM ck_companydetails WHERE C_Name = ? AND C_Street = ?";
        String insertQuery = "INSERT INTO ck_companydetails (C_Name, C_Street, C_HouseNumber, C_Location, C_PostalCode, C_Country, C_Other) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectToDatabase();
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            selectStatement.setString(1, Name);
            selectStatement.setString(2, Street);
            ResultSet selectResult = selectStatement.executeQuery();

            if (selectResult.next()) {
                System.out.println("Customer already exists");
                return false;
            } else {
                insertStatement.setString(1, Name);
                insertStatement.setString(2, Street);
                insertStatement.setInt(3, HouseNumber);
                insertStatement.setString(4, Location);
                insertStatement.setInt(5, PostalCode);
                insertStatement.setString(6, Country);
                insertStatement.setString(7, Other);
                insertStatement.executeUpdate();
                System.out.println("Customer added");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Insert a new Contact Person into ck_contactpersons
    protected boolean InsertNewContactPerson(int Rank, String Title, String Gender, String FirstName, String LastName, String EMail, String TelNr, String CompanyName) {
        String selectQuery = "SELECT * FROM ck_contactperson WHERE Firstname = ? AND Lastname = ? AND EMail = ?";
        String getCompanyID = "SELECT Company_ID FROM ck_companydetails WHERE C_Name = ?";
        String getGenderID = "SELECT Gender_ID FROM ck_gender WHERE Type = ?";
        String insertQuery = "INSERT INTO ck_contactperson (Rank, Title, Gender_ID, Firstname, Lastname, EMail, PhoneNr, Company_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectToDatabase()) {
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            PreparedStatement getCompanyIDStatement = connection.prepareStatement(getCompanyID);
            PreparedStatement getGenderIDStatement = connection.prepareStatement(getGenderID);

            selectStatement.setString(1, FirstName);
            selectStatement.setString(2, LastName);
            selectStatement.setString(3, EMail);
            ResultSet selectResult = selectStatement.executeQuery();

            if (selectResult.next()) {
                System.out.println("Contact Person already exists");
                return false;
            } else {
                getCompanyIDStatement.setString(1, CompanyName);
                ResultSet companyIDResult = getCompanyIDStatement.executeQuery();
                companyIDResult.next();
                int CompanyID = companyIDResult.getInt("Company_ID");

                getGenderIDStatement.setString(1, Gender);
                ResultSet genderIDResult = getGenderIDStatement.executeQuery();
                genderIDResult.next();
                int GenderID = genderIDResult.getInt("Gender_ID");

                if (Title == null || Title.equals("Choose")) {
                    Title = ""; // or set a default value
                }

                insertStatement.setInt(1, Rank);
                insertStatement.setString(2, Title);
                insertStatement.setInt(3, GenderID);
                insertStatement.setString(4, FirstName);
                insertStatement.setString(5, LastName);
                insertStatement.setString(6, EMail);
                insertStatement.setString(7, TelNr);
                insertStatement.setInt(8, CompanyID);

                insertStatement.executeUpdate();
                System.out.println("Contact Person added");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected boolean InsertNewCategory(String Name, String Type, String Other) {
        String selectQuery = "SELECT * FROM ck_categories WHERE Name = ?";
        String selectCategoryType = "SELECT CGType_ID FROM ck_categorytypes WHERE Type = ?";
        String insertQuery = "INSERT INTO ck_categories (Name, Type, Others) VALUES (?, ?, ?)";

        try (Connection connection = ConnectToDatabase())
        {
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
             PreparedStatement selectCategoryTypeStatement = connection.prepareStatement(selectCategoryType);

            selectStatement.setString(1, Name);
            ResultSet selectResult = selectStatement.executeQuery();

            if (selectResult.next()) {
                System.out.println("Category already exists");
                return false;
            } else {
                selectCategoryTypeStatement.setString(1, Type);
                ResultSet categoryTypeResult = selectCategoryTypeStatement.executeQuery();
                categoryTypeResult.next();
                int CategoryTypeID = categoryTypeResult.getInt("CGType_ID");
                System.out.println(categoryTypeResult.next());

                insertStatement.setString(1, Name);
                insertStatement.setInt(2, CategoryTypeID);
                insertStatement.setString(3, Other);
                insertStatement.executeUpdate();
                System.out.println("Category added");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected boolean InsertNewInvoice(String Category, String Name, String Description, String Amount, Date Date, boolean isPaid, String CompanyName) {
        String selectQuery = "SELECT * FROM ck_invoices WHERE Name = ? AND Date = ?";
        String GetCategoryID = "SELECT Category_ID FROM ck_categories WHERE Name = ?";
        String GetCompanyID = "SELECT Company_ID FROM ck_companydetails WHERE C_Name = ?";
        String insertQuery = "INSERT INTO ck_invoices (Category_ID, Name, Description, Amount, Date, Timestamp, isPaid, Company_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Convert Amount to Double
        String AmoundWithDot = Amount.replace(",", ".");
        double AmountDouble = Double.parseDouble(AmoundWithDot);

        try (Connection connection = ConnectToDatabase()) {
            int CompanyName_ID;
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
             PreparedStatement getCategoryIDStatement = connection.prepareStatement(GetCategoryID);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
             PreparedStatement getCompanyIDStatement = connection.prepareStatement(GetCompanyID);

             if(!CompanyName.equalsIgnoreCase("None")) {
                 getCompanyIDStatement.setString(1, CompanyName);
                 ResultSet companyIDResult = getCompanyIDStatement.executeQuery();
                 companyIDResult.next();
                 CompanyName_ID = companyIDResult.getInt("Company_ID");
             } else {
                 CompanyName_ID = -1;
             }

            selectStatement.setString(1, Name);
            selectStatement.setDate(2, Date);
            ResultSet selectResult = selectStatement.executeQuery();

            getCategoryIDStatement.setString(1, Category);
            ResultSet categoryIDResult = getCategoryIDStatement.executeQuery();
            categoryIDResult.next();
            int CategoryID = categoryIDResult.getInt("Category_ID");

            if (selectResult.next()) {
                System.out.println("Invoice already exists");
                return false;
            } else {
                insertStatement.setInt(1, CategoryID);
                insertStatement.setString(2, Name);
                insertStatement.setString(3, Description);
                insertStatement.setDouble(4, AmountDouble);
                insertStatement.setDate(5, Date);
                insertStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                insertStatement.setInt(7, isPaid ? 1 : 0);
                insertStatement.setInt(8, CompanyName_ID);
                insertStatement.executeUpdate();
                System.out.println("Invoice added");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    //                                                                                                            EDITOR

    // Change isPaid from 0 to 1 in ck_invoices
    protected boolean SetInvoicePaid(String Invoice) {
        String updateQuery = "UPDATE ck_invoices SET isPaid = 1 WHERE Name = ? AND Amount = ?";
        // Split String to get Name and Date using -
        String[] InvoiceParts = Invoice.split(" - ");
        System.out.println(Arrays.toString(InvoiceParts));
        String InvoiceName = InvoiceParts[0];
        Double InvoiceAmount = Double.parseDouble(InvoiceParts[1].replace(",", "."));

        try (Connection connection = ConnectToDatabase()) {
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, InvoiceName);
            updateStatement.setDouble(2, InvoiceAmount);
            updateStatement.executeUpdate();
            System.out.println("Invoice is now paid");
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //                                                                                                            GETTER

    // Get all Types from ck_gender - Return ArrayList<String>
    protected ArrayList<String> GetGenderTypes() {
        ArrayList<String> genderTypes = new ArrayList<>();
        try {
            Connection connection = ConnectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ck_gender");

            // Add all types to ArrayList
            while (resultSet.next()) {
                genderTypes.add(resultSet.getString("Type"));
            }

            return genderTypes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetTitles() {
        ArrayList<String> TitleList = new ArrayList<>();
        try {
            Connection connection = ConnectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ck_titles");

            // Add all types to ArrayList
            while (resultSet.next()) {
                TitleList.add(resultSet.getString("Title"));
            }

            return TitleList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetCountries() {
        ArrayList<String> CountryList = new ArrayList<>();
        try {
            Connection connection = ConnectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ck_countries");

            // Add all types to ArrayList
            while (resultSet.next()) {
                CountryList.add(resultSet.getString("Country"));
            }

            return CountryList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Get All Customers from ck_contactperson with Company Name from ck_companydetails and Gender from ck_gender
    protected ArrayList<String> GetAllCustomers() {
        ArrayList<String> CustomerList = new ArrayList<>();
        try (Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ContactPers_ID, Title, Firstname, Lastname, EMail, PhoneNr, Gender, CompanyName, CompanyOther FROM ck_getcp_with_cpn");

            while (resultSet.next()) {
                String customer = resultSet.getString("ContactPers_ID") + " - " +
                        resultSet.getString("Title") + " - " +
                        resultSet.getString("Firstname") + " - " +
                        resultSet.getString("Lastname") + " - " +
                        resultSet.getString("EMail") + " - " +
                        resultSet.getString("PhoneNr") + " - " +
                        resultSet.getString("Gender") + " - " +
                        resultSet.getString("CompanyName") + " - " +
                        resultSet.getString("CompanyOther");
                CustomerList.add(customer);
            }
            return CustomerList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetAllCompanyNames() {
        ArrayList<String> CompanyList = new ArrayList<>();
        try(Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT C_Name FROM ck_companydetails");

            while (resultSet.next()) {
                CompanyList.add(resultSet.getString("C_Name"));
            }

            return CompanyList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetNameFromCategories() {
        ArrayList<String> CategoryList = new ArrayList<>();
        try(Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Name FROM ck_categories");

            while (resultSet.next()) {
                CategoryList.add(resultSet.getString("Name"));
            }

            return CategoryList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetAllCategories() {
        ArrayList<String> CategoryList = new ArrayList<>();

        String GetCategoryType = "SELECT Type FROM ck_categorytypes WHERE CGType_ID = ?";
        String SelectCategories = "SELECT * FROM ck_categories";

        try(Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SelectCategories);
            PreparedStatement getCategoryTypeStatement = connection.prepareStatement(GetCategoryType);

            while (resultSet.next()) {
                int CategoryTypeID = resultSet.getInt("Type");
                getCategoryTypeStatement.setInt(1, CategoryTypeID);
                ResultSet categoryTypeResult = getCategoryTypeStatement.executeQuery();
                categoryTypeResult.next();
                CategoryList.add(categoryTypeResult.getString("Type") + " - " +
                        resultSet.getString("Name") + " - " +
                        resultSet.getString("Others"));
            }

            return CategoryList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetAllInvoices() {
        ArrayList<String> InvoiceList = new ArrayList<>();
        try (Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ck_invoices");
            String GetCategoryByID = "SELECT Name FROM ck_categories WHERE Category_ID = ?";
            String GetCompanyByID = "SELECT C_Name FROM ck_companydetails WHERE Company_ID = ?";

            PreparedStatement getCategoryIDStatement = connection.prepareStatement(GetCategoryByID);
            PreparedStatement getCompanyIDStatement = connection.prepareStatement(GetCompanyByID);

            while (resultSet.next()) {
                int CategoryID = resultSet.getInt("Category_ID");
                getCategoryIDStatement.setInt(1, CategoryID);
                ResultSet categoryResult = getCategoryIDStatement.executeQuery();
                if (categoryResult.next()) {
                    String companyName = " "; // Standardwert, falls keine Firma verknüpft ist
                    int CompanyID = resultSet.getInt("Company_ID");

                    if (CompanyID != -1) { // Nur abfragen, wenn Company_ID nicht -1 ist
                        getCompanyIDStatement.setInt(1, CompanyID);
                        ResultSet companyResult = getCompanyIDStatement.executeQuery();
                        if (companyResult.next()) {
                            companyName = companyResult.getString("C_Name");
                        }
                    }

                    InvoiceList.add(categoryResult.getString("Name") + " - " +
                            resultSet.getString("Name") + " - " +
                            resultSet.getString("Description") + " - " +
                            resultSet.getString("Amount") + " - " +
                            companyName + " - " +
                            resultSet.getString("Date") + " - " +
                            resultSet.getString("Timestamp") + " - " +
                            resultSet.getString("isPaid"));
                }
            }

            return InvoiceList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetAllInvoicesWithCategory(boolean PaidMode) {
        ArrayList<String> invoicesWithCategory = new ArrayList<>();
        String query;

        if (!PaidMode) {
            query = "SELECT * FROM ck_invoices_with_category WHERE isPaid = 1";
        } else {
            query = "SELECT * FROM ck_invoices_with_category";
        }

        try (Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String invoiceName = rs.getString("InvoiceName");
                double amount = rs.getDouble("Amount");
                Date date = rs.getDate("Date");
                String categoryType = rs.getString("CategoryType");

                String invoice = String.format("%s - %.2f - %s - %s", invoiceName, amount, date.toString(), categoryType);
                invoicesWithCategory.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoicesWithCategory;
    }

    protected ArrayList<String> GetInvoicesByDateRange(Date StartDate, Date EndDate) {
        ArrayList<String> Invoices = new ArrayList<>();
        String CallProcedure = "CALL GetInvoicesByDateRange(?, ?)";
        try (Connection connection = ConnectToDatabase()) {
            CallableStatement callableStatement = connection.prepareCall(CallProcedure);
            callableStatement.setDate(1, StartDate);
            callableStatement.setDate(2, EndDate);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String invoice = String.format("%s - %.2f - %s - %s",
                        resultSet.getString("Name"),
                        resultSet.getDouble("Amount"),
                        resultSet.getString("Date"),
                        resultSet.getString("Type"));
                Invoices.add(invoice);
            }
            return Invoices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected ArrayList<String> GetInvoicesByDateRange_TABLE(Date StartDate, Date EndDate) {
        ArrayList<String> Invoices = new ArrayList<>();
        String CallProcedure = "CALL GetInvoicesByDateRange_TABLE(?, ?)";
        try (Connection connection = ConnectToDatabase()) {
            CallableStatement callableStatement = connection.prepareCall(CallProcedure);
            callableStatement.setDate(1, StartDate);
            callableStatement.setDate(2, EndDate);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String invoice = String.format("%s - %s - %s - %.2f - %s - %s - %s - %d",
                        resultSet.getString("CategoryType"),
                        resultSet.getString("Name"),
                        resultSet.getString("Description"),
                        resultSet.getDouble("Amount"),
                        resultSet.getString("CompanyName"),
                        resultSet.getString("Date"),
                        resultSet.getString("Timestamp"),
                        resultSet.getInt("isPaid"));
                Invoices.add(invoice);
            }
            return Invoices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetInvoicesByAmountRange(double StartAmount, double EndAmount) {
        ArrayList<String> Invoices = new ArrayList<>();
        String CallProcedure = "CALL GetInvoicesByAmountRange(?, ?)";
        try (Connection connection = ConnectToDatabase()) {
            CallableStatement callableStatement = connection.prepareCall(CallProcedure);
            callableStatement.setDouble(1, StartAmount);
            callableStatement.setDouble(2, EndAmount);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String invoice = String.format("%s - %.2f - %s - %s",
                        resultSet.getString("Name"),
                        resultSet.getDouble("Amount"),
                        resultSet.getString("Date"),
                        resultSet.getString("Type"));
                Invoices.add(invoice);
            }
            return Invoices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetInvoicesByAmountRange_TABLE(double StartAmount, double EndAmount) {
        ArrayList<String> Invoices = new ArrayList<>();
        String CallProcedure = "CALL GetInvoicesByAmountRange_TABLE(?, ?)";
        try (Connection connection = ConnectToDatabase()) {
            CallableStatement callableStatement = connection.prepareCall(CallProcedure);
            callableStatement.setDouble(1, StartAmount);
            callableStatement.setDouble(2, EndAmount);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String invoice = String.format("%s - %s - %s - %.2f - %s - %s - %s - %d",
                        resultSet.getString("CategoryType"),
                        resultSet.getString("Name"),
                        resultSet.getString("Description"),
                        resultSet.getDouble("Amount"),
                        resultSet.getString("CompanyName"),
                        resultSet.getString("Date"),
                        resultSet.getString("Timestamp"),
                        resultSet.getInt("isPaid"));
                Invoices.add(invoice);
            }
            return Invoices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetInvoicesByYear(String Year) {
        ArrayList<String> Invoices = new ArrayList<>();
        String GetInvoicesByYear = "CALL GetInvoicesByYear(?)";

        try(Connection connection = ConnectToDatabase()) {
            CallableStatement callableStatement = connection.prepareCall(GetInvoicesByYear);
            callableStatement.setString(1, Year);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                String invoice = String.format("%s - %.2f - %s - %s",
                        resultSet.getString("Name"),
                        resultSet.getDouble("Amount"),
                        resultSet.getString("Date"),
                        resultSet.getString("Type"));
                Invoices.add(invoice);
            }

            return Invoices;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetOpenInvoices() {
        ArrayList<String> OpenInvoices = new ArrayList<>();
        String CompanyName = "None";

        String query = "SELECT Name, Amount, Company_ID FROM ck_invoices WHERE isPaid = 0";
        String GetCompanyByID = "SELECT C_Name FROM ck_companydetails WHERE Company_ID = ?";
        try (Connection connection = ConnectToDatabase()) {
            // First, get Company Name from ck_companydetails with Company_ID
            PreparedStatement getCompanyIDStatement = connection.prepareStatement(GetCompanyByID);
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            while (resultSet.next()) {
                int CompanyID = resultSet.getInt("Company_ID");
                if (CompanyID != -1) {
                    getCompanyIDStatement.setInt(1, CompanyID);
                    ResultSet companyResult = getCompanyIDStatement.executeQuery();
                    if (companyResult.next()) {
                        CompanyName = companyResult.getString("C_Name");
                    }
                }

                String invoice = String.format("%s - %.2f - %s", resultSet.getString("Name"), resultSet.getDouble("Amount"), CompanyName);
                OpenInvoices.add(invoice);
            }
            return OpenInvoices;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    protected String GetCategoryType(String CategoryName) {
        String CategoryType;
        String GetCategoryType = "SELECT Type FROM ck_categorytypes WHERE CGType_ID = ?";
        String GetCategoryID = "SELECT Type FROM ck_categories WHERE Name = ?";

        try (Connection connection = ConnectToDatabase()) {
            PreparedStatement getCategoryIDStatement = connection.prepareStatement(GetCategoryID);
            PreparedStatement getCategoryTypeStatement = connection.prepareStatement(GetCategoryType);

            getCategoryIDStatement.setString(1, CategoryName);
            ResultSet categoryIDResult = getCategoryIDStatement.executeQuery();

            if (categoryIDResult.next()) {
                int Category_Type = categoryIDResult.getInt("Type");

                getCategoryTypeStatement.setInt(1, Category_Type);
                ResultSet categoryTypeResult = getCategoryTypeStatement.executeQuery();

                if (categoryTypeResult.next()) {
                    CategoryType = categoryTypeResult.getString("Type");
                    return CategoryType;
                } else {
                    return "Category Type not found";
                }
            } else {
                return "Category ID not found";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // OVERVIEW STATISTIC FUNCTIONS
    protected ArrayList<String> GetBestYear() {
        String BestYear;
        double TotalAmount;
        ArrayList<String> BestYearList = new ArrayList<>();

        // Select the Date of the best year using amount ( SUM(Amount) ) and group by year or something
        String GetBestYear = "SELECT YEAR(Date) AS Year, SUM(Amount) AS TotalAmount FROM ck_invoices GROUP BY YEAR(Date) ORDER BY TotalAmount DESC LIMIT 1";

        // Get the Date of the best year and return it
        try (Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GetBestYear);
            resultSet.next();
            BestYear = resultSet.getString("Year");
            TotalAmount = resultSet.getDouble("TotalAmount");

            BestYearList.add(BestYear);
            BestYearList.add(String.valueOf(TotalAmount));
            return BestYearList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetWorstYear() {
        String WorstYear;
        double TotalAmount;
        ArrayList<String> WorstYearList = new ArrayList<>();

        // Select the Date of the worst year using amount ( SUM(Amount) ) and group by year or something
        String GetWorstYear = "SELECT YEAR(Date) AS Year, SUM(Amount) AS TotalAmount FROM ck_invoices GROUP BY YEAR(Date) ORDER BY TotalAmount ASC LIMIT 1";

        // Get the Date of the worst year and return it
        try (Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GetWorstYear);
            resultSet.next();
            WorstYear = resultSet.getString("Year");
            TotalAmount = resultSet.getDouble("TotalAmount");

            WorstYearList.add(WorstYear);
            WorstYearList.add(String.valueOf(TotalAmount));
            return WorstYearList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetBestMonth() {
        String BestMonth, YearToMonth;
        double TotalAmount;
        ArrayList<String> BestMonthList = new ArrayList<>();
        String GetBestMonth = "SELECT YEAR(Date) AS Year, MONTH(Date) AS Month, SUM(Amount) AS TotalAmount FROM ck_invoices GROUP BY YEAR(Date), MONTH(Date) ORDER BY TotalAmount DESC LIMIT 1";
        try (Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GetBestMonth);
            resultSet.next();
            BestMonth = resultSet.getString("Month");
            YearToMonth = resultSet.getString("Year");
            TotalAmount = resultSet.getDouble("TotalAmount");

            String monthYear = YearToMonth + "-" + BestMonth;
            BestMonthList.add(monthYear);
            BestMonthList.add(String.valueOf(TotalAmount));
            return BestMonthList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetWorstMonth() {
        String BestMonth, YearToMonth;
        double TotalAmount;
        ArrayList<String> BestMontList = new ArrayList<>();
        String GetWorstMonth = "SELECT YEAR(Date) AS Year, MONTH(Date) AS Month, SUM(Amount) AS TotalAmount FROM ck_invoices GROUP BY YEAR(Date), MONTH(Date) ORDER BY TotalAmount ASC LIMIT 1";
        try (Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GetWorstMonth);
            resultSet.next();
            BestMonth = resultSet.getString("Month");
            YearToMonth = resultSet.getString("Year");
            TotalAmount = resultSet.getDouble("TotalAmount");

            String monthYear = YearToMonth + "-" + BestMonth;
            BestMontList.add(monthYear);
            BestMontList.add(String.valueOf(TotalAmount));
            return BestMontList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected String GetBestCustomer() {
        String BestCustomer;

        ArrayList<String> BestCustomerList = new ArrayList<>();
        String GetBestCustomer = "SELECT CompanyName FROM ck_getbestcustomer";

        try (Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GetBestCustomer);
            resultSet.next();
            BestCustomer = resultSet.getString("CompanyName");

            return BestCustomer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    protected String Get_MostUsed_Category() {
        // SELECT * FROM ck_get_most_used_category;
        String MostUsedCategory;
        String GetMostUsedCategory = "SELECT CategoryName FROM ck_get_most_used_category";
        try (Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GetMostUsedCategory);
            resultSet.next();
            MostUsedCategory = resultSet.getString("CategoryName");

            return MostUsedCategory;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected String Get_LeastUsed_Category() {
        String LeastUsedCategory;
        String GetLeastUsedCategory = "SELECT * FROM ck_get_least_used_category;";
        try (Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GetLeastUsedCategory);
            resultSet.next();
            LeastUsedCategory = resultSet.getString("CategoryName");

            return LeastUsedCategory;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<String> GetAllYearsFromInvoices() {
        ArrayList<String> Years = new ArrayList<>();
        String GetYears = "SELECT DISTINCT YEAR(Date) AS Year FROM ck_invoices";
        try (Connection connection = ConnectToDatabase()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GetYears);

            while (resultSet.next()) {
                Years.add(resultSet.getString("Year"));
            }

            return Years;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // DELETE FUNCTIONS


    // Delte active Invocie
    protected void DeleteInvoice(String CategoryName, String InvoiceName, double Amount) {
        String deleteQuery = "DELETE FROM ck_invoices WHERE Category_ID = ? AND Name = ? AND Amount = ?";
        String getCategoryID = "SELECT Category_ID FROM ck_categories WHERE Name = ?";

        try (Connection connection = ConnectToDatabase()) {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            PreparedStatement getCategoryIDStatement = connection.prepareStatement(getCategoryID);

            getCategoryIDStatement.setString(1, CategoryName);
            ResultSet categoryIDResult = getCategoryIDStatement.executeQuery();
            categoryIDResult.next();
            int CategoryID = categoryIDResult.getInt("Category_ID");

            deleteStatement.setInt(1, CategoryID);
            deleteStatement.setString(2, InvoiceName);
            deleteStatement.setDouble(3, Amount);
            deleteStatement.executeUpdate();
            System.out.println("Invoice Successfully deleted");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE ACTIVE CATEGORY
    protected String DeleteCategory(String CategoryName) {
        String deleteQuery = "DELETE FROM ck_categories WHERE Category_ID = ?";
        String GetCategoryID = "SELECT Category_ID FROM ck_categories WHERE Name = ?";
        String SelectInvoicesWithCategory = "SELECT * FROM ck_invoices WHERE Category_ID = ?";

        try (Connection connection = ConnectToDatabase()) {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            PreparedStatement getCategoryIDStatement = connection.prepareStatement(GetCategoryID);
            PreparedStatement selectInvoicesWithCategoryStatement = connection.prepareStatement(SelectInvoicesWithCategory);

            getCategoryIDStatement.setString(1, CategoryName);
            ResultSet categoryIDResult = getCategoryIDStatement.executeQuery();
            categoryIDResult.next();
            int CategoryID = categoryIDResult.getInt("Category_ID");

            selectInvoicesWithCategoryStatement.setInt(1, CategoryID);
            ResultSet invoicesWithCategoryResult = selectInvoicesWithCategoryStatement.executeQuery();
            // If There are invoices with this category, do not delete
            if (invoicesWithCategoryResult.next()) {
                System.out.println("There are invoices with this category, cannot delete");
                return "There are invoices with this category, cannot delete";
            }

            deleteStatement.setInt(1, CategoryID);
            deleteStatement.executeUpdate();
            System.out.println("Category Successfully deleted");
            return "Category Successfully deleted";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected String DeleteCustomer(String CompanyName) {
        String deleteCompany = "DELETE FROM ck_companydetails WHERE C_Name = ?";
        String deleteCustomer = "DELETE FROM ck_contactperson WHERE Company_ID = ?";
        String GetCompanyID = "SELECT Company_ID FROM ck_companydetails WHERE C_Name = ?";
        String SelectContactPersons = "SELECT * FROM ck_contactperson WHERE Company_ID = ?";

        try (Connection connection = ConnectToDatabase()) {
            PreparedStatement deleteCompanyStatement = connection.prepareStatement(deleteCompany);
            PreparedStatement deleteCustomerStatement = connection.prepareStatement(deleteCustomer);
            PreparedStatement getCompanyIDStatement = connection.prepareStatement(GetCompanyID);
            PreparedStatement selectContactPersonsStatement = connection.prepareStatement(SelectContactPersons);

            getCompanyIDStatement.setString(1, CompanyName);
            ResultSet companyIDResult = getCompanyIDStatement.executeQuery();
            companyIDResult.next();
            int CompanyID = companyIDResult.getInt("Company_ID");

            selectContactPersonsStatement.setInt(1, CompanyID);
            ResultSet contactPersonsResult = selectContactPersonsStatement.executeQuery();
            // If There are contact persons with this company, delete this contact persons as well
            while (contactPersonsResult.next()) {
                deleteCustomerStatement.setInt(1, CompanyID);
                deleteCustomerStatement.executeUpdate();
            }

            deleteCustomerStatement.setInt(1, CompanyID);
            deleteCustomerStatement.executeUpdate();

            deleteCompanyStatement.setString(1, CompanyName);
            deleteCompanyStatement.executeUpdate();
            System.out.println("Customer Successfully deleted");
            return "Customer Successfully deleted";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

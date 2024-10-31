package accounting.accounting;

import java.sql.*;
import java.util.ArrayList;

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
    protected void InsertNewCompany(String Name, String Street, int HouseNumber, String Location, int PostalCode, String Country, String Other) {
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Insert a new Contact Person into ck_contactpersons
    protected void InsertNewContactPerson(int Rank, String Title, String Gender, String FirstName, String LastName, String EMail, String TelNr, String CompanyName) {
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void InsertNewCategory(String Name, String Type, String Other) {
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Table name : ck_invoices
    // Rows: Category_ID = Foreign Key from ck_categories Category_ID
    // Name = Varchar
    // Description = Varchar
    // Amount = Double
    // Date = Date
    // Timestamp = Timestamp for when the invoice was created exactly

    protected void InsertNewInvoice(String Category, String Name, String Description, String Amount, Date Date) {
        String selectQuery = "SELECT * FROM ck_invoices WHERE Name = ? AND Date = ?";
        String GetCategoryID = "SELECT Category_ID FROM ck_categories WHERE Name = ?";
        String insertQuery = "INSERT INTO ck_invoices (Category_ID, Name, Description, Amount, Date, Timestamp) VALUES (?, ?, ?, ?, ?, ?)";

        // Convert Amount to Double
        String AmoundWithDot = Amount.replace(",", ".");
        double AmountDouble = Double.parseDouble(AmoundWithDot);

        try (Connection connection = ConnectToDatabase();
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
             PreparedStatement getCategoryIDStatement = connection.prepareStatement(GetCategoryID);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            selectStatement.setString(1, Name);
            selectStatement.setDate(2, Date);
            ResultSet selectResult = selectStatement.executeQuery();

            getCategoryIDStatement.setString(1, Category);
            ResultSet categoryIDResult = getCategoryIDStatement.executeQuery();
            categoryIDResult.next();
            int CategoryID = categoryIDResult.getInt("Category_ID");

            if (selectResult.next()) {
                System.out.println("Invoice already exists");
            } else {
                insertStatement.setInt(1, CategoryID);
                insertStatement.setString(2, Name);
                insertStatement.setString(3, Description);
                insertStatement.setDouble(4, AmountDouble);
                insertStatement.setDate(5, Date);
                insertStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                insertStatement.executeUpdate();
                System.out.println("Invoice added");
            }
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

    protected ArrayList<String> GetAllCategories() {
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
}

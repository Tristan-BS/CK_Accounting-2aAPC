package accounting.accounting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
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
}

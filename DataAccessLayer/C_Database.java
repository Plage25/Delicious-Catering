package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C_Database {
    // private static final String jdbcURL =
    // "jdbc:sqlserver://localhost:1433;databasename=AdventureWorksDW2014;integratedSecurity=true;";

    String dbURL = "jdbc:sqlserver://localhost; database=DeliciousCateringDBD";
    String user = "DeliciousCatering";
    String pass = "DeliciousCatering";

    public void Connect() {

        try (Connection connection = DriverManager.getConnection(dbURL, user, pass);) {

            // Code here.
            if (connection != null) {
                System.out.println("Connected");
            }

        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean LoginCheck(String email, String password) {
        ResultSet resultSet = null;
        boolean result = false;
        String DBDtest = "nothing";

        try (Connection connection = DriverManager.getConnection(dbURL, user, pass);
                Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM dbo.Client WHERE Email = '" + email + "' AND Password = '" + password
                    + "'";
            resultSet = statement.executeQuery(selectSql);
            // Print results from select statement

            DBDtest = "nothing";
            while (resultSet.next()) {
                DBDtest = resultSet.getString(4) + " " + resultSet.getString(5);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (DBDtest.equals("nothing")) {
            result = false;
        } else {
            result = true;
        }

        return result;
    }

}

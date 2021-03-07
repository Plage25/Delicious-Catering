package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public void Register(String FirstName, String Surname, String Email, String Password, String PhoneNumber) {

        try (Connection connection = DriverManager.getConnection(dbURL, user, pass);
                Statement statement = connection.createStatement();) {

            // the sql insert statement
            PreparedStatement preparedStmt = connection.prepareStatement(
                    "INSERT INTO Client(FirstName,LastName,Email,Password,P_Number) VALUES (?,?,?,?,?)");
            preparedStmt.setString(1, FirstName);
            preparedStmt.setString(2, Surname);
            preparedStmt.setString(3, Email);
            preparedStmt.setString(4, Password);
            preparedStmt.setString(5, PhoneNumber);
            // execute the preparedstatement
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }

    public void InputOrder(int fk, String eventType, String eventDate, String time, String eventAdress,
            int amountOfAdults, int amountOfKids, int adultMeals, int childMeals, int alcoholDrinks,
            int nonAlcoholDrinks, int desserts, String payed, double amntOutsanding, double totalPrice,
            String decorations, double decPrice) {

        try (Connection connection = DriverManager.getConnection(dbURL, user, pass);
                Statement statement = connection.createStatement();) {

            // the sql insert statement
            PreparedStatement preparedStmt = connection
                    .prepareStatement("INSERT INTO EventDetails VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStmt.setInt(1, fk);
            preparedStmt.setString(2, eventType);
            preparedStmt.setString(3, eventDate);
            preparedStmt.setString(4, time);
            preparedStmt.setString(5, eventAdress);
            preparedStmt.setInt(6, amountOfAdults);
            preparedStmt.setInt(7, amountOfKids);
            preparedStmt.setInt(8, adultMeals);
            preparedStmt.setInt(9, childMeals);
            preparedStmt.setInt(10, alcoholDrinks);
            preparedStmt.setInt(11, nonAlcoholDrinks);
            preparedStmt.setInt(12, desserts);
            preparedStmt.setString(13, payed);
            preparedStmt.setDouble(14, amntOutsanding);
            preparedStmt.setDouble(15, totalPrice);
            preparedStmt.setString(16, decorations);
            preparedStmt.setDouble(17, decPrice);
            // execute the preparedstatement
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public boolean checkValidDate(String date) {

        ResultSet resultSet = null;
        boolean result = false;
        String DBDtest = "nothing";

        try (Connection connection = DriverManager.getConnection(dbURL, user, pass);
                Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM EventDetails WHERE Date = '" + date + "'";
            resultSet = statement.executeQuery(selectSql);

            DBDtest = "nothing";
            while (resultSet.next()) {
                DBDtest = resultSet.getString(4);// Gets date column
                                                 // System.out.println(resultSet.getString(3)+resultSet.getString(4)+resultSet.getString(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (DBDtest.equals("nothing")) {
            result = true;
        } else {
            result = false;
        }
        System.out.println(result + "Database check");
        return result;
    }

    public void Forgotpassword(String Email) {
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(dbURL, user, pass);
                Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM Client WHERE Email = '" + Email + "'";
            resultSet = statement.executeQuery(selectSql);

            if (resultSet.next()) {
                String password = resultSet.getString(5);// Gets date column
                                                         // System.out.println(resultSet.getString(3)+resultSet.getString(4)+resultSet.getString(5));
                System.out.println("Your password is: " + password);
                System.out.println("Press ENTER to continue");
                try {
                    System.in.read();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Wrong email address inserted. Wait 3 seconds to be redirected...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean DeleteOrder(int EventID) {
        ResultSet resultSet = null;
        boolean result = false;
        String DBDtest = "nothing";

        try (Connection connection = DriverManager.getConnection(dbURL, user, pass);
                Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            

            String sqlst = "SELECT * FROM dbo.EventDetails WHERE EventID = '" + EventID + "'";
            resultSet = statement.executeQuery(sqlst);
            DBDtest = "nothing";
            while (resultSet.next()) {
                DBDtest = resultSet.getString(4) + " " + resultSet.getString(5); 
                String deleteSql = "DELETE FROM dbo.EventDetails WHERE EventID = '" + EventID + "'";
                statement.executeUpdate(deleteSql);   
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

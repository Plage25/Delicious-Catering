package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class C_Database {
    // private static final String jdbcURL =
    // "jdbc:sqlserver://localhost:1433;databasename=AdventureWorksDW2014;integratedSecurity=true;";
    Scanner input = new Scanner(System.in);
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
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Insert successful! Please wait 3 seconds.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
                System.out.println("Wrong email address inserted.Wait 3 seconds to be redirected...");
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

    public void UpdateOrder(int eventID, int fk, String eventType, String eventDate, String time, String eventAdress,
            int amountOfAdults, int amountOfKids, int adultMeals, int childMeals, int alcoholDrinks,
            int nonAlcoholDrinks, int desserts, String payed, double amntOutsanding, double totalPrice,
            String decorations, double decPrice) {
        try (Connection connection = DriverManager.getConnection(dbURL, user, pass);
                Statement statement = connection.createStatement();) {
            // update users set num_points = ? where first_name = ?"
            // the sql insert statement
            PreparedStatement preparedStmt = connection.prepareStatement(
                    "UPDATE EventDetails set Type = ?, Date = ?, Time = ?, Address = ?, TotalAdults = ?, TotalKids = ?, AdultMeals = ?, KidMeals = ?, AlcoholDrinks = ?, NonAlcoholDrinks = ?, Desserts = ?, Payed = ?, Amnt_Outstanding = ?, Total_Price = ?, Decorations = ?, DecorPrice = ? WHERE EventID = ?");
            preparedStmt.setString(1, eventType);
            preparedStmt.setString(2, eventDate);
            preparedStmt.setString(3, time);
            preparedStmt.setString(4, eventAdress);
            preparedStmt.setInt(5, amountOfAdults);
            preparedStmt.setInt(6, amountOfKids);
            preparedStmt.setInt(7, adultMeals);
            preparedStmt.setInt(8, childMeals);
            preparedStmt.setInt(9, alcoholDrinks);
            preparedStmt.setInt(10, nonAlcoholDrinks);
            preparedStmt.setInt(11, desserts);
            preparedStmt.setString(12, payed);
            preparedStmt.setDouble(13, amntOutsanding);
            preparedStmt.setDouble(14, totalPrice);
            preparedStmt.setString(15, decorations);
            preparedStmt.setDouble(16, decPrice);
            preparedStmt.setDouble(17, eventID);
            // execute the preparedstatement
            preparedStmt.executeUpdate();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Update successful! Please wait 3 seconds.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }

    public void ViewOrders(String email) {
        ResultSet resultSet = null;
        ResultSet price = null;
        double TotalPrice=0;
        double adultmealPrice=0;
        double KidmealPrice=0;
        double alcoholprice=0;
        double NonalcoholPrice=0;
        double dessertPrice=0;
        double totalPeople=0;
        double decorPrice=0;

        try (Connection connection = DriverManager.getConnection(dbURL, user, pass);
                Statement statement = connection.createStatement();
                Statement statementPrice = connection.createStatement();) {

            ////////////////////// Adultmeal price grab/////////////////////////
            

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM dbo.EventDetails INNER JOIN dbo.Client ON EventDetails.ClientID=Client.ClientID WHERE Client.Email = '"
                    + email + "'";
            resultSet = statement.executeQuery(selectSql);
            System.out.println("");
            System.out.println("\t\tHistory of orders made");

            while (resultSet.next()) {
                String priceSql = "SELECT * FROM dbo.Menu WHERE FoodID = 1 ";
            price = statementPrice.executeQuery(priceSql);
            while (price.next()) {
            adultmealPrice = price.getDouble(3);
            }
            ////////////////////// Kid-meal price grab/////////////////////////
            priceSql = "SELECT * FROM dbo.Menu WHERE FoodID = 2 ";
            price = statementPrice.executeQuery(priceSql);
            while (price.next()) {
            KidmealPrice = price.getDouble(3);
            }
            ////////////////////// Alochol drinks price grab/////////////////////////
            priceSql = "SELECT * FROM dbo.Menu WHERE FoodID = 3 ";
            price = statementPrice.executeQuery(priceSql);
            while (price.next()) {
            alcoholprice = price.getDouble(3);
            }
            ////////////////////// non-alcohol price grab/////////////////////////
            priceSql = "SELECT * FROM dbo.Menu WHERE FoodID = 4 ";
            price = statementPrice.executeQuery(priceSql);
            while (price.next()) {
            NonalcoholPrice = price.getDouble(3);
            }
            ////////////////////// Dessert price grab/////////////////////////
            priceSql = "SELECT * FROM dbo.Menu WHERE FoodID = 5 ";
            price = statementPrice.executeQuery(priceSql);
            while (price.next()) {
            dessertPrice = price.getDouble(3);
            }

                totalPeople=resultSet.getInt(7)+resultSet.getInt(8);
                /////Food price calculations///
                if (totalPeople>40) {
                    adultmealPrice = (adultmealPrice*resultSet.getInt(9))*0.85;
                } else {
                    adultmealPrice = adultmealPrice*resultSet.getInt(9);
                }
                
                KidmealPrice = KidmealPrice*resultSet.getInt(10);
                alcoholprice = alcoholprice*resultSet.getInt(11);
                NonalcoholPrice = NonalcoholPrice*resultSet.getInt(12);
                dessertPrice = dessertPrice*resultSet.getInt(13);
                decorPrice = resultSet.getDouble(18);

                TotalPrice=adultmealPrice+KidmealPrice+alcoholprice+NonalcoholPrice+dessertPrice+decorPrice;

                System.out.println("");
                System.out.println("=====================  EventID : " + resultSet.getString(1) + "  ==================");
                System.out.println("");
                System.out.println("ClientID: " + resultSet.getString(2));
                System.out.println("Type: " + resultSet.getString(3));
                System.out.println("Date: " + resultSet.getString(4));
                System.out.println("Time: " + resultSet.getString(5));
                System.out.println("Address: " + resultSet.getString(6));
                System.out.println("Total Adults: " + resultSet.getString(7));
                System.out.println("Total Kids: " + resultSet.getString(8));
                System.out.println("Adult Meals: " + resultSet.getString(9));
                System.out.println("Kid Meals: " + resultSet.getString(10));
                System.out.println("AcoholicDrinks: " + resultSet.getString(11));
                System.out.println("Non-AlcoholicDrinks: " + resultSet.getString(12));
                System.out.println("Desserts: " + resultSet.getString(13));
                System.out.println("Payed: " + resultSet.getString(14));
                System.out.println("Amount Outstanding: " + resultSet.getString(15));
                System.out.println("Total Price: " + TotalPrice);
                System.out.println("Decorations: " + resultSet.getString(17));
                System.out.println("Decor Price: " + resultSet.getString(18));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

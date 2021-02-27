package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
	


public class C_Database 
{
    public static void main(String[] args) 
    {
        //  private static final String jdbcURL = "jdbc:sqlserver://localhost:1433;databasename=AdventureWorksDW2014;integratedSecurity=true;";
  
        String dbURL = "jdbc:sqlserver://localhost; database=DeliciousCateringDBD" ;
            String user = "DeliciousCatering";
            String pass = "DeliciousCatering";
            
            try (Connection connection = DriverManager.getConnection(dbURL,user,pass);) 
            {
                
                // Code here.
                if (connection != null)
                {
                    System.out.println("Connected");
                }
                
            }
            // Handle any errors that may have occurred.
            catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet resultSet = null;
            try (Connection connection = DriverManager.getConnection(dbURL,user,pass);
                Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT TOP 2 FirstName, LastName from dbo.Client";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}

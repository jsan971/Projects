package helper;

import java.sql.*;

/** This class allows us to communicate with our database.*/

public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // Local
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // username
    private static final String password = "Passw0rd!";
    public static Connection connection; // connection interface


    /** This method opens our database connection.
     This method is triggered when we call JDBC.OpenConnection();
     */

    public static void openConnection() {
        try {
            Class.forName(driver); // locate driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); //Reference Connection object
            System.out.println("DB Connection open");
        }
        catch(Exception e)
        {
            System.out.println("DB Connection closed");
        }
    }

    /** This method closes our database connection.
     This method is triggered when we call JDBC.closeConnection();
     */
    public static void closeConnection(){
        try {
            connection.close();
            System.out.println("DB Connection closed");
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
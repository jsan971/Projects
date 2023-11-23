package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.sql.Timestamp;

import model.Customer;

import java.sql.*;

/** This class will allow us to have a CRUD setup for our customers table. */

public class CustomerDao {

    /** This method allows us to get all customers.
     A list allCustomers has been created. Iterating through our customers table we will
     store each row into an customerResult. The result will also be added to our allCustomers list.

     @return  allCustomers Returns all of the customers from our customers table
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception{
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

        while(resultSet.next()) {
            int Customer_ID = resultSet.getInt("Customer_ID");
            String Customer_Name = resultSet.getString("Customer_Name");
            String Address = resultSet.getString("Address");
            String Postal_Code = resultSet.getString("Postal_Code");
            String Phone = resultSet.getString("Phone");
            int Division_ID = resultSet.getInt("Division_ID");
            Customer customerResult = new Customer(Customer_ID, Customer_Name, Address, Postal_Code,Phone, Division_ID);
            allCustomers.add(customerResult);

        }
        //JDBC.closeConnection();
        return allCustomers;
    }

    /** This method retrieves a single customer.
     A String value is passed to the Customer_Name parameter
     which is then used in the sql query to retrieve the specific row based on the Customer_Name given.
     The customer information is then stored in customerResult.
     @param Customer_Name The Customer_Name
     @return customerResult returns the customerResult
     */
    public static Customer getCustomer(String Customer_Name) throws SQLException, Exception {

        JDBC.openConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        preparedStatement = JDBC.connection.prepareStatement("SELECT * FROM customers WHERE Customer_Name = ?");
        preparedStatement.setString(1, Customer_Name);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int Customer_ID = resultSet.getInt("Customer_ID");
            Customer_Name = resultSet.getString("Customer_Name");
            String Address = resultSet.getString("Address");
            String Postal_Code = resultSet.getString("Postal_Code");
            String Phone = resultSet.getString("Phone");
            int Division_ID = resultSet.getInt("Division_ID");
            Customer customerResult = new Customer(Customer_ID, Customer_Name, Address, Postal_Code,Phone, Division_ID);

            return customerResult;
        }
        JDBC.closeConnection();
        return null;
    }

    /** This method updates an existing customer.
     * An update query is used to pass our parameter information to update an existing customer in our customers
     * table.
     * @param Customer_ID For Customer_ID
     * @param CustomerName For CustomerName
     * @param CustomerAddress For CustomerAddress
     * @param CustomerPhoneNumber For CustomerPhoneNumber
     * @param CustomerPostalCode For CustomerPostalCode
     * @param CustomerCountry For CustomerCountry
     * @param CustomerState For CustomerState
     * @param DivisionID For Division_ID
     * @param Customer_ID For Customer_ID
     */
    public static void UpdateCustomer(ActionEvent event, int Customer_ID, String CustomerName, String CustomerAddress, String CustomerPhoneNumber,
                                            String CustomerPostalCode, String CustomerCountry, String CustomerState, int DivisionID) throws SQLException {
        JDBC.openConnection();

        CustomerAddress = CustomerCountry + " address: " + CustomerAddress + ", " + CustomerState;

        String sql = "UPDATE customers SET Customer_Name =?, Address =?, Postal_Code =?, Phone =?,Last_Update=?, Division_ID =? where Customer_ID =?";

        PreparedStatement statement = JDBC.connection.prepareStatement(sql);

        statement.setString(1, CustomerName);
        statement.setString(2, CustomerAddress);
        statement.setString(3, CustomerPostalCode);
        statement.setString(4, CustomerPhoneNumber);
        statement.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
        statement.setInt(6, DivisionID);
        statement.setInt(7, Customer_ID);


        int rows = statement.executeUpdate();
    }

    /** This method processes a customer deletion.
     * An integer value is passed to Customer_ID which then is used in our sql
     * query to delete the appointment matching our Customer_ID, once the appointment
     * is deleted, the customer record can be removed. This is due to FK constraints.
     @param Customer_ID The Customer_ID
     */

    public static void DeleteCustomer(ActionEvent event, int Customer_ID) throws SQLException {
        System.out.println(Customer_ID);

        JDBC.openConnection();

        try {
            String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
            PreparedStatement statement = JDBC.connection.prepareStatement(sql);

            statement.setInt(1, Customer_ID);
            int rows = statement.executeUpdate();

            sql = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement statement2 = JDBC.connection.prepareStatement(sql);

            statement2.setInt(1, Customer_ID);
             rows = statement2.executeUpdate();

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JDBC.closeConnection();
    }

    /** This method creates a customer record record.
     An insert query is created to insert our parameter values to our customers table.
     * @param CustomerName For CustomerName
     * @param CustomerAddress For CustomerAddress
     * @param CustomerPhoneNumber For CustomerPhoneNumber
     * @param CustomerPostalCode For CustomerPostalCode
     * @param CustomerCountry For CustomerCountry
     * @param CustomerState For CustomerState
     * @param DivisionID For Division_ID
     */
    public static void CreateCustomerRecord(ActionEvent event, String CustomerName, String CustomerAddress, String CustomerPhoneNumber,
                                            String CustomerPostalCode, String CustomerCountry, String CustomerState, int DivisionID) throws SQLException {

        JDBC.openConnection();
        int resultSet = 0;

        CustomerAddress = CustomerCountry + " address: " + CustomerAddress + ", " + CustomerState;

        String query = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID, Create_Date,Last_Update ) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(query);
        preparedStatement.setString(1, CustomerName);
        preparedStatement.setString(2, CustomerAddress);
        preparedStatement.setString(3,CustomerPostalCode);
        preparedStatement.setString(4,CustomerPhoneNumber);
        preparedStatement.setInt(5, DivisionID);
        preparedStatement.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
        preparedStatement.setTimestamp(7,new Timestamp(System.currentTimeMillis()));

        resultSet = preparedStatement.executeUpdate();

        JDBC.closeConnection();
    }

}


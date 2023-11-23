package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.Appointment;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/** This class will allow us to have a CRUD setup for our appointments table. */
public class AppointmentDao {

    /** This method allows us to get all appointments.
     A list allAppointments has been created. Iterating through our appointments table we will
     store each row into an appointmentResult. The result will also be added to our allAppointments list.

      @return  allAppointments Returns all of the appointments from our appointments table
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException, Exception {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        JDBC.openConnection();

        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM appointments");

        while(resultSet.next()){
            int Appointment_ID = resultSet.getInt("Appointment_ID");
            String Title = resultSet.getString("Title");
            String Description = resultSet.getString("Description");
            String Location = resultSet.getString("Location");
            String Type = resultSet.getString("Type");
            Timestamp Start = resultSet.getTimestamp("Start");
            Timestamp End = resultSet.getTimestamp("End");
            int Customer_ID = resultSet.getInt("Customer_ID");
            int User_ID = resultSet.getInt("User_ID");
            int Contact_ID = resultSet.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID);
            allAppointments.add(appointmentResult);
        }
        return allAppointments;
    }

    /** This method allows us to get all appointments by the current month.
     A list allbyMonthAppointments has been created. Iterating through our appointments table by the current month we will
     store each row into an appointmentResult. The result will also be added to our allAppointments list.

     @return  allbyMonthAppointments Returns all of the appointments by month from our appointments table.
     */

    public static ObservableList<Appointment> getCurrentMonth() throws SQLException, Exception {
        ObservableList<Appointment> allbyMonthAppointments = FXCollections.observableArrayList();
        JDBC.openConnection();

        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM appointments WHERE EXTRACT(MONTH FROM Start) = EXTRACT(MONTH FROM CURRENT_DATE);");

        while(resultSet.next()){
            int Appointment_ID = resultSet.getInt("Appointment_ID");
            String Title = resultSet.getString("Title");
            String Description = resultSet.getString("Description");
            String Location = resultSet.getString("Location");
            String Type = resultSet.getString("Type");
            Timestamp Start = resultSet.getTimestamp("Start");
            Timestamp End = resultSet.getTimestamp("End");
            int Customer_ID = resultSet.getInt("Customer_ID");
            int User_ID = resultSet.getInt("User_ID");
            int Contact_ID = resultSet.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID);
            allbyMonthAppointments.add(appointmentResult);
        }
        return allbyMonthAppointments;
    }


    /** This method allows us to get all appointments by the current month.
     A list allbyWeekAppointments has been created. Iterating through our appointments table by the current year and week which we will
     store each row into an appointmentResult. The result will also be added to our allbyWeekAppointments list.

     @return  allbyWeekAppointments Returns all of the appointments by week from our appointments table.
     */
    public static ObservableList<Appointment> getCurrentWeek() throws SQLException, Exception {
        ObservableList<Appointment> allbyWeekAppointments = FXCollections.observableArrayList();
        JDBC.openConnection();

        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM appointments WHERE YEAR(Start) = YEAR(CURRENT_DATE) AND WEEK(Start) = WEEK(CURRENT_DATE);");

        while(resultSet.next()){
            int Appointment_ID = resultSet.getInt("Appointment_ID");
            String Title = resultSet.getString("Title");
            String Description = resultSet.getString("Description");
            String Location = resultSet.getString("Location");
            String Type = resultSet.getString("Type");
            Timestamp Start = resultSet.getTimestamp("Start");
            Timestamp End = resultSet.getTimestamp("End");
            int Customer_ID = resultSet.getInt("Customer_ID");
            int User_ID = resultSet.getInt("User_ID");
            int Contact_ID = resultSet.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID);
            allbyWeekAppointments.add(appointmentResult);
        }
        return allbyWeekAppointments;
    }

    /** This method retrieves a single appointment.
     An Integer value is passed to the Appointment_ID parameter
     which is then used in the sql query to retrieve the specific row based on the Appointment_ID given.
     The appointment information is then stored in appointmentResult.
     @param Appointment_ID The Appointment_ID
     @return appointmentResult returns the appointmentResult
     */
    public static Appointment getAppointment(int Appointment_ID) throws SQLException, Exception {
        JDBC.openConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        preparedStatement = JDBC.connection.prepareStatement("SELECT * FROM appointments WHERE Appointment_ID = ?");
        preparedStatement.setInt(1, Appointment_ID);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            Appointment_ID = resultSet.getInt("Appointment_ID");
            String Title = resultSet.getString("Title");
            String Description = resultSet.getString("Description");
            String Location = resultSet.getString("Location");
            String Type = resultSet.getString("Type");
            Timestamp Start = resultSet.getTimestamp("Start");
            Timestamp End = resultSet.getTimestamp("End");
            int Customer_ID = resultSet.getInt("Customer_ID");
            int User_ID = resultSet.getInt("User_ID");
            int Contact_ID = resultSet.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID);

            return appointmentResult;
        }

        return null;
    }

    /** This method creates an appointment record.
     An insert query is created to insert our parameter values to our appointments table. A format is also used for our
     timestamps which are : HH:MM:SS
     @param Title For Title
     @param Description For Description
     @param Location For Location
     @param Type For Type
     @param StartDate For StartDate
     @param StartTime For StartTime
     @param EndTime For EndTime
     @param Customer_ID For Customer_ID
     @param Contact_ID For Contact_ID
     @param User_ID For User_ID
     */
    public static void CreateAppointmentRecord(ActionEvent event, String Title, String Description, String Location, String Type, LocalDate StartDate, String StartTime, String EndTime, int Customer_ID, int User_ID, int Contact_ID ) throws SQLException {
        JDBC.openConnection();
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

        String query = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID, Create_Date, Last_Update) Values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(query);
        preparedStatement.setString(1, Title);
        preparedStatement.setString(2, Description);
        preparedStatement.setString(3, Location);
        preparedStatement.setString(4, Type);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(StartDate + " " + StartTime));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(StartDate + " " + EndTime));
        preparedStatement.setInt(7, Customer_ID);
        preparedStatement.setInt(8,User_ID);
        preparedStatement.setInt(9, Contact_ID);
        preparedStatement.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
        preparedStatement.setTimestamp(11, new Timestamp(System.currentTimeMillis()));

        result = preparedStatement.executeUpdate();

        JDBC.closeConnection();
    }
    /** This method processes an appointment deletion.
     * An integer value is passed to Appointment_ID which then is used in our sql
     * query to delete the appointment matching our Appointment_ID
     @param Appointment_ID The Appointment_ID
     */
    public static void DeleteAppointment(ActionEvent event, int Appointment_ID) throws SQLException {
        System.out.println(Appointment_ID);

        JDBC.openConnection();

        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement statement = JDBC.connection.prepareStatement(sql);

            statement.setInt(1, Appointment_ID);
            int rows = statement.executeUpdate();

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JDBC.closeConnection();
    }
    /** This method updates an existing appointment.
     * An update query is used to pass our parameter information to update an existing appointment in our appointments
     * table.  A format is also used for our timestamps which are : HH:MM:SS
     * @param Appointment_ID For Appointment_ID
     * @param Title For Title
     * @param Description For Description
     * @param Location For Location
     * @param Type For Type
     * @param StartDate For StartDate
     * @param StartTime For StartTime
     * @param EndTime For EndTime
     * @param Customer_ID For Customer_ID
     * @param Contact_ID For Contact_ID
     * @param User_ID For User_ID
     */
    public static void UpdateAppointment(ActionEvent event, int Appointment_ID, String Title, String Description, String Location, String Type, LocalDate StartDate, String StartTime, String EndTime, int Customer_ID, int User_ID, int Contact_ID ) throws SQLException {
        JDBC.openConnection();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

        int resultSet = 0;

        String sql = "UPDATE appointments SET Title =?, Description =?, Location =?, Type =?, Start =?, End=?, Customer_ID =?, User_ID=?, Contact_ID=?, Last_Update=? WHERE Appointment_ID =?";
        PreparedStatement statement = JDBC.connection.prepareStatement(sql);

        statement.setString(1, Title);
        statement.setString(2, Description);
        statement.setString(3, Location);
        statement.setString(4, Type);
        statement.setTimestamp(5, Timestamp.valueOf(StartDate + " " + StartTime));
        statement.setTimestamp(6, Timestamp.valueOf(StartDate + " " + EndTime));
        statement.setInt(7, Customer_ID);
        statement.setInt(8, User_ID);
        statement.setInt(9, Contact_ID);
        statement.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
        statement.setInt(11, Appointment_ID);
        int rows = statement.executeUpdate();

    }

}

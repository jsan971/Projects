package controller;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * The ReportsControler class manages our scene for the Reports.fxml.
 *
 * This controller is initializing our reports view that includes event handlers
 * to allow the user to generate reports for appointment and customer records.  The appointment details
 * scene also has a tableview, combo boxes, and buttons allowing user interaction.
 *
 * In the initialize method below, this lets us setup our event handlers and displaying
 * specific data to the end user. This is where the main logic of our controller resides in.
 */


public class ReportsController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TableView<Appointment> AppointmentTable;

    @FXML
    private TableColumn<?, ?> Appointment_ID;

    @FXML
    private TableColumn<?, ?> Title;

    @FXML
    private TableColumn<?, ?> Type;

    @FXML
    private TableColumn<?, ?> Description;

    @FXML
    private TableColumn<?, ?> Start;

    @FXML
    private TableColumn<?, ?> End;

    @FXML
    private TableColumn<?, ?> Customer_ID;

    @FXML
    private ComboBox ContactComboBox;

    @FXML
    private ComboBox MonthComboBox;

    @FXML
    private ComboBox TypeComboBox;

    String getContact;
    int getContactID;

    /**
     * This method initializes our reports details controller.
     * This is called shortly after the controller and our view is present.
     * @param url For locations to resolve a particular path for the main object, the location is typically unknown.
     * @param resourceBundle This will localize our main object
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ObservableList<String> months = FXCollections.observableArrayList(
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        );



        MonthComboBox.setItems(months);

        try {

            JDBC.openConnection();
            Statement statement = JDBC.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Contact_Name FROM contacts");

            ObservableList<String> contacts = FXCollections.observableArrayList();
            while (resultSet.next()) {
                String contactname = resultSet.getString("Contact_Name");
                contacts.add(String.valueOf(contactname));
                ContactComboBox.setItems(contacts);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        ContactComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    JDBC.openConnection();

                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;

                    getContact = (String) ContactComboBox.getValue();

                    preparedStatement = JDBC.connection.prepareStatement("SELECT Contact_ID FROM contacts WHERE Contact_Name =  ?");
                    preparedStatement.setString(1, getContact);
                    resultSet = preparedStatement.executeQuery();


                    while (resultSet.next()) {
                        getContactID = resultSet.getInt("Contact_ID");
                        System.out.println("Get Contact ID " + getContactID);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ObservableList<Appointment> Appointments = FXCollections.observableArrayList();

                Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
                Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
                Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
                Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
                Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
                End.setCellValueFactory(new PropertyValueFactory<>("End"));
                Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));


                try {
                    Appointments.addAll(getAllAppointments(getContactID));
                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

                AppointmentTable.setItems(Appointments);
            }
        });

        TypeComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String getMonth = (String) MonthComboBox.getValue();

                String getType = (String) TypeComboBox.getValue();

                System.out.println(getMonth);

                int count =0;
                try {
                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;
                    preparedStatement = JDBC.connection.prepareStatement("SELECT COUNT(*) FROM appointments WHERE MONTH(Start) = MONTH(STR_TO_DATE(?, '%M')) AND Type =?");
                    preparedStatement.setString(1,getMonth);
                    preparedStatement.setString(2,getType);

                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        if (rs.next()) {
                            count = rs.getInt(1); // retrieve the count value from the first column of the first row
                        }
                    }

                } catch (SQLException ex) {
                    // handle any errors
                    ex.printStackTrace();
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Report - Total For Month and Type");
                alert.setContentText("TOTAL : " + count + " | " + "MONTH : " +  getMonth + " " + " TYPE : " + getType);
                alert.show();
            }
        });
        try {

            JDBC.openConnection();
            Statement statement = JDBC.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT(Type) FROM Appointments");

            ObservableList<String> contacts = FXCollections.observableArrayList();
            while (resultSet.next()) {
                String getType = resultSet.getString("Type");
                contacts.add(String.valueOf(getType));
                TypeComboBox.setItems(contacts);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void onActionGoHome(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/LoggedIn.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionCount(ActionEvent event) throws IOException {

        int count =0;
        try {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            preparedStatement = JDBC.connection.prepareStatement("SELECT COUNT(*) FROM customers");

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1); // retrieve the count value from the first column of the first row
                }
            }

        } catch (SQLException ex) {
            // handle any errors
            ex.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Report - Total Number of Customers");
        alert.setContentText("TOTAL : " + count );
        alert.show();
    }

    public static ObservableList<Appointment> getAllAppointments(int getContactID) throws SQLException, Exception {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        JDBC.openConnection();


        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        preparedStatement = JDBC.connection.prepareStatement("SELECT * FROM appointments WHERE Contact_ID = ?");
        preparedStatement.setInt(1, getContactID);
        resultSet = preparedStatement.executeQuery();

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
}

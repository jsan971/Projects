package controller;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

/***
 * The LoggedIn class manages our scene for the LoggedIn.fxml.
 *
 * This controller is initializing our LoggedIn view that includes event handlers
 * allowing to select specific options such as creating, viewing, updating, deleting
 * customer appointments or customer records. The LoggedIn
 * scene includes buttons allowing user interaction.
 *
 * There is is where our alerts will be displayed if there is an appointment is within 15 minutes or less, if not then
 * no appointments are displayed. This uses boolean login to determine if an alert has been displayed or not.
 * In the initialize method below, this lets us setup our event handlers for the user
 * interaction with our buttons and scene changes. This is where the main logic of our controller resides in.
 */

public class LoggedInController implements Initializable {
    Stage stage;
    Parent scene;


    /**
     * This method initializes our appointment details controller.
     * This is called shortly after the controller and our view is present.
     * @param url For locations to resolve a particular path for the main object, the location is typically unknown.
     * @param resourceBundle This will localize our main object
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        JDBC.openConnection();

        ZonedDateTime currentLocalTime = ZonedDateTime.now(ZoneId.systemDefault());

        try {
            boolean displayAlert = false;
            JDBC.openConnection();

            Statement statement = JDBC.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Start, Appointment_ID FROM Appointments");

            while (resultSet.next()) {

                Timestamp forAlert = resultSet.getTimestamp("Start");
                ZonedDateTime forAlertZoneDateTime = forAlert.toInstant().atZone(ZoneId.systemDefault());
                int getAppointmentID = resultSet.getInt("Appointment_ID");

                Duration fifteenalert = Duration.between(forAlertZoneDateTime, currentLocalTime);


                if(!displayAlert && fifteenalert.abs().toMinutes() <= 15){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Appointment Alert");
                    alert.setContentText("Appointment is in 15 minutes or less. Appointment ID : " + getAppointmentID + " " +
                            "Date : " + forAlert);
                    alert.show();
                    displayAlert = true;
                }
            }
            if(!displayAlert){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Appointment Alert");
                alert.setContentText("No Appointment");
                alert.show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void onActionReports(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionCreateCustomerRecord(ActionEvent event) throws IOException {
        //System.out.println("Create customer record was clicked.");
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/CreateCustomerRecord.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionUpdateCustomerRecord(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/CustomerDetails.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionUpdateAppointmentRecord(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/AppointmentDetails.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionCreateAppointmentRecord(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/CreateAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


}

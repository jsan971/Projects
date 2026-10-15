package controller;
import helper.AppointmentDao;
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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

/***
 * The CreateAppointmentController class manages our scene for the CreateAppointments.fxml.
 *
 * This controller is initializing our create appointments view that includes event handlers
 * to allow the user to create and schedule appointments. The CreateAppointment
 * scene includes various text fields, combo boxes and a date picker for user interaction.
 *
 * In the initialize method below, this lets us setup our event handlers and allowing
 * the end user to create and schedule customer appointments. This is where the main logic of our controller resides in.
 */

public class CreateAppointmentController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TextField TitleField;

    @FXML
    private TextField DescriptionField;

    @FXML
    private TextField LocationField;

    @FXML
    private TextField TypeField;

    @FXML
    private DatePicker StartDatePicker;

    @FXML
    private TextField StartTimeID;

    @FXML
    private TextField EndTimeID;

    @FXML
    private ComboBox UserIDComboBox;

    @FXML
    private ComboBox ContactComboBox;

    @FXML
    private ComboBox CustomerIDComboBox;

    @FXML
    private Button Create_Record_Button;

    int getUserID;
    int getCustomerID;
    String getContact;
    int getContactID;

    /**
     * This method initializes our appointment details controller.
     * This is called shortly after the controller and our view is present.
     * @param url For locations to resolve a particular path for the main object, the location is typically unknown.
     * @param resourceBundle This will localize our main object
     */

    public void initialize(URL url, ResourceBundle resourceBundle) {
        JDBC.openConnection();

        try {
            Statement statement = JDBC.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT User_ID FROM users");

            ObservableList<Integer> users = FXCollections.observableArrayList();
            while(resultSet.next()){
                int userid = resultSet.getInt("User_ID");
                users.add(userid);
                UserIDComboBox.setItems(users);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        UserIDComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getUserID = (int) UserIDComboBox.getValue();
            }
        });

        try {
            Statement statement = JDBC.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Contact_Name FROM contacts");

            ObservableList<String> contacts = FXCollections.observableArrayList();
            while(resultSet.next()){
                String contactname = resultSet.getString("Contact_Name");
                contacts.add(String.valueOf(contactname));
                ContactComboBox.setItems(contacts);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Statement statement = JDBC.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Customer_ID FROM customers");

            ObservableList<Integer> customers = FXCollections.observableArrayList();
            while(resultSet.next()){
                int customerid = resultSet.getInt("Customer_ID");
                customers.add(customerid);
                CustomerIDComboBox.setItems(customers);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        CustomerIDComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getCustomerID = (int) CustomerIDComboBox.getValue();
            }
        });

        ContactComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;

                    getContact = (String) ContactComboBox.getValue();

                    preparedStatement = JDBC.connection.prepareStatement("SELECT Contact_ID FROM contacts WHERE Contact_Name =  ?");
                    preparedStatement.setString(1,getContact);
                    resultSet = preparedStatement.executeQuery();


                    while(resultSet.next()){
                        getContactID = resultSet.getInt("Contact_ID");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        Create_Record_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(getContactID == 0 || getUserID == 0 || getCustomerID == 0 || TitleField.getText().isEmpty() || DescriptionField.getText().isEmpty() || LocationField.getText().isEmpty() || TypeField.getText().isEmpty() || StartTimeID.getText().isEmpty()
                        || EndTimeID.getText().isEmpty() || StartTimeID.getText().isEmpty()  || StartDatePicker.getValue() == null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Please enter all required fields.");
                    alert.show();
                }
                else {
                    try {
                        JDBC.openConnection();

                        ZoneId easternTimeZone = ZoneId.of("America/New_York");

                        String StartTimeStr = StartTimeID.getText();

                        String[] components = StartTimeStr.split(":");
                        int hour = Integer.parseInt(components[0]);
                        int minute = Integer.parseInt(components[1]);
                        int second = Integer.parseInt(components[2]);

                        ZonedDateTime starttimestampEST = ZonedDateTime.now(easternTimeZone)
                                .withHour(hour)
                                .withMinute(minute)
                                .withSecond(second)
                                .withNano(0);

                        ZonedDateTime startTimeEst = ZonedDateTime.now(easternTimeZone).with(LocalTime.of(8,0,0));

                        String EndTimeStr = EndTimeID.getText();
                        components = EndTimeStr.split(":");

                         hour = Integer.parseInt(components[0]);
                         minute = Integer.parseInt(components[1]);
                         second = Integer.parseInt(components[2]);

                        ZonedDateTime endtimestampEST = ZonedDateTime.now(easternTimeZone)
                                .withHour(hour)
                                .withMinute(minute)
                                .withSecond(second)
                                .withNano(0);

                        ZonedDateTime endTimeEst = ZonedDateTime.now(easternTimeZone).with(LocalTime.of(22,0,0));

                        Timestamp checkstart = Timestamp.valueOf(StartDatePicker.getValue() + " " + StartTimeID.getText());
                        Timestamp checkend = Timestamp.valueOf(StartDatePicker.getValue() + " " + EndTimeID.getText()); // end time of your timestamp range

                        String sql = "SELECT COUNT(*) FROM appointments WHERE (Start <= ? AND End >= ?)";
                        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
                        preparedStatement.setTimestamp(1, checkstart);
                        preparedStatement.setTimestamp(2, checkend);

                        ResultSet resultSet = preparedStatement.executeQuery();

                        if(starttimestampEST.isAfter(startTimeEst) && endtimestampEST.isBefore(endTimeEst)){
                            System.out.println("Lets check for overlapping times...");

                            if (resultSet.next()) {
                                int count = resultSet.getInt(1);
                                if (count > 0) {
                                    // overlapping timestamp range found
                                    System.out.println("Overlapping Timestamp Found");
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setContentText("Pick another date, overlap");
                                    alert.show();
                                } else {
                                    // no overlapping timestamp range found
                                    System.out.println("NO Overlapping Timestamp Found");
                                    AppointmentDao.CreateAppointmentRecord(event, TitleField.getText(), DescriptionField.getText(), LocationField.getText(), TypeField.getText(), StartDatePicker.getValue(), StartTimeID.getText(), EndTimeID.getText(), getCustomerID, getUserID, getContactID );
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setContentText("Record Created! No Overlapping times");
                                    alert.show();

                                }
                            }

                        }
                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Invalid Time...");
                            alert.show();
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });


    }
    @FXML
    void onActionGoHome(ActionEvent event) throws IOException {
        //System.out.println("Create customer record was clicked.");
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/LoggedIn.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}

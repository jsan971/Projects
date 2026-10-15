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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * The AppointmentDetailsController class manages our scene for the AppointmentDetails.fxml.
 *
 * This controller is initializing our appointment details view that includes event handlers
 * to allow the user to update, delete, or view appointment details.  The appointment details
 * scene also has a tableview, combo boxes, buttons and text fields allowing user interaction.
 *
 * In the initialize method below, this lets us setup our event handlers and displaying
 * specific data to the end user. This is where the main logic of our controller resides in.
 */

public class AppointmentDetailsController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TableView<Appointment> AppointmentTable;

    @FXML
    private TableColumn<?, ?> Appointment_ID;

    @FXML
    private TableColumn<?, ?> Title;

    @FXML
    private TableColumn<?, ?> Description;

    @FXML
    private TableColumn<?, ?> Location;

    @FXML
    private TableColumn<?, ?> Type;

    @FXML
    private TableColumn<?, ?> Start;

    @FXML
    private TableColumn<?, ?> End;

    @FXML
    private TableColumn<?, ?> Customer_ID;

    @FXML
    private TableColumn<?, ?> User_ID;

    @FXML
    private TableColumn<?, ?> Contact_ID;

    @FXML
    private TextField AppointmentID;

    @FXML
    private TextField TitleID;

    @FXML
    private TextField DescriptionID;

    @FXML
    private TextField LocationID;

    @FXML
    private TextField TypeID;

    @FXML
    private TextField UserID;

    @FXML
    private TextField ContactID;

    @FXML
    private TextField CustomerID;

    @FXML
    private TextField StartDateID;

    @FXML
    private TextField EndDateID;

    @FXML
    private TextField StartTimeID;

    @FXML
    private TextField EndTimeID;

    @FXML
    private ComboBox UserComboBox;

    @FXML
    private ComboBox ContactComboBox;

    @FXML
    private ComboBox CustomerComboBox;

    @FXML
    private DatePicker DatePickerID;

    @FXML
    private Button Update_Button;

    int getUserID;
    String getContact;
    int getContactID;
    int getCustomerID;

    /**
     * This method initializes our appointment details controller.
     * This is called shortly after the controller and our view is present.
     * @param url For locations to resolve a particular path for the main object, the location is typically unknown.
     * @param resourceBundle This will localize our main object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Appointment> Appointments = FXCollections.observableArrayList();

        Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<>("End"));
        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        User_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        Contact_ID.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));


        try {
            Appointments.addAll(AppointmentDao.getAllAppointments());
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

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

        try {
            Statement statement = JDBC.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Customer_ID FROM customers");

            ObservableList<Integer> customers = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int customerid = resultSet.getInt("Customer_ID");
                customers.add(customerid);
                CustomerComboBox.setItems(customers);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            Statement statement = JDBC.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT User_ID FROM users");

            ObservableList<Integer> users = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int userid = resultSet.getInt("User_ID");
                users.add(userid);
                UserComboBox.setItems(users);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AppointmentTable.setItems(Appointments);

    }

    @FXML
    void onActionGoHome(ActionEvent event) throws IOException {
        //System.out.println("Create customer record was clicked.");
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/LoggedIn.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @FXML
    void onDelete(ActionEvent event) throws IOException, SQLException {

        Appointment appointment = AppointmentTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to delete this apppointment?");
        alert.setContentText("Appointment to be deleted ID : " + appointment.getAppointment_ID() + " Appointment Type : " + appointment.getType());
        ButtonType response = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (response == ButtonType.OK) {
            AppointmentDao.DeleteAppointment(event, appointment.getAppointment_ID());

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText("Appointment Deleted ID : " + appointment.getAppointment_ID() + " Appointment Type : " + appointment.getType());
            alert2.show();
        } else if (response == ButtonType.CANCEL) {
            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setHeaderText("No appointment deleted");
            alert3.show();
        }

        ObservableList<Appointment> Appointments = FXCollections.observableArrayList();

        try {
            Appointments.addAll(AppointmentDao.getAllAppointments());

        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        AppointmentTable.setItems(Appointments);
    }

    @FXML
    void onActionByMonth(ActionEvent event) throws Exception {

        ObservableList<Appointment> currentMonth = FXCollections.observableArrayList();

        Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<>("End"));
        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        User_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        Contact_ID.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));


        try {
            currentMonth.addAll(AppointmentDao.getCurrentMonth());
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        AppointmentTable.setItems(currentMonth);

        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setHeaderText("Filtered by current month...");
        alert2.show();

    }

    @FXML
    void onActionCurrentWeek(ActionEvent event) throws Exception {

        ObservableList<Appointment> currentWeek = FXCollections.observableArrayList();

        Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<>("End"));
        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        User_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        Contact_ID.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));

        try {
            currentWeek.addAll(AppointmentDao.getCurrentWeek());
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        AppointmentTable.setItems(currentWeek);

        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setHeaderText("Filtered by current week...");
        alert2.show();

    }

    @FXML
    void onActionAll(ActionEvent event) throws Exception {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<>("End"));
        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        User_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        Contact_ID.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));

        try {
            allAppointments.addAll(AppointmentDao.getAllAppointments());
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        AppointmentTable.setItems(allAppointments);

        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setHeaderText("Filtered by all appointments...");
        alert2.show();

    }

    @FXML
    void onActionDetails(ActionEvent event) throws Exception {

        Appointment appointment = AppointmentTable.getSelectionModel().getSelectedItem();

        if (appointment != null) {

            int Appointment_ID = appointment.getAppointment_ID();

            AppointmentDao.getAppointment(Appointment_ID);

            AppointmentID.setText(String.valueOf(appointment.getAppointment_ID()));
            TitleID.setText(String.valueOf(appointment.getTitle()));
            DescriptionID.setText(String.valueOf(appointment.getDescription()));
            TypeID.setText(String.valueOf(appointment.getType()));
            LocationID.setText(String.valueOf(appointment.getLocation()));
            StartDateID.setText(String.valueOf(appointment.getStart()));
            EndDateID.setText(String.valueOf(appointment.getEnd()));
            UserID.setText(String.valueOf(appointment.getUser_ID()));
            ContactID.setText(String.valueOf(appointment.getContact_ID()));
            CustomerID.setText(String.valueOf(appointment.getCustomer_ID()));


            UserComboBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    getUserID = (int) UserComboBox.getValue();
                }
            });


            CustomerComboBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    getCustomerID = (int) CustomerComboBox.getValue();
                }
            });

            ContactComboBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    getContact = (String) ContactComboBox.getValue();
                    try {

                        PreparedStatement preparedStatement = null;
                        ResultSet resultSet = null;

                        preparedStatement = JDBC.connection.prepareStatement("SELECT Contact_ID FROM contacts WHERE Contact_Name =  ?");
                        preparedStatement.setString(1, getContact);
                        resultSet = preparedStatement.executeQuery();


                        while (resultSet.next()) {
                            getContactID = resultSet.getInt("Contact_ID");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });


            Update_Button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Update Clicked");

                    if ( getContactID == 0 || getUserID == 0 || getCustomerID == 0 || TitleID.getText().isEmpty() || DescriptionID.getText().isEmpty() || LocationID.getText().isEmpty() || TypeID.getText().isEmpty() || DatePickerID.getValue() == null || StartTimeID.getText().isEmpty()
                    ) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Please enter all required fields.");
                        alert.show();
                    } else {

                        try {

                            // Create a ZonedDateTime for the timestamp in the Eastern Time zone
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

                            Timestamp checkstart = Timestamp.valueOf(DatePickerID.getValue() + " " + StartTimeID.getText());
                            Timestamp checkend = Timestamp.valueOf(DatePickerID.getValue() + " " + EndTimeID.getText()); // end time of your timestamp range

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
                                        AppointmentDao.UpdateAppointment(event, appointment.getAppointment_ID(), TitleID.getText(), DescriptionID.getText(), LocationID.getText(),TypeID.getText(), DatePickerID.getValue(), StartTimeID.getText(), EndTimeID.getText(), getCustomerID, getUserID, getContactID);                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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

                    ObservableList<Appointment> Appointments = FXCollections.observableArrayList();

                    try {
                        Appointments.addAll(AppointmentDao.getAllAppointments());
                    } catch (Exception ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    AppointmentTable.setItems(Appointments);
                }
            });
        } else {
            System.out.println("No Appointment Record Selected");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No Appointment Record has been selected");
            alert.show();
        }
    }
}
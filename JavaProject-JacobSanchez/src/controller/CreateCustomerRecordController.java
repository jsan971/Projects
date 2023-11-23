package controller;

import helper.CustomerDao;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/***
 * The CreateCustomerRecordsController class manages our scene for the CreateCustomerRecords.fxml.
 *
 * This controller is initializing our create customer records view that includes event handlers
 * to allow the user to create customer records. The CreateCustomerRecords
 * scene includes various text fields and combo boxes for user interaction.
 *
 * In the initialize method below, this lets us setup our event handlers and allowing
 * the end user to create customer records. This is where the main logic of our controller resides in.
 *
 * Lambda Expression #1
 *
 * We are using a lambda expression for our countryComboBox, whenever the an item is picked
 * by the end user, the lambda expression is put into effect. In this particular action,
 * the country_id is retrieved once the user selects a country name from the list.
 * Then this ID is used to query the list of first_level_divisions based on the ID. This creates a filter system
 * as it will filter states based on the customers country selection.
 *
 * The part of the the lambda expression is `countryComboBox.e -> {....});` is being used rather than creating an additional
 * action listener. `e` is essentially our object for this particular action event whenever the end user
 * selects the country name from the list.
 *
 * I chose this because it improves readability and provides encapsulation. This reduces the overall need
 * to create additional classes or functions. Thus making better organized code for developers that may review your code in the future.
 *
 *
 * Lambda Expression #2
 *
 * We are using a second lambda expression on our create customer record button. When the button is clicked,
 * a series of checks will be made to ensure all fields and combo boxes have been filled out. Visual alerts are also
 * handled here letting the user know whether the user was created or they need to complete all fields.
 *
 * Once all the required fields are entered, a call will be triggered to our customer dao to store the information
 * into the customers tables in the database.
 *
 * The part of the lambda expression is `Create_Record_Button.eTwo -> {....});.  eTwo is our object that we created
 * for this action. This will get triggered as soon as the user selects this button.
 *
 * I chose this as my second lambda expression as it also improves the codes structure for organization and eliminates any redundant
 * code that traditional event handler may cause.
 */

public class CreateCustomerRecordController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TextField Customer_Name_Field;
    @FXML
    private TextField Customer_Address_Field;
    @FXML
    private TextField Customer_PostalCode_Field;
    @FXML
    private TextField Customer_PhoneNumber_Field;

    @FXML
    private ComboBox countryComboBox;
    @FXML
    private ComboBox stateComboBox;

    @FXML
    private Button Create_Record_Button;

    String getCountry;
    String getState;
    int country_ID;
    int getDivisionID;

    /**
     * This method initializes our appointment details controller.
     * This is called shortly after the controller and our view is present.
     * @param url For locations to resolve a particular path for the main object, the location is typically unknown.
     * @param resourceBundle This will localize our main object
     */

    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            JDBC.openConnection();
            Statement statement = JDBC.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Country FROM countries");

            ObservableList<String> countries = FXCollections.observableArrayList();
            while(resultSet.next()){
                String countryName = resultSet.getString("Country");
                countries.add(countryName);
                countryComboBox.setItems(countries);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // This is our Lambda Expression #1
        countryComboBox.setOnAction (e -> {

                try {
                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;

                    getCountry = (String) countryComboBox.getValue();
                    preparedStatement = JDBC.connection.prepareStatement("SELECT Country_ID FROM countries WHERE country =  ?");
                    preparedStatement.setString(1, getCountry);
                    resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        country_ID = resultSet.getInt("Country_ID");
                        //System.out.println(country_ID);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;
                    preparedStatement = JDBC.connection.prepareStatement("SELECT Division FROM first_level_divisions WHERE Country_ID = ?");
                    preparedStatement.setInt(1, country_ID);
                    resultSet = preparedStatement.executeQuery();


                    ObservableList<String> states = FXCollections.observableArrayList();
                    while (resultSet.next()) {
                        String stateName = resultSet.getString("Division");
                        states.add(stateName);
                        stateComboBox.setItems(states);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


        });

        stateComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getState = (String) stateComboBox.getValue();

                try {
                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;
                    preparedStatement = JDBC.connection.prepareStatement("SELECT Division_ID FROM first_level_divisions WHERE Division =  ?");
                    preparedStatement.setString(1,getState);
                    resultSet = preparedStatement.executeQuery();

                    while(resultSet.next()){
                        getDivisionID = resultSet.getInt("Division_ID");
                        //System.out.println(country_ID);
                    }
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        // Lambda Expression # 2

        Create_Record_Button.setOnAction(etwo -> {
        JDBC.openConnection();
            if(getCountry == null || getState ==null || Customer_Name_Field.getText().isEmpty() || Customer_Address_Field.getText().isEmpty() || Customer_PhoneNumber_Field.getText().isEmpty()
                    || Customer_PostalCode_Field.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please enter all required fields.");
                alert.show();
            }
            else {
                try {
                    CustomerDao.CreateCustomerRecord(etwo, Customer_Name_Field.getText(), Customer_Address_Field.getText(), Customer_PhoneNumber_Field.getText(),
                            Customer_PostalCode_Field.getText(), getCountry, getState, getDivisionID);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Record Created!");
                    alert.show();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
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

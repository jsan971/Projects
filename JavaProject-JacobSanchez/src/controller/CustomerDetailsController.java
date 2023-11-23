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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import java.io.IOException;
import java.net.URL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * The CustomerDetailsController class manages our scene for the CustomerDetails.fxml.
 *
 * This controller is initializing our customer details view that includes event handlers
 * to allow the user to update, delete, or view customer details.  The customer details
 * scene also has a tableview, buttons, combo boxes and text fields allowing user interaction.
 *
 * In the initialize method below, this lets us setup our event handlers and displaying
 * specific data to the end user. This is where the main logic of our controller resides in.
 */


public class CustomerDetailsController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TableView<Customer> CustomerTable;

    @FXML
    private TableColumn<?,?> Customer_ID;

    @FXML
    private TableColumn<?,?> Customer_Name;

    @FXML
    private TableColumn<?,?> Address;

    @FXML
    private TableColumn<?,?> Postal_Code;

    @FXML
    private TableColumn<?,?> Phone;

    @FXML
    private TableColumn<?,?> Division_ID;

    @FXML
    private TextField CId;

    @FXML
    private TextField CName;

    @FXML
    private TextField CAddress;

    @FXML
    private TextField CPCode;

    @FXML
    private TextField CPhone;

    @FXML
    private ComboBox CCountry ;

    @FXML
    private ComboBox CState;

    @FXML
            private Button Update_Button;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Customer> Customers = FXCollections.observableArrayList();
        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        Customer_Name.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        Postal_Code.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Division_ID.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));

        try{
            Customers.addAll(CustomerDao.getAllCustomers());
        } catch (Exception ex){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        CustomerTable.setItems(Customers);

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

        Customer customer = CustomerTable.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you want to delete this customer?");
            alert.setContentText("Customer to be deleted : " + customer.getCustomer_Name());
            ButtonType response = alert.showAndWait().orElse(ButtonType.CANCEL);

        if(response == ButtonType.OK){
            CustomerDao.DeleteCustomer(event, customer.getCustomer_ID());

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText("Customer Deleted : " + customer.getCustomer_Name());
            alert2.show();
        }
        else if (response == ButtonType.CANCEL){
            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setHeaderText("No customer deleted");
            alert3.show();
        }

        ObservableList<Customer> Customers = FXCollections.observableArrayList();

        try{
            Customers.addAll(CustomerDao.getAllCustomers());

        } catch (Exception ex){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        CustomerTable.setItems(Customers);

    }
    @FXML
    void onActionDetails(ActionEvent event) throws Exception {

        Customer customer = CustomerTable.getSelectionModel().getSelectedItem();

       if(customer != null){

           String Customer_Name = customer.getCustomer_Name();

           CustomerDao.getCustomer(Customer_Name);

           CId.setText(String.valueOf(customer.getCustomer_ID()));
           CName.setText(String.valueOf(customer.getCustomer_Name()));
           CAddress.setText(String.valueOf(customer.getAddress()));
           CPCode.setText(String.valueOf(customer.getPostal_Code()));
           CPhone.setText(String.valueOf(customer.getPhone()));

           try {
               JDBC.openConnection();
               Statement statement = JDBC.connection.createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT Country FROM countries");

               ObservableList<String> countries = FXCollections.observableArrayList();
               while(resultSet.next()){
                   String countryName = resultSet.getString("Country");
                   countries.add(countryName);
                   CCountry.setItems(countries);
               }
           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }
           CCountry.setOnAction(new EventHandler<ActionEvent>() {

               @Override
               public void handle(ActionEvent event) {
                   try {
                       PreparedStatement preparedStatement = null;
                       ResultSet resultSet = null;

                       getCountry = (String) CCountry.getValue();
                       preparedStatement = JDBC.connection.prepareStatement("SELECT Country_ID FROM countries WHERE country =  ?");
                       preparedStatement.setString(1,getCountry);
                       resultSet = preparedStatement.executeQuery();

                       while(resultSet.next()){
                           country_ID = resultSet.getInt("Country_ID");
                           //System.out.println(country_ID);
                       }
                   }catch (SQLException throwables) {
                       throwables.printStackTrace();
                   }
                   try {
                       PreparedStatement preparedStatement = null;
                       ResultSet resultSet = null;
                       preparedStatement = JDBC.connection.prepareStatement("SELECT Division FROM first_level_divisions WHERE Country_ID = ?");
                       preparedStatement.setInt(1, country_ID);
                       resultSet = preparedStatement.executeQuery();


                       ObservableList<String> states = FXCollections.observableArrayList();
                       while(resultSet.next()){
                           String stateName = resultSet.getString("Division");
                           states.add(stateName);
                           CState.setItems(states);
                       }
                   } catch (SQLException throwables) {
                       throwables.printStackTrace();
                   }
               }
           });

           CState.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   getState = (String) CState.getValue();

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

           Update_Button.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   if(getCountry == null || getState ==null || CName.getText().isEmpty() || CAddress.getText().isEmpty() ||
                   CPhone.getText().isEmpty() || CPCode.getText().isEmpty()) {
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.setContentText("Please enter all required fields.");
                       alert.show();
                   }
                       else{
                           try {
                               CustomerDao.UpdateCustomer(event, customer.getCustomer_ID(), CName.getText(), CAddress.getText(), CPhone.getText(),
                                       CPCode.getText(),getCountry, getState, getDivisionID);

                               System.out.println(CName.getText());
                               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                               alert.setContentText("Customer Updated!");
                               alert.show();

                           } catch (SQLException throwables) {
                               throwables.printStackTrace();
                           }
                       }


                       ObservableList<Customer> Customers = FXCollections.observableArrayList();

                       try{
                           Customers.addAll(CustomerDao.getAllCustomers());
                       } catch (Exception ex){
                           Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                       }

                       CustomerTable.setItems(Customers);
                   }

           });

       } else {
           System.out.println("No Customer Record Selected");
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No Customer Record has been selected");
          alert.show();
        }
    }
}

package controller;
import helper.login;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;

/***
 * The controller class manages our scene for the sample.fxml.
 *
 * This controller is initializing our main view that includes event handlers
 * to allow the user to log into the application.  The sample.fxml
 * scene includes text fields and buttons allowing user interaction.
 *
 * In the initialize method below, this lets us setup our event handlers for our username
 * and password. This is our very first controller that gets called in our application.
 */

public class Controller implements Initializable {

    @FXML
    private Button login_button;

    @FXML
    private Label country;

    @FXML
    private TextField username_field;

    @FXML
    private TextField password_field;

    /**
     * This method initializes our "Main" controller.
     * This is called shortly after the controller and our view is present.
     * @param url For locations to resolve a particular path for the main object, the location is typically unknown.
     * @param resourceBundle This will localize our main object
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ZoneId zoneID = ZoneId.systemDefault();
        String zone = zoneID.getId();
        country.setText(zone);

        System.out.println("Start of the login form.");
        login_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    login.Login(event, username_field.getText(), password_field.getText());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

package helper;
import controller.LoggedInController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/** This class allows the login functionality. */

public class login {

    /** This is our SceneChanger method. This will process a scene change upon a successful login.
     @param fxmlFile For fxmlfile
     @param event For event
     */

    public static void SceneChanger(ActionEvent event, String fxmlFile) throws IOException {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(login.class.getResource(fxmlFile));
        root = loader.load();
        LoggedInController loggedInController = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    /** This is our Login method. This will process a scene change upon a successful login.
     * This takes two parameters, username and password. The data will then loop through the users
     * table checking for a matching username or password. User activity is also tracked and logged in
     * LoginActivity.txt file located in the root of the application. Depending on the local language set,
     there will be an automatic translation of errors or alerts in the GUI. Ex) French is detected.
     @param username For username
     @param password For password
     @param event For event
     */

    public static void Login(ActionEvent event, String username, String password) throws Exception {
        ResourceBundle rb = ResourceBundle.getBundle("sample/Detect_fr_FR");

        JDBC.openConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

            try {

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String timestamp = dateFormat.format(date);

                File file = new File("C:/Users/LabUser/IdeaProjects/JavaProject-JacobSanchezAttempt3/LoginActivity.txt");
                FileWriter fileWriter = new FileWriter(file, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);

                preparedStatement = JDBC.connection.prepareStatement("SELECT password FROM users WHERE User_Name =  ?");
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();

                if (!resultSet.isBeforeFirst()) {

                    if (Locale.getDefault().getLanguage().equals("en")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("User not found");
                        alert.show();
                        printWriter.println(timestamp + " - User Not Found Unsuccessful Login");
                    } else if (Locale.getDefault().getLanguage().equals("fr")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(rb.getString("User") + " " + rb.getString("not") + " " + rb.getString("found"));
                        alert.show();
                        printWriter.println(timestamp + " - User : " + username +" Not Found Unsuccessful Login");
                    }
                } else {
                    while (resultSet.next()) {
                        String getPass = resultSet.getString("Password");
                        if (getPass.equals(password)) {
                            SceneChanger(event, "../view/LoggedIn.fxml");
                            printWriter.println(timestamp + " - Login Successful - User : " + username);

                        } else {
                            if (Locale.getDefault().getLanguage().equals("en")) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("Passwords did not match");
                                alert.show();
                                printWriter.println(timestamp + " - Incorrect Password Unsuccessful Login - User : " + username);

                            } else if (Locale.getDefault().getLanguage().equals("fr")) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText(rb.getString("Passwords") + " " + rb.getString("did") + " " + rb.getString("not") + " " + rb.getString("match"));
                                alert.show();
                            }
                        }
                    }
                }
                 printWriter.close();
                  fileWriter.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (JDBC.connection != null) {
                    try {
                        JDBC.connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

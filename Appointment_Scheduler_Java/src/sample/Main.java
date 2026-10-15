package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/** This class creates an app to create customer records and appointments.*/
public class Main extends Application {

    /** This method loads our first stage.
      The stage is our login page.
     @param primaryStage The first stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        Locale locale = Locale.getDefault();

        //Locale.setDefault(new Locale("fr"));

        loader.setLocation(getClass().getResource("../view/Sample.fxml"));

        if(Locale.getDefault().getLanguage().equals("en")){
            loader.setResources(ResourceBundle.getBundle("sample/Detect_en_US"));
        }
        else if(Locale.getDefault().getLanguage().equals("fr")){
            loader.setResources(ResourceBundle.getBundle("sample/Detect_fr_FR"));
        }
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }

    /** This is our main method. This is the first method that is triggered when our program runs.
     @param args args
     */
    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}




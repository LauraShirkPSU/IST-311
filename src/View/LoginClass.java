package View;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LoginClass extends Application
{
    
    @Override
    public void start(Stage primaryStage)
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Finance Tracker");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}

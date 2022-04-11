package Controllers;

import Model.Authentication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import javafx.stage.Stage;

public class LoginController implements Initializable
{

    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnLogin;
    

    @FXML
    private Button exitButton;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        // TODO
    }    


    @FXML
    private void btnExit(ActionEvent event)
    {   
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void login(ActionEvent event) throws IOException, SQLException
    {   
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        
        if(username.equals("") || password.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Username and Password must be populated");
        }
        else
        {            
            Authentication auth = new Authentication();
            
            if(auth.isAuthenticated(username, password) == true)
            {   
                JOptionPane.showMessageDialog(null, "Login Successful");
                SceneController sc = new SceneController();
                sc.switchToDashboardScene(event);               
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Login Failed");
                tfUsername.setText("");
                tfPassword.setText("");
                tfUsername.requestFocus();                    
            }                
        }
        
    }    

}

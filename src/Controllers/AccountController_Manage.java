/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class AccountController_Manage implements Initializable
{

    @FXML
    private Button btnGoGack;
    @FXML
    private Label labelUsername;
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfPassWord;
    @FXML
    private Button btnUpdate;
    @FXML
    private Label labelPassword;
    @FXML
    private Label labelNickname;
    @FXML
    private TextField tfNickname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        // TODO
    }    
    
    @FXML
    private void goBack(ActionEvent event) throws IOException
    {
        SceneController sc = new SceneController();
        sc.switchToDashboardScene(event);
    }

    @FXML
    private void updateAccount(ActionEvent event)
    {
        
    }
    
}

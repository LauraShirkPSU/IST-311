/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class AddIncomeController implements Initializable
{

    @FXML
    private Button btnGoBack;
    @FXML
    private Label lblVal_IncomeDate;
    @FXML
    private Label lblVal_IncomeAmount;
    @FXML
    private Label lblVal_IncomeEntity;
    @FXML
    private Button btnSave;

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
        sc.switchToIncomeScene(event);
    }

    @FXML
    private void saveIncome(ActionEvent event)
    {
    }
    
}

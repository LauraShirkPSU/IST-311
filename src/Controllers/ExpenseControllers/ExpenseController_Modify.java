/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers.ExpenseControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class ExpenseController_Modify implements Initializable
{

    @FXML
    private TextField tfExpenseAmount;
    @FXML
    private DatePicker dpExpenseDate;
    @FXML
    private ChoiceBox cbExpenseCategory;
    @FXML
    private Button btnGoBack;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDeleteExpense;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void goBack(ActionEvent event)
    {
    }

    @FXML
    private void saveExpense(ActionEvent event)
    {
    }

    @FXML
    private void deleteExpense(ActionEvent event)
    {
    }
    
}

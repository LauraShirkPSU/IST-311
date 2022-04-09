/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.BudgetCategoriesDAO;
import Model.ExpenseDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class ExpenseController_Add implements Initializable
{    
    @FXML
    private DatePicker dpExpenseDate;
    @FXML
    private TextField tfExpenseAmount;
    @FXML
    private ChoiceBox dropCatList;    
    @FXML
    private Button btnGoBack;
    @FXML
    private Button btnSaveExpense;
        
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ArrayList<String> categories = new ArrayList<>();
        BudgetCategoriesDAO bcDAO = new BudgetCategoriesDAO();
        try {
            categories = bcDAO.getCategoryNames();
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseController_Add.class.getName()).log(Level.SEVERE, null, ex);
        }
        dropCatList.setItems(FXCollections.observableArrayList(categories));    
        
        btnSaveExpense.setDisable(true);
        
        
    }
    
   

    @FXML
    private void goBack(ActionEvent event) throws IOException
    {
        SceneController sc = new SceneController();
        sc.switchToExpenseScene(event);
    }

    @FXML
    private void saveExpense(ActionEvent event) throws SQLException
    {
        LocalDate date = dpExpenseDate.getValue();
        Double newExpenseAmount = Double.parseDouble(tfExpenseAmount.getText());
        String newExpenseCategory = dropCatList.getSelectionModel().getSelectedItem().toString();
       
        ExpenseDAO dao = new ExpenseDAO();        
        
        switch (dao.addExpenseEntry(date.toString(), newExpenseAmount, newExpenseCategory)) 
        {
            case 0 -> JOptionPane.showMessageDialog(null, "Please Try Again");
            default -> 
            {
                JOptionPane.showMessageDialog(null, "Record Successfully Added");  
                clearFields();
            } 
        }
        
    }
    
    private void clearFields()
    {   
        tfExpenseAmount.setText("");
        dpExpenseDate.requestFocus();
    }

    
}

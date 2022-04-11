/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers.ExpenseControllers;

import Controllers.SceneController;
import Model.BudgetCategoryModel.BudgetCategoryDAO;
import Model.ExpenseModel.ExpenseDAO;
import Model.Validator;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    @FXML
    private Label lblVal_BudgetCategory;
    @FXML
    private Label lblVal_ExpenseDate;
    @FXML
    private Label lblVal_ExpenseAmount;

    private Validator validator = new Validator();
        
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ArrayList<String> categories = new ArrayList<>();
        BudgetCategoryDAO bcDAO = new BudgetCategoryDAO();
        try {
            categories = bcDAO.getCategoryNames();
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseController_Add.class.getName()).log(Level.SEVERE, null, ex);
        }
        dropCatList.setItems(FXCollections.observableArrayList(categories));    
        resetValidationControls();  
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
        resetValidationControls();
        if (ValidateForm()) 
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
    }
    
    private void clearFields()
    {   
        tfExpenseAmount.setText("");
        dpExpenseDate.requestFocus();
    }

    private void resetValidationControls() {
        System.out.println("Resetting Validation Controls");
        lblVal_ExpenseAmount.setText("");
        lblVal_ExpenseDate.setText("");
        lblVal_BudgetCategory.setText("");
        lblVal_ExpenseAmount.setVisible(false);
        lblVal_ExpenseDate.setVisible(false);
        lblVal_BudgetCategory.setVisible(false);
    }

    private Boolean ValidateForm() {
        Boolean blnIsValid = true;
        if (dpExpenseDate.getValue() == null) {
            lblVal_ExpenseDate.setText("Required Field");
            lblVal_ExpenseDate.setVisible(true);
            blnIsValid = false;
        }
        if (tfExpenseAmount.getText() == "") {
            lblVal_ExpenseAmount.setText("Required Field");
            lblVal_ExpenseAmount.setVisible(true);
            blnIsValid = false;
        } else {
            //if (!isNumeric(tfExpenseAmount.getText())) {
            if (!validator.isNumeric(tfExpenseAmount.getText())) {
                lblVal_ExpenseAmount.setText("Value is not numeric");
                lblVal_ExpenseAmount.setVisible(true);
                blnIsValid = false;
            }
        }

        if (dropCatList.getSelectionModel().isEmpty()) {
            lblVal_BudgetCategory.setText("Required Field");
            lblVal_BudgetCategory.setVisible(true);
            blnIsValid = false;
        }
        return blnIsValid;
    }
    
}

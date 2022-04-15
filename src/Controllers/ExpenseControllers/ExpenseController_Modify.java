/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers.ExpenseControllers;

import Controllers.SceneController;
import Model.BudgetCategoryModel.BudgetCategoryDAO;
import Model.ExpenseModel.Expense;
import Model.ExpenseModel.ExpenseDAO;
import Model.Session;
import Model.SessionDAO;
import Model.Validator;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

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
    
    private Expense currentExpense;
    private ExpenseDAO expenseDAO;
    private Session session;
    private SessionDAO sessionDAO;
    @FXML
    private Label lbl_Val_ExpenseDate;
    @FXML
    private Label lbl_Val_ExpenseAmount;
    @FXML
    private Label lbl_Val_ExpenseCategory;

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
        cbExpenseCategory.setItems(FXCollections.observableArrayList(categories));
        
        try {
            sessionDAO = new SessionDAO();
            currentExpense = new Expense();
            expenseDAO = new ExpenseDAO();
            
            session = sessionDAO.getSession();
            int expenseID = session.getExpenseIndex();
            currentExpense = expenseDAO.getExpenseObject(expenseID);
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseController_Modify.class.getName()).log(Level.SEVERE, null, ex);
        }
        setFormFields(currentExpense);
        resetValidationControls();
        
    }    

    private void setFormFields(Expense expense)
    {
        dpExpenseDate.setValue(LocalDate.parse(expense.getTransactionDate().toString(), DateTimeFormatter.ISO_DATE));
        tfExpenseAmount.setText(expense.getTransactionAmount().toString());
        cbExpenseCategory.setValue(expense.getExpenseCategory());
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
            String newExpenseCategory = cbExpenseCategory.getSelectionModel().getSelectedItem().toString();
            int expenseID = currentExpense.getExpenseID();
       
            ExpenseDAO dao = new ExpenseDAO();        
        
            switch (dao.updateExpenseEntry(date.toString(), newExpenseAmount, newExpenseCategory, expenseID)) 
            {
                case 0 -> JOptionPane.showMessageDialog(null, "Please Try Again");
                default -> 
                {
                    JOptionPane.showMessageDialog(null, "Record Successfully Updated");  
                    clearFields();
                } 
            }
        }
    }

    @FXML
    private void deleteExpense(ActionEvent event) throws SQLException
    {
        resetValidationControls();
        if (ValidateForm())
        {
            int expenseID = currentExpense.getExpenseID();
            
            ExpenseDAO dao = new ExpenseDAO();
            
            switch (dao.deleteExpenseEntry(expenseID))
            {
                case 0 -> JOptionPane.showMessageDialog(null, "Please Try Again");
                default ->
                {
                    JOptionPane.showMessageDialog(null, "Record Successfully Deleted");
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

    private void resetValidationControls() 
    {
        lbl_Val_ExpenseAmount.setText("");
        lbl_Val_ExpenseDate.setText("");
        lbl_Val_ExpenseCategory.setText("");
        lbl_Val_ExpenseAmount.setVisible(false);
        lbl_Val_ExpenseDate.setVisible(false);
        lbl_Val_ExpenseCategory.setVisible(false);
    }

    private Boolean ValidateForm() 
    {
        Boolean blnIsValid = true;
        if (dpExpenseDate.getValue() == null) {
            lbl_Val_ExpenseDate.setText("Required Field");
            lbl_Val_ExpenseDate.setVisible(true);
            blnIsValid = false;
        }
        if (tfExpenseAmount.getText() == "") {
            lbl_Val_ExpenseAmount.setText("Required Field");
            lbl_Val_ExpenseAmount.setVisible(true);
            blnIsValid = false;
        } else {
            if (!validator.isNumeric(tfExpenseAmount.getText())) {
                lbl_Val_ExpenseAmount.setText("Value is not numeric");
                lbl_Val_ExpenseAmount.setVisible(true);
                blnIsValid = false;
            }
        }

        if (cbExpenseCategory.getSelectionModel().isEmpty()) {
            lbl_Val_ExpenseCategory.setText("Required Field");
            lbl_Val_ExpenseCategory.setVisible(true);
            blnIsValid = false;
        }
        return blnIsValid;
    }
}

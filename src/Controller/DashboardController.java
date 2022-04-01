package Controller;

import Model.ExpenseDAO;
import Model.IncomeDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class DashboardController implements Initializable
{

    @FXML
    private Button btnBudgetCategories;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnIncome;
    @FXML
    private Button btnManageAcct;
    @FXML
    private Button btnExpenses;
    @FXML
    private Button btnReports;
    @FXML
    private Button btnNotifications;
    @FXML
    private Label label_TotalExpenses;
    @FXML
    private Label label_TotalIncome;
    @FXML
    private Label label_AvailableToSpend;
     
 
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Double availableToSpend = 0.00;
        try {
            IncomeDAO incObject = new IncomeDAO();
            label_TotalIncome.setText("Total Income to Date:         " + incObject.sumIncome().toString());
            availableToSpend = availableToSpend + incObject.sumIncome();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ExpenseDAO expObject = new ExpenseDAO();
            label_TotalExpenses.setText("Total Expenses to Date:         " + expObject.sumExpenses().toString());
            availableToSpend = availableToSpend - expObject.sumExpenses();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        label_AvailableToSpend.setText("Funds available to spend:      " + availableToSpend.toString());
        
    }    
    
    
    @FXML
    private void callBudgetCategories(ActionEvent event) throws SQLException, IOException 
    {    
        SceneController sc = new SceneController();
        sc.switchToBudgetCategoriesScene(event);
       
    }

    @FXML
    private void logout(ActionEvent event) throws IOException
    {   
        SceneController sc = new SceneController();
        sc.switchToLoginScene(event);
    }

    @FXML
    private void manageIncome(ActionEvent event) throws IOException
    {
        SceneController sc = new SceneController();
        sc.switchToIncomeScene(event);
    }

    @FXML
    private void manageAccount(ActionEvent event) throws IOException
    {  
        SceneController sc = new SceneController();
        sc.switchToManageAccountScene(event);
    }

    @FXML
    private void manageExpense(ActionEvent event) throws IOException
    {
        SceneController sc = new SceneController();
        sc.switchToExpenseScene(event);
    }

    @FXML
    private void manageReports(ActionEvent event)
    {
    }

    @FXML
    private void manageNotifications(ActionEvent event)
    {
    }

    
}

package Controllers.BudgetCategoryControllers;

import Controllers.SceneController;
import Model.Authentication;
import Model.BudgetCategoryModel.BudgetCategory;
import Model.BudgetCategoryModel.BudgetCategoryDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import Model.ExpenseModel.Expense;
import Model.ExpenseModel.ExpenseDAO;
import Model.Session;
import Model.SessionDAO;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;


public class BudgetCategoryController_Details implements Initializable
{

    @FXML
    private Button btnGoBack;
    @FXML
    private Label categoryName;
    @FXML
    private Label labelBudgetAmount;
    @FXML
    private Label labelThresholdLimit;
    @FXML
    private Label labelCategoryName;
    @FXML
    private TableView<Expense> tblCategoryExpenses;
    @FXML
    private TableColumn<Expense, Date> col_ExpenseDate;
    @FXML
    private TableColumn<Expense, Double> col_ExpenseAmount;
    @FXML
    private Label label_totalSpent;
    
    private BudgetCategory currentBudgetCategory;
    private BudgetCategoryDAO budgetCategoriesDAO;
    private Session session;
    private SessionDAO sessionDAO;
    private ExpenseDAO expDAO;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try 
        {
            sessionDAO = new SessionDAO();
            currentBudgetCategory = new BudgetCategory();
            budgetCategoriesDAO = new BudgetCategoryDAO();
            expDAO = new ExpenseDAO();
            String strExpenseCategory = null;
            try
            {
                session = sessionDAO.getSession();
                strExpenseCategory = session.getBudgetCategoryDetailsName();
                currentBudgetCategory = budgetCategoriesDAO.getBudgetCategory(strExpenseCategory);
                setFormFields(currentBudgetCategory);
                label_totalSpent.setText(expDAO.sumMonthExpenses(strExpenseCategory).toString());
                
            } catch (SQLException ex) {
                Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
            }
            col_ExpenseDate.setCellValueFactory(new PropertyValueFactory<>("TransactionDate"));
            col_ExpenseAmount.setCellValueFactory(new PropertyValueFactory<>("TransactionAmount"));
            
            ObservableList<Expense> categoryExpenses;            
            categoryExpenses = ExpenseDAO.getCategoryExpenses(strExpenseCategory);
            
            tblCategoryExpenses.setItems(categoryExpenses);
        } catch (SQLException ex) {
            Logger.getLogger(BudgetCategoryController_Details.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void goBack(ActionEvent event) throws IOException, SQLException
    {
        SceneController sc = new SceneController();
        sc.switchToBudgetCategoriesScene(event);
    }
    
    private void setFormFields(BudgetCategory bc) {
        labelCategoryName.setText(bc.getCategoryName());
        labelBudgetAmount.setText(bc.getCategoryAmt().toString());
        labelThresholdLimit.setText(bc.getThresholdLimit().toString());
        //.requestFocus();
    }
}

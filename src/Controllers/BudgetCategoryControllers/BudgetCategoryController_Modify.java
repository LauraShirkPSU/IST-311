
package Controllers.BudgetCategoryControllers;

import Controllers.SceneController;
import Model.Authentication;
import Model.BudgetCategoryModel.BudgetCategoryDAO;
import Model.BudgetCategoryModel.BudgetCategory;
import Model.Session;
import Model.SessionDAO;
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
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class BudgetCategoryController_Modify implements Initializable
{

    @FXML
    private Button btnGoBack;
    @FXML
    private TextField tfCategoryName;
    @FXML
    private TextField tfCategoryAmount;
    @FXML
    private TextField tfThresholdLimit;
    @FXML
    private Button btnSaveUpdate;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnClear;
    @FXML
    private Button btn_DeleteCategory;
    
    private BudgetCategory currentBudgetCategory;
    private BudgetCategoryDAO budgetCategoriesDAO;
    private Session session;
    private SessionDAO sessionDAO;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {        
        sessionDAO = new SessionDAO();
        currentBudgetCategory = new BudgetCategory();
        budgetCategoriesDAO = new BudgetCategoryDAO();
        try 
        {
            session = sessionDAO.getSession();
            String strBudgetCategory = session.getBudgetCategoryName();
            currentBudgetCategory = budgetCategoriesDAO.getBudgetCategory(strBudgetCategory);
            setFormFields(currentBudgetCategory);

        } catch (SQLException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void goBack(ActionEvent event) throws IOException, SQLException
    {   
        SceneController sc = new SceneController();
        sc.switchToBudgetCategoriesScene(event);
    }

    @FXML
    private void saveUpdate(ActionEvent event) throws IOException, SQLException
    {
        String newCatName = tfCategoryName.getText();
        Double newCatAmt = Double.parseDouble(tfCategoryAmount.getText());
        Double newThresh = Double.parseDouble(tfThresholdLimit.getText());
                        
        BudgetCategoryDAO dao = new BudgetCategoryDAO();        
        
        switch (dao.updateBudgetCategory(newCatName, newCatAmt, newThresh)) 
        {
            case 0 -> JOptionPane.showMessageDialog(null, "Please Try Again");
            case -1 -> JOptionPane.showMessageDialog(null, "Error");
            default -> {
                JOptionPane.showMessageDialog(null, "Record Successfully Updated");  
                clearFields();
            }
        }
        SceneController sc = new SceneController();
        sc.switchToBudgetCategoriesScene(event);
    }

    @FXML
    private void cancelUpdates(ActionEvent event) throws IOException, SQLException
    {
        if((!tfCategoryName.getText().equals(currentBudgetCategory.getCategoryName())) || Double.parseDouble(tfCategoryAmount.getText()) != currentBudgetCategory.getCategoryAmt() || Double.parseDouble(tfThresholdLimit.getText()) != currentBudgetCategory.getThresholdLimit())
        {
            int n = JOptionPane.showConfirmDialog(null, "If you cancel now, you will lose your changes.  Do you still want to cancel?", null, JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION)
            {
            SceneController sc = new SceneController();
            sc.switchToBudgetCategoriesScene(event);
            }
            else if (n == JOptionPane.NO_OPTION)
            {                
            }
        }
        else
        {
            SceneController sc = new SceneController();
            sc.switchToBudgetCategoriesScene(event);   
        }
    }

    @FXML
    private void clearUpdates(ActionEvent event)
    {   
        clearFields();
    }
    
    private void clearFields()
    {
        tfCategoryName.setText("");
        tfCategoryAmount.setText("");
        tfThresholdLimit.setText("");
        tfCategoryName.requestFocus();
    }
    
    private void setFormFields(BudgetCategory bc) {
        tfCategoryName.setText(bc.getCategoryName());
        tfCategoryAmount.setText(bc.getCategoryAmt().toString());
        tfThresholdLimit.setText(bc.getThresholdLimit().toString());
        tfCategoryName.requestFocus();
    }

    @FXML
    private void deleteCategory(ActionEvent event)
    {
        String deleteCatName = tfCategoryName.getText();
        
        BudgetCategoryDAO dao = new BudgetCategoryDAO();        
        
        switch (dao.deleteBudgetCategory(deleteCatName)) 
        {
            case 0 -> JOptionPane.showMessageDialog(null, "Please Try Again");
            case -1 -> JOptionPane.showMessageDialog(null, "Error");
            default -> {
                JOptionPane.showMessageDialog(null, "Record Successfully Deleted");  
                clearFields();
            }
        }
    }
    
}

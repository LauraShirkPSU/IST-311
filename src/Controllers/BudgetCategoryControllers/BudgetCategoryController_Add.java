package Controllers.BudgetCategoryControllers;

import Controllers.SceneController;
import Model.BudgetCategoryModel.BudgetCategoryDAO;
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


public class BudgetCategoryController_Add implements Initializable
{
    @FXML
    private Button btnSaveChanges;
    @FXML
    private Button btnClearChanges;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnGoBack;
    @FXML
    private TextField tfCategoryName;
    @FXML
    private TextField tfCategoryAmount;
    @FXML
    private TextField tfThresholdLimit;

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
    private void saveChanges(ActionEvent event)
    {   
        String newCatName = tfCategoryName.getText();
        Double newCatAmt = Double.parseDouble(tfCategoryAmount.getText());
        Double newThresh = Double.parseDouble(tfThresholdLimit.getText());
                        
        BudgetCategoryDAO dao = new BudgetCategoryDAO();        
        
        switch (dao.addNewBudgetCategory(newCatName, newCatAmt, newThresh)) 
        {
            case 0 -> JOptionPane.showMessageDialog(null, "Please Try Again");
            case -1 -> JOptionPane.showMessageDialog(null, "'" + newCatName + "' already exists.  Try a different name.");
            default -> {
                JOptionPane.showMessageDialog(null, "Record Successfully Added");  
                clearFields();
            }
        }
        
    }

    @FXML
    private void clearChanges(ActionEvent event)
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

    @FXML
    private void cancelChanges(ActionEvent event) throws IOException, SQLException
    {
        if(tfCategoryName.getText() != "" || tfCategoryAmount.getText() != "" || tfThresholdLimit.getText() != "")
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
    private void goBack(ActionEvent event) throws IOException, SQLException
    {   
        SceneController sc = new SceneController();
        sc.switchToBudgetCategoriesScene(event);
    }
    
}


package Controller;

import Model.BudgetCategoriesDAO;
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

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class UpdateCategoryController implements Initializable
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
    private void goBack(ActionEvent event) throws IOException, SQLException
    {   
        SceneController sc = new SceneController();
        sc.switchToBudgetCategoriesScene(event);
    }

    @FXML
    private void saveUpdate(ActionEvent event)
    {
        String newCatName = tfCategoryName.getText();
        Double newCatAmt = Double.parseDouble(tfCategoryAmount.getText());
        Double newThresh = Double.parseDouble(tfThresholdLimit.getText());
                        
        BudgetCategoriesDAO dao = new BudgetCategoriesDAO();        
        
        switch (dao.updateBudgetCategory(newCatName, newCatAmt, newThresh)) 
        {
            case 0 -> JOptionPane.showMessageDialog(null, "Please Try Again");
            case -1 -> JOptionPane.showMessageDialog(null, "Error");
            default -> {
                JOptionPane.showMessageDialog(null, "Record Successfully Added");  
                clearFields();
            }
        }
    }

    @FXML
    private void cancelUpdates(ActionEvent event) throws IOException, SQLException
    {
        SceneController sc = new SceneController();
        sc.switchToBudgetCategoriesScene(event);
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
    
}

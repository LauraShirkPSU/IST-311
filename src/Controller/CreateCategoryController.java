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


public class CreateCategoryController implements Initializable
{

    @FXML
    private TextField newCategoryName;
    @FXML
    private TextField newCategoryAmt;
    @FXML
    private TextField newThreshLimit;
    @FXML
    private Button btnSaveChanges;
    @FXML
    private Button btnClearChanges;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnGoBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void saveChanges(ActionEvent event)
    {   
        String newCatName = newCategoryName.getText();
        Double newCatAmt = Double.parseDouble(newCategoryAmt.getText());
        Double newThresh = Double.parseDouble(newThreshLimit.getText());
        
        BudgetCategoriesDAO dao = new BudgetCategoriesDAO();
        
        dao.addNewBudgetCategory(newCatName, newCatAmt, newThresh);        
    }

    @FXML
    private void clearChanges(ActionEvent event)
    {   
        newCategoryName.setText("");
        newCategoryAmt.setText("");
        newThreshLimit.setText("");
        newCategoryName.requestFocus();
    }

    @FXML
    private void cancelChanges(ActionEvent event) throws IOException, SQLException
    {
        SceneController sc = new SceneController();
        sc.switchToBudgetCategoriesScene(event);
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException, SQLException
    {   
        SceneController sc = new SceneController();
        sc.switchToBudgetCategoriesScene(event);
    }
    
}

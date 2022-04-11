
package Controllers.BudgetCategoryControllers;

import Controllers.SceneController;
import Model.BudgetCategoryModel.BudgetCategoryDAO;
import Model.BudgetCategoryModel.BudgetCategory;
import Model.Session;
import Model.SessionDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class BudgetCategoryController_Main implements Initializable
{
    @FXML
    private TableView<BudgetCategory> tblView = new TableView<>();
    @FXML
    private TableColumn<BudgetCategory, String> col_Name;
    @FXML
    private TableColumn<BudgetCategory, Double> col_Amt;
    @FXML
    private TableColumn<BudgetCategory, Double> col_ThreshLimit;
    
    @FXML
    private Button btnCreateCategory;
    @FXML
    private Button btnGoBack;
    @FXML
    private Button btnModifyCategory;
    @FXML
    private Button btnViewCategoryDetails;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        col_Name.setCellValueFactory(new PropertyValueFactory<>("CategoryName"));
        col_Amt.setCellValueFactory(new PropertyValueFactory<>("CategoryAmt"));
        col_ThreshLimit.setCellValueFactory(new PropertyValueFactory<>("ThresholdLimit"));
        
        ObservableList<BudgetCategory> catList;
    
        catList = BudgetCategoryDAO.getCategories();
        tblView.setItems(catList);        
        
    }   

    @FXML
    private void createNewCategory(ActionEvent event) throws IOException
    {
        SceneController sc = new SceneController();
        sc.switchToBudgetCategoryCreateScene(event);
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException
    {   
        SceneController sc = new SceneController();
        sc.switchToDashboardScene(event);
    }

    @FXML
    private void modifyCategory(ActionEvent event) throws IOException 
    {
        BudgetCategory bc = tblView.getSelectionModel().getSelectedItem();
        Session session = new Session();
        SessionDAO sessionDAO = new SessionDAO();
        if(bc != null)
        {          
            try 
            {
                sessionDAO.setSessionProperty("BudgetCategoryName", bc.getCategoryName());
                session = sessionDAO.getSession();
            } 
            catch (SQLException ignore) 
            {
            }
            SceneController sc = new SceneController();
            sc.switchToModifyCategoryScene(event);
        }
        else
        {  
            JOptionPane.showMessageDialog(null, "Please select a category");
        }

        
    }

    @FXML
    private void viewCategoryDetails(ActionEvent event) throws IOException
    {
        BudgetCategory bc = tblView.getSelectionModel().getSelectedItem();
        Session session = new Session();
        SessionDAO sessionDAO = new SessionDAO();
        if(bc != null)
        {          
            try 
            {
                sessionDAO.setSessionProperty("CategoryDetailsName", bc.getCategoryName());
                session = sessionDAO.getSession();
            } 
            catch (SQLException ignore) 
            {
            }
            SceneController sc = new SceneController();
            sc.switchToCategoryDetailsScene(event);
        }
        else
        {  
            JOptionPane.showMessageDialog(null, "Please select a category");
        }
    }

    

}


package Controller;

import Model.BudgetCategoriesDAO;
import Model.BudgetCategory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class BudgetCategoriesController implements Initializable
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        col_Name.setCellValueFactory(new PropertyValueFactory<BudgetCategory, String>("CategoryName"));
        col_Amt.setCellValueFactory(new PropertyValueFactory<BudgetCategory, Double>("CategoryAmt"));
        col_ThreshLimit.setCellValueFactory(new PropertyValueFactory<BudgetCategory, Double>("ThresholdLimit"));
        
        ObservableList<BudgetCategory> catList;
    
        catList = BudgetCategoriesDAO.getCategories();
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

}

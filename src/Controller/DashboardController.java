package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;



public class DashboardController implements Initializable
{

    @FXML
    private Button btnBudgetCategories;
    @FXML
    private Button btnLogout;
    private TableView tableView = new TableView<>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
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

    
}

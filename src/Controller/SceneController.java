
package Controller;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SceneController
{
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void switchToLoginScene(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\LoginFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToDashboardScene(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\DashboardFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToBudgetCategoriesScene(ActionEvent event) throws IOException, SQLException
    {         
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\BudgetCategoriesFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();        
    }    
    
    public void switchToBudgetCategoryCreateScene(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\CreateCategoryFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
}


package Controllers;

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
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\BudgetCategoryView\\BudgetCategoryMainFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }    
    
    public void switchToBudgetCategoryCreateScene(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\BudgetCategoryView\\BudgetCategoryAddFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    
    public void switchToModifyCategoryScene(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\BudgetCategoryView\\BudgetCategoryModifyFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }
    
    public void switchToCategoryDetailsScene(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\BudgetCategoryView\\BudgetCategoryDetailsFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }
    
    public void switchToManageAccountScene(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\ManageAccountFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }
    
    public void switchToExpenseScene(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\ExpenseView\\ExpenseMainFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }
    
    public void switchToIncomeScene(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\IncomeView\\IncomeMainFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }
    
    public void switchToAddExpenseScene(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\ExpenseView\\ExpenseAddFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }
    
    public void switchToAddIncomeScene(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\IncomeView\\IncomeAddFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAddIncomeEntityScene(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\IncomeView\\IncomeEntityAddFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers.IncomeControllers;

import Controllers.SceneController;
import Model.IncomeModel.Income;
import Model.IncomeModel.IncomeDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class IncomeController_Main implements Initializable
{

    @FXML
    private TableView<Income> tblView_Income = new TableView();
    @FXML
    private TableColumn<Income, Date> col_IncomeDate;
    @FXML
    private TableColumn<Income, Double> col_IncomeAmount;
    @FXML
    private TableColumn<Income, String> col_IncomeEntity;
    
    @FXML
    private Button btn_AddIncome;
    @FXML
    private Button btnGoBack;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try {
            col_IncomeDate.setCellValueFactory(new PropertyValueFactory<Income, Date>("transactionDate"));
            col_IncomeAmount.setCellValueFactory(new PropertyValueFactory<Income, Double>("transactionAmount"));
            col_IncomeEntity.setCellValueFactory(new PropertyValueFactory<Income, String>("incomeEntity"));
            
            ObservableList<Income> catList;
            
            catList = IncomeDAO.getAllIncome(); 
            tblView_Income.setItems(catList);
        } catch (SQLException ex) {
            Logger.getLogger(IncomeController_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void addIncome(ActionEvent event) throws IOException
    {   
        SceneController sc = new SceneController();
        sc.switchToAddIncomeScene(event);
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException
    {
        SceneController sc = new SceneController();
        sc.switchToDashboardScene(event);
    }
    
}

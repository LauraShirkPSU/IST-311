/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers.ExpenseControllers;

import Controllers.SceneController;
import Model.ExpenseModel.Expense;
import Model.ExpenseModel.ExpenseDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
public class ExpenseController_Main implements Initializable
{   
    @FXML
    private TableView<Expense> tblViewExpense = new TableView();
    @FXML
    private TableColumn<Expense, Date> col_ExpDate;
    @FXML
    private TableColumn<Expense, Double> col_ExpAmount;
    @FXML
    private TableColumn<Expense, String> col_ExpCategory;
    @FXML
    private Button btnGoBack;
    @FXML
    private Button btnAddExpense;
    @FXML
    private Button btnModifyExpense;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try {
            col_ExpDate.setCellValueFactory(new PropertyValueFactory<Expense, Date>("transactionDate"));
            col_ExpAmount.setCellValueFactory(new PropertyValueFactory<Expense, Double>("transactionAmount"));
            col_ExpCategory.setCellValueFactory(new PropertyValueFactory<Expense, String>("expenseCategory"));
            
            ObservableList<Expense> catList;
            
            catList = ExpenseDAO.getExpenses();   
            tblViewExpense.setItems(catList);
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseController_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void goBack(ActionEvent event) throws IOException
    {
        SceneController sc = new SceneController();
        sc.switchToDashboardScene(event);
    }

    @FXML
    private void addExpense(ActionEvent event) throws IOException
    {
        SceneController sc = new SceneController();
        sc.switchToAddExpenseScene(event);
    }

    @FXML
    private void modifyExpense(ActionEvent event)
    {
    }
    
}

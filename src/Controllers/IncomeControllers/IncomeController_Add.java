/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers.IncomeControllers;

import Controllers.ExpenseControllers.ExpenseController_Add;
import Controllers.SceneController;
import Model.BudgetCategoryModel.BudgetCategoryDAO;
import Model.IncomeModel.IncomeDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class IncomeController_Add implements Initializable
{

    @FXML
    private Button btnGoBack;
    @FXML
    private Label lblVal_IncomeDate;
    @FXML
    private Label lblVal_IncomeAmount;
    @FXML
    private Label lblVal_IncomeEntity;
    @FXML
    private Button btnSave;
    @FXML
    private DatePicker dpIncomeDate;
    @FXML
    private TextField tfIncomeAmount;
    @FXML
    private ChoiceBox cbIncomeEntity;
    @FXML
    private Button btnAddNewEntity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ArrayList<String> entities = new ArrayList<>();
        IncomeDAO incDAO = new IncomeDAO();
        try {
            entities = incDAO.getEntityNames();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeController_Add.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbIncomeEntity.setItems(FXCollections.observableArrayList(entities));    
        resetValidationControls();  
    }    

    @FXML
    private void goBack(ActionEvent event) throws IOException
    {   
        SceneController sc = new SceneController();
        sc.switchToIncomeScene(event);
    }

    @FXML
    private void saveIncome(ActionEvent event)
    {
    }
    
    

    @FXML
    private void addNewEntity(ActionEvent event) throws IOException
    {
        SceneController sc = new SceneController();
        sc.switchToAddIncomeEntityScene(event);    
    }
    
    
    private void resetValidationControls() {
        System.out.println("Resetting Validation Controls");
        lblVal_IncomeDate.setText("");
        lblVal_IncomeAmount.setText("");
        lblVal_IncomeEntity.setText("");
        lblVal_IncomeDate.setVisible(false);
        lblVal_IncomeAmount.setVisible(false);
        lblVal_IncomeEntity.setVisible(false);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers.IncomeControllers;

import Controllers.SceneController;
import Model.IncomeModel.Income;
import Model.IncomeModel.IncomeDAO;
import Model.Session;
import Model.SessionDAO;
import Model.Validator;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class IncomeController_Modify implements Initializable
{

    @FXML
    private Button btnGoBack;
    @FXML
    private DatePicker dpIncomeDate;
    @FXML
    private TextField tfIncomeAmount;
    @FXML
    private ChoiceBox cbIncomeEntity;
    @FXML
    private Label lbl_Val_IncomeDate;
    @FXML
    private Label lbl_Val_IncomeAmount;
    @FXML
    private Label lbl_Val_IncomeEntity;
    @FXML
    private Button btnSaveIncome;
    
    private Income currentIncome;
    private IncomeDAO incomeDAO;
    private Session session;
    private SessionDAO sessionDAO;
    
    private Validator validator = new Validator();

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
        
        try {
            sessionDAO = new SessionDAO();
            currentIncome = new Income();
            incomeDAO = new IncomeDAO();
            
            session = sessionDAO.getSession();
            int incomeID = session.getIncomeIndex();
            currentIncome = incomeDAO.getIncomeObject(incomeID);
        } catch (SQLException ex) {
            Logger.getLogger(IncomeController_Modify.class.getName()).log(Level.SEVERE, null, ex);
        }
        setFormFields(currentIncome);
        resetValidationControls();
    }    

    @FXML
    private void goBack(ActionEvent event) throws IOException
    {
        SceneController sc = new SceneController();
        sc.switchToIncomeScene(event);
    }

    @FXML
    private void saveIncome(ActionEvent event) throws SQLException
    {
        resetValidationControls();
        if (ValidateForm()) 
        {
            LocalDate newIncomeDate = dpIncomeDate.getValue();
            Double newIncomeAmount = Double.parseDouble(tfIncomeAmount.getText());
            String newIncomeEntity = cbIncomeEntity.getSelectionModel().getSelectedItem().toString();
        
            IncomeDAO dao = new IncomeDAO();
            
            switch (dao.updateIncomeRecord(newIncomeDate.toString(), newIncomeAmount, newIncomeEntity)) 
        {
            case 0 -> JOptionPane.showMessageDialog(null, "Please Try Again");
            case -1 -> JOptionPane.showMessageDialog(null, "Error");
            default -> {
                JOptionPane.showMessageDialog(null, "Record Successfully Deleted");  
                clearFields();
            }
        }
        }
        
        
    }
    
    private void setFormFields(Income income)
    {
        dpIncomeDate.setValue(LocalDate.parse(income.getTransactionDate().toString(), DateTimeFormatter.ISO_DATE));
        tfIncomeAmount.setText(income.getTransactionAmount().toString());
        cbIncomeEntity.setValue(income.getIncomeEntity());
    }
    
    private void clearFields()
    {   
        tfIncomeAmount.setText("");
        dpIncomeDate.requestFocus();
    }
    
    private void resetValidationControls() 
    {
        lbl_Val_IncomeDate.setText("");
        lbl_Val_IncomeAmount.setText("");
        lbl_Val_IncomeEntity.setText("");
        lbl_Val_IncomeDate.setVisible(false);
        lbl_Val_IncomeAmount.setVisible(false);
        lbl_Val_IncomeEntity.setVisible(false);
    }
    
    private Boolean ValidateForm() 
    {
        Boolean blnIsValid = true;
        if (dpIncomeDate.getValue() == null) 
        {
            lbl_Val_IncomeDate.setText("Required Field");
            lbl_Val_IncomeDate.setVisible(true);
            blnIsValid = false;
        }
        if (tfIncomeAmount.getText() == "") 
        {
            lbl_Val_IncomeAmount.setText("Required Field");
            lbl_Val_IncomeAmount.setVisible(true);
            blnIsValid = false;
        } 
        else 
        {
            if (!validator.isNumeric(tfIncomeAmount.getText())) 
            {
                lbl_Val_IncomeAmount.setText("Value is not numeric");
                lbl_Val_IncomeAmount.setVisible(true);
                blnIsValid = false;
            }
        }

        if (cbIncomeEntity.getSelectionModel().isEmpty()) {
            lbl_Val_IncomeEntity.setText("Required Field");
            lbl_Val_IncomeEntity.setVisible(true);
            blnIsValid = false;
        }
        return blnIsValid;
    }
}

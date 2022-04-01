
package Model;

import static Model.dbConnection.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;


public class BudgetCategoriesDAO
{
    private final Connection con;

    public BudgetCategoriesDAO()
    {
        this.con = dbConnection.connectDB();
    }
    
    public ResultSet getBudgetCategoriesResultSet() throws SQLException
    {        
        PreparedStatement pst;
        ResultSet rs;
        String sqlStatement = "Select * from budgetcategories";
        
        pst = con.prepareStatement(sqlStatement);
        rs = pst.executeQuery();
        //rs.close();
        return rs;
    }
    
    public ArrayList getBudgetCategories() throws SQLException
    {
        ArrayList<BudgetCategory> categories = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        String sqlStatement = "Select * from budgetcategories";
        
        pst = con.prepareStatement(sqlStatement);
        rs = pst.executeQuery();
                    
        while(rs.next())
        {
            BudgetCategory category = new BudgetCategory();
            category.setCategoryName(rs.getString("CategoryName"));
            category.setCategoryAmt(rs.getDouble("CategoryAmount"));
            category.setThresholdLimit(rs.getDouble("ThresholdLimit"));
            categories.add(category);
        }         
    return categories;    
    }
    
    public static ObservableList<BudgetCategory> getCategories()
    {
        Connection conn = connectDB();
        ObservableList<BudgetCategory> list = FXCollections.observableArrayList();
        
        try {
            String sqlStatement = "Select * from budgetcategories";
            PreparedStatement pst = conn.prepareStatement(sqlStatement);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next())
            {
                list.add(new BudgetCategory(rs.getString("CategoryName"), rs.getDouble("CategoryAmount"), rs.getDouble("ThresholdLimit")));
            }                   
            
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    
    public int addNewBudgetCategory(String newName, Double newAmt, Double newThreshLimit)
    {
        String newCatName = newName;
        Double newCatAmt = newAmt;
        Double newThresh = newThreshLimit;
        int rowsUpdated = 0;
        
        try {
            String sqlStatement = "INSERT INTO budgetcategories (CategoryName, CategoryAmount, ThresholdLimit) values ('"+ newCatName + "', " + newCatAmt + ", " + newThresh + ");";
            PreparedStatement pst = con.prepareStatement(sqlStatement);
            //int rowsUpdated = 0;
            try {
                rowsUpdated = pst.executeUpdate();
            }
            catch (SQLException ex) {  
                if(ex.toString().contains("SQLIntegrityConstraintViolationException: Duplicate entry")){
                    rowsUpdated = -1;
                }      
            }              
 
        } catch (SQLException ex) {
            Logger.getLogger(BudgetCategoriesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsUpdated;
    }
    
    public int updateBudgetCategory(String newName, Double newAmt, Double newThreshLimit)
    {
        String CatName = newName;
        Double newCatAmt = newAmt;
        Double newThresh = newThreshLimit;
        int rowsUpdated = 0;
        
        try
        {
            String sqlStatement = "update budgetcategories set CategoryAmount = " + newCatAmt + ", ThresholdLimit = " + newThresh + " where CategoryName = '" + CatName + "';";
            PreparedStatement pst = con.prepareStatement(sqlStatement);
            try {
                rowsUpdated = pst.executeUpdate();
            }
            catch(SQLException ex)
            {
                rowsUpdated = -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BudgetCategoriesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rowsUpdated;
    }

}


package Model.BudgetCategoryModel;

import Model.BudgetCategoryModel.BudgetCategory;
import Model.dbConnection;
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


public class BudgetCategoryDAO
{
    private final Connection con;

    public BudgetCategoryDAO()
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
        return rs;
    }
    
    public BudgetCategory getBudgetCategory(String strBudgetCategoryName) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        BudgetCategory category = new BudgetCategory();

        String sqlStatement = "Select * from budgetcategories where CategoryName = '" + strBudgetCategoryName + "'";
        try {
            con = dbConnection.connectDB();
            pst = con.prepareStatement(sqlStatement);
            rs = pst.executeQuery();

            while (rs.next()) {
                category.setCategoryName(rs.getString("CategoryName"));
                category.setCategoryAmt(rs.getDouble("CategoryAmount"));
                category.setThresholdLimit(rs.getDouble("ThresholdLimit"));
            }
        } catch (SQLException ignore) {
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException ignore) {
            }
            if (pst != null) try {
                pst.close();
            } catch (SQLException ignore) {
            }
            if (con != null) try {
                con.close();
            } catch (SQLException ignore) {
            }
        }

        return category;
    }
    
//    public ArrayList getBudgetCategories() throws SQLException
//    {
//        ArrayList<BudgetCategory> categories = new ArrayList<>();
//        PreparedStatement pst;
//        ResultSet rs;
//        String sqlStatement = "Select * from budgetcategories";
//        
//        pst = con.prepareStatement(sqlStatement);
//        rs = pst.executeQuery();
//                    
//        while(rs.next())
//        {
//            BudgetCategory category = new BudgetCategory();
//            category.setCategoryName(rs.getString("CategoryName"));
//            category.setCategoryAmt(rs.getDouble("CategoryAmount"));
//            category.setThresholdLimit(rs.getDouble("ThresholdLimit"));
//            categories.add(category);
//        }         
//    return categories;    
//    }
    
    public ArrayList getCategoryNames() throws SQLException
    {
        ArrayList<String> categories = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        String sqlStatement = "Select * from budgetcategories";
        
        pst = con.prepareStatement(sqlStatement);
        rs = pst.executeQuery();
                    
        while(rs.next())
        {            
            categories.add(rs.getString("CategoryName"));
        }         
    return categories;    
    }
    
    public static ObservableList<BudgetCategory> getCategories()
    {
        Connection con = connectDB();
        ObservableList<BudgetCategory> list = FXCollections.observableArrayList();
        
        try {
            String sqlStatement = "Select * from budgetcategories";
            PreparedStatement pst = con.prepareStatement(sqlStatement);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next())
            {
                list.add(new BudgetCategory(rs.getString("CategoryName"), rs.getDouble("CategoryAmount"), rs.getDouble("ThresholdLimit")));
            }
            con.close();
        } 
        catch (SQLException ex) 
        {
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
        
        try 
        {
            String sqlStatement = "INSERT INTO budgetcategories (CategoryName, CategoryAmount, ThresholdLimit) values ('"+ newCatName + "', " + newCatAmt + ", " + newThresh + ");";
            PreparedStatement pst = con.prepareStatement(sqlStatement);
            
            try 
            {
                rowsUpdated = pst.executeUpdate();
            }
            catch (SQLException ex) 
            {  
                if(ex.toString().contains("SQLIntegrityConstraintViolationException: Duplicate entry"))
                {
                    rowsUpdated = -1;
                }      
            }              
            con.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BudgetCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            try 
            {
                rowsUpdated = pst.executeUpdate();
            }
            catch(SQLException ex)
            {
                rowsUpdated = -1;
            }
            con.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BudgetCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rowsUpdated;
    }

    public int deleteBudgetCategory(String categoryName)
    {
        String catName = categoryName;
        int rowsUpdated = 0;
        
        try
        {
            String sqlStatement = "delete from budgetcategories where CategoryName = '" + catName + "';";
            PreparedStatement pst = con.prepareStatement(sqlStatement);
            try 
            {
                rowsUpdated = pst.executeUpdate();
            }
            catch(SQLException ex)
            {
                rowsUpdated = -1;
            }
            con.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BudgetCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rowsUpdated;
    }
}

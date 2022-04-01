/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import static Model.dbConnection.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Laura
 */
public class ExpenseDAO
{   
    private final Connection con;
    
    public ExpenseDAO()
    {
        this.con = dbConnection.connectDB();
    }
    
    public ResultSet getExpensesResultSet() throws SQLException
    {        
        PreparedStatement pst;
        ResultSet rs;
        String sqlStatement = "Select * from expenses";
        
        pst = con.prepareStatement(sqlStatement);
        rs = pst.executeQuery();
        return rs;
    }
    
    public ArrayList getBudgetCategories() throws SQLException
    {
        ArrayList<Expense> expenses = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        String sqlStatement = "Select * from expenses";
        
        pst = con.prepareStatement(sqlStatement);
        rs = pst.executeQuery();
                    
        while(rs.next())
        {
            Expense expense = new Expense();
            expense.setTransactionDate(rs.getDate("TransactionDate"));
            expense.setTransactionAmount(rs.getDouble("TransactionAmount"));
            expense.setExpenseCategory(rs.getString("ExpenseCategory"));
            expenses.add(expense);
        }         
    return expenses;    
    }
    
    public static ObservableList<Expense> getExpenses()
    {
        Connection conn = connectDB();
        ObservableList<Expense> list = FXCollections.observableArrayList();        
        
        try {
            String sqlStatement = "Select * from Expenses";
            PreparedStatement pst = conn.prepareStatement(sqlStatement);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next())
            {
                list.add(new Expense(rs.getDate("TransactionDate"), rs.getDouble("TransactionAmount"), rs.getString("ExpenseCategory")));
            }                   
            
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    
    public int addExpenseEntry(String newDate, Double newAmt, String newEntity)
    {
        String newExpenseDate = newDate;
        Double newExpenseAmt = newAmt;
        String newExpenseEntity = newEntity;
        int rowsUpdated = 0;
        
        try {
            String sqlStatement = "INSERT INTO Expenses (TransactionDate, TransactionAmount, ExpenseCategory) values ('"+ newExpenseDate + "', " + newExpenseAmt + ", '" + newExpenseEntity + "');";
            PreparedStatement pst = con.prepareStatement(sqlStatement);
                        
            rowsUpdated = pst.executeUpdate();
            } 
        catch (SQLException ex) {
            Logger.getLogger(BudgetCategoriesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsUpdated;
    }
    
}

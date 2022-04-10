/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ExpenseModel;

import Model.ExpenseModel.Expense;
import Model.BudgetCategoryModel.BudgetCategoryDAO;
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
        if(rs != null)
            {
                rs.close();
            }
            if(pst != null)
            {
                pst.close();
            }
            else
            {
                con.close();
            }
        return rs;
    }
    
    
    public static ObservableList<Expense> getExpenses() throws SQLException
    {
        Connection con = connectDB();
        ObservableList<Expense> list = FXCollections.observableArrayList();        
        
        try 
        {
            String sqlStatement = "Select * from Expenses";
            PreparedStatement pst = con.prepareStatement(sqlStatement);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next())
            {
                list.add(new Expense(rs.getDate("TransactionDate"), rs.getDouble("TransactionAmount"), rs.getString("ExpenseCategory")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
        return list;
    } 
    
    public static ObservableList<Expense> getCategoryExpenses(String category) throws SQLException
    {
        String expenseCategory = category;
        Connection con = connectDB();
        ObservableList<Expense> list = FXCollections.observableArrayList();        
        
        try 
        {
            String sqlStatement = "Select * from Expenses where ExpenseCategory = '" + expenseCategory + "';";
            PreparedStatement pst = con.prepareStatement(sqlStatement);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next())
            {
                list.add(new Expense(rs.getDate("TransactionDate"), rs.getDouble("TransactionAmount")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
        return list;
    } 
    
    public int addExpenseEntry(String newDate, Double newAmt, String newEntity) throws SQLException
    {
        String newExpenseDate = newDate;
        Double newExpenseAmt = newAmt;
        String newExpenseEntity = newEntity;
        int rowsUpdated = 0;
        
        try 
        {
            String sqlStatement = "INSERT INTO Expenses (TransactionDate, TransactionAmount, ExpenseCategory) values ('"+ newExpenseDate + "', " + newExpenseAmt + ", '" + newExpenseEntity + "');";
            PreparedStatement pst = con.prepareStatement(sqlStatement);
                        
            rowsUpdated = pst.executeUpdate();
            } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BudgetCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
        return rowsUpdated;
    }
    
    public Double sumExpenses() throws SQLException
    {
        Double totalExpense = 0.00;
        
        try 
        {
            String sqlStatement = "Select SUM(TransactionAmount) as ExpenseSum from Expenses;";
            PreparedStatement pst = con.prepareStatement(sqlStatement);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                totalExpense = rs.getDouble("ExpenseSum");
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(BudgetCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalExpense;
    }
    
    public Double sumMonthExpenses(String expenseCategory)
    {
        String sumExpCategory = expenseCategory;
        Double sumMonthExpenses = 0.00;
        
        try 
        {
            String sqlStatement = "Select SUM(TransactionAmount) as MonthExpense from Expenses where ExpenseCategory = '" + sumExpCategory + "' and MONTH(TransactionDate)=MONTH(curdate());";
            PreparedStatement pst = pst = con.prepareStatement(sqlStatement);
            ResultSet rs = pst.executeQuery(); 
        
            while(rs.next())
            {
                sumMonthExpenses = rs.getDouble("MonthExpense");
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ExpenseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sumMonthExpenses;   
    }
    
    
    
    //select sum(TransactionAmount) from Expenses where MONTH(TransactionDate)=MONTH(curdate());
}

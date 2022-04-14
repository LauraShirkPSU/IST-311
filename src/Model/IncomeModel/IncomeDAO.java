/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.IncomeModel;

import Model.IncomeModel.Income;
import Model.BudgetCategoryModel.BudgetCategoryDAO;
import Model.dbConnection;
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
public class IncomeDAO
{   
    private final Connection con;
    
    public IncomeDAO()
    {
        this.con = dbConnection.connectDB();
    }
    
    public ResultSet getIncomeResultSet() throws SQLException
    {        
        PreparedStatement pst;
        ResultSet rs;
        String sqlStatement = "Select * from Income";
        
        pst = con.prepareStatement(sqlStatement);
        rs = pst.executeQuery();
        con.close();
        return rs;
    }
    
    public ArrayList getIncome() throws SQLException
    {
        ArrayList<Income> allIncome = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        String sqlStatement = "Select * from Income";
        
        pst = con.prepareStatement(sqlStatement);
        rs = pst.executeQuery();
                    
        while(rs.next())
        {
            Income income = new Income();
            income.setTransactionDate(rs.getDate("TransactionDate"));
            income.setTransactionAmount(rs.getDouble("TransactionAmount"));
            income.setIncomeEntity(rs.getString("IncomeEntity"));
            allIncome.add(income);
        } 
        con.close();
    return allIncome;    
    }
    
    public static ObservableList<Income> getAllIncome() throws SQLException
    {
        Connection con = connectDB();
        ObservableList<Income> list = FXCollections.observableArrayList();        
        
        try 
        {
            String sqlStatement = "Select * from Income";
            PreparedStatement pst = con.prepareStatement(sqlStatement);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next())
            {
                list.add(new Income(rs.getDate("TransactionDate"), rs.getDouble("TransactionAmount"), rs.getString("IncomeEntity")));
            }             
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
        return list;
    } 
    
    public int addIncomeEntry(String newDate, Double newAmt, String newEntity) throws SQLException
    {
        String newIncomeDate = newDate;
        Double newIncomeAmt = newAmt;
        String newIncomeEntity = newEntity;
        int rowsUpdated = 0;
        
        try 
        {
            String sqlStatement = "INSERT INTO Income (TransactionDate, TransactionAmount, IncomeEntity) values ('"+ newIncomeDate + "', " + newIncomeAmt + ", '" + newIncomeEntity + "');";
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
    
    public Double sumIncome() throws SQLException
    {
        Double totalIncome = 0.00;
        
        try 
        {
            String sqlStatement = "Select SUM(TransactionAmount) as IncomeSum from Income;";
            PreparedStatement pst = con.prepareStatement(sqlStatement);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                totalIncome = rs.getDouble("IncomeSum");
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(BudgetCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalIncome;
    }
    
    public ArrayList<String> getEntityNames() throws SQLException
    {
        ArrayList<String> entities = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        String sqlStatement = "Select * from IncomeEntity";
        
        pst = con.prepareStatement(sqlStatement);
        rs = pst.executeQuery();
                    
        while(rs.next())
        {            
            entities.add(rs.getString("EntityName"));
        }         
    return entities;  
    }
}


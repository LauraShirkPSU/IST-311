/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.IncomeModel;

import java.util.Date;

/**
 *
 * @author Laura
 */
public class Income
{   
    private Date transactionDate;
    private Double transactionAmount;
    private String incomeEntity;

    public Income(Date transactionDate, Double transactionAmount, String incomeEntity)
    {
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.incomeEntity = incomeEntity;
    }

    public Income()
    {
    }

    public Date getTransactionDate()
    {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate)
    {
        this.transactionDate = transactionDate;
    }

    public Double getTransactionAmount()
    {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount)
    {
        this.transactionAmount = transactionAmount;
    }

    public String getIncomeEntity()
    {
        return incomeEntity;
    }

    public void setIncomeEntity(String incomeEntity)
    {
        this.incomeEntity = incomeEntity;
    }
    
    
    
}

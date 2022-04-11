package Model.ExpenseModel;

import java.util.Date;

public class Expense 
{
    private Date transactionDate;
    private Double transactionAmount;
    private String expenseCategory;

    public Expense(Date transactionDate, Double transactionAmount, String expenseCategory)
    {        
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.expenseCategory = expenseCategory;
    }
    
    public Expense()
    {
        
    }
    
    public Expense(Date transactionDate, Double transactionAmount)
    {
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
    }

    public String getExpenseCategory()
    {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory)
    {
        this.expenseCategory = expenseCategory;
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
    
    
    
}

package Model;

public class Session {
    
    private String Username;
    private String BudgetCategoryName;
    private String BudgetCategoryDetailName;
    
    /* Username */
    public void setUsername(String strUsername)
    {
        this.Username = strUsername;
    }
    
    public String GetUsername()
    {
        return Username;
    }
    
    /* BudgetCategoryName */
    public String getBudgetCategoryName() 
    {
        return BudgetCategoryName;
    }
    
    public void setBudgetCategoryName(String BudgetCategoryName) 
    {
        this.BudgetCategoryName = BudgetCategoryName;
    }

    /* BudgetCategoryDetailsName */
    public String getBudgetCategoryDetailsName() 
    {
        return BudgetCategoryDetailName;
    }
    
    public void setBudgetCategoryDetailsName(String BudgetCategoryDetailName) 
    {
        this.BudgetCategoryDetailName = BudgetCategoryDetailName;
    }
}

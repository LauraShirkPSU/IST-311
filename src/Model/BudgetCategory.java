
package Model;


public class BudgetCategory
{
    private String CategoryName;
    private Double CategoryAmt;
    private Double ThresholdLimit;

    public BudgetCategory()
    {
    }
  
    
    public BudgetCategory(String CategoryName, Double CategoryAmt, Double ThresholdLimit)
    {
        this.CategoryName = CategoryName;
        this.CategoryAmt = CategoryAmt;
        this.ThresholdLimit = ThresholdLimit;
    }

    public String getCategoryName()
    {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName)
    {
        this.CategoryName = CategoryName;
    }

    public Double getCategoryAmt()
    {
        return CategoryAmt;
    }

    public void setCategoryAmt(Double CategoryAmt)
    {
        this.CategoryAmt = CategoryAmt;
    }

    public Double getThresholdLimit()
    {
        return ThresholdLimit;
    }

    public void setThresholdLimit(Double ThresholdLimit)
    {
        this.ThresholdLimit = ThresholdLimit;
    }

    @Override
    public String toString()
    {
        return "BudgetCategory{" + "CategoryName=" + CategoryName + ", CategoryAmt=" + CategoryAmt + ", ThresholdLimit=" + ThresholdLimit + '}';
    }

    
  
    
}

package Model.IncomeModel;

public class IncomeEntity
{
    private String entityName;
    private String entityAddress;

    public IncomeEntity(String entityName, String entityAddress)
    {
        this.entityName = entityName;
        this.entityAddress = entityAddress;
    }

    public IncomeEntity(String entityName)
    {
        this.entityName = entityName;
    }

    public String getEntityName()
    {
        return entityName;
    }

    public void setEntityName(String entityName)
    {
        this.entityName = entityName;
    }

    public String getEntityAddress()
    {
        return entityAddress;
    }

    public void setEntityAddress(String entityAddress)
    {
        this.entityAddress = entityAddress;
    }
    
    
}

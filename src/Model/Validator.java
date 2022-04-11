
package Model;


public class Validator 
{
    public Validator()
    {
        // do nothing
    }
    public Boolean isNumeric(String strString) 
    {
        return strString.matches("-?\\d+(\\.\\d+)?"); 
    }
}
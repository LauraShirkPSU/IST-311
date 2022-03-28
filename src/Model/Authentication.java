
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Authentication
{    
    Connection con = dbConnection.connectDB();
    
    public boolean isAuthenticated(String userName, String passWord)
    {
        Boolean result = false;
        try {
            String username = userName;
            String password = passWord;
            PreparedStatement pst;
            ResultSet rs;
            
            pst = con.prepareStatement("Select * from users where username =? and password=?");
            
            pst.setString(1, username);
            pst.setString(2, password);
            
            rs = pst.executeQuery();
            
            if(rs.next())
            {
                result = true;
            }
            else
            {
                result = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}

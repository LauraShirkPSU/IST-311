/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Laura
 */
public class UserDAO
{
    private final Connection con;

    public UserDAO()
    {
        this.con = dbConnection.connectDB();
    }
    
    public User getUserObject(String userName) throws SQLException
        {        
            String username = userName;
            User user = new User();            
                
            PreparedStatement pst;
            ResultSet rs;
            String sqlStatement = "Select * from users where username = '" + username + "';";
        
            pst = con.prepareStatement(sqlStatement);
            rs = pst.executeQuery();
                    
            while(rs.next())
            {
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setNickname(rs.getString("Nickname"));
            }         
            con.close();
        return user;
        }
        
}

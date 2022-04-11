package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Authentication {
    

    public boolean isAuthenticated(String userName, String passWord) {
        Connection con = null;
        Boolean result = false;
        PreparedStatement pst = null;
        ResultSet rs =  null;
        try {
            con = dbConnection.connectDB();
            String username = userName;
            String password = passWord;

            pst = con.prepareStatement("Select * from users where username =? and password=?");
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                result = true;
                SessionDAO sessionDAO = new SessionDAO();
                sessionDAO.resetSession();
                sessionDAO.setSessionProperty("UserName", username);
            } else {
                result = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) try {rs.close();} catch (SQLException ignore) {}
            if (pst != null) try {pst.close();} catch (SQLException ignore) {}
            if (con != null) try {con.close();} catch (SQLException ignore) {}
        }   
        return result;
    }
}
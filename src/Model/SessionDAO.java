package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionDAO {

    public SessionDAO() {
        //System.out.println("Inside SessionDAO - initialized");
    }

    public Session getSession() throws SQLException {
        Session curSession = new Session();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = dbConnection.connectDB();
            pst = con.prepareStatement("SELECT * FROM Session;");
            rs = pst.executeQuery();
            while (rs.next()) {
                curSession.setUsername(rs.getString("Username"));
                curSession.setBudgetCategoryName(rs.getString("BudgetCategoryName"));
                curSession.setBudgetCategoryDetailsName(rs.getString("CategoryDetailsName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException ignore) {
            }
            if (pst != null) try {
                pst.close();
            } catch (SQLException ignore) {
            }
            if (con != null) try {
                con.close();
            } catch (SQLException ignore) {
            }
        }
        //System.out.println("Returning session object");
        return curSession;
    }

    public void setSessionProperty(String strSessionPropertyName, String strValue) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        //System.out.println(strSessionPropertyName);
        //System.out.println(strValue);
        try {
            con = dbConnection.connectDB();
            pst = con.prepareStatement("UPDATE Session SET " + strSessionPropertyName + " = ?;");
            pst.setString(1, strValue);            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException ignore) {
            }
            if (pst != null) try {
                pst.close();
            } catch (SQLException ignore) {
            }
            if (con != null) try {
                con.close();
            } catch (SQLException ignore) {
            }
        }
    }

    // Reset Session to empty values
    public void resetSession() throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = dbConnection.connectDB();
            pst = con.prepareStatement("UPDATE Session SET UserName = '', BudgetCategoryName = '';");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException ignore) {
            }
            if (pst != null) try {
                pst.close();
            } catch (SQLException ignore) {
            }
            if (con != null) try {
                con.close();
            } catch (SQLException ignore) {
            }
        }
    }
}


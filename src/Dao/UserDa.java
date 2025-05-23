package Dao;

import Database.MySqlConnection;
import Model.SignUp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDa {
    MySqlConnection mysql = new MySqlConnection();
  
    // Sign-up
    public void signUp(SignUp user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO signUp (email, name, code, password) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserEmail());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getUserCode());
            pstmt.setString(4, user.getUserPassword());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            mysql.closeConnection(conn);
        }
    }

    // Check login
    public boolean checkUser(SignUp user) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM sighUp WHERE email = ? AND password =?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserEmail());
            pstmt.setString(2, user.getUserPassword());
            ResultSet result = pstmt.executeQuery();

            return result.next();
        } catch (SQLException e) {
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            mysql.closeConnection(conn);
        }

        return false;
    }
}

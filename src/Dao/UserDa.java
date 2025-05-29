package Dao;

import Database.MySqlConnection;
import Model.Admin;
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
    public boolean signUp(SignUp user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO user_DB (email, name, code, password) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserEmail());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getUserCode());
            pstmt.setString(4, user.getUserPassword());

            int result = pstmt.executeUpdate();
            return result >0;
        } catch (SQLException e) {
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    public boolean emailExists(String email) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT id FROM user_DB WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // returns true if email exists
        } catch (SQLException e) {
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE, null, e);
            return true;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    // Check login
    public boolean checkUser(SignUp user) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM user_DB WHERE email = ? AND password =?";
        
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
    
    
    // admin
    public boolean admin(Admin admin){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM admin_DB WHERE username = ? AND password = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            ResultSet result = pstmt.executeQuery();
            return result.next();
        } catch (SQLException e){
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
}

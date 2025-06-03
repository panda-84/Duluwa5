/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.MySqlConnection;
import Model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class AdminDa {
    MySqlConnection mysql = new MySqlConnection();
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

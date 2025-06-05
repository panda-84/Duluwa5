
package Dao;

import java.sql.Connection;
import Database.MySqlConnection;
import Model.GuideA;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class GuideDa {
    MySqlConnection mysql = new MySqlConnection();
    
   
    public boolean insertGuide(GuideA guide) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO guide (first_name, middle_name, last_name, gender, phone_number, age, status, bio, photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, guide.getFirstName());
            pstmt.setString(2,guide.getMiddleName());
            pstmt.setString(3,guide.getLastName());
            pstmt.setString(4,guide.getGender());
            pstmt.setString(5,guide.getNumber());
            pstmt.setInt(6,guide.getAge());
            pstmt.setString(7,guide.getStatus());
            pstmt.setString(8,guide.getBio());
            pstmt.setBytes(9,guide.getPicture());

            int result = pstmt.executeUpdate();
            return result >0;
        } catch (SQLException e) {
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
}

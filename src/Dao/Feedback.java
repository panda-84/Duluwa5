/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author acer
 */
public class Feedback {
    public static boolean submitFeedback(int guideId, int userId, int rating, String comment) {
        Connection conn = new MySqlConnection().openConnection();
        String sql = "INSERT INTO guide_feedback (guide_ID, user_id, rating, comment) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, guideId);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, rating);
            pstmt.setString(4, comment);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

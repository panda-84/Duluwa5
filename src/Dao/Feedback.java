/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author acer
 */
public class Feedback {
    MySqlConnection mysql = new MySqlConnection();
    public boolean submitRating(int guideId, int userId, int rating) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO guide_feedback (guide_id, user_id, rating) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, guideId);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, rating);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(Feedback.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    // Get average rating for a guide
    public double getAverageRating(int guideId) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT AVG(rating) AS avg_rating FROM guide_feedback WHERE guide_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, guideId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("avg_rating");
            }
        } catch (SQLException e) {
            Logger.getLogger(Feedback.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            mysql.closeConnection(conn);
        }

        return 0.0;
    }
    
    public double getAverageRatingForGuide(int guideId) {
        double average = 0.0;
        String sql = "SELECT AVG(rating) AS avg_rating FROM guide_feedback WHERE guide_id = ?";

        try (Connection conn = mysql.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, guideId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    average = rs.getDouble("avg_rating");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return average;
    }

}

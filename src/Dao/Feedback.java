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
import java.util.HashMap;
import java.util.Map;
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
    
    public boolean hasUserRatedGuide(int guideId, int userId) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT 1 FROM guide_feedback WHERE guide_id = ? AND user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, guideId);
            stmt.setInt(2, userId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // returns true if a record exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    public Map<Integer, Double> getGuideRatingPercentages() {
        Map<Integer, Double> ratingPercentages = new HashMap<>();
        String sql = """
            SELECT guide_id, 
                   (COUNT(DISTINCT user_id) / (SELECT COUNT(*) FROM user_db) * 100) AS percentage
            FROM guide_feedback
            GROUP BY guide_id
        """;

        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int guideId = rs.getInt("guide_id");
                double percentage = rs.getDouble("percentage");
                ratingPercentages.put(guideId, percentage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratingPercentages;
    }


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.MySqlConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author acer
 */
public class PlansDa {
    MySqlConnection mysql = new MySqlConnection();
    
    public boolean addPlan(int userId, String planText) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO user_plans (user_id, plan_text) VALUES (?, ?)";
        try (
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, planText);
            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Map<Integer, String> getPlanMapByUserId(int userId) {
        Map<Integer, String> plans = new LinkedHashMap<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT plan_id, plan_text FROM user_plans WHERE user_id = ? ORDER BY created_at ASC";
        try (
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                plans.put(rs.getInt("plan_id"), rs.getString("plan_text"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return plans;
    }

    public boolean deletePlanById(int planId) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM user_plans WHERE plan_id = ?";
        try (
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, planId);
            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

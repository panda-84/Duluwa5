/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author acer
 */
public class EquipmentsDa {
    MySqlConnection mysql = new MySqlConnection();
    
    public boolean addEquipment(int userId, String item) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO user_equipments (user_id, item_name) VALUES (?, ?)";
        try (
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, item);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Map<Integer, String> getEquipmentsByUserId(int userId) {
        Map<Integer, String> equipments = new LinkedHashMap<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT equipment_id, item_name FROM user_equipments WHERE user_id = ?";
        try (
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                equipments.put(rs.getInt("equipment_id"), rs.getString("item_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipments;
    }

    public boolean deleteEquipmentById(int equipmentId) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM user_equipments WHERE equipment_id = ?";
        try (
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, equipmentId);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

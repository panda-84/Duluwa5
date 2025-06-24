package Dao;

import Database.MySqlConnection;

import Model.SignUp;
import Model.UserEquipment;
import Model.UserPlan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


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
    
   

    
    
    //forgetPassword
    public boolean forgetPassword(String email,String code,String newPassword){
        Connection conn = mysql.openConnection();
        String checkSql = "SELECT user_id FROM user_DB WHERE email = ? AND code = ?";
        String updateSql = "UPDATE user_DB SET password=? WHERE email = ? AND code = ?";
        
        try(
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            PreparedStatement updateStmt = conn.prepareStatement(updateSql)
        ){
            checkStmt.setString(1, email);
            checkStmt.setString(2,code);
            ResultSet rs = checkStmt.executeQuery();
            
            if (!rs.next()){
                return false;
            }
            
            updateStmt.setString(1, newPassword);
            updateStmt.setString(2, email);
            updateStmt.setString(3, code);
            
            int updated = updateStmt.executeUpdate();
            return updated > 0;
        } catch (SQLException e){
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally{
            mysql.closeConnection(conn);
        }
    }
        
    //change password
    public boolean changePassword(String email,String oldPassword,String newPassword){
        Connection conn = mysql.openConnection();
        String checkSql = "SELECT user_id FROM user_DB WHERE email = ? AND password = ?";
        String updateSql = "UPDATE user_DB SET password=? WHERE email = ? AND password = ?";

        try(
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            PreparedStatement updateStmt = conn.prepareStatement(updateSql)
        ){
            checkStmt.setString(1, email);
            checkStmt.setString(2,oldPassword);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()){
                return false;
            }

            updateStmt.setString(1, newPassword);
            updateStmt.setString(2, email);
            updateStmt.setString(3, oldPassword);

            int updated = updateStmt.executeUpdate();
            return updated > 0;
        } catch (SQLException e){
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE,  null, e);
            return false;
        } finally{
            mysql.closeConnection(conn);
        }

    }
    
   
    
    public  boolean deleteAccountById(int userId) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM user_DB WHERE user_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

   
    
   
    
    
    
    public int getUserCount() {
        int count = 0;
        Connection conn = mysql.openConnection();
        String sql = "SELECT COUNT(*) AS total FROM user_DB";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }

        return count;
    }
    
    
    public ArrayList<UserPlan> getPlansByUserId(int userId) {
        ArrayList<UserPlan> plans = new ArrayList<>();
        String query = "SELECT plan_id, user_id, plan FROM user_plans WHERE user_id = ?";
        Connection conn = mysql.openConnection();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserPlan plan = new UserPlan();
                plan.setPlanId(rs.getInt("plan_id"));
                plan.setUserId(rs.getInt("user_id"));
                plan.setPlan(rs.getString("plan"));

                plans.add(plan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plans;
    }
    
    public ArrayList<UserEquipment> getEquipmentsByUserId(int userId) {
        ArrayList<UserEquipment> equipments = new ArrayList<>();
        String query = "SELECT equipment_id, user_id, equipment FROM user_equipments WHERE user_id = ?";
        Connection conn = mysql.openConnection();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserEquipment equipment = new UserEquipment();
                equipment.setEquipmentId(rs.getInt("equipment_id"));
                equipment.setUserId(rs.getInt("user_id"));
                equipment.setEquipment(rs.getString("equipment"));

                equipments.add(equipment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipments;
    }
        
    public boolean insertPlan(int userId, String planText) {
        String query = "INSERT INTO user_plans (user_id, plan) VALUES (?, ?)";
        Connection conn = mysql.openConnection();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, planText);

            int rows = stmt.executeUpdate();
            return rows > 0; // true if insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deletePlan(int planId) {
        String query = "DELETE FROM user_plans WHERE plan_id = ?";

        try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, planId);
            int rows = stmt.executeUpdate();
            return rows > 0; // true if a row was deleted

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    
    public boolean insertEquipment(int userId, String Equipment) {
        String query = "INSERT INTO user_equipments (user_id, equipment) VALUES (?, ?)";
        Connection conn = mysql.openConnection();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, Equipment);

            int rows = stmt.executeUpdate();
            return rows > 0; // true if insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

}

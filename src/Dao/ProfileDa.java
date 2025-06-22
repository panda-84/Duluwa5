/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.MySqlConnection;
import Model.ProfileModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author acer
 */
public class ProfileDa {
    MySqlConnection mysql = new MySqlConnection();
    
    public ProfileModel loadProfileByUserId(int userId) {
    Connection conn = mysql.openConnection();
    String sql = "SELECT * FROM user_profile WHERE user_id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            
            int age = rs.getObject("age") != null ? rs.getInt("age") : 0;
            
            ProfileModel profile =  new ProfileModel();
            
            profile.setUserId(rs.getInt("user_id"));
            profile.setFirstName(getSafeString(rs,"first_name"));
            profile.setMiddleName(getSafeString(rs,"middle_name"));
            profile.setLastName(getSafeString(rs,"last_name"));
            profile.setAge(age);
            profile.setNationality(getSafeString(rs,"nationality"));
            profile.setBio(getSafeString(rs,"bio"));
            profile.setCountry(getSafeString(rs,"country"));
            profile.setGender(getSafeString(rs,"gender"));
            profile.setPicture(rs.getBytes("photo"));
            
//                rs.getBytes("photo")
            return profile;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        mysql.closeConnection(conn);
    }

    return null;
}
    
    private String getSafeString(ResultSet rs, String columnLabel) throws SQLException{
        String value = rs.getString(columnLabel);
        return value != null ? value : "";
    }

    public void createUserProfileIfNotExists(int userId) {
    Connection conn = mysql.openConnection();
    try {
        String check = "SELECT user_id FROM user_profile WHERE user_id = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(check)) {
            checkStmt.setInt(1, userId);
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                String insert = "INSERT INTO user_profile (user_id) VALUES (?)";
                System.out.println("userId" + userId);
                try (PreparedStatement insertStmt = conn.prepareStatement(insert)) {
                    insertStmt.setInt(1, userId);
                    insertStmt.executeUpdate();
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        mysql.closeConnection(conn);
    }
}
    public boolean profileInfo(ProfileModel profile) {
        Connection conn = mysql.openConnection();
        
        String sql = "INSERT INTO user_profile (user_id,first_name, middle_name, last_name, age, nationality, bio, country,gender, photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        
    try (PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, profile.getUserId());
            pstmt.setString(2, profile.getFirstName());
            pstmt.setString(3, profile.getMiddleName());
            pstmt.setString(4, profile. getLastName());
            pstmt.setInt(5, profile.getAge());
            pstmt.setString(6, profile.getNationality());
            pstmt.setString(7, profile.getBio());
            pstmt.setString(8, profile.getCountry());
            String gender = profile.getGender();
            if (!gender.equals("Male") && !gender.equals("Female")) {
                JOptionPane.showMessageDialog(null, "choose gender");
                return false;
            }

            pstmt.setString(9, gender); 
            pstmt.setBytes(10,profile.getPicture());
           
           

            int result = pstmt.executeUpdate();
            return result >0;
        } catch (SQLException e) {
            e.printStackTrace();
            
            Logger.getLogger(ProfileDa.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    public boolean updateProfile(ProfileModel profile) {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE user_profile SET first_name=?, middle_name=?, last_name=?, age=?, nationality=?, bio=?, country=?, gender=?, photo=? WHERE user_id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, profile.getFirstName());
            pstmt.setString(2, profile.getMiddleName());
            pstmt.setString(3, profile.getLastName());
            pstmt.setInt(4, profile.getAge());
            pstmt.setString(5, profile.getNationality());
            pstmt.setString(6, profile.getBio());
            pstmt.setString(7, profile.getCountry());
            pstmt.setString(8, profile.getGender());
            pstmt.setBytes(9, profile.getPicture());
            pstmt.setInt(10, profile.getUserId());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            Logger.getLogger(ProfileDa.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("SQL Error on update: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    public boolean updateProfileUser(ProfileModel profile) {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE user_profile SET first_name=?, middle_name=?, last_name=?, age=?, nationality=?, bio=?, country=?, gender=?, photo=? WHERE user_id=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, profile.getFirstName());
            pstmt.setString(2, profile.getMiddleName());
            pstmt.setString(3, profile.getLastName());
            pstmt.setInt(4, profile.getAge());
            pstmt.setString(5, profile.getNationality());
            pstmt.setString(6, profile.getBio());
            pstmt.setString(7, profile.getCountry());
            pstmt.setString(8, profile.getGender());
            pstmt.setBytes(9, profile.getPicture());
            pstmt.setInt(10, profile.getUserId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean deleteUserById(int id) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM user_DB WHERE user_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    public ProfileModel getUserById(int id) {
        Connection conn = mysql.openConnection();

        String sql = "SELECT * FROM user_profile WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new ProfileModel(
                    rs.getInt("user_id"),
                    rs.getString("first_name"),
                    rs.getString("middle_name"),
                    rs.getString("last_name"),
                    rs.getInt("age"),
                    rs.getString("nationality"),
                    rs.getString("bio"),
                    rs.getString("country"),
                    rs.getString("gender"),
                    rs.getBytes("photo")
                    
                );
                 

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
    
    public List<ProfileModel> getAllProfile() {
        List<ProfileModel> profiles = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM user_profile";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ProfileModel profile = new ProfileModel(
                    rs.getInt("user_id"),
                    rs.getString("first_name"),
                    rs.getString("middle_name"),
                    rs.getString("last_name"),
                    rs.getInt("age"),
                    rs.getString("nationality"),
                    rs.getString("bio"),
                    rs.getString("country"),
                    rs.getString("gender"),
                    rs.getBytes("photo")
                    
                );
                profile.setUserId(rs.getInt("user_id"));
                
                profiles.add(profile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }

        return profiles;
    }
    
    public boolean updateUserPhoto(int userId, byte[] photoBytes) {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE user_profile SET photo=? WHERE user_id=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBytes(1, photoBytes);
            pstmt.setInt(2, userId);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    
    public boolean deleteUserAccountById(int userId) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM user_db WHERE user_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    
    public List<ProfileModel> getAllUserProfiles() {
        List<ProfileModel> list = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM user_profile";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ProfileModel profile = new ProfileModel();
                profile.setUserId(rs.getInt("user_id"));
                profile.setFirstName(rs.getString("first_name"));
                profile.setMiddleName(rs.getString("middle_name"));
                profile.setLastName(rs.getString("last_name"));
                profile.setAge(rs.getInt("age"));
                profile.setNationality(rs.getString("nationality"));
                profile.setBio(rs.getString("bio"));
                profile.setCountry(rs.getString("country"));
                profile.setGender(rs.getString("gender"));
                profile.setPicture(rs.getBytes("photo")); // optional
                list.add(profile);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }

        return list;
    }


}


package Dao;

import java.sql.Connection;
import Database.MySqlConnection;
import Model.GuideA;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class GuideDa {
    MySqlConnection mysql = new MySqlConnection();
    
   
    public boolean insertGuide(GuideA guide) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO guide (first_name, middle_name, last_name, gender, phone_number, age, status, bio, photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, guide.getFirstName());
            pstmt.setString(2, guide.getMiddleName() == null ? "" : guide.getMiddleName());
            pstmt.setString(3,guide.getLastName());
            pstmt.setString(4,guide.getGender());
            pstmt.setLong(5, Long.parseLong(guide.getNumber()));
            pstmt.setInt(6,guide.getAge());
            String status = guide.getStatus();
            if (!status.equals("Available") && !status.equals("not Available")) {
                JOptionPane.showMessageDialog(null, "Invalid status. Must be 'Available' or 'not Available'.");
                return false;
            }

            pstmt.setString(7, status); 
            pstmt.setString(8,guide.getBio());
            pstmt.setBytes(9,guide.getPicture());
            
            System.out.println("Inserting guide:");
            System.out.println("First Name: " + guide.getFirstName());
            System.out.println("Middle Name: " + guide.getMiddleName());
            System.out.println("Last Name: " + guide.getLastName());
            System.out.println("Gender: " + guide.getGender());
            System.out.println("Phone: " + guide.getNumber());
            System.out.println("Age: " + guide.getAge());
            System.out.println("Status: " + guide.getStatus());
            System.out.println("Bio: " + guide.getBio());
            System.out.println("Image bytes: " + (guide.getPicture() != null ? guide.getPicture().length : "null"));

            int result = pstmt.executeUpdate();
            return result >0;
        } catch (SQLException e) {
            Logger.getLogger(GuideDa.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    public boolean updateGuide(GuideA guide) {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE guide SET first_name=?, middle_name=?, last_name=?, gender=?, phone_number=?, age=?, status=?, bio=?, photo=? WHERE guide_ID=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, guide.getFirstName());
            pstmt.setString(2, guide.getMiddleName());
            pstmt.setString(3, guide.getLastName());
            pstmt.setString(4, guide.getGender());
            pstmt.setString(5, guide.getNumber());
            pstmt.setInt(6, guide.getAge());
            pstmt.setString(7, guide.getStatus());
            pstmt.setString(8, guide.getBio());
            pstmt.setBytes(9, guide.getPicture());
            pstmt.setInt(10, guide.getGuideId());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            Logger.getLogger(GuideDa.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("SQL Error on update: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    public GuideA getGuideById(int id) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM guide WHERE guide_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new GuideA(
                    rs.getString("first_name"),
                    rs.getString("middle_name"),
                    rs.getString("last_name"),
                    rs.getString("gender"),
                    rs.getString("phone_number"),
                    rs.getInt("age"),
                    rs.getString("status"),
                    rs.getString("bio"),
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
    
    public boolean deleteGuideById(int id) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM guide WHERE guide_ID = ?";

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



}

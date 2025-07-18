/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.MySqlConnection;
import Model.BookingT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.lang.model.util.Types;
import javax.swing.JOptionPane;


/**
 *
 * @author acer
 */
public class AdminBookingDa {
    MySqlConnection mysql = new MySqlConnection();
    public boolean adminBookTicket(BookingT book) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO booking (guide_ID, first_name, middle_name, last_name, phone_number, email, start_date, people_number, age, country, nationality, address, zip_code, payment, end_date,total_price) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, book.getGuideID());
            pstmt.setString(2, book.getFirstName());
            pstmt.setString(3, book.getMiddleName());
            pstmt.setString(4, book. getLastName());
            pstmt.setString(5, book.getPhoneNumber());
            pstmt.setString(6, book.getEmail());
            pstmt.setString(7, book.getStartDate());
            pstmt.setString(8, book.getNumberOfPeople());
            pstmt.setInt(9, book.getAge());
            pstmt.setString(10, book.getCountry());
            pstmt.setString(11, book.getNationality());
            pstmt.setString(12, book.getAddress());
            pstmt.setString(13, book.getZipCode());
            
            String payment = book.getPayment();
            if (!payment.equals("Cash") && !payment.equals("Esewa")) {
                JOptionPane.showMessageDialog(null, "Invalid payment. Must be 'Cash' or 'Esewa'.");
                return false;
            }
            pstmt.setString(14, payment);
            
            pstmt.setString(15, book.getEndDate());
            pstmt.setBigDecimal(16,book.getTotalPrice());
            int result = pstmt.executeUpdate();
            return result >0;
        } catch (SQLException e) {
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    public List<BookingT> getAllPayment() {
        List<BookingT> bookings = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM booking";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BookingT book = new BookingT(
                    rs.getString("first_name"),
                    rs.getString("middle_name"),
                    rs.getString("last_name"),
                    rs.getString("phone_number"),
                    rs.getString("email"),
                    rs.getString("start_date"),
                    rs.getString("people_number"),
                    rs.getInt("age"),
                    rs.getString("country"),
                    rs.getString("nationality"),
                    rs.getString("address"),
                    rs.getString("zip_code"),
                    rs.getString("payment"),
                    rs.getString("end_date"),
                    rs.getBigDecimal("total_price")
                    
                );
                book.setBookId(rs.getInt("booking_ID"));
                book.setGuideID(rs.getInt("guide_ID"));
                bookings.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }

        return bookings;
    }
    public boolean deleteBookingById(int id) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM booking WHERE booking_id = ?";

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
    
    
    public BookingT getBookById(int id) {
        Connection conn = mysql.openConnection();

        String sql = "SELECT * FROM booking WHERE booking_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new BookingT(
                    rs.getInt("guide_ID"),
                    rs.getString("first_name"),
                    rs.getString("middle_name"),
                    rs.getString("last_name"),
                    rs.getString("phone_number"),
                    rs.getString("email"),
                    rs.getString("start_date"),
                    rs.getString("people_number"),
                    rs.getInt("age"),
                    rs.getString("country"),
                    rs.getString("nationality"),
                    rs.getString("address"),
                    rs.getString("zip_code"),
                    rs.getString("payment"),
                    rs.getString("end_date"),
                    rs.getBigDecimal("total_price")
                );
                 

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
    
    
    public boolean updateBooking(BookingT book) {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE booking SET guide_ID=?, first_name=?, middle_name=?, last_name=?, phone_number=?, email=?, start_date=?, people_number=?, age=?, country=?, nationality=?, address=?, zip_code=?, payment=?, end_date=?, total_price=? WHERE booking_id=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            
            pstmt.setInt(1, book.getGuideID());
            pstmt.setString(2, book.getFirstName());
            pstmt.setString(3, book.getMiddleName());
            pstmt.setString(4, book. getLastName());
            pstmt.setString(5, book.getPhoneNumber());
            pstmt.setString(6, book.getEmail());
            pstmt.setString(7, book.getStartDate());
            pstmt.setString(8, book.getNumberOfPeople());
            pstmt.setInt(9, book.getAge());
            pstmt.setString(10, book.getCountry());
            pstmt.setString(11, book.getNationality());
            pstmt.setString(12, book.getAddress());
            pstmt.setString(13, book.getZipCode());
            pstmt.setString(14, book.getPayment());
            pstmt.setString(15, book.getEndDate());
            pstmt.setBigDecimal(16, book.getTotalPrice());
            pstmt.setInt(17, book.getBookId()); 

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            Logger.getLogger(AdminBookingDa.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("SQL Error on update: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
}

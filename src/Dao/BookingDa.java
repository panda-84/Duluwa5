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
import java.time.LocalDate;
import javax.swing.JOptionPane;


/**
 *
 * @author acer
 */
public class BookingDa {
    MySqlConnection mysql = new MySqlConnection();
    public BookingT bookTicket(BookingT book) {
        Connection conn = mysql.openConnection();
        
        String sql = "INSERT INTO booking (guide_ID,first_name, middle_name, last_name, phone_number, email, start_date, people_number, age, country, nationality, address, zip_code, payment, end_date, total_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        
        
    try (PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
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

            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    book.setBookId(generatedId); // ✅ Set booking ID
                }
                return book;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE, null, e);
            return null;
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
                    rs.getString("endDate"),
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
    
    public ArrayList<BookingT> getBookings() {
    ArrayList<BookingT> bookList = new ArrayList<>();
    Connection conn = mysql.openConnection();
    String sql = "SELECT *, CONCAT_WS(' ', first_name, middle_name, last_name) AS full_name FROM booking";

    try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            BookingT book =new BookingT(
                    rs.getString("full_name"),
                    rs.getInt("age"),
                    rs.getString("phone_number"),
                    rs.getString("email"),
                    rs.getString("country"),
                    rs.getString("address"),
                    rs.getString("people_number"),
                    rs.getString("payment"),
                    rs.getString("start_date"),
                    rs.getString("end_date")
            );
                    
            book.setGuideID(rs.getInt("guide_ID"));
            book.setBookId(rs.getInt("booking_id"));
            bookList.add(book);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        mysql.closeConnection(conn);
    }

    return bookList;
}
    
    public List<String> getUnavailableDateRanges(int guideId, LocalDate newStart, LocalDate newEnd) {
        List<String> unavailableRanges = new ArrayList<>();
        String sql = "SELECT start_date, end_date FROM booking WHERE guide_id = ? " +
                     "AND (start_date <= ? AND end_date >= ?)";

        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, guideId);
            stmt.setString(2, newEnd.toString());
            stmt.setString(3, newStart.toString());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String start = rs.getString("start_date");
                String end = rs.getString("end_date");
                unavailableRanges.add(start + " to " + end);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return unavailableRanges;
    }


   
    
    
}

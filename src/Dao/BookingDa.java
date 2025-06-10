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


/**
 *
 * @author acer
 */
public class BookingDa {
    MySqlConnection mysql = new MySqlConnection();
    public boolean bookTicket(BookingT book) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO booking (first_name, middle_name, last_name, phone_number, email, start_date, people_number, age, country, nationality, address, zip_code, payment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getFirstName());
            pstmt.setString(2, book.getMiddleName());
            pstmt.setString(3, book. getLastName());
            pstmt.setString(4, book.getPhoneNumber());
            pstmt.setString(5, book.getEmail());
            pstmt.setString(6, book.getStartDate());
            pstmt.setString(7, book.getNumberOfPeople());
            pstmt.setInt(8, book.getAge());
            pstmt.setString(9, book.getCountry());
            pstmt.setString(10, book.getNationality());
            pstmt.setString(11, book.getAddress());
            pstmt.setString(12, book.getZipCode());
            pstmt.setString(13, book.getPayment());

            int result = pstmt.executeUpdate();
            return result >0;
        } catch (SQLException e) {
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
}

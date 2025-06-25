/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.BookingDa;
import Model.BookingT;
import Model.GuideA;
import View.BookingReceipt;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class BookingController {
   

    public static BookingT bookingAll(String firstNameText, String middleNameText, String lastNameText, String phoneNumberText, String emailText,
    String startDateText, String peopleText, String ageText, String countryText, String nationalityText, String addressText, String zipCodeText, String paymentText, String endDateText, GuideA guide, JFrame frame) {

    BookingDa dao = new BookingDa();
    System.out.println("Guide ID: " + guide.getGuideId());

    // ✅ Check for empty fields
    if (firstNameText.isEmpty() || lastNameText.isEmpty() || phoneNumberText.isEmpty() || emailText.isEmpty() ||
        startDateText.isEmpty() || peopleText.isEmpty() || ageText.isEmpty() || countryText.isEmpty() || nationalityText.isEmpty() ||
        addressText.isEmpty() || zipCodeText.isEmpty() || paymentText.isEmpty() || endDateText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill all the fields");
        return null;
    }

    // ✅ Check for '@' in email
    if (!emailText.contains("@")) {
        JOptionPane.showMessageDialog(null, "Invalid email.");
        return null;
    }

    try {
        int age = Integer.parseInt(ageText);

        // ✅ Parse and validate dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateText, formatter);
        LocalDate endDate = LocalDate.parse(endDateText, formatter);
        LocalDate today = LocalDate.now();

        if (startDate.isBefore(today) || endDate.isBefore(today)) {
            JOptionPane.showMessageDialog(null, "Please choose a valid date. Past dates are not allowed.");
            return null;
        }

        if (!endDate.isAfter(startDate)) {
            JOptionPane.showMessageDialog(null, "End date must be after start date.");
            return null;
        }

        // ✅ Check guide availability
        List<String> unavailable = dao.getUnavailableDateRanges(guide.getGuideId(), startDate, endDate);
        if (!unavailable.isEmpty()) {
            StringBuilder message = new StringBuilder("This guide is not available for the following dates:\n");
            for (String range : unavailable) {
                message.append("• ").append(range).append("\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
            return null;
        }

        // ✅ Calculate total days and price (Rs. 5000 per day)
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        BigDecimal totalPrice = BigDecimal.valueOf(totalDays).multiply(BigDecimal.valueOf(5000));

        JOptionPane.showMessageDialog(null,
            "Booking Duration: " + totalDays + " day(s)\n" +
            "Per Day Rate: Rs. 5000\n" +
            "Total Price: Rs. " + totalPrice);

        // ✅ Create and save booking
        String fullName = firstNameText + " " + middleNameText + " " + lastNameText;
        BookingT book = new BookingT(guide.getGuideId(), firstNameText, middleNameText, lastNameText, phoneNumberText, emailText, startDateText, peopleText,
            age, countryText, nationalityText, addressText, zipCodeText, paymentText, endDateText, totalPrice);
        book.setFullName(fullName);

        BookingT inserted = dao.bookTicket(book);
        if (inserted != null) {
            JOptionPane.showMessageDialog(null, "Successfully Booked");
            return inserted;
        } else {
            return null;
        }

    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy-MM-dd");
        return null;
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(frame, "Booking failed: " + e.getMessage());
        return null;
    }
}


   
    
    

    public void loadPaymentDataIntoTable(JTable paymentTable) {
        BookingDa dao = new BookingDa();
        List<BookingT> bookList = dao.getAllPayment();

        DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
        model.setRowCount(0); 

        for (BookingT book : bookList) {
            Object[] row = new Object[]{
                book.getBookId(),
                book.getFirstName(),
                book.getMiddleName(),
                book.getLastName(),
                book.getPhoneNumber(),
                book.getEmail(),
                book.getStartDate(),
                book.getNumberOfPeople(),
                book.getAge(),
                book.getCountry(),
                book.getNationality(),
                book.getAddress(),
                book.getZipCode(),
                book.getPayment(),
                book.getEndDate(),
                book.getTotalPrice()
                
            };
            model.addRow(row);
        }
    }
    
}

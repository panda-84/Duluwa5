/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.AdminBookingDa;
import Dao.BookingDa;
import Model.BookingT;
import View.adminDashboard;
import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class adminBookingController {
    public static boolean adminBookingAll(String guideIdText, String firstNameText, String middleNameText, String lastNameText, String phoneNumberText, String emailText,
        String startDateText, String peopleText, String ageText, String countryText, String nationalityText, String addressText, String zipCodeText, String paymentText, String endDateText, JFrame frame) {

    AdminBookingDa dao = new AdminBookingDa();

    // ✅ Empty field check
    if (firstNameText.isEmpty() || lastNameText.isEmpty() || phoneNumberText.isEmpty() || emailText.isEmpty() ||
        startDateText.isEmpty() || peopleText.isEmpty() || ageText.isEmpty() || countryText.isEmpty() || nationalityText.isEmpty() ||
        addressText.isEmpty() || zipCodeText.isEmpty() || paymentText.isEmpty() || endDateText.isEmpty() || guideIdText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill all the fields");
        return false;
    }

    // ✅ Check for '@' in email
    if (!emailText.contains("@")) {
        JOptionPane.showMessageDialog(null, "Invalid email. '@' symbol is missing.");
        return false;
    }

    try {
        int age = Integer.parseInt(ageText);
        int guide_ID = Integer.parseInt(guideIdText);

        // ✅ Date validation
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateText, formatter);
        LocalDate endDate = LocalDate.parse(endDateText, formatter);
        LocalDate today = LocalDate.now();

        if (startDate.isBefore(today) || endDate.isBefore(today)) {
            JOptionPane.showMessageDialog(null, "Please choose a valid date. Past dates are not allowed.");
            return false;
        }

        if (!endDate.isAfter(startDate)) {
            JOptionPane.showMessageDialog(null, "End date must be after start date.");
            return false;
        }

        // ✅ Check for guide availability
        BookingDa userBookingDao = new BookingDa(); // Use your BookingDa for checking
        List<String> unavailable = userBookingDao.getUnavailableDateRanges(guide_ID, startDate, endDate);
        if (!unavailable.isEmpty()) {
            StringBuilder message = new StringBuilder("This guide is not available for the following dates:\n");
            for (String range : unavailable) {
                message.append("• ").append(range).append("\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
            return false;
        }

        // ✅ Calculate total days and total price
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        BigDecimal totalPrice = BigDecimal.valueOf(totalDays).multiply(BigDecimal.valueOf(5000));

        JOptionPane.showMessageDialog(null,
            "Booking Duration: " + totalDays + " day(s)\n" +
            "Per Day Rate: Rs. 5000\n" +
            "Total Price: Rs. " + totalPrice);

        // ✅ Proceed with booking
        BookingT book = new BookingT(guide_ID, firstNameText, middleNameText, lastNameText, phoneNumberText, emailText, startDateText, peopleText,
            age, countryText, nationalityText, addressText, zipCodeText, paymentText, endDateText, totalPrice);

        boolean success = dao.adminBookTicket(book);

        if (success) {
            JOptionPane.showMessageDialog(null, "Successfully Booked");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Booking failed.");
            return false;
        }

    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy-MM-dd");
        return false;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        return false;
    }
}


    
    public void loadPaymentDataIntoTable(JTable paymentTable) {
        AdminBookingDa dao = new AdminBookingDa();
        List<BookingT> bookList = dao.getAllPayment();

        DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
        model.setRowCount(0); 

        for (BookingT book : bookList) {
            Object[] row = new Object[]{
                book.getBookId(),
                book.getGuideID(),
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
    
    public void deleteBookingUpdate(String bookingIdText){
        if (bookingIdText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a Booking ID to delete.");
            return;
        }

        try {
            int id = Integer.parseInt(bookingIdText);

            // Confirm with user
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete Booking ID " + id + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                AdminBookingDa dao = new AdminBookingDa();
                boolean success = dao.deleteBookingById(id);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Guide deleted successfully.");
                    adminDashboard clear = new adminDashboard();
                    clear.clearBookingFields();
                } else {
                    JOptionPane.showMessageDialog(null, "No guide found with ID: " + id);
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Guide ID format.");
        }
    }
    
    public void updateBookingSubmit(JTable paymentTable,
                                JTextField bBookingId,
                                JTextField bGuide_ID,
                                JTextField bFirstName,
                                JTextField bMiddleName,
                                JTextField bLastName,
                                JTextField bNumber,
                                JTextField bEmail,
                                JDateChooser bStartDate,
                                JDateChooser bEndDate,
                                JTextField bNumberOfPeople,
                                JTextField bAge,
                                JTextField bCountry,
                                JTextField bNationality,
                                JTextField bAddress,
                                JTextField bZipCode,
                                JComboBox<String> bPayment) {

        try {
            // Parse date
            Date selectedDate = bStartDate.getDate();
            Date endDate = bEndDate.getDate();

            if (selectedDate == null || endDate == null) {
                JOptionPane.showMessageDialog(null, "Please select both start and end dates.");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startDateText = sdf.format(selectedDate);
            String endDateText = sdf.format(endDate);

            // Collect all text field inputs
            String firstNameText = bFirstName.getText().trim();
            String middleNameText = bMiddleName.getText().trim();
            String lastNameText = bLastName.getText().trim();
            String phoneNumberText = bNumber.getText().trim();
            String emailText = bEmail.getText().trim();
            String peopleText = bNumberOfPeople.getText().trim();
            String ageText = bAge.getText().trim();
            String countryText = bCountry.getText().trim();
            String nationalityText = bNationality.getText().trim();
            String addressText = bAddress.getText().trim();
            String zipCodeText = bZipCode.getText().trim();
            String paymentText = (String) bPayment.getSelectedItem();
            String guideIdText = bGuide_ID.getText().trim();
            String bookingIdText = bBookingId.getText().trim();

            // Check if any required field is empty
            if (firstNameText.isEmpty() || middleNameText.isEmpty() || lastNameText.isEmpty() || phoneNumberText.isEmpty()
                    || emailText.isEmpty() || startDateText.isEmpty() || endDateText.isEmpty()
                    || peopleText.isEmpty() || ageText.isEmpty() || countryText.isEmpty()
                    || nationalityText.isEmpty() || addressText.isEmpty() || zipCodeText.isEmpty()
                    || paymentText == null || guideIdText.isEmpty() || bookingIdText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
                return;
            }

            if (!emailText.contains("@")) {
                JOptionPane.showMessageDialog(null, "Invalid email address. '@' is required.");
                return;
            }

            // Parse number fields
            int age = Integer.parseInt(ageText);
            int guide_ID = Integer.parseInt(guideIdText);
            int booking_id = Integer.parseInt(bookingIdText);

            // Date validation
            LocalDate start = LocalDate.parse(startDateText);
            LocalDate end = LocalDate.parse(endDateText);
            LocalDate today = LocalDate.now();

            if (start.isBefore(today) || end.isBefore(today)) {
                JOptionPane.showMessageDialog(null, "Please select future dates.");
                return;
            }

            if (!end.isAfter(start)) {
                JOptionPane.showMessageDialog(null, "End date must be after start date.");
                return;
            }

            // ✅ Calculate total price (5000 per day, NOT per person)
            long totalDays = ChronoUnit.DAYS.between(start, end) + 1;
            BigDecimal totalPrice = BigDecimal.valueOf(totalDays).multiply(BigDecimal.valueOf(5000));

            // ✅ Show confirmation
            JOptionPane.showMessageDialog(null,
                "Booking Duration: " + totalDays + " day(s)\n" +
                "Per Day Rate: Rs. 5000\n" +
                "Total Price: Rs. " + totalPrice);

            // Create booking object
            BookingT book = new BookingT(guide_ID, firstNameText, middleNameText, lastNameText, phoneNumberText,
                    emailText, startDateText, peopleText, age, countryText, nationalityText, addressText,
                    zipCodeText, paymentText, endDateText, totalPrice);
            book.setBookId(booking_id);

            // Perform update
            AdminBookingDa dao = new AdminBookingDa();
            boolean success = dao.updateBooking(book);

            if (success) {
                JOptionPane.showMessageDialog(null, "Booking updated successfully.");
                loadPaymentDataIntoTable(paymentTable);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update booking.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    
    
   
    
    
     
}

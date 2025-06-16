/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.AdminBookingDa;
import Model.BookingT;
import View.adminDashboard;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class adminBookingController {
    public static boolean adminBookingAll(String guideIdText, String firstNameText, String middleNameText, String lastNameText, String phoneNumberText, String emailText,
        String startDateText, String peopleText, String ageText, String countryText, String nationalityText, String addressText, String zipCodeText, String paymentText, String endDateText, JFrame frame){
        AdminBookingDa dao = new AdminBookingDa();
        
        if (firstNameText.isEmpty() || middleNameText.isEmpty()|| lastNameText.isEmpty()|| phoneNumberText.isEmpty()|| emailText.isEmpty()||
                startDateText.isEmpty()|| peopleText.isEmpty()|| ageText.isEmpty()|| countryText.isEmpty()|| nationalityText.isEmpty()||
                addressText.isEmpty()|| zipCodeText.isEmpty()|| paymentText.isEmpty()|| endDateText.isEmpty()|| guideIdText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        }
         else {
            try{
                int age = Integer.parseInt(ageText);
                int guide_ID = Integer.parseInt(guideIdText);
                
                BookingT book = new BookingT( guide_ID,firstNameText, middleNameText, lastNameText, phoneNumberText, emailText, startDateText, peopleText,
                age, countryText, nationalityText, addressText, zipCodeText, paymentText, endDateText);

                boolean success = dao.adminBookTicket(book);


                if (success) {
                    JOptionPane.showMessageDialog(null, "successfully Booked");
//                    JTable paymentTable=new JTable();
//                    paymentTable.loadPaymentDataIntoTable(paymentTable);


//                    clearBooking();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Booking failed.");
                   return false;
                }
                
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                return false;
            }
            
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
                book.getEndDate()
                
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
    
    
    
   
    
    
     
}

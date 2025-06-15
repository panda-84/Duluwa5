/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.BookingDa;
import Model.BookingT;
import Model.GuideA;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class BookingController {
   

    public static boolean bookingAll(String firstNameText, String middleNameText, String lastNameText, String phoneNumberText, String emailText,
        String startDateText, String peopleText, String ageText, String countryText, String nationalityText, String addressText, String zipCodeText, String paymentText,GuideA guide, JFrame frame){
        BookingDa dao = new BookingDa();
        System.out.println("Guide ID: " + guide.getGuideId());
        if (firstNameText.isEmpty() || middleNameText.isEmpty()|| lastNameText.isEmpty()|| phoneNumberText.isEmpty()|| emailText.isEmpty()||
                startDateText.isEmpty()|| peopleText.isEmpty()|| ageText.isEmpty()|| countryText.isEmpty()|| nationalityText.isEmpty()||
                addressText.isEmpty()|| zipCodeText.isEmpty()|| paymentText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        }
         else {
            try{
                int age = Integer.parseInt(ageText);
                BookingT book = new BookingT(guide.getGuideId(),firstNameText, middleNameText, lastNameText, phoneNumberText, emailText, startDateText, peopleText,
                age, countryText, nationalityText, addressText, zipCodeText, paymentText);

                boolean success = dao.bookTicket(book);
                

                if (success) {
                    JOptionPane.showMessageDialog(null, "successfully Booked");

//                    clearBooking();
                    return true;
                } else {
                   return false;
                }
                
            }
            catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Booking failed: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                
                return false;
            }
            
        }
        
        
        
    }
    
//    private void clearBooking(String firstNameText, String middleNameText, String lastNameText, String phoneNumberText, String emailText,
//        String startDateText, String peopleText, String ageText, String countryText, String nationalityText, String addressText, String zipCodeText, String paymentText){
//        firstNameText.setText("");
//        middleNameText.setText("");
//        lastNameText.setText("");
//        phoneNumberText.setText("");
//        emailText.setText("");
//        startDateText.setDate(null);
//        peopleText.setText("");
//        ageText.setText("");
//        countryText.setText("");
//        nationalityText.setText("");
//        addressText.setText("");
//        zipCodeText.setText("");
//        paymentText.setSelectedIndex(0);
//    
//    }  
    
    

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
                book.getPayment()
                
            };
            model.addRow(row);
        }
    }
    
}

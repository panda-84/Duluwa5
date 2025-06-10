/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.BookingDa;
import Model.BookingT;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class BookingController {
    public static boolean bookingAll(String firstNameText, String middleNameText, String lastNameText, String phoneNumberText, String emailText,
        String startDateText, String peopleText, String ageText, String countryText, String nationalityText, String addressText, String zipCodeText, String paymentText, JFrame frame){
        BookingDa dao = new BookingDa();
        
        if (firstNameText.isEmpty() || middleNameText.isEmpty()|| lastNameText.isEmpty()|| phoneNumberText.isEmpty()|| emailText.isEmpty()||
                startDateText.isEmpty()|| peopleText.isEmpty()|| ageText.isEmpty()|| countryText.isEmpty()|| nationalityText.isEmpty()||
                addressText.isEmpty()|| zipCodeText.isEmpty()|| paymentText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        }
         else {
            try{
                int age = Integer.parseInt(ageText);
                BookingT book = new BookingT(firstNameText, middleNameText, lastNameText, phoneNumberText, emailText, startDateText, peopleText,
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
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.ProfileDa;
import Model.ProfileModel;
import View.adminDashboard;
import View.userProfileEdit;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class ProfileController {
//    public static boolean adminBookingAll(String guideIdText, String firstNameText, String middleNameText, String lastNameText, String phoneNumberText, String emailText,
//        String startDateText, String peopleText, String ageText, String countryText, String nationalityText, String addressText, String zipCodeText, String paymentText, String endDateText, JFrame frame){
//        ProfileDa dao = new ProfileDa();
//        
//        if (firstNameText.isEmpty() || middleNameText.isEmpty()|| lastNameText.isEmpty()|| phoneNumberText.isEmpty()|| emailText.isEmpty()||
//                startDateText.isEmpty()|| peopleText.isEmpty()|| ageText.isEmpty()|| countryText.isEmpty()|| nationalityText.isEmpty()||
//                addressText.isEmpty()|| zipCodeText.isEmpty()|| paymentText.isEmpty()|| endDateText.isEmpty()|| guideIdText.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Please fill all the fields");
//            return false;
//        }
//         else {
//            try{
//                int age = Integer.parseInt(ageText);
//                int guide_ID = Integer.parseInt(guideIdText);
//                
//                ProfileModel book = new ProfileModel( guide_ID,firstNameText, middleNameText, lastNameText, phoneNumberText, emailText, startDateText, peopleText,
//                age, countryText, nationalityText, addressText, zipCodeText, paymentText, endDateText);
//
//                boolean success = dao.adminBookTicket(book);
//
//
//                if (success) {
//                    JOptionPane.showMessageDialog(null, "successfully Booked");
////                    JTable paymentTable=new JTable();
////                    paymentTable.loadPaymentDataIntoTable(paymentTable);
//
//
////                    clearBooking();
//                    return true;
//                } else {
//                    JOptionPane.showMessageDialog(null, "Booking failed.");
//                   return false;
//                }
//                
//            }
//            catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
//                return false;
//            }
//            
//        }
//        
//        
//        
//    }
    
    public void deleteProfileUpdate(String profileIdText){
        if (profileIdText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a profile ID to delete.");
            return;
        }

        try {
            int id = Integer.parseInt(profileIdText);

            // Confirm with user
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete Booking ID " + id + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                ProfileDa dao = new ProfileDa();
                boolean success = dao.deleteUserById(id);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Guide deleted successfully.");
                    adminDashboard clear = new adminDashboard();
                    clear.clearUserFields();
                } else {
                    JOptionPane.showMessageDialog(null, "No guide found with ID: " + id);
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Guide ID format.");
        }
    }
    
    public void  updateProfileAdmin(String firstName, String middleName, String lastName, String ageText, String nationality,
        String country, String gender, String bio, String profileIdText,  byte[] imageBytes){
        if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || ageText.isEmpty() || nationality.isEmpty() || country.isEmpty() || gender.isEmpty() || bio.isEmpty() || profileIdText.isEmpty()) {
            JOptionPane.showMessageDialog(null,"please fill all the fields.");
            return;
        }
        try{
            int age = Integer.parseInt(ageText);
            int userId = Integer.parseInt(profileIdText);
            
            ProfileModel profile = new ProfileModel(userId,firstName,middleName, lastName, age, nationality,bio, country, gender, imageBytes);
            
            ProfileDa dao = new ProfileDa();
            boolean success = dao.updateProfile(profile);
            if(success){
               JOptionPane.showMessageDialog(null,"Profile updated Successfully "); 
            } else{
               JOptionPane.showMessageDialog(null,"Failed to update Profile");  
            }
         }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "invalid age or userID");
        }
        catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " +e.getMessage());
        }
        
    }
    
    private boolean checkInputs(String... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) return false;
        }
        return true;
    }
    
    
}

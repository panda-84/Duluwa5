/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.ProfileDa;
import Model.ProfileModel;
import View.adminDashboard;
import View.userProfileEdit;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
    
//    public void deleteProfileUpdate(String profileIdText){
//        if (profileIdText.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Please enter a profile ID to delete.");
//            return;
//        }
//
//        try {
//            int id = Integer.parseInt(profileIdText);
//
//            // Confirm with user
//            int confirm = JOptionPane.showConfirmDialog(null,
//                    "Are you sure you want to delete Booking ID " + id + "?",
//                    "Confirm Deletion",
//                    JOptionPane.YES_NO_OPTION);
//
//            if (confirm == JOptionPane.YES_OPTION) {
//                ProfileDa dao = new ProfileDa();
//                boolean success = dao.deleteUserById(id);
//
//                if (success) {
//                    JOptionPane.showMessageDialog(null, "Guide deleted successfully.");
//                    adminDashboard clear = new adminDashboard();
//                    clear.clearUserFields();
//                } else {
//                    JOptionPane.showMessageDialog(null, "No guide found with ID: " + id);
//                }
//            }
//
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Invalid Guide ID format.");
//        }
//    }
    
//    public void  updateProfileAdmin(String firstName, String middleName, String lastName, String ageText, String nationality,
//        String country, String gender, String bio, String profileIdText,  byte[] imageBytes){
//        if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || ageText.isEmpty() || nationality.isEmpty() || country.isEmpty() || gender.isEmpty() || bio.isEmpty() || profileIdText.isEmpty()) {
//            JOptionPane.showMessageDialog(null,"please fill all the fields.");
//            return;
//        }
//        try{
//            int age = Integer.parseInt(ageText);
//            int userId = Integer.parseInt(profileIdText);
//            
//            ProfileModel profile = new ProfileModel(userId,firstName,middleName, lastName, age, nationality,bio, country, gender, imageBytes);
//            
//            ProfileDa dao = new ProfileDa();
//            boolean success = dao.updateProfile(profile);
//            if(success){
//               JOptionPane.showMessageDialog(null,"Profile updated Successfully "); 
//            } else{
//               JOptionPane.showMessageDialog(null,"Failed to update Profile");  
//            }
//         }
//        catch (NumberFormatException e){
//            JOptionPane.showMessageDialog(null, "invalid age or userID");
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Error: " +e.getMessage());
//        }
//        
//    }
    
//    private boolean checkInputs(String... fields) {
//        for (String field : fields) {
//            if (field == null || field.trim().isEmpty()) return false;
//        }
//        return true;
//    }
    
    
    
        public void searchUserProfileById(int userId, adminDashboard view) {
        ProfileDa dao = new ProfileDa();
        ProfileModel profile = dao.loadProfileByUserId(userId);

        if (profile != null) {
            // Fill text fields
            view.setUFirstName(profile.getFirstName());
            view.setUMiddleName(profile.getMiddleName());
            view.setULastName(profile.getLastName());
            view.setUNationality(profile.getNationality());
            view.setUCountry(profile.getCountry());
            view.setUGender(profile.getGender());
            view.setUAge(String.valueOf(profile.getAge()));
            view.setUBio(profile.getBio());

            // Load image
            byte[] imageBytes = profile.getPicture();
            if (imageBytes != null) {
                ImageIcon icon = resizeImage(null, imageBytes, view.getUImageLabel());
                view.setUImageIcon(icon);
            } else {
                view.setUImageIcon(null); // or a default image
            }

        } else {
            JOptionPane.showMessageDialog(null, "No profile found for user ID: " + userId);
        }
    }

    // Reusable helper
    private ImageIcon resizeImage(String path, byte[] imageBytes, JLabel label) {
        ImageIcon icon = (path != null) ? new ImageIcon(path) : new ImageIcon(imageBytes);
        Image img = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
    
    
    public void updateUserProfileFromAdmin(adminDashboard view) {
        String idText = view.getUserIdText();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a user ID.");
            return;
        }

        

        // Get form values
       String firstName = view.getUFirstName();
        String middleName = view.getUMiddleName();
        String lastName = view.getULastName();
        String nationality = view.getUNationality();
        String country = view.getUCountry();
        String gender = view.getUGender();
        String bio = view.getUBio();
        String ageText = view.getUAge();
        

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Invalid age.");
            return;
        }

        int userId;
        try {
            userId = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Invalid user ID.");
            return;
        }

        // Convert image to byte[]
        byte[] imageBytes = null;
        try {
            ImageIcon icon = (ImageIcon) view.getUImageLabel().getIcon();
            if (icon != null) {
                Image image = icon.getImage();
                File tempFile = new File("temp_user_profile.jpg");
                javax.imageio.ImageIO.write(toBufferedImage(image), "jpg", tempFile);
                imageBytes = Files.readAllBytes(tempFile.toPath());
                tempFile.delete();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Prepare model
        ProfileModel profile = new ProfileModel(
            userId, firstName, middleName, lastName, age,
            nationality, bio, country, gender, imageBytes
        );

        // Update using DAO
        ProfileDa dao = new ProfileDa();
        boolean success = dao.updateProfileUser(profile);

        if (success) {
            JOptionPane.showMessageDialog(view, "User profile updated successfully.");
            loadAllUserProfilesIntoTable(view);
        } else {
            JOptionPane.showMessageDialog(view, "Failed to update user profile.");
        }
    }

    
    public static java.awt.image.BufferedImage toBufferedImage(Image img) {
        if (img instanceof java.awt.image.BufferedImage) {
            return (java.awt.image.BufferedImage) img;
        }

        java.awt.image.BufferedImage bimage = new java.awt.image.BufferedImage(
            img.getWidth(null), img.getHeight(null), java.awt.image.BufferedImage.TYPE_INT_RGB);

        java.awt.Graphics2D g = bimage.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();

        return bimage;
    }
    
    
    

    public void clearUserProfileFields(adminDashboard view){
        view.setUFirstName("");
        view.setUMiddleName("");
        view.setULastName("");
        view.setUAge("");
        view.setUNationality("");
        view.setUCountry("");
        view.setUGender("Male");
        view.setUBio("");
        view.setUImageIcon(null);
        view.setUserIdText("");
    }
    
    public void deleteUserProfileFromAdmin(adminDashboard view) {
        String idText = view.getUserIdText();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a user ID.");
            return;
        }

        int userId;
        try {
            userId = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Invalid user ID.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            view,
            "Are you sure you want to delete this user profile?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            ProfileDa dao = new ProfileDa();
            boolean deleted = dao.deleteUserAccountById(userId);

            if (deleted) {
                JOptionPane.showMessageDialog(view, "User account deleted.");
                clearUserProfileFields(view);
                loadAllUserProfilesIntoTable(view); 
            } else {
                JOptionPane.showMessageDialog(view, "Failed to delete user account.");
            }
        }
    }
    
    public void loadAllUserProfilesIntoTable(adminDashboard view) {
    ProfileDa dao = new ProfileDa();
    List<ProfileModel> profiles = dao.getAllUserProfiles();

    DefaultTableModel model = (DefaultTableModel) view.getUserTable().getModel();
    model.setRowCount(0); // Clear existing rows

    for (ProfileModel profile : profiles) {
        Object[] row = {
            profile.getUserId(),
            profile.getFirstName(),
            profile.getMiddleName(),
            profile.getLastName(),
            profile.getAge(),
            profile.getGender(),
            profile.getNationality(),
            profile.getCountry(),
            profile.getBio()
        };
        model.addRow(row);
    }
}


    
}

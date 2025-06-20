/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.ProfileDa;
import Model.ProfileModel;
import View.Dashboard;
import View.userProfileEdit;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author acer
 */
public class UserController {
     private final int userId;
    private final userProfileEdit view;
    
    
    public UserController(int userId, userProfileEdit view) {
        this.userId = userId;
        this.view = view;
        if (view != null) {
           loadProfile(); // ✅ Only load if editing view is present
         } // Load when controller is created
    }
    public void loadProfile() {
    ProfileDa dao = new ProfileDa();
    ProfileModel profile = dao.loadProfileByUserId(userId);

    if (profile != null) {
        view.setFirstName(profile.getFirstName());
        view.setMiddleName(profile.getMiddleName());
        view.setLastName(profile.getLastName());
        view.setAge(String.valueOf(profile.getAge()));
        view.setNationality(profile.getNationality());
        view.setCountry(profile.getCountry());
        view.setGender(profile.getGender());
        view.setBio(profile.getBio());
    } else {
        JOptionPane.showMessageDialog(null, "No profile found for this user.");
    }
}

    public void updateProfile() {
         System.out.println("updateProfile() called");
    String firstName = view.getFirstName();
    String middleName = view.getMiddleName();
    String lastName = view.getLastName();
    String ageText = view.getAge();
    String nationality = view.getNationality();
    String country = view.getCountry();
    String gender = view.getGender().trim();

    if (gender.equalsIgnoreCase("male")) {
        gender = "Male";
    } else if (gender.equalsIgnoreCase("female")) {
        gender = "Female";
    } else {
        JOptionPane.showMessageDialog(null, "Invalid gender selected.");
        return;
    }
    String bio = view.getBio();

    if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill all required fields.");
        return;
    }
    
    
    System.out.println("Final Gender value: " + gender);
    int age;
    try {
        age = Integer.parseInt(ageText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid age.");
        return;
    }

    // Use ProfileModel to store data
    ProfileModel profile = new ProfileModel(
        userId,
        firstName,
        middleName,
        lastName,
        age,
        nationality,
        bio,
        country,
        gender,
        null // no photo
    );
    
    System.out.println("Prepared values:");
System.out.println("Gender = " + profile.getGender());
System.out.println("UserId = " + profile.getUserId());

    ProfileDa dao = new ProfileDa();
    boolean success = dao.updateProfileUser(profile);
    
    if (success) {
        
        System.out.println("Updating DB for userId = " + profile.getUserId());
        System.out.println("First name = " + profile.getFirstName());
        JOptionPane.showMessageDialog(null, "Profile updated successfully.");
        
        if (view instanceof userProfileEdit) {
            userProfileEdit editView = (userProfileEdit) view;
            Dashboard dashboard = editView.getDashboard();

            if (dashboard != null) {
                displayProfileSummary(dashboard);      
                loadProfileImage(dashboard);           
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Profile update failed.");
    }
}
    
    
    public void displayProfileSummary(Dashboard view) {
        ProfileDa dao = new ProfileDa();
        ProfileModel profile = dao.loadProfileByUserId(userId);

        if (profile != null) {
            String fullName = profile.getFirstName();
            if (profile.getMiddleName() != null && !profile.getMiddleName().isEmpty()) {
                fullName += " " + profile.getMiddleName();
            }
            fullName += " " + profile.getLastName();

            view.setLabelFullName(fullName.trim());
            view.setLabelAge(String.valueOf(profile.getAge()));
            view.setLabelGender(profile.getGender());
            view.setLabelCountry(profile.getCountry());
            view.setLabelNationality(profile.getNationality());
            view.setLabelBio(profile.getBio());
        } else {
            JOptionPane.showMessageDialog(null, "No profile found for user.");
        }
    }
    
    
    public void changeUserPhoto(Dashboard dashboard) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "jpeg", "png"));

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try {
                byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());

                // Save to DB
                ProfileDa dao = new ProfileDa();
                boolean success = dao.updateUserPhoto(userId, imageBytes);

                if (success) {
                    // Update image on UI
                    ImageIcon icon = resizeImage(selectedFile.getAbsolutePath(), null, dashboard.getLabelImg());
                    dashboard.setCurrentImageBytes(imageBytes);
                    dashboard.getLabelImg().setIcon(icon);

                    JOptionPane.showMessageDialog(null, "Photo updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update photo.");
                }

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to read image file.");
            }
        }
    }

    
    public void loadProfileImage(Dashboard dashboard) {
        ProfileDa dao = new ProfileDa();
        ProfileModel profile = dao.loadProfileByUserId(userId);

        if (profile != null && profile.getPicture() != null) {
            byte[] imageBytes = profile.getPicture();
            dashboard.setCurrentImageBytes(imageBytes); // store for reuse
            ImageIcon icon = resizeImage(null, imageBytes, dashboard.getLabelImg());
            dashboard.getLabelImg().setIcon(icon);
        }
    }

    // Utility method to scale the image
    private ImageIcon resizeImage(String path, byte[] imageBytes, JLabel label) {
        ImageIcon icon = (path != null) ? new ImageIcon(path) : new ImageIcon(imageBytes);
        Image img = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
   

    
}

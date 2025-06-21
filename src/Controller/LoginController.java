/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Dao.ProfileDa;
import Dao.UserDa;
import Model.SignUp;
import View.Dashboard;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class LoginController {
    public static boolean loginUser(String emailText, String passwordText, JFrame frame) {
        UserDa dao = new UserDa();

        if (emailText.isEmpty() || passwordText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        } else if (!emailText.contains("@")) {
            JOptionPane.showMessageDialog(null, "Invalid email address. Please include '@'");
            return false;
        } else if (emailText.equals("Enter your username") || passwordText.equals("password")) {
            JOptionPane.showMessageDialog(null, "Please enter valid credentials");
            return false;
        } else {
            SignUp user = new SignUp(emailText, passwordText);
            
            boolean success = dao.checkUser(user);
              

            if (success) {
                
                int userId = dao.getUserIdByEmail(emailText); // get ID
                user.setUserId(userId);
                ProfileDa profileDao = new ProfileDa();
                profileDao.createUserProfileIfNotExists(userId);
                
                JOptionPane.showMessageDialog(null, "Logged in successfully");
                
                Dashboard d = new Dashboard(user);
                d.setVisible(true);
                d.pack();
                d.setLocationRelativeTo(null);
                d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.dispose(); 
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Failed to login.");
                return false;
            }
        }
    }
}

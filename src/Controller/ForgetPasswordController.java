/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.UserDa;
import Model.SignUp;
import View.Login;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class ForgetPasswordController {
    public static boolean forgetPasswordUser(String emailText, String codeText, String passwordText, String retypeText,JFrame frame){
        UserDa dao = new UserDa();
        if (emailText.isEmpty() || codeText.isEmpty() || passwordText.isEmpty() || retypeText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        } 
        else if (!emailText.contains("@")) {
            JOptionPane.showMessageDialog(null, "Invalid email address. Please include '@'");
            return false;
        } 
        else if (!codeText.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(null, "Code must be a 4-digit number");
            return false;
        } 
        else if (!isValidPassword(passwordText)) {
            JOptionPane.showMessageDialog(null, "Password must be 8-20 characters long and include uppercase, lowercase, digit, and special character");
            return false;
        } 
        else if (!passwordText.equals(retypeText)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match");
            return false;
        } 
        else {
            SignUp newUser = new SignUp(emailText, "", codeText, passwordText);
            boolean success = dao.forgetPassword(
                newUser.getUserEmail(),
                newUser.getUserCode(),
                newUser.getUserPassword()
            );

            if (success) {
                JOptionPane.showMessageDialog(null, "Changed password successfully");
                Login l = new Login();
                l.setVisible(true);
                l.pack();
                l.setLocationRelativeTo(null);
                l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.dispose();
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Failed to change password. Invalid email or code.");
                return false;
            }
        }

    }

    
    public static boolean isValidPassword(String password) {
    if (password.length() < 8 || password.length() > 20) return false;

    String upperCaseChars = "(?=.*[A-Z])";
    String lowerCaseChars = "(?=.*[a-z])";
    String numbers = "(?=.*[0-9])";
    String specialChars = "(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?])";

    return password.matches(upperCaseChars + lowerCaseChars + numbers + specialChars + ".{8,20}");
}
}

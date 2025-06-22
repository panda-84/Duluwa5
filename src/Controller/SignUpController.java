/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.ProfileDa;
import Dao.UserDa;
import Model.SignUp;
import View.Login;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class SignUpController {
    public static boolean registerUser(String emailText, String nameText, String codeText,
                                       String passwordText, String retypeText, JFrame frame) {
        UserDa dao = new UserDa();

        if (emailText.isEmpty() || nameText.isEmpty() || codeText.isEmpty()
                || passwordText.isEmpty() || retypeText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        } else if (!emailText.contains("@")) {
            JOptionPane.showMessageDialog(null, "Invalid email address.");
            return false;
        } else if (!codeText.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(null, "Code must be a 4-digit number");
            return false;
        } else if (!isValidPassword(passwordText)) {
            JOptionPane.showMessageDialog(null, "Password must be 8-20 characters long and include uppercase, lowercase, digit, and special character");
            return false;
        } else if (!passwordText.equals(retypeText)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match");
            return false;
        } else if (dao.emailExists(emailText)) {
            JOptionPane.showMessageDialog(null, "This email is already registered.");
            return false;
        } else {
//            int userId = dao.getUserIdByEmail(emailText); // fetch just created ID
//            ProfileDa profileDao = new ProfileDa();
//            profileDao.createUserProfileIfNotExists(userId);
            SignUp newUser = new SignUp(emailText, nameText, codeText, passwordText);
            boolean success = dao.signUp(newUser);
            if (success) {
               int userId = dao.getUserIdByEmail(emailText); // fetch just created ID
                ProfileDa profileDao = new ProfileDa();
                profileDao.createUserProfileIfNotExists(userId);
                JOptionPane.showMessageDialog(null, "Account created successfully");
                
                Login login = new Login();
                login.setVisible(true);
                login.pack();
                login.setLocationRelativeTo(null);
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.dispose(); // Dispose the sign-up frame
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Failed to create account. Please try again.");
                return false;
            }
        }
    }

    public static boolean isValidPassword(String password) {
        if (password.length() < 8 || password.length() > 20) return false;

        String upperCaseChars = ".*[A-Z].*";
        String lowerCaseChars = ".*[a-z].*";
        String numbers = ".*[0-9].*";
        String specialChars = ".*[!@#$%^&*()\\-+=].*";

        return password.matches(upperCaseChars) &&
               password.matches(lowerCaseChars) &&
               password.matches(numbers) &&
               password.matches(specialChars);
    }
}

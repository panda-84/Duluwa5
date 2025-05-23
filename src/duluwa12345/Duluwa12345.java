/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package duluwa12345;

import Database.Database;
import Database.MySqlConnection;
import View.NewPassword;



/**
 *
 * @author Bibek
 */
public class Duluwa12345 {
      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Database db = new MySqlConnection();
        if (db.openConnection() != null){
            System.out.println("Database connected successfully");
        
        }else{
            System.out.println("Failed to connect to database");
        }
        
        
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new NewPassword().setVisible(true);}
        });
        
    }
    
}

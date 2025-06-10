/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author acer
 */
public class Admin {
    private int admin_id;
    private String username;
    private String password;
    
    public Admin(String username, String password){
        this.username=username;
        this.password = password;
    }
    
    public int getAdminID(){
        return admin_id;
    }
    
    public void setAdminID(int admin_id){
        this.admin_id = admin_id; 
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
}

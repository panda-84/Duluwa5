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
    private int id;
    private String username;
    private String password;
    
    public Admin(String username, String password){
        this.username=username;
        this.password = password;
    }
    
    public int getAdminID(){
        return id;
    }
    
    public void setAdminID(int id){
        this.id = id; 
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

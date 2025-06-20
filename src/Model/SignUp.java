/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author acer
 */
public class SignUp {
    private int user_id;
    private String email;
    private String name;
    private String code;
    private String password;
    
    public SignUp(){
    }
    
    public SignUp(String email,String name,String code,String password){
       this.email =email;
       this.name = name;
       this.code = code;
       this.password = password;
    }
    
     // Constructor for login
    public SignUp(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    
    public int getUserId(){
        return user_id;
    }
    public void setUserId(int user_id){
        this.user_id = user_id;
    }
    
    public String getUserEmail(){
        return email;
    }
    public void setUserEmail(String email){
        this.email = email;
    }
    
    public String getUserName(){
        return name;
    }
    public void setUserName(String name){
        this.name = name;
    }
    
    public String getUserCode(){
        return code;
    }
    public void setUserCode(String code){
        this.code = code;
    }
    
    public String getUserPassword(){
        return password;
    }
    public void setUserPassword(String password){
        this.password = password;
    }
}

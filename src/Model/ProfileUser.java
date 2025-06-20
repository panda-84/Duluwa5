/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author acer
 */
public class ProfileUser {
    private int user_id;
    private String firstName;
    private String middleName;
    private String lastName;
    private int age;
    private String nationality;
    private String bio;
    private String country;
    private String gender;
    private String fullName;
    private byte[] picture;
    
   
    
    
    public ProfileUser( int user_id,String firstName, String middleName, String lastName,int age, String nationality,String country,
             String gender, String bio,   byte[] picture)
    {
       this.user_id = user_id;
       this.firstName = firstName;
       this.middleName = middleName;
       this.lastName = lastName;
       this.age = age;
       this.nationality = nationality;
       this.country = country;
        this.gender = gender;
       this.bio = bio;
       this.picture = picture;
       

    }
    
    public ProfileUser( int user_id,String firstName, String middleName, String lastName,int age, String nationality,String country,
             String gender, String bio)
    {
       this.user_id = user_id;
       this.firstName = firstName;
       this.middleName = middleName;
       this.lastName = lastName;
       this.age = age;
       this.nationality = nationality;
       this.country = country;
        this.gender = gender;
       this.bio = bio;
       
       

    }
    
    
    
    
    
   
    public int getUserId(){
        return user_id;
    }
    
    public void setUserId(int user_id){
        this.user_id = user_id;
    }
    
     public String getFullName(){
        return fullName;
    }
    
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getMiddleName(){
        return middleName;
    }
    
    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
  
    
    
    
    public String getBio(){
        return bio;
    }
    
    public void setBio(String bio){
        this.bio = bio;
    }
    
    public String getGender(){
        return gender;
    }
    
    public void setGender(String gender){
        this.gender = gender;
    }
    
    public int getAge(){
        return age;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    public String getCountry(){
        return country;
    }
    
    public void setCountry(String country){
        this.country = country;
    }
    
    public String getNationality(){
        return nationality;
    }
    
    public void setnationality(String nationality){
        this.nationality = nationality;
    }
    
   
    
    
    
    public byte[] getPicture(){
        return picture;
    }
    
    public void setPicture(byte[] picture){
        this.picture = picture;
    }
}


package Model;

import Dao.*;


public class GuideA {
    
    private int guide_id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private int age;
    private String status;
    private String bio;
    private byte[] picture;
    
    public GuideA( String firstName,String middleName, String lastName, String gender, String phoneNumber, int age, String status, String bio, byte[] picture ){
      
      this.firstName = firstName;
      this.middleName = middleName;
      this.lastName = lastName;
      this.gender = gender;
      this.phoneNumber = phoneNumber;
      this.age = age;
      this.status = status;
      this.bio = bio;
      this.picture = picture;
      
    }
    
    public int getGuideId(){
        return guide_id;
    }
    
    public void setGuideId(int guide_id){
        this.guide_id = guide_id;
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
    
    public String getGender(){
        return gender;
    }
    
    public void setGender(String gender){
        this.gender = gender;
    }
    
    public String getNumber(){
        return phoneNumber;
    }
    
    public void setNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public int getAge(){
        return age;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public String getBio(){
        return bio;
    }
    
    public void setBio(String bio){
        this.bio = bio;
    }
    
    public byte[] getPicture(){
        return picture;
    }
    
    public void setPicture(byte[] picture){
        this.picture = picture;
    }
}

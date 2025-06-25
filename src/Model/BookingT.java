/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;

/**
 *
 * @author acer
 */
public class BookingT {
    private int booking_id;
    private int guide_ID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String startDate;
    private String numberOfPeople;
    private int age;
    private String country;
    private String nationality;
    private String address;
    private String zipCode;
    private String payment;
    private String endDate;
    private BigDecimal totalPrice;
    private String fullName;
    
    public BookingT( String firstName, String middleName, String lastName, String phoneNumber, String email, String startDate, String numberOfPeople,
                   int age, String country, String nationality, String address, String zipCode, String payment, String endDate,BigDecimal totalPrice)
    {
       
       this.firstName = firstName;
       this.middleName = middleName;
       this.lastName = lastName;
       this.phoneNumber = phoneNumber;
       this.email = email;
       this.startDate = startDate;
       this.numberOfPeople = numberOfPeople;
       this.age = age;
       this.country = country;
       this.nationality = nationality;
       this.address = address;
       this.zipCode = zipCode;
       this.payment = payment;
       this.endDate = endDate;
       this.totalPrice =totalPrice;
       
    }
    public BookingT( int guide_ID,String firstName, String middleName, String lastName, String phoneNumber, String email, String startDate, String numberOfPeople,
                   int age, String country, String nationality, String address, String zipCode, String payment, String endDate, BigDecimal totalPrice)
    {
       this.guide_ID = guide_ID;
       this.firstName = firstName;
       this.middleName = middleName;
       this.lastName = lastName;
       this.phoneNumber = phoneNumber;
       this.email = email;
       this.startDate = startDate;
       this.numberOfPeople = numberOfPeople;
       this.age = age;
       this.country = country;
       this.nationality = nationality;
       this.address = address;
       this.zipCode = zipCode;
       this.payment = payment;
       this.endDate = endDate;
       this.totalPrice=totalPrice;
    }
    

    
    public BookingT(String fullName,int age, String phoneNumber, String email, String country, String address,String numberOfPeople, String payment, String startDate, String endDate){
       
        this.fullName = fullName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.country = country;
        this.address = address;
        this.numberOfPeople= numberOfPeople;
        this.payment = payment;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getEndDate(){
        return endDate;
    }
    
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }
    
    public int getBookId(){
        return booking_id;
    }
    
    public void setBookId(int booking_id){
        this.booking_id = booking_id;
    }
    
    public int getGuideID(){
        return guide_ID;
    }
    
    public void setGuideID(int guide_ID){
        this.guide_ID = guide_ID;
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
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getStartDate(){
        return startDate;
    }
    
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }
    
    public String getNumberOfPeople(){
        return numberOfPeople;
    }
    
    public void setNumberOfPeople(String numberOfPeople){
        this.numberOfPeople = numberOfPeople;
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
    
    public String getAddress(){
        return address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getZipCode(){
        return zipCode;
    }
    
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
    
    public String getPayment(){
        return payment;
    }
    
    public void setPayment(String payment){
        this.payment = payment;
    }
    
    public BigDecimal getTotalPrice(){
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice){
        this.totalPrice=totalPrice;
    }
    
    
}

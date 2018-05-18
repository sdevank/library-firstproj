package com.sample.library.user;

import com.sample.library.Address;

import java.util.ArrayList;

public class User {

    private int userId;
    private String userName;
    private Address address;
    private String phone;
    private boolean isStudent;
    private int totalItems;
    private static int overdueItems;
    private String membershipName;
    private ArrayList<String> rentedTitles;

    public User(int userId,String userName, Address address, String phone, boolean isStudent, int totalTaken, int overdue, String membershipName){
        this.userId = userId;
        this.userName = userName;
        this.address = address;
        this.phone = phone;
        this.isStudent = isStudent;
        totalItems = totalTaken;
        overdueItems = overdue;
        this.membershipName = membershipName;
        rentedTitles = new ArrayList<>();
     }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public static int getOverdueItems() {
        return overdueItems;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void addRentedItem(String rentedTitles) {
        this.rentedTitles.add(rentedTitles);
            this.totalItems++;

    }

    public ArrayList<String> getRentedTitles() {
        return rentedTitles;
    }

    public String toString(){
        return "User name: "+userName+" ,Address: "+address+" , Phone: "+phone+" ,and is student? "+isStudent+", total items taken: "+totalItems
                +" ,Overdue items: "+overdueItems+" ,Membership Plan: "+membershipName;
    }

}

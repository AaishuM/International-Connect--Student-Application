package com.uniguides.userscampusapply.HelperClasses.HelperAdapter;

public class Reg2Helper {
    String address, email, phoneNumber, username;

    public Reg2Helper(String address, String email, String phoneNumber, String username) {
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Reg2Helper() {
    }
}

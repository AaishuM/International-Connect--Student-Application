package com.uniguides.userscampusapply.HelperClasses.HelperAdapter;

public class NotiHelper {

    String username,email,message;

    public NotiHelper(String username, String email, String message) {
        this.username = username;
        this.email = email;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotiHelper() {
    }
}

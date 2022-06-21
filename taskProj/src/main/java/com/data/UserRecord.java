package com.data;

public class UserRecord {

    private String userName = null;
    private String password = null;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.decryptPassword(this.password);
    }

    public void setPassword(String password) {
        this.password = this.encryptPassword(password);
    }

    private String encryptPassword(String password){
        return "encrypted_" + password;
    }

    private String decryptPassword(String encryptPassword){
        return encryptPassword.split("_",2)[1];
    }
}

package com.data;

public class RoleRecord {
    private String userName = null;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String role = null;

    public String getRole(){return this.role;}

    public String setRole(String role){ return this.role = role; }

    public RoleRecord(){}

    public RoleRecord(String userName, String role){
        this.userName = userName;
        this.role = role;
    }

}

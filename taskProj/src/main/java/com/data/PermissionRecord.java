package com.data;

public class PermissionRecord {
    private String role = null;
    public String getRole(){return this.role;}
    public void setRole(String role){this.role = role;}

    private String tableName = null;
    public String getTableName(){return this.tableName;}
    public void setTableName(String tableName){this.tableName = tableName;}
    private String permission = null;
    public String getPermission(){return this.permission;}
    public void setPermission(){this.permission = permission;}

    public PermissionRecord(String role, String tableName, String permission){
        this.role = role;
        this.tableName = tableName;
        this.permission = permission;
    };
}

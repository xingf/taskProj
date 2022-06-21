package com.role;

import com.data.Database;

import java.util.ArrayList;
import java.util.Iterator;

public class RoleDao {
    public ArrayList<String> queryUserRoles(String userName) throws Exception{
        try{
            return Database.queryUserRoles(userName);
        }catch(Exception e){
            throw e;
        }
    }

    public boolean queryDeleteRolePermission(String role){
        ArrayList<String> permissions = Database.queryPermissions(role, Database.TABLENAME_ROLE);
        Iterator<String> itr = permissions.iterator();

        while(itr.hasNext()){
            if(itr.next().equals(Database.PERMISSION_DELETE)){
                return true;
            }
        }
        return false;
    }
    public boolean queryCreateRolePermission(String role){
        ArrayList<String> permissions = Database.queryPermissions(role, Database.TABLENAME_ROLE);
        Iterator<String> itr = permissions.iterator();

        while(itr.hasNext()){
            if(itr.next().equals(Database.PERMISSION_ADD)){
                return true;
            }
        }
        return false;
    }

    public boolean queryAssignRolePermission(String role){
        ArrayList<String> permissions = Database.queryPermissions(role, Database.TABLENAME_ROLE);
        Iterator<String> itr = permissions.iterator();
        boolean hasPermission = false;

        if(permissions.contains(Database.PERMISSION_ADD) && permissions.contains(Database.PERMISSION_MODIFY)){
            return true;
        }else{
            return false;
        }

    }

    public ArrayList<String> queryRoles(){
        return Database.queryRoles();
    }

    public boolean queryIfExistedRole(String role){
        ArrayList<String> roles = this.queryRoles();
        if(roles.contains(role)){
            return true;
        }else{
            return false;
        }
    }

    public boolean addRole(String role){
        if(Database.addRole(role)){
            return true;
        }else{
            return false;
        }
    }

    public boolean existedUser(String userName){
        return Database.existedUserNameInUserTable(userName);
    }

    public boolean assignRoleToUser(String user, String role){
        return Database.addRoleToUser(user, role);
    }

    public boolean deleteRole(String deleterole){
        return Database.deleteRole(deleterole);
    }
}

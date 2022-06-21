package com.user;

import com.auth.Token;
import com.data.Database;
import com.data.UserRecord;

import java.util.ArrayList;
import java.util.Iterator;

public class UserDao {
    public ArrayList<String> queryAllUsers(){
        return Database.queryAllUsers();
    }

    public ArrayList<String> queryUserRoles(String userName) throws Exception{
        try{
            return Database.queryUserRoles(userName);
        }catch(Exception e){
            throw e;
        }
    }

    public boolean hasDeletePermission(String role){
        ArrayList<String> permissions = Database.queryPermissions(role, Database.TABLENAME_ROLE);
        Iterator<String> itr = permissions.iterator();
        while(itr.hasNext()){
            if(itr.next().equals(Database.PERMISSION_DELETE)){
                return true;
            }
        }
        return false;
    }

    public boolean insertUserIntoUserTable(String username, String password)
    {
        UserRecord userRecord  = new UserRecord();
        userRecord.setUserName(username);
        userRecord.setPassword(password);
        Database.insertUserRecord(userRecord);

        Token token = Token.generateToken(username, password);
        this.updateTokenMap(username, token);

        return true;
    }

    private void updateTokenMap(String username, Token token){
        Database.updateTokenMap(username, token);
    }
    public ArrayList<String> queryUserRoleMap(){
        return Database.getAllUserRoleMap();
    }

    public Token getToken(String username){
        return Database.getToken(username);
    }

    public boolean existedUserName(String userName){
        return Database.existedUserNameInUserTable(userName);
    }

    public boolean deleteUser(String deleteUserName){
        if(Database.existedUserNameInUserTable(deleteUserName)){
            if(Database.deleteUser(deleteUserName)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}

package com.data;

import com.auth.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Database {

    public static final String ROLE_USER = "user_role";
    public static final String ROLE_ADMINISTRATOR = "administrator_role";
    public static final String ROLE_OTHER = "other_role";
//    public static final String ROLE_ILLEGAL = "illegal_role";

    public static final String PERMISSION_ADD = "add_permission";
    public static final String PERMISSION_DELETE = "delete_permission";
    public static final String PERMISSION_MODIFY = "modify_permission";
    public static final String PERMISSION_QUERY = "query_permission";

    public static final String TABLENAME_USER = "user";

    public static final String TABLENAME_ROLE = "role";

    public static final String TABLENAME_PERMISSION = "permission";

    private static ArrayList<UserRecord> userTable = new ArrayList();
    private static ArrayList<RoleRecord> roleTable = new ArrayList();

    private static ArrayList<String> roles = new ArrayList();

    private static HashMap<String, Token> tokenMapTable = new HashMap();
    private static ArrayList<PermissionRecord> permissionTable = new ArrayList();
    private static Database db = new Database();

    static{
        Database.roles.add(Database.ROLE_ADMINISTRATOR);
        Database.roles.add(Database.ROLE_USER);
        Database.roles.add(Database.ROLE_OTHER);

        Database.permissionTable.add(new PermissionRecord(Database.ROLE_ADMINISTRATOR, "user", Database.PERMISSION_ADD)) ;
        Database.permissionTable.add(new PermissionRecord(Database.ROLE_ADMINISTRATOR, "user", Database.PERMISSION_DELETE)) ;
        Database.permissionTable.add(new PermissionRecord(Database.ROLE_ADMINISTRATOR, "user", Database.PERMISSION_MODIFY)) ;
        Database.permissionTable.add(new PermissionRecord(Database.ROLE_ADMINISTRATOR, "user", Database.PERMISSION_QUERY)) ;

        Database.permissionTable.add(new PermissionRecord(Database.ROLE_ADMINISTRATOR, "role", Database.PERMISSION_ADD));
        Database.permissionTable.add(new PermissionRecord(Database.ROLE_ADMINISTRATOR, "role", Database.PERMISSION_DELETE));
        Database.permissionTable.add(new PermissionRecord(Database.ROLE_ADMINISTRATOR, "role", Database.PERMISSION_MODIFY));
        Database.permissionTable.add(new PermissionRecord(Database.ROLE_ADMINISTRATOR, "role", Database.PERMISSION_QUERY));


        UserRecord urd1 = new UserRecord();
        urd1.setPassword("123456");
        urd1.setUserName("user");
        userTable.add(urd1);

        Database.updateTokenMap(urd1.getUserName(), Token.generateToken(urd1.getUserName(), urd1.getPassword()));

        UserRecord urd2 = new UserRecord();
        urd2.setPassword("123456");
        urd2.setUserName("admin");
        userTable.add(urd2);
        Database.updateTokenMap(urd2.getUserName(), Token.generateToken(urd2.getUserName(), urd2.getPassword()));


        UserRecord urd3 = new UserRecord();
        urd3.setPassword("123456");
        urd3.setUserName("other");
        userTable.add(urd3);
        Database.updateTokenMap(urd3.getUserName(), Token.generateToken(urd3.getUserName(), urd3.getPassword()));


        Database.roleTable.add(new RoleRecord("user", Database.ROLE_USER));
        Database.roleTable.add(new RoleRecord("admin", Database.ROLE_ADMINISTRATOR));
        Database.roleTable.add(new RoleRecord("other", Database.ROLE_OTHER));

    }
    private Database(){};

    public static Database getDatabase(){
        return Database.db;
    }

    public static boolean insertUserRecord(UserRecord record){
        if(Database.userTable.add(record)){
            return true;
        }else{
            return false;
        }
    }


    public static boolean existedInUserTable(String userName, String password){
        Iterator<UserRecord> itr = Database.userTable.iterator();
        UserRecord curUserRecord = null;
        while(itr.hasNext()){
            curUserRecord = itr.next();
            if(curUserRecord.getUserName().equals(userName) && curUserRecord.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> queryAllUsers(){
        ArrayList<String> users = new ArrayList<>();
        Iterator<UserRecord> itr = Database.userTable.iterator();
        UserRecord curUserRecord = null;
        while(itr.hasNext()){
            curUserRecord = itr.next();
            users.add(curUserRecord.getUserName());
        }
        return users;
    }

    public static boolean existedUserNameInUserTable(String userName){
        Iterator<UserRecord> itr = Database.userTable.iterator();
        UserRecord curUserRecord = null;
        while(itr.hasNext()){
            curUserRecord = itr.next();
            if(curUserRecord.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    public static boolean deleteUser(String userName){
        Iterator<UserRecord> itr = Database.userTable.iterator();
        UserRecord curUserRecord = null;
        while(itr.hasNext()){
            curUserRecord = itr.next();
            if(curUserRecord.getUserName().equals(userName)){
                itr.remove();
                return true;
            }
        }
        return false;
    }

    public static boolean existedRole(String role){
        if(Database.roles.contains(role)){
            return true;
        }else{
            return false;
        }
    }


    public static boolean deleteRole(String role){
        int idx = Database.roles.indexOf(role);
        if(idx >= 0){
            Database.roles.remove(idx);
            return true;
        }else{
            return false;
        }
    }

    public static boolean insertUserRole(String userName, String role){
        if(Database.existedUserRole(userName, role)){
            return false;
        }else{
            RoleRecord roleRecord = new RoleRecord(userName, role);
            if(Database.roleTable.add(roleRecord)){
                return true;
            }else{
                return false;
            }
        }
    }

    public static boolean existedUserRole(String userName, String role){
        Iterator<RoleRecord> itr = Database.roleTable.iterator();
        RoleRecord curRoleRecord = null;
        while(itr.hasNext()){
            curRoleRecord = itr.next();
            if(curRoleRecord.getUserName().equals(userName) && curRoleRecord.getRole().equals(role)){
                return false;
            }
        }
        return true;
    }

    public static ArrayList<String> queryRoles() {
        return Database.roles;
//        Iterator<RoleRecord> itr = Database.roleTable.iterator();
//        RoleRecord curRoleRecord = null;
//        ArrayList<String> roles = new ArrayList<>();
//        while(itr.hasNext()){
//            curRoleRecord = itr.next();
//            String role = curRoleRecord.getRole();
//            if(!roles.contains(role)){
//                if(!roles.add(role)){
//                    throw new Exception("Error in query");
//                }
//            }
//        }
//        return roles;
    }

    public static ArrayList<String> queryUserRoles(String userName) throws Exception {
        Iterator<RoleRecord> itr = Database.roleTable.iterator();
        RoleRecord curRoleRecord = null;
        ArrayList<String> roles = new ArrayList<>();
        while(itr.hasNext()){
            curRoleRecord = itr.next();
            if(curRoleRecord.getUserName().equals(userName)){
                if(!roles.add(curRoleRecord.getRole())){
                    throw new Exception("Error in query");
                }
            }
        }
        return roles;
    }


    public static ArrayList<String> queryPermissions(String role, String tableName){
        ArrayList<String> permissions = new ArrayList();
        PermissionRecord curPermissionRecord = null;
        Iterator<PermissionRecord> itr = Database.permissionTable.iterator();
        while(itr.hasNext()){
            curPermissionRecord = itr.next();
            if(curPermissionRecord.getTableName().equals(tableName) && curPermissionRecord.getRole().equals(role)){
                permissions.add(curPermissionRecord.getPermission());
            }
        }
        return permissions;
    }

    public static boolean addRole(String role){
        if(Database.roles.add(role)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean addRoleToUser(String assignedUser, String assignedRole){
        if(Database.roleTable.add(new RoleRecord(assignedUser, assignedRole))){
            return true;
        }else{
            return false;
        }
    }

    public static ArrayList<String> getAllUserRoleMap(){
        ArrayList<String> map = new ArrayList();
        Iterator<RoleRecord> itr = Database.roleTable.iterator();
        RoleRecord curRoleRecord = null;
        while(itr.hasNext()){
            curRoleRecord = itr.next();
            map.add("username:"+curRoleRecord.getUserName() + "-->role:"+curRoleRecord.getRole());
        }
        return map;
    }

    public static void updateTokenMap(String username, Token token){
       Database.tokenMapTable.put(username, token);
    }

    public static HashMap<String, Token> getTokenMap(){
        return Database.tokenMapTable;
    }

    public static Token getToken(String username){
        if(Database.tokenMapTable.containsKey(username)){
            return Database.tokenMapTable.get(username);
        }else{
            return null;
        }
    }

    public static Token getTokenByTokenString(String tokenString){
        Iterator <Map.Entry< String, Token >> iterator = Database.tokenMapTable.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry< String, Token > entry = iterator.next();
            if(entry.getValue().getTokenValue().equals(tokenString)){
                return entry.getValue();
            }
        }
        return null;
    }
    public static String getUserNameBy(String tokenVal){
        Iterator <Map.Entry< String, Token >> iterator = Database.tokenMapTable.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry< String, Token > entry = iterator.next();
            if(entry.getValue().getTokenValue().equals(tokenVal)){
                return entry.getValue().getUsername();
            }
        }
        return null;
    }

    public static boolean invalidToken(String tokenVal){
        String username = Database.getUserNameBy(tokenVal);
        Iterator <Map.Entry< String, Token >> iterator = Database.tokenMapTable.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry< String, Token > entry = iterator.next();
            if(entry.getValue().getTokenValue().equals(tokenVal)){
                entry.getValue().setExpired();
                return true;
            }
        }
        return false;
    }

    public static boolean existedToken(String tokenVal){
        String username = Database.getUserNameBy(tokenVal);
        Iterator <Map.Entry< String, Token >> iterator = Database.tokenMapTable.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry< String, Token > entry = iterator.next();
            if(entry.getValue().getTokenValue().equals(tokenVal)){
                return true;
            }
        }
        return false;
    }
}

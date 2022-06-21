package com.auth;

import com.data.Database;

import java.util.HashMap;

public class Authenticator {

    public static boolean authenticate(String username, String password){
        HashMap<String, Token> tokenMap = Database.getTokenMap();
        Token token = null;
        if(tokenMap.containsKey(username)){
            token = tokenMap.get(username);
            if(token.getExpiredTime().getTime() < System.currentTimeMillis()){
                return false;
            }
            return true;

        }
        return false;
    }

    public static boolean isExpired(Token token){
        if(System.currentTimeMillis() < token.getExpiredTime().getTime()){
            return true;
        }else{
            return false;
        }
    }
}

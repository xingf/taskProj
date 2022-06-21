package com.auth;

import java.sql.Timestamp;
import java.util.UUID;

public class Token {

    public static final long EXPIRE_TIME_DURATION = 2 * 60 * 60 * 1000;

    public String tokenValue = null;

    public Timestamp expiredTime = null;

    public Token(){}

    private String username = null;


    public static Token generateToken(String userName, String password){

        Token token = new Token();
        token.tokenValue = UUID.randomUUID().toString();
        token.expiredTime = new Timestamp(System.currentTimeMillis() + Token.EXPIRE_TIME_DURATION);
        token.username = userName;
        return token;
    }


    public Timestamp getExpiredTime(){
        return this.expiredTime;
    }

    public String getTokenValue(){
        return this.tokenValue;
    }

    public String getUsername(){
        return this.username;
    }

    public void setExpired(){
        this.expiredTime = new Timestamp(0);
    }
}

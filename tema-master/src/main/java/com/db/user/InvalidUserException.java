package com.db.user;

public class InvalidUserException extends Exception{
    public InvalidUserException(String msg) {
        super(msg);
    }

}
package com.validators;

public class PasswordChecker {
    boolean isNotShorter(String password, int length){
        if(password.length() < length){
            return false;
        }
        else {
            return true;
        }
    }

    boolean hasUpperCase(String password){
        return true;
    }

    boolean hasSpecialChar(String password){
        return true;
    }
}

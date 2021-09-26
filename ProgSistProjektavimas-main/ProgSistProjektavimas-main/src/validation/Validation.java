package validation;

public class Validation {


    public int[] checkPassword(String password, PasswordPolicy passwordPolicy) {
        if(password.length() < passwordPolicy.minLength){
            int[] arr = {1};
            return arr;
        }
        if(passwordPolicy.uppercase){
            char[] charArr = password.toCharArray();
            boolean a;
            int length = password.length();

            for(int i=0;i<length;i++) {
                a = Character.isUpperCase(charArr[i]);  //----- is this ok?
                if (a) {
                    int[] arr = {};
                    return arr;
                }
            }
            int[] arr2 = {2};
            return arr2;
        }
        if(passwordPolicy.specialSymbols){
            char[] charArr = password.toCharArray();
            char[] symArr = passwordPolicy.symbols.toCharArray();
            int symLength = passwordPolicy.symbols.length();
            int length = password.length();

            for(int i=0;i<length;i++) {
                for(int j=0;j<symLength; j++){
                    if(charArr[i] == symArr[j]){
                        int[] arr = {};
                        return arr;
                    }
                }
            }
            int[] arr3 = {3};
            return arr3;
        }

        int[] arr = {};
        return arr;
    }

    public int[] checkPhoneNumber(String phoneNumber, PhoneNumberPolicy phoneNumberPolicy) {
        char[] charArr = phoneNumber.toCharArray();
        int length = phoneNumber.length();
        int[] arrErr = {0};
        if(phoneNumberPolicy.country == Country.NONE) {
            arrErr[0] = 1;
        }else{
            arrErr[0] = 2;
            if(strStartsWith(phoneNumber,phoneNumberPolicy.country.getPref())){
            }else{
                return arrErr;
            }
        }
            if (charArr[0] == '+' || charArr[0] == '0' || charArr[0] == '1' || charArr[0] == '2' || charArr[0] == '3' || charArr[0] == '4' || charArr[0] == '5' || charArr[0] == '6' || charArr[0] == '7' || charArr[0] == '8' || charArr[0] == '9') {
                for (int i = 1; i < length; i++) {
                    if (charArr[i] != '0' && charArr[i] != '1' && charArr[i] != '2' && charArr[i] != '3' && charArr[i] != '4' && charArr[i] != '5' && charArr[i] != '6' && charArr[i] != '7' && charArr[i] != '8' && charArr[i] != '9') {
                        return arrErr;
                    }
                }
                int[] arr = {};
                return arr;
            } else {
                return arrErr;
            }
    }

    public String applyNationalPhoneNumberCode(String phoneNumbers, PhoneNumberPolicy phoneNumberPolicy) {
        char[] charArr = phoneNumbers.toCharArray();
        String newNumber;
        if(charArr[0] == '8'){
            newNumber = phoneNumberPolicy.country.getPref() + subString(phoneNumbers,1);
            return newNumber;
        }
        else{
            return phoneNumbers;
        }
    }

    public int[] checkEmail(String email, EmailPolicy emailPolicy) {
        char[] charArr = email.toCharArray();
        int length = email.length();
        if(emailPolicy.restrict){
            char[] symArr = emailPolicy.symbols.toCharArray();
            int symLength = emailPolicy.symbols.length();

            for(int i=0;i<length;i++) {
                for(int j=0;j<symLength; j++){
                    if(charArr[i] == symArr[j]){
                        int[] arr = {2};
                        return arr;
                    }
                }
            }
            int[] arr = {};
            return arr;
        }
        if(emailPolicy.allowDomain){
            String[] domainArr = emailPolicy.getDomains();
            int domainLength;
            int arrPoss;
            char[] remainder;
            for(int i=0;i<domainArr.length;i++){
                domainLength = domainArr[i].length();
                arrPoss=length-domainLength;

                if(charArr[arrPoss-1]=='.'){
                    remainder = new char[domainLength];
                    for(int j=0;j<domainLength;j++){
                        remainder[j]=charArr[arrPoss+j];
                    }
                    if(charrayEquals(remainder,domainArr[i].toCharArray())){
                        int[] arr = {};
                        return arr;
                    }
                }
            }
            int[] arr = {3};
            return arr;
        }



        for (int i=0;i<length;i++){
            if(charArr[i]=='@'){
                int[] arr = {};
                return arr;
            }
        }
        int[] arr = {1};
        return arr;
    }



    public boolean strStartsWith(String word, String prefix){
        char[] wordArr = word.toCharArray();
        char[] prefixArr = prefix.toCharArray();
        int wordLength = word.length();
        int prefixLength = prefix.length();

        if(prefixLength <= wordLength){
            for(int i=0;i<prefixLength;i++){
                if(wordArr[i] != prefixArr[i]){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }
    public boolean charrayEquals(char[] wordArr,char[] secondArr){
        int wordLength = wordArr.length;
        int secondLength = secondArr.length;

        if(wordLength == secondLength){
            for(int i=0;i<wordLength;i++){
                if(wordArr[i] != secondArr[i]){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }
    public String subString(String word,int position){
        char[] charArr = word.toCharArray();
        int length = word.length()-position;
        char[] newCharArr = new char[length];
        for(int i=0;i<length;i++){
            newCharArr[i] = charArr[i+position];
        }
        return new String(newCharArr);
    }

}

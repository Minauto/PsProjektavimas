package validation;

public class Validation {


    public int[] checkPassword(String password, PasswordPolicy passwordPolicy) {
        int[] arr = new int[0];
        int tab=0;

        if(password.length() < passwordPolicy.minLength){
            arr = new int[3];
            arr[tab] = 1;
            tab++;
        }
        if(passwordPolicy.uppercase){
            char[] charArr = password.toCharArray();
            boolean a;
            int length = password.length();
            int hasUpper = 0;

            for(int i=0;i<length;i++) {
                a = Character.isUpperCase(charArr[i]);
                if (a) {
                    hasUpper = 1;
                }
            }
            if(hasUpper == 0){
                if(arr.length==0){
                    arr = new int[3];
                }
                arr[tab] = 2;
                tab++;
            }

        }
        if(passwordPolicy.specialSymbols){
            char[] charArr = password.toCharArray();
            char[] symArr = passwordPolicy.symbols.toCharArray();
            int symLength = passwordPolicy.symbols.length();
            int length = password.length();
            int hasSpecial = 0;

            for(int i=0;i<length;i++) {
                for(int j=0;j<symLength; j++){
                    if(charArr[i] == symArr[j]){
                        hasSpecial = 1;
                    }
                }
            }
            if(hasSpecial == 0){
                if(arr.length==0){
                    arr = new int[3];
                }
                arr[tab] = 3;
                tab++;
            }
        }

        return arr;
    }

    public int[] checkPhoneNumber(String phoneNumber, PhoneNumberPolicy phoneNumberPolicy) {
        char[] charArr = phoneNumber.toCharArray();
        int length = phoneNumber.length();
        int[] arrErr = {1};
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
        int[] arr = new int[0];
        int tab = 0;
        int lengthCheck = 0;

        for (int i=0;i<length;i++){
            if(charArr[i]=='@'){
                lengthCheck=1;
            }
        }
        if(lengthCheck == 0){
            if(arr.length==0){
                arr = new int[3];
            }
            arr[tab] = 1;
            tab++;
        }


        if(emailPolicy.restrict){
            char[] symArr = emailPolicy.symbols.toCharArray();
            int symLength = emailPolicy.symbols.length();

            for(int i=0;i<length;i++) {
                for(int j=0;j<symLength; j++){
                    if(charArr[i] == symArr[j]){
                        if(arr.length==0){
                            arr = new int[3];
                        }
                        arr[tab] = 2;
                        tab++;
                    }
                }
            }
        }
        if(emailPolicy.allowDomain){
            String[] tldArr = emailPolicy.getTLDomains();
            String[] domArr = emailPolicy.getDomains();
            int tldLength;
            int domLength;
            int tldPoss;
            int domPoss;
            int domainCheck=0;
            char[] remainder;
            char[] dom;
            for(int i=0;i<tldArr.length;i++){
                tldLength = tldArr[i].length();
                tldPoss=length-tldLength;
                if(charArr[tldPoss-1]=='.'){
                    remainder = new char[tldLength];
                    for(int j=0;j<tldLength;j++){
                        remainder[j]=charArr[tldPoss+j];
                    }
                    if(charrayEquals(remainder,tldArr[i].toCharArray())){
                        for(int n=0;n<domArr.length;n++){
                            domLength = domArr[n].length();
                            domPoss=(tldPoss-domLength)-1;
                            dom = new char[domLength];
                            for(int m=0;m<domLength;m++){
                                dom[m]=charArr[domPoss+m];
                            }
                            if(charrayEquals(dom,domArr[n].toCharArray())){
                                domainCheck = 1;
                            }
                        }

                    }
                }
            }
            if(domainCheck == 0){
                if(arr.length==0){
                    arr = new int[3];
                }
                arr[tab] = 3;
                tab++;
            }
        }
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

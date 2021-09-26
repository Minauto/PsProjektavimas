package validation;

public class PasswordPolicy {

    int minLength = 0;
    boolean uppercase = false;
    boolean specialSymbols = false;
    String symbols;

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public void requireUppercase(boolean b) {
        this.uppercase = b;
    }

    public void requireSpecialSymbols(boolean b) {
        this.specialSymbols = b;
    }

    public void setSpecialSymbols(String specialSymbols) {
        this.symbols = specialSymbols;
    }
}

package validation;

public enum Country {
    NONE(""),
     LITHUANIA("+370"),
    ESTONIA("+372");

     private String pref;

     public String getPref(){
         return this.pref;
     }

    private Country(String prefix) {
         this.pref = prefix;
    }
}

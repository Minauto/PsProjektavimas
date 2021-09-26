package validation;

public class EmailPolicy {

    boolean restrict = false;
    String symbols;
    boolean allowDomain = false;
    String [] domains;
    public void haveRestrictedSymbols(boolean b) {
        this.restrict = b;
    }

    public void setRestrictedSymbols(String restrictedSymbols) {
        this.symbols = restrictedSymbols;
    }

    public void haveAllowedTopLevelDomains(boolean b) {
        allowDomain = b;
    }

    public void setValidTopLevelDomains(String[] topLevelDomains) {
        domains = topLevelDomains;
    }

    public String [] getDomains(){
        return this.domains;
    }
}

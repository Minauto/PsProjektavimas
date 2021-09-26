package validation;

public class PhoneNumberPolicy {

    Country country = Country.NONE;

    public PhoneNumberPolicy() {
    }
    public PhoneNumberPolicy(Country country) {
        this.country = country;
    }
}

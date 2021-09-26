package validation;

import org.junit.Assert;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

@RunWith(Theories.class)
public class ValidatorTests {

    private Validation validation = new Validation();

    @BeforeEach
    private void setup() {
        validation = new Validation();
    }

    @DataPoints("passwords")
    public static String[] passwords() {
        return new String[]{
                "test145", "testUOoju124", "-12345+$$67", "sgsggvxbvcx", "10f~dssfdsf", "TESTASTTESTAS"};
    }

    @DataPoints("special symbols")
    public static String[] specialSymbols() {
        return new String[]{
                "!+-~^$"};
    }

    @DataPoints("phone numbers")
    public static String[] phoneNumbers() { // this is for checking if phone number has nonsense symbols in it.
        return new String[]{
                "+370ABC64488423", "86554 2322", "868~888888"};
    }

    @DataPoints("Lithuania phone numbers")
    public static String[] LithuaniaPhoneNumbers() { // this is for 86 to +370 conversion test.
        return new String[]{
                "864578956", "864422111", "868888888"};
    }

    @DataPoints("Estonia phone numbers")
    public static String[] EstoniaPhoneNumbers() { // this is for the phone validators third task. These are correct.
        return new String[]{
                "+3724455666", "+3728756421", "+3725864752"};
    }

    @DataPoints("emails")
    public static String[] emails() {
        return new String[]{
                "pranas.pranauskas@gmail.fr", "stasys.stasiulisgmail.pl",
                "jonas.jonaitisgmail.com", "petras.petr#aitis@gmail.com"};
    }

    @DataPoints("restricted email symbols")
    public static String[] restrictedEmailSymbolList() {
        return new String[]{
                "&$^!#%"};
    }

    @DataPoints("top level domains")
    public static String[][] allowedTopLevelDomains() {
        return new String[][] {
                new String[] {"com", "org", "net", "gov", "lt", "en", "de"}
        };
    }


    @Theory
    public void TestPasswordLengthCheck(@FromDataPoints("passwords") String password) {
        int minLength = 8;
        PasswordPolicy passwordPolicy = new PasswordPolicy();
        passwordPolicy.setMinLength(minLength);
        int[] status = validation.checkPassword(password, passwordPolicy); // if password check fails, error code array is returned, otherwise its empty.
        findError(status, 1); // let "password too short error code" be 1.
    }

    @Theory
    public void TestPasswordUppercaseLetterExistance(@FromDataPoints("passwords") String password) {
        PasswordPolicy passwordPolicy = new PasswordPolicy();
        passwordPolicy.requireUppercase(true);
        int[] status = validation.checkPassword(password, passwordPolicy);
        findError(status, 2); // 2 for password with no uppercase letter.
    }

    @Theory
    public void TestPasswordSpecialSymbolExistance(@FromDataPoints("passwords") String password, @FromDataPoints("special symbols") String specialSymbols) {
        PasswordPolicy passwordPolicy = new PasswordPolicy();
        passwordPolicy.requireSpecialSymbols(true);
        passwordPolicy.setSpecialSymbols(specialSymbols);
        int[] status = validation.checkPassword(password, passwordPolicy);
        findError(status, 3); // 3 for passwords those have none of the required special symbols.
    }

    @Theory
    public void TestIfNumberIsPlusAndNumbersOnly(@FromDataPoints("phone numbers") String phoneNumber) {
        PhoneNumberPolicy phoneNumberPolicy = new PhoneNumberPolicy(); // Default setup. Default phone number policy specifies, that only the plus sign and numbers are allowed.
        int[] status = validation.checkPhoneNumber(phoneNumber, phoneNumberPolicy);
        findError(status, 1); // 1 if phone number has other symbols than the plus sign and numbers.
    }

    @Theory
    public void TestIfEightIsChangedToLithuaniaNationalNumberCode(@FromDataPoints("Lithuania phone numbers") String phoneNumbers) {
        PhoneNumberPolicy phoneNumberPolicy = new PhoneNumberPolicy(Country.LITHUANIA);
        String nationalPhoneNumber = validation.applyNationalPhoneNumberCode(phoneNumbers, phoneNumberPolicy);
        Assert.assertTrue(nationalPhoneNumber.startsWith("+370"));
    }

    @Theory
    public void TestPhoneNumberNationalNumberCodeApplication(@FromDataPoints("Estonia phone numbers") String phoneNumber) {
        PhoneNumberPolicy phoneNumberPolicy = new PhoneNumberPolicy(Country.ESTONIA);
        int[] status = validation.checkPhoneNumber(phoneNumber, phoneNumberPolicy); // Check phone number does not recognize for example: 86 as correct, so method above must be used first.
        findError(status, 2); // 2 if number does not follow country's rules.
    }

    @Theory
    public void TestIfEmailHasEta(@FromDataPoints("emails") String email) {
        EmailPolicy emailPolicy = new EmailPolicy(); // This is the default setup. Default email policy specifies that eta must exist.
        int[] status = validation.checkEmail(email, emailPolicy);
        findError(status, 1); // 1 if email does not have eta symbol.
    }

    @Theory
    public void TestIfEmailDoesNotHaveRestrictedSymbols(@FromDataPoints("emails") String email, @FromDataPoints("restricted email symbols") String restrictedSymbols) {
        EmailPolicy emailPolicy = new EmailPolicy();
        emailPolicy.haveRestrictedSymbols(true);
        emailPolicy.setRestrictedSymbols(restrictedSymbols);
        int[] status = validation.checkEmail(email, emailPolicy);
        findError(status, 2); // 2 if email contains a restricted symbol.
    }

    @Theory
    public void TestIfEmailHasCorrectDomain(@FromDataPoints("emails") String email, @FromDataPoints("top level domains") String[] topLevelDomains) {
        EmailPolicy emailPolicy = new EmailPolicy();
        emailPolicy.haveAllowedTopLevelDomains(true);
        emailPolicy.setValidTopLevelDomains(topLevelDomains);
        int[] status = validation.checkEmail(email, emailPolicy);
        findError(status, 3); // 3 if top level domain is wrong (if not found in the list).
    }

    private void findError(int[] status, int errorCode) {
        Assert.assertFalse(IntStream.of(status).anyMatch(x -> x == errorCode));
    }



    @AfterEach
    private void close() {

    }

}

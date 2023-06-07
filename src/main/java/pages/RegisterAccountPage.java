package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import main.java.objects.Investor;

public class RegisterAccountPage extends BasePage {

    public RegisterAccountPage(WebDriver driver)
    {
        super(driver);
    }

    // Page Variables
    public static String KEY_URL = "investor/registration";

    // Locators
    private static By SEL_GENDER = By.cssSelector("select#investor_gender");
    private static By TXT_TITLE = By.cssSelector("input#investor_title");
    private static By SEL_TYPE = By.cssSelector("select#investor_type");
    private static By TXT_FIRST_NAME = By.cssSelector("input#investor_first_name");
    private static By TXT_LAST_NAME = By.cssSelector("input#investor_last_name");
    private static By TXT_EMAIL = By.cssSelector("input#investor_email");
    private static By TXT_PASSWORD = By.cssSelector("input#investor_password");
    private static By CHK_TERMS_AND_CONDITIONS = By.cssSelector("label[for='investor_accepts_terms_and_conditions']");
    private static By CHK_PRIVACY_POLICY = By.cssSelector("input#investor_accepts_privacy_policy");
    private static By BTN_REGISTER = By.cssSelector("input[type='submit']");
    private static By LBL_ERROR_EMAIL_ALREADY_TAKEN = By.cssSelector("div.investor_email div.invalid-feedback");
    private static By LBL_ERROR_NOT_CONFIRM_TERMS_CONDITIONS = By.cssSelector("fieldset.investor_accepts_terms_and_conditions div.invalid-feedback");
    private static By LBL_ERROR_NOT_CONFIRM_POLICY = By.cssSelector("fieldset.investor_accepts_privacy_policy  div.invalid-feedback");
    private static By LBL_ERROR_FIRST_NAME_CHARS_EXCEED = By.cssSelector("div.investor_first_name div.invalid-feedback");
    private static By LBL_ERROR_LAST_NAME_CHARS_EXCEED = By.cssSelector("div.investor_last_name div.invalid-feedback");
    // Error messages
    private String requiredSelectError = "Please select an item in the list";
    private String requiredTextError = "Please fill out this field";

    public void inputValidAndRandomDataForInvestorCreation(Investor investor){
        select(SEL_GENDER, investor.getInvestorGender());
        select(SEL_TYPE, investor.getInvestorType());
        writeText(TXT_TITLE, investor.getInvestorTitle());
        writeText(TXT_FIRST_NAME, investor.getInvestorFirstName());
        writeText(TXT_LAST_NAME, investor.getInvestorSurname());
        writeText(TXT_EMAIL, investor.getInvestorEmail());
        writeText(TXT_PASSWORD, investor.getInvestorPassword());
    }

    public void selectGender(Investor investor) {
        select(SEL_GENDER, investor.getInvestorGender());
    }

    public void selectType(Investor investor) {
        select(SEL_TYPE, investor.getInvestorType());
    }

    public void writeFirstName(Investor investor) {
        writeText(TXT_FIRST_NAME, investor.getInvestorFirstName());
    }

    public void writeLastName(Investor investor) {
        writeText(TXT_LAST_NAME, investor.getInvestorSurname());
    }

    public void writeEmail(Investor investor) {
        writeText(TXT_EMAIL, investor.getInvestorEmail());
    }

    public void writePassword(Investor investor) {
        writeText(TXT_PASSWORD, investor.getInvestorPassword());
    }

    public void clickCheckBoxTermsAndConditions() {
        clickByJavaScript(CHK_TERMS_AND_CONDITIONS);
    }

    public void clickCheckBoxPrivacyPolicy() {
        clickByJavaScript(CHK_PRIVACY_POLICY);
    }
    public void clickRegisterButton() {
        clickByJavaScript(BTN_REGISTER);
    }

    public boolean isRequiredSelectErrorDisplayed() {
        return isElementWithTextExist(requiredSelectError);
    }

    public boolean isRequiredTextErrorDisplayed() {
        return isElementWithTextExist(requiredTextError);
    }

    public boolean isErrorEmailAlreadyTakenDisplayed() {
        return isElementExist(LBL_ERROR_EMAIL_ALREADY_TAKEN);
    }

    public boolean isErrorNotConfirmTermsConditionsDisplayed() {
        return isElementExist(LBL_ERROR_NOT_CONFIRM_TERMS_CONDITIONS);
    }

    public boolean isErrorNotConfirmPolicyDisplayed() {
        return isElementExist(LBL_ERROR_NOT_CONFIRM_POLICY);
    }

    public boolean isErrorFirstNameCharsExceed() {
        return isElementExist(LBL_ERROR_FIRST_NAME_CHARS_EXCEED);
    }

    public boolean isErrorLastNameCharsExceed() {
        return isElementExist(LBL_ERROR_LAST_NAME_CHARS_EXCEED);
    }
}

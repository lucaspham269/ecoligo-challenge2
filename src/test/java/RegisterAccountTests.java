package test.java;

import org.testng.Assert;
import org.testng.annotations.Test;
import main.java.objects.Investor;
import main.java.pages.RegisterAccountPage;

public class RegisterAccountTests extends TestBase{
    Investor investor;

    @Test(priority = 1)
    // Scenario 1: As a user, I should register an account with regular valid data successfully.
    public void asAUserIShouldRegisterAnAccountWithRegularValidDataSuccessfully() throws Exception {
        String testCaseName = "Scenario 1: As a user, I should register an account with regular valid data successfully.";
        System.out.println(testCaseName);

        investor = new Investor();
        webApp.navigateToURL(webApp.getUrl() + RegisterAccountPage.KEY_URL);
        registerAccountPage.inputValidAndRandomDataForInvestorCreation(investor);
        registerAccountPage.clickCheckBoxTermsAndConditions();
        registerAccountPage.clickCheckBoxPrivacyPolicy();
        registerAccountPage.clickRegisterButton();
        webApp.waitForURLToContain("edit");
        webApp.waitForPageLoadingComplete();

        // Check point: The 1st step of registration is successful.
        Assert.assertTrue(registerAccountStep2Page.isOnRegisterAccountStep2Page());
    }

    @Test(priority = 2)
    // Scenario 2: As a user, I should not be able to register an account which already existing.
    public void asAUserIShouldNotBeAbleToRegisterAnAccountWhichAlreadyBeenRegistered() throws Exception {
        String testCaseName = "Scenario 2: As a user, I should not be able to register an account which already existing.";
        System.out.println(testCaseName);

        // Pre-condition: Register new account successfully
        investor = new Investor();
        webApp.navigateToURL(webApp.getUrl() + RegisterAccountPage.KEY_URL);
        registerAccountPage.inputValidAndRandomDataForInvestorCreation(investor);
        registerAccountPage.clickCheckBoxTermsAndConditions();
        registerAccountPage.clickCheckBoxPrivacyPolicy();
        registerAccountPage.clickRegisterButton();
        webApp.waitForURLToContain("edit");
        webApp.waitForPageLoadingComplete();
        Assert.assertTrue(registerAccountStep2Page.isOnRegisterAccountStep2Page());

        // The following steps can't be completed since I have no idea how to move forward for the complete.

        driver.manage().deleteAllCookies();

        // Register with the same email again
        webApp.navigateToURL(webApp.getUrl() + RegisterAccountPage.KEY_URL);
        registerAccountPage.inputValidAndRandomDataForInvestorCreation(investor);
        registerAccountPage.clickCheckBoxTermsAndConditions();
        registerAccountPage.clickCheckBoxPrivacyPolicy();
        registerAccountPage.clickRegisterButton();

        // Check point: I should see the error message reports that the email already existing.
        Assert.assertTrue(registerAccountPage.isErrorEmailAlreadyTakenDisplayed());
    }

    @Test(priority = 3)
    // Scenario 3: As a user, I should not be able to register an account without filling all required data.
    public void asAUserIShouldNotBeAbleToRegisterAnAccountWithoutFillingAllRequiredData() throws Exception {
        String testCaseName = "Scenario 3: As a user, I should not be able to register an account without filling all required data.";
        System.out.println(testCaseName);
        
        investor = new Investor();
        webApp.navigateToURL(webApp.getUrl() + RegisterAccountPage.KEY_URL);

        // Check if Gender is required.
        registerAccountPage.clickRegisterButton();
        result.addItem("Checking if Gender error message display.", registerAccountPage.isRequiredSelectErrorDisplayed());
        registerAccountPage.selectGender(investor);

        // Check if Type is required.
        registerAccountPage.clickRegisterButton();
        result.addItem("Checking if Type error message display.", registerAccountPage.isRequiredSelectErrorDisplayed());
        registerAccountPage.selectType(investor);

        // Check if First Name is required.
        registerAccountPage.clickRegisterButton();
        result.addItem("Checking if First Name error message display.", registerAccountPage.isRequiredTextErrorDisplayed());
        registerAccountPage.writeFirstName(investor);

        // Check if Last Name is required.
        registerAccountPage.clickRegisterButton();
        result.addItem("Checking if Last Name error message display.", registerAccountPage.isRequiredTextErrorDisplayed());
        registerAccountPage.writeLastName(investor);

        // Check if Email is required.
        registerAccountPage.clickRegisterButton();
        result.addItem("Checking if Email error message display.", registerAccountPage.isRequiredTextErrorDisplayed());
        registerAccountPage.writeEmail(investor);

        // Check if Password is required.
        registerAccountPage.clickRegisterButton();
        result.addItem("Checking if Password error message display.", registerAccountPage.isRequiredTextErrorDisplayed());
        registerAccountPage.writePassword(investor);


        registerAccountPage.clickRegisterButton();
        // Check if Confirm Terms and Conditions is required.
        result.addItem("Checking if Confirm Terms and Conditions error message display.", registerAccountPage.isErrorNotConfirmTermsConditionsDisplayed());
        // Check if Confirm Policy is required.
        result.addItem("Checking if Confirm Policy error message display.", registerAccountPage.isErrorNotConfirmPolicyDisplayed());
        registerAccountPage.writePassword(investor);
        registerAccountPage.clickCheckBoxTermsAndConditions();
        registerAccountPage.clickCheckBoxPrivacyPolicy();

        registerAccountPage.clickRegisterButton();
        webApp.waitForURLToContain("edit");
        webApp.waitForPageLoadingComplete();

        // Check point: The 1st step of registration is successful.
        Assert.assertTrue(registerAccountStep2Page.isOnRegisterAccountStep2Page());
    }

    @Test(priority = 4)
    // Scenario 4: As a user, I should register an account with long string contains special characters data successfully.
    public void asAUserIShouldRegisterAnAccountWithLongStringContainsSpecialCharactersDataSuccessfully() throws Exception {
        String testCaseName = "Scenario 4: As a user, I should register an account with long string contains special characters data successfully.";
        System.out.println(testCaseName);

        // Prepare test data
        investor = new Investor();
        String longStringStringContainsSpecialCharacters = investor.randomLongStringContainsSpecialCharacters(100);
        investor.setInvestorFirstName(longStringStringContainsSpecialCharacters);
        investor.setInvestorSurname(longStringStringContainsSpecialCharacters);

        // Test steps
        webApp.navigateToURL(webApp.getUrl() + RegisterAccountPage.KEY_URL);
        registerAccountPage.inputValidAndRandomDataForInvestorCreation(investor);
        registerAccountPage.clickCheckBoxTermsAndConditions();
        registerAccountPage.clickCheckBoxPrivacyPolicy();
        registerAccountPage.clickRegisterButton();

        //Check point
        result.addItem("First name chars exceed.", registerAccountPage.isErrorFirstNameCharsExceed());
        result.addItem("Last name chars exceed.", registerAccountPage.isErrorLastNameCharsExceed());

        // Test steps
        String newLongStringStringContainsSpecialCharacters = investor.randomLongStringContainsSpecialCharacters(48);
        investor.setInvestorFirstName(newLongStringStringContainsSpecialCharacters);
        investor.setInvestorSurname(newLongStringStringContainsSpecialCharacters);
        registerAccountPage.inputValidAndRandomDataForInvestorCreation(investor);
        registerAccountPage.clickCheckBoxTermsAndConditions();
        registerAccountPage.clickCheckBoxPrivacyPolicy();
        registerAccountPage.clickRegisterButton();

        webApp.waitForURLToContain("edit");
        webApp.waitForPageLoadingComplete();

        // Check point: The 1st step of registration is successful.
        Assert.assertTrue(result.getAssertResult());
        Assert.assertTrue(registerAccountStep2Page.isOnRegisterAccountStep2Page());
    }
}


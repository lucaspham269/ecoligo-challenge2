package main.java.pages;

import org.openqa.selenium.WebDriver;

public class RegisterAccountStep2Page extends BasePage {

    public RegisterAccountStep2Page(WebDriver driver)
    {
        super(driver);
    }

    // Page Variables
    public static String KEY_URL = "registration/edit";

    public boolean isOnRegisterAccountStep2Page()
    {
        String currentURL = driver.getCurrentUrl();
        if(currentURL.contains(KEY_URL))
            return true;
        return false;
    }
}

package test.java;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.titusfortner.logging.SeleniumLogger;

import main.java.pages.PageController;
import main.java.pages.RegisterAccountPage;
import main.java.pages.RegisterAccountStep2Page;
import main.java.utilities.AssertCollection;
import main.java.utilities.DriverManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBase {
    protected WebDriver driver;
    public PageController webApp;
    public Logger logger;
    public AssertCollection result;

    public RegisterAccountPage registerAccountPage;
    public RegisterAccountStep2Page registerAccountStep2Page;

    //Do the test setup
    @BeforeMethod
    @Parameters(value={"browser"})
    public void setupTest (String browser) throws MalformedURLException , IOException, InterruptedException,Exception {
        System.out.println("Browser: " + browser);
        driver = new DriverManager(browser).createDriver();
        webApp = new PageController(driver);
        SeleniumLogger seleniumLogger = new SeleniumLogger();
        seleniumLogger.setLevel(Level.FINE);
        result = new AssertCollection();
        registerAccountPage = webApp.getRegisterAccountPage();
        registerAccountStep2Page = webApp.getRegisterAccountStep2Page();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

    public PageController getWebApp() {
        return webApp;
    }

    public void setWebApp(PageController webApp) {
        this.webApp = webApp;
    }
}

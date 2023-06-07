package main.java.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;

import java.net.MalformedURLException;

public class DriverManager {

    public String browser = null;
    public WebDriver driver = null;

    public DriverManager(String browser) {
        this.browser = browser;
    }

    public WebDriver createDriver() throws MalformedURLException, Exception {
        switch (browser) {
            case "firefox":
                FirefoxOptions firfoxOptions = new FirefoxOptions();
                firfoxOptions.addArguments("-private");
                firfoxOptions.addArguments("--width=1920");
                firfoxOptions.addArguments("--height=1080");
                driver = new FirefoxDriver(firfoxOptions);
                break;
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("test-type");
                chromeOptions.addArguments("disable-popup-blocking");
                chromeOptions.addArguments("ignore-certificate-errors");
                chromeOptions.addArguments("disable-translate");
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.addArguments("start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                Assert.assertTrue(false, "There is a problem on browser selection! Please check testng xml file!");
                break;
        }
        return driver;
    }
}

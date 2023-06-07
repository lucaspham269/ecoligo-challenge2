package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public FluentWait<WebDriver> fluentWait;

    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        fluentWait = new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(10)) // Maximum wait time
        .pollingEvery(Duration.ofMillis(500)) // Polling interval
        .ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class));
    }

    public void navigateToURL(String url) {
        driver.navigate().to(url);
        System.out.println("Navigated to  " + url );
    }

    //Click Method by locator
    public void click(By elementLocation) {
        waitForElement(elementLocation);
        driver.findElement(elementLocation).click();
    }

    //Click Method by Web Element
    public void click(WebElement element) {
        element.click();
    }

    //Click by Actions 
    public void clickByAction(By elementLocation) {
        WebElement elementToClick = driver.findElement(elementLocation);
        Actions actions = new Actions(driver);
        actions.click(elementToClick);
    }

    //Click by Javascript 
    public void clickByJavaScript(By elementLocation) {
        WebElement elementToClick = driver.findElement(elementLocation);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", elementToClick);
    }

    //Select Method by locator
    public void select(By elementLocation, String string) {
        Select drpDay = new Select(driver.findElement(elementLocation));
        drpDay.selectByVisibleText(string);
    }

    //Write Text by locator
    public void writeText(By elementLocation, String text) {
        driver.findElement(elementLocation).clear();
        driver.findElement(elementLocation).sendKeys(text);
    }

    //Write Text by Web Element
    public void writeText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    //Clean Write Test by locator
    public void deleteText(By elementLocation) {
        driver.findElement(elementLocation).clear();
    }

    //Clean Write Test by Web Element
    public void deleteText(WebElement element) {
        element.clear();
    }

    //Read Text
    public String readText(By elementLocation) {
        return driver.findElement(elementLocation).getText();
    }

    //Read Text
    public String readText(WebElement element) {
        return element.getText();
    }

    //Is Web Element exist by locator?
    public boolean isElementExist(By elementLocation) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(elementLocation));
            return driver.findElement(elementLocation).isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    //Is Web Element exist by element?
    public boolean isElementExist(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isElementWithTextExist(String text) {
        try {
            String xpath = "//*[text()='" + text + "']";
            By elementLocation = By.xpath(xpath);
            return isElementExist(elementLocation);
        } catch (Exception ex) {
            return false;
        }
    }

    public void waitForPageLoadingComplete() {
        // Wait for the page to load completely
        wait.until(webDriver -> {
            String readyState = (String) ((JavascriptExecutor) webDriver).executeScript("return document.readyState");
            return readyState.equals("complete");
        });
    }

    public void waitForURLToContain(String partialOfUrl) {
        wait.until(ExpectedConditions.urlContains(partialOfUrl));
    }

    public void waitForElement(By elementLocation) {
        try {
            fluentWait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(elementLocation);
                }
            });
        } catch (Exception ex) {
            throw new SkipException(ex.toString());
        }
    }

    public String getCurrentURL() {
        String currentURL = driver.getCurrentUrl();
        return currentURL;
    }

    public void selectValueOnDropBox(By elementLocation, int num) {
        Select optionSelect = new Select(driver.findElement(elementLocation));
        optionSelect.selectByIndex(num);
    }

    public void selectValueOnDropBox(By elementLocation, String text) {
        Select optionSelect = new Select(driver.findElement(elementLocation));
        optionSelect.selectByValue(text);
    }

    public void selectVisibleTextOnDropBox(By elementLocation, String text) {
        Select optionSelect = new Select(driver.findElement(elementLocation));
        optionSelect.selectByVisibleText(text);
    }

    public void waitAnElementDisplay(By elementLocation){
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocation));
    }

    public void waitAnElementPresent(By elementLocation){
        wait.until(ExpectedConditions.presenceOfElementLocated(elementLocation));
    }

    //Get number of items
    public int getNumberOfItems (By elementLocation){
        List<WebElement> items = null;
        try {
            items = driver.findElements(elementLocation);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return items.size();
    }

    //Get item
    public WebElement getItems (By elementLocation, int index){
        List<WebElement> items = null;
        try {
            items = driver.findElements(elementLocation);
        }
        catch (Exception ex){
            System.out.print(ex);
        }
        return items.get(index);
    }

    //Get list of items
    public List<WebElement> getListItems (By elementLocation){
        List<WebElement> items = null;
        try {
            items = driver.findElements(elementLocation);
        }
        catch (Exception ex){
            System.out.print(ex);
        }
        return items;
    }
}
package com.test.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class stepDefinitions {

    WebDriver driver = new ChromeDriver();
    Duration timeoutInSeconds = Duration.ofSeconds(10);
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(2));
    Actions actions = new Actions(driver);

    public static Logger logger = LogManager.getLogger();



    @Given("I launch chrome browser")
    public void iLaunchChromeBrowser() {
        System.setProperty("webDriver.chrome.driver", "\"D:\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe\"" );
        logger.info("Chrome browser is launched");
    }

    @When("I navigate to amazon.ca")
    public void iNavigateToAmazonCa() {
        driver.get("https://www.amazon.ca/");
        logger.info("Navigated to Amazon.ca");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"nav-logo-sprites\"]")));
        logger.info("Wait for page to full load");
    }

    @And("verify hamburger menu exists")
    public void verifyHamburgerMenuExists() {
        boolean isPresent = driver.findElements(By.xpath("//*[@id=\"nav-hamburger-menu\"]/i")).size() > 0;
        if (!isPresent){
            driver.navigate().refresh();
            logger.info("Hamburger menu not found, refresh page");
        }
        logger.info("Hamburger menu  found, continuing with test steps");
    }

    @Then("click on hamburger menu")
    public void clickOnHamburgerMenu() {
        driver.findElement(By.xpath("//*[@id=\"nav-hamburger-menu\"]")).click();
        logger.info("Click on Hamburger Menu");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/ul[1]")));
        logger.info("Wait until hamburger menu populates");
    }

    @And("verify hamburger menu opens")
    public void verifyHamburgerMenuOpens() {
        boolean isPresent = driver.findElements(By.xpath("//*[@id=\"nav-hamburger-menu\"]")).size() > 0;
        if (!isPresent){
            driver.navigate().refresh();
            logger.info("Hamburger menu did not open, refreshing");
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[10]/a")));
        logger.info("Hamburger menu open successfully, continuing test steps");
    }

    @Then("click on Kindle")
    public void clickOnKindle() {
        WebElement kindle = driver.findElement(By.cssSelector("li:nth-child(10) .hmenu-item"));
        actions.moveToElement(kindle).click().build().perform();
        logger.info("Find and click on Kindle option in hamburger menu");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/ul[5]/li[2]/div")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hmenu-content\"]/ul[5]/li[3]/a")));
        logger.info("Wait for submenu to load properly");

    }

    @Then("click on Kindle under Kindle E-Readers")
    public void clickOnKindleUnderKindleEReaders() {
        driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[5]/li[3]/a")).click();
        logger.info("Click on Kindle Options under Kindle E-Readers");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[3]/div[4]/div[12]/div[1]/div[4]/div/div[1]/div/div/div/form/div[1]/div/div/div/div[3]/div/div[14]/div/div/span[2]/span/input")));
        logger.info("Wait for Kindle Page to render");
    }

    @Then("click Buy Now")
    public void clickBuyNow() {
        driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[12]/div[1]/div[4]/div/div[1]/div/div/div/form/div[1]/div/div/div/div[3]/div/div[14]/div/div/span[2]/span/input")).click();
        logger.info("Click on Buy Now Options");
    }

    @And("verify user is asked for email or phone number")
    public void verifyUserIsAskedForEmailOrPhoneNumber() {
        Boolean emailorphonefieldisPresent = driver.findElements(By.xpath("//*[@id=\"ap_email\"]")).size() > 0;
        if(emailorphonefieldisPresent = true){
            System.out.println("User is successfully asked for email or phone number");
        }
    }


}

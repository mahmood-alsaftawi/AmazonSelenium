package com.test.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
        driver.manage().window().maximize();
    }

    @When("I navigate to amazon.ca")
    public void iNavigateToAmazonCa() {
        driver.get("https://www.amazon.ca/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Wait until hamburger menu populates");
    }

    @And("verify hamburger menu opens")
    public void verifyHamburgerMenuOpens() {
        if(driver.findElements(By.xpath("//div[@id='nav-main']")).size() == 0){

            driver.navigate().refresh();
            logger.info("Hamburger menu did not open, refreshing");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        logger.info("Hamburger menu open successfully, continuing test steps");
    }

    @Then("click on Kindle")
    public void clickOnKindle() {
        List<WebElement> kindleOptions = driver.findElements(By.className("hmenu-item"));
        WebElement kindle = driver.findElement(By.xpath("//div[contains(text(),'Kindle')]"));
        kindle.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Find and click on Kindle option in hamburger menu");
        logger.info("Wait for submenu to load properly");

    }

    @Then("click on Kindle under Kindle E-Readers")
    public void clickOnKindleUnderKindleEReaders() {
        driver.findElement(By.xpath("//a[@class='hmenu-item' and text()='Kindle']")).click();
        logger.info("Click on Kindle Options under Kindle E-Readers");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Wait for Kindle Page to render");
    }

    @Then("click Buy Now")
    public void clickBuyNow() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath("//input[@id='buy-now-button']")).click();
        logger.info("Click on Buy Now Options");
    }

    @And("verify user is asked for email or phone number")
    public void verifyUserIsAskedForEmailOrPhoneNumber() {
        Boolean emailorphonefieldisPresent = driver.findElements(By.xpath("//*[@id=\"ap_email\"]")).size() > 0;
        if(emailorphonefieldisPresent = true){
            System.out.println("User is successfully asked for email or phone number");
        } else {
            System.out.println("User not asked for email or phone number");
        }
        driver.quit();
    }


}

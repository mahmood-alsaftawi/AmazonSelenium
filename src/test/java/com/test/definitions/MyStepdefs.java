package com.test.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyStepdefs {
    WebDriver driver;

    @Given("I launch chrome browser")
    public void iLaunchChromeBrowser() {
        System.setProperty("webDriver.chrome.driver", "\"D:\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe\"" );
        driver=new ChromeDriver();
    }

    @When("I navigate to amazon.ca")
    public void iNavigateToAmazonCa() {
        driver.get("https://www.amazon.ca/");
    }

    @Then("verify amazon.ca logo is present")
    public void verifyAmazonCaLoadsProperly() {
        boolean status = driver.findElement(By.xpath("//*[@id=\"nav-logo-sprites\"]")).isDisplayed();
        Assert.assertTrue(status);
    }

    @And("close browser")
    public void closeBrowser() {
        driver.quit();
    }

}

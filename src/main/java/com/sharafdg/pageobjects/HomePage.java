package com.sharafdg.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

//    @FindBy(xpath="//a[contains(@class,'top_login')]")
    @FindBy(xpath="//div[contains(@class,'login-wrp')]")
    WebElement signInButton;

    @FindBy(xpath="//span[contains(@class,'signin-text')]")
    WebElement signedInUserName;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickOnSignInButton() {
        wait.until(ExpectedConditions.visibilityOf(signInButton));
        signInButton.click();
    }

    public String getSignedInUserName() {
//        signedInUserName = driver.findElement(By.xpath("//span[contains(@class,'signin-text')]"));
//        wait.until(ExpectedConditions.visibilityOf(signedInUserName));

        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(signedInUserName));
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException caught. Retrying...");
            }
            attempts++;
        }
        return signedInUserName.getText();
    }
}

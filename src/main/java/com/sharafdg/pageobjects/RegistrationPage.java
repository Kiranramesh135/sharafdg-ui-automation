package com.sharafdg.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id="reg_email")
    WebElement emailAddressTextBox;

    @FindBy(id="reg_password")
    WebElement passwordTextBox;

    @FindBy(id="first_name")
    WebElement firstNameTextBox;

    @FindBy(id="last_name")
    WebElement lastNameTextBox;

    @FindBy(id="account_mobile_no")
    WebElement accountMobileTextBox;

    @FindBy(id="otp_terms")
    WebElement termsCheckBox;

    @FindBy(xpath="//input[contains(@name,'register')]")
    WebElement registerButton;

    @FindBy(xpath="//button[contains(@class,'btn btn-white icon-close')]")
    WebElement cookieNoteButton;


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void registerUser(String email, String password, String firstName, String lastName, String mobileNumber) {
        wait.until(ExpectedConditions.visibilityOf(emailAddressTextBox));
        emailAddressTextBox.sendKeys(email);
        passwordTextBox.sendKeys(password);
        firstNameTextBox.sendKeys(firstName);
        lastNameTextBox.sendKeys(lastName);
        accountMobileTextBox.sendKeys(mobileNumber);
        termsCheckBox.click();
        cookieNoteButton.click();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", registerButton);
        registerButton.click();
    }


}

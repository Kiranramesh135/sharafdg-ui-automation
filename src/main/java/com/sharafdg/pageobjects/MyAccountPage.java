package com.sharafdg.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//h1[contains(@class,'headline')]")
    WebElement headLine;

    @FindBy(id = "phone-email")
    WebElement emailIdTextBox;

    @FindBy(xpath = "//a[contains(@class,'btn btn-secondary')]")
    WebElement createAccountButton;

    @FindBy(xpath = "//input[contains(@type,'submit')]")
    WebElement nextButton;

    @FindBy(id = "login_with_password")
    WebElement loginWithPasswordButton;

    @FindBy(id = "password")
    WebElement passwordTextBox;

    @FindBy(xpath = "//input[contains(@name,'login')]")
    WebElement loginButton;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickCreateAccountButton() {
        wait.until(ExpectedConditions.visibilityOf(createAccountButton));
        createAccountButton.click();
    }

    public String getPageHeadLine() {
        wait.until(ExpectedConditions.visibilityOf(headLine));
        return headLine.getText();
    }

    public void enterEmailId(String emailID) {
        wait.until(ExpectedConditions.visibilityOf(emailIdTextBox));
        emailIdTextBox.sendKeys(emailID);
        nextButton.click();
    }

    public void loginWithPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(loginWithPasswordButton));
        loginWithPasswordButton.click();
        wait.until(ExpectedConditions.visibilityOf(passwordTextBox));
        passwordTextBox.sendKeys(password);
        loginButton.click();
    }
}

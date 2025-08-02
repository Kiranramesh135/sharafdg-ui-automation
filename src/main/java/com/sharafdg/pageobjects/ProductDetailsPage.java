package com.sharafdg.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailsPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//h1[contains(text(),'Apple iPhone 14 Pro (256GB) – Silver')]")
    WebElement productName;

    @FindBy(xpath = "(//button[contains(normalize-space(), 'Add to Cart')])[1]")
    WebElement addToCartButton;

    @FindBy(xpath = "//a[contains(normalize-space(), 'Proceed to Checkout')]")
    WebElement proceedToCheckOutButton;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void addToCartAndCheckout() {
        wait.until(ExpectedConditions.visibilityOf(productName));
        addToCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckOutButton));
        proceedToCheckOutButton.click();
    }
}

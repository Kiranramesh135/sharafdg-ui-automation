package com.sharafdg.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "shipping_first_name")
    WebElement firstNameTextBox;

    @FindBy(id = "shipping_last_name")
    WebElement lastNameTextBox;

    @FindBy(xpath = "(//input[contains(@placeholder,'Search')])[1]")
    WebElement emiratesDropDown;

    @FindBy(xpath = "//label[contains(text(),'Area')]/..//input[contains(@placeholder,'Search')]")
    WebElement areaDropDown;

    @FindBy(id = "shipping_address_1")
    WebElement addressTextBox;

    @FindBy(id = "payment_method_cod")
    WebElement codRadioButton;

    @FindBy(id = "place_order")
    WebElement placeOrderButton;

    @FindBy(xpath = "//h3[contains(@class,'woocommerce-thankyou-order-received')]")
    WebElement orderConfirmationText;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String enterDetailsAndCheckout(String firstName, String lastName, String shippingState, String area, String address) {
        try {

            wait.until(ExpectedConditions.visibilityOf(firstNameTextBox));
            firstNameTextBox.clear();
            firstNameTextBox.sendKeys(firstName);
            lastNameTextBox.clear();
            lastNameTextBox.sendKeys(lastName);
            emiratesDropDown.sendKeys(shippingState);
            emiratesDropDown.click();
            lastNameTextBox.click();
            lastNameTextBox.sendKeys(Keys.TAB);
            emiratesDropDown.sendKeys(Keys.TAB);
            wait.until(ExpectedConditions.visibilityOf(areaDropDown));
            areaDropDown.sendKeys(area);
            areaDropDown.sendKeys(Keys.TAB);
            Thread.sleep(5000);

            ((JavascriptExecutor) driver).executeScript("scroll(0,450)");
            wait.until(ExpectedConditions.visibilityOf(addressTextBox));
            addressTextBox.sendKeys(address);
            Thread.sleep(5000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            long lastHeight = (long) js.executeScript("return document.body.scrollHeight");

            while (true) {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(1000); // Wait for page to load more content if lazy-loaded

                long newHeight = (long) js.executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }

            WebElement codRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("payment_method_cod")));
            codRadioButton.click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.visibilityOf(placeOrderButton));
            placeOrderButton.click();
            wait.until(ExpectedConditions.visibilityOf(orderConfirmationText));

        } catch (InterruptedException e) {
            System.out.println("Error in completeCheckout: " + e.getMessage());
            e.printStackTrace();
        }

        return orderConfirmationText.getText();
    }

}

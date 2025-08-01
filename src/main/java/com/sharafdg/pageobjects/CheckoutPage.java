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

    @FindBy(xpath="//button[contains(@class,'btn btn-white icon-close')]")
    WebElement cookieNoteButton;

    @FindBy(id="shipping_first_name")
    WebElement firstNameTextBox;

    @FindBy(id="shipping_last_name")
    WebElement lastNameTextBox;

    @FindBy(id="shipping_state")
    WebElement shippingStateDropDown;

    @FindBy(xpath = "(//input[contains(@placeholder,'Search')])[1]")
    WebElement emiratesDropDown;

//    @FindBy(xpath = "(//input[contains(@placeholder,'Search')])[1]")
    @FindBy(xpath = "//label[contains(text(),'Area')]/..//input[contains(@placeholder,'Search')]")
    WebElement areaDropDown;

    @FindBy(id = "(shipping_city")
    WebElement shippingCity;

    @FindBy(id="shipping_address_1")
    WebElement addressTextBox;

    @FindBy(id="payment_method_cod")
    WebElement codRadioButton;

    @FindBy(id="place_order")
    WebElement placeOrderButton;

    @FindBy(xpath="//h3[contains(@class,'woocommerce-thankyou-order-received')]")
    WebElement orderComfirmationText;








//    Search textbox in dropdown
//    //input[contains(@placeholder,'Search')]


    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String enterDetailsAndCheckout(String firstName, String lastName, String shippingState, String area, String address) {
        try {
            wait.until(ExpectedConditions.visibilityOf(cookieNoteButton));
            cookieNoteButton.click();
            wait.until(ExpectedConditions.visibilityOf(firstNameTextBox));
            firstNameTextBox.clear();
            firstNameTextBox.sendKeys(firstName);
            lastNameTextBox.clear();
            lastNameTextBox.sendKeys(lastName);
            emiratesDropDown.sendKeys(shippingState);
            lastNameTextBox.click();
            lastNameTextBox.sendKeys(Keys.TAB);
            emiratesDropDown.sendKeys(Keys.TAB);
            wait.until(ExpectedConditions.visibilityOf(areaDropDown));
            areaDropDown.sendKeys(area);
            areaDropDown.sendKeys(Keys.TAB);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ((JavascriptExecutor) driver).executeScript("scroll(0,450)");
            wait.until(ExpectedConditions.visibilityOf(addressTextBox));
            addressTextBox.sendKeys(address);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

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
            wait.until(ExpectedConditions.visibilityOf(orderComfirmationText));

        } catch (Exception e) {
            System.out.println("Error in completeCheckout: " + e.getMessage());
            e.printStackTrace();
        }

        return orderComfirmationText.getText();
        }



}

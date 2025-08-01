package com.sharafdg.tests;

import com.sharafdg.pageobjects.*;
import com.sharafdg.utils.RandomDataUtil;
import org.openqa.selenium.devtools.v122.domsnapshot.model.StringIndex;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSharafdgWebsite extends BaseTest{

    HomePage homePage;
    MyAccountPage myAccountPage;
    RegistrationPage registrationPage;
    ProductDetailsPage productDetailsPage;
    CheckoutPage checkoutPage;
    String email;
    String password;
    String firstName;
    String lastName;
    String phoneNumber;

    @BeforeMethod
    public void initialize() {
        homePage = new HomePage(driver);
        myAccountPage = new MyAccountPage(driver);
        registrationPage = new RegistrationPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        checkoutPage = new CheckoutPage(driver);

    }

    @Test(description = "New user registration flow", priority = 1)
    public void testUserRegistration() {

        email = RandomDataUtil.getRandomEmail();
        password = RandomDataUtil.getRandomPassword(8);
        firstName = RandomDataUtil.getRandomFirstName();
        lastName = RandomDataUtil.getRandomLastName();
        phoneNumber = "582949109";

        System.out.println("email = "+email);
        System.out.println("password = "+password);

        homePage.clickOnSignInButton();
        Reporter.log("Landed on HomePage and clicked on signIn button");
        Assert.assertEquals(myAccountPage.getPageHeadLine(),"Welcome");
        myAccountPage.clickCreateAccountButton();
        Reporter.log("Clicked on create button in MyAccountPage");
        registrationPage.registerUser(email,password,firstName,lastName,phoneNumber);
        Reporter.log("Entered user details and clicked on Register button in Registration page");
        Assert.assertEquals(homePage.getSignedInUserName(),firstName.toUpperCase());
        Reporter.log("Asserted the user name in HomePage after user registration");

    }

    @Test(description = "User login flow", priority = 2)
    public void testUserLogin() {

        homePage.clickOnSignInButton();
        Reporter.log("Landed on HomePage and clicked on signIn button");
        Assert.assertEquals(myAccountPage.getPageHeadLine(),"Welcome");
        myAccountPage.enterEmailId(email);
        Reporter.log("Landed on MyAccountPage and clicked on next button");
        myAccountPage.loginWithPassword(password);
        Reporter.log("Entered password and clicked on login button");
        Assert.assertEquals(homePage.getSignedInUserName(),firstName.toUpperCase());
        Reporter.log("Assert the user name in HomePage after user login");
    }

    @Test(description = "Product purchase flow", priority = 3)
    public void testProductPurchase() {

        homePage.clickOnSignInButton();
        Reporter.log("Landed on HomePage and clicked on signIn button");
        Assert.assertEquals(myAccountPage.getPageHeadLine(),"Welcome");
        myAccountPage.enterEmailId(email);
        Reporter.log("Landed on MyAccountPage and clicked on next button");
        myAccountPage.loginWithPassword(password);
        Reporter.log("Entered password and clicked on login button");
        Assert.assertEquals(homePage.getSignedInUserName(),firstName.toUpperCase());
        Reporter.log("Asserted the user name in HomePage after user login");
        driver.get("https://uae.sdgstage.com/product/apple-iphone-14-pro-256gb-silver-physical-dual-sim-international-version/?promo=2587484");
        Reporter.log("Navigate to product page");
        productDetailsPage.addToCartAndCheckout();
        Reporter.log("Checkout, enter address details, select payment method and place order");
        String actualOrderConfirmationMsg = checkoutPage.enterDetailsAndCheckout(firstName, lastName, "Dubai", "Abu Hail", "Abu Hail Street");
        Assert.assertEquals(actualOrderConfirmationMsg,"Thank you for 20 years of tech, trust, and transformation.");
        Reporter.log("Assert order confirmation message");
    }
}

package myAccountTests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MyAccountOrdersPage;

public class myAccountOrdersPageTest extends BaseTests {

    @Test
    public void orderLinkValidationTest() {
        String orderDetailTitle = "Order Details";
        String customerDetailTitle = "Customer Details";
        String billingDetailTitle = "Billing Address";

        String userEmail = "maria@hotmail.com";
        String userPass = "maria590!095!";

        MyAccountOrdersPage myAccountOrdersPage = new MyAccountOrdersPage(this.driver);
        myAccountOrdersPage.navigateToLoginMyAccount();

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.loginInputs(userEmail, userPass);
        loginPage.clickLoginBtn();
        myAccountOrdersPage.clickOrdersLink();
        myAccountOrdersPage.clickViewBtn();

        Assert.assertEquals(myAccountOrdersPage.viewOrderDetailsTitle(), orderDetailTitle);
        Assert.assertEquals(myAccountOrdersPage.viewCustomerDetailsTitle(), customerDetailTitle);
        Assert.assertEquals(myAccountOrdersPage.viewBillingAddressTitle(), billingDetailTitle);

    }

    @Test
    public void ordersPageConfirmationTest() {
        String userEmail = "maria@hotmail.com";
        String userPass = "maria590!095!";

        MyAccountOrdersPage myAccountOrdersPage = new MyAccountOrdersPage(this.driver);
        myAccountOrdersPage.navigateToLoginMyAccount();

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.loginInputs(userEmail, userPass);
        loginPage.clickLoginBtn();
        myAccountOrdersPage.clickOrdersLink();

        Assert.assertTrue(myAccountOrdersPage.ordersPageConfirmation());

    }


    @Test
    public void viewOrdersPageConfirmationTest() {
        String userEmail = "maria@hotmail.com";
        String userPass = "maria590!095!";
        MyAccountOrdersPage myAccountOrdersPage = new MyAccountOrdersPage(this.driver);
        myAccountOrdersPage.navigateToLoginMyAccount();

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.loginInputs(userEmail, userPass);
        loginPage.clickLoginBtn();
        myAccountOrdersPage.clickOrdersLink();
        myAccountOrdersPage.clickViewBtn();

        Assert.assertTrue(myAccountOrdersPage.viewOrdersPageConfirmation());

    }

    @Test
    public void billingAddressesEditPageConfirmationTest() {

        String userEmail = "maria@hotmail.com";
        String userPass = "maria590!095!";
        MyAccountOrdersPage myAccountOrdersPage = new MyAccountOrdersPage(this.driver);
        myAccountOrdersPage.navigateToLoginMyAccount();

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.loginInputs(userEmail, userPass);
        loginPage.clickLoginBtn();
        myAccountOrdersPage.clickOrdersLink();
        myAccountOrdersPage.clickViewBtn();
        myAccountOrdersPage.clickAddressesLink();
        myAccountOrdersPage.clickAddressesEditLink();

        Assert.assertTrue(myAccountOrdersPage.billingAddressesEditPageConfirmation());

    }

    @Test
    public void changePassAccountDetails() {
        String userEmail = "maria@hotmail.com";
        String userPass = "maria590!095!";
        MyAccountOrdersPage myAccountOrdersPage = new MyAccountOrdersPage(this.driver);
        myAccountOrdersPage.navigateToLoginMyAccount();

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.loginInputs(userEmail, userPass);
        loginPage.clickLoginBtn();

        myAccountOrdersPage.clickAccountDetailsLink();
        Assert.assertTrue(myAccountOrdersPage.changePassAccountDetails());
        myAccountOrdersPage.clickLogOutLink();

    }


}

package myAccountTests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.My_AccountPage;

public class MyAccountPageTest extends BaseTests {

    @Test
    public void myAccountValidation() {
        String userEmail = "maria@hotmail.com";
        String userPass = "maria590!095!";

        My_AccountPage myAccountPage = new My_AccountPage(this.driver);
        myAccountPage.loginToMyAccount(userEmail, userPass);
        Assert.assertTrue(myAccountPage.webElementsDisplayed());

    }

}

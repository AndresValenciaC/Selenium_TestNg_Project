package registerPageTests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;

public class RegisterTests extends BaseTests {

    @Test
    public void registerValidation() {
        String userEmail = "testing@hotmail.com";
        String userPass = "12345!ABCD!6789";
        String dashBoardTxt, contentConfirmationDashboard;

        RegisterPage registerPage = new RegisterPage(this.driver);

        registerPage.registerValidationUser(userEmail, userPass);
        dashBoardTxt = registerPage.getDashboardMyAccountPageTxt();
        contentConfirmationDashboard = registerPage.getAccountValidationContentTxt();
        Assert.assertEquals(dashBoardTxt, "Dashboard");
        Assert.assertEquals(contentConfirmationDashboard, "From your account dashboard " +
                "you can view your recent orders, manage your shipping and billing addresses and " +
                "edit your password and account details.");

        registerPage.clickLogOutLink();
    }

}

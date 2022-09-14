package loginPageTests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTests {

    @Test
    public void loginAuthTest() {
        String regUserEmail = "maria@hotmail.com";
        String regUserPass = "maria590!095!";

        String userEmail = "maria@hotmail.com";
        String userPass = "maria590!095!";

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.loginValidationUser(userEmail, regUserEmail, userPass, regUserPass);
        String loginValidationContentTxt = loginPage.getLoginValidationContentTxt();

        Assert.assertEquals(loginValidationContentTxt, "From your account dashboard " +
                "you can view your recent orders, manage your shipping and billing addresses and " +
                "edit your password and account details.");

    }


}

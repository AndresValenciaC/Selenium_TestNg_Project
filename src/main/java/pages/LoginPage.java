package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static pages.RegisterPage.emailValidator;

public class LoginPage extends BasePage {
    public Logger logger = LogManager.getLogger();
    protected WebDriver driver;
    /**
     * Locators
     */

    @FindBy(css = "#customer_login > div.u-column1.col-1 > h2")
    private WebElement loginTitle;
    @FindBy(css = "#menu-item-50 > a")
    private WebElement myAccountNavigationLink;
    @FindBy(css = "#username")
    private WebElement emailInput;
    @FindBy(css = "#password")
    private WebElement passwordInput;
    @FindBy(css = " input[name=\"login\"]")
    private WebElement inputBtn;

    @FindBy(css = "div > div.woocommerce > div > p:nth-child(2)")
    private WebElement myAccountUserLoginContent;

    @FindBy(css = "ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--customer-logout > a")
    private WebElement logOutMyAccountLink;


    public LoginPage(WebDriver driver) {
        super(driver, 10);
        this.driver = driver;
    }

    public void clickLoginBtn() {
        try {
            waitUntilElementToBeClickable(inputBtn);
            inputBtn.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickLogOutLink() {
        try {
            waitUntilElementToBeClickable(logOutMyAccountLink);
            logOutMyAccountLink.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }


    public void loginInputs(String userEmail, String userPassword) {

        waitUntilElementToBeVisible(emailInput);
        emailInput.clear();
        emailInput.sendKeys(userEmail);

        waitUntilElementToBeVisible(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(userPassword);

    }


    public void loginValidationUser(String userEmail, String regUserEmail, String userPassword, String regUserPassword) {
        clickNavigationMenuBar(myAccountNavigationLink);

        try {

            loginInputs(userEmail, userPassword);


        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);

        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
        }

        if (loginTitle.isDisplayed()) {
            System.out.println("You are in Login Page");
            if (userEmail.length() == 0) {
                System.out.println("userEmail box its empty");
            } else if (userPassword.length() == 0) {
                System.out.println("userPassword box its empty");
            } else if (!emailValidator(userEmail)) {
                System.out.println("The email address " + userEmail + "Its not valid");
            } else if (userEmail != regUserEmail) {
                System.out.println("userEmail regUserEmail don`t match");
            } else if (userPassword != regUserPassword) {
                System.out.println("userPassword regUserEmail don`t match");
            } else {
                System.out.println("You login form its correct");
                clickLoginBtn();
            }

        } else {
            System.out.println("You are Not in login page");

        }

    }

    public String getLoginValidationContentTxt() {
        try {
            waitUntilElementToBeVisible(myAccountUserLoginContent);

            return myAccountUserLoginContent.getText();
            // From your account dashboard you can view your
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }


}

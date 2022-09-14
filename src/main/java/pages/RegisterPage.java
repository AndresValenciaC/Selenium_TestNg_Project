package pages;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    public Logger logger = LogManager.getLogger();
    public String registerUserEmail, registerPassword;
    protected WebDriver driver;
    /**
     * Locators
     */

    @FindBy(css = "#site-logo > a > img")
    private WebElement homePracticeSiteLogoIMageLink;
    @FindBy(css = "#menu-item-50 > a")
    private WebElement myAccountNavigationLink;
    @FindBy(css = "#reg_email[name= \"email\"]")
    private WebElement emailInput;
    @FindBy(css = "#reg_password[name=\"password\"]")
    private WebElement passWordInput;
    @FindBy(css = "input.woocommerce-Button.button[name= \"register\"]")
    private WebElement registerBtn;
    @FindBy(css = "div > div.woocommerce > div > p:nth-child(2)")
    private WebElement myAccountUserRegisteredContent;
    @FindBy(css = "li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--dashboard.is-active > a")
    private WebElement dashboardUserRegisterValidation;
    @FindBy(css = "ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--customer-logout > a")
    private WebElement logOutMyAccountLink;

    public RegisterPage(WebDriver driver) {
        super(driver, 10);
        this.driver = driver;
    }

    public static boolean emailValidator(String email) {
        // Obtain an instance of `EmailValidator`
        EmailValidator validator = EmailValidator.getInstance();

        // Validate the specific String that contains a specific email address
        return validator.isValid(email);
    }

    public void clickRegisterBtn() {
        try {
            waitUntilElementToBeClickable(registerBtn);
            registerBtn.click();
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

    public void registerValidationUser(String registerUserEmail, String registerPassword) {

        clickNavigationMenuBar(myAccountNavigationLink);

        try {
            waitUntilElementToBeVisible(emailInput);
            emailInput.clear();
            if (emailValidator(registerUserEmail)) {
                System.out.println("The email address " + registerUserEmail + "Its valid");
                emailInput.sendKeys(registerUserEmail);
            } else {
                System.out.println("The email address " + registerUserEmail + "Its Invalid, you must enter a valid email address");
            }

            waitUntilElementToBeVisible(passWordInput);
            passWordInput.clear();
            passWordInput.sendKeys(registerPassword);

            if (emailInput.getText() == "") {
                System.out.println("Email Box its empty");

            } else if (passWordInput.getText() == "") {
                System.out.println("Password Box its empty");
            }
            clickRegisterBtn();

        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);

        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
        }

    }

    public String getAccountValidationContentTxt() {
        try {
            waitUntilElementToBeVisible(myAccountUserRegisteredContent);
            return myAccountUserRegisteredContent.getText();
            // From your account dashboard you can view your
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }


    public String getDashboardMyAccountPageTxt() {
        try {
            waitUntilElementToBeVisible(dashboardUserRegisterValidation);
            return dashboardUserRegisterValidation.getText();
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

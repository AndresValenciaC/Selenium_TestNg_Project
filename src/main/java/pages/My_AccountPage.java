package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class My_AccountPage extends BasePage {

    public Logger logger = LogManager.getLogger();
    protected WebDriver driver;


    public My_AccountPage(WebDriver driver) {
        super(driver, 10);
        this.driver = driver;
    }

    /**
     * Locators
     */

    @FindBy(css = "#menu-item-50 > a")
    private WebElement myAccountNavigationLink;

    @FindBy(css = "ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--dashboard.is-active > a")
    private WebElement Dashboard;

    @FindBy(css = "ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--orders > a")
    private WebElement Orders;

    @FindBy(css = "ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--edit-address > a")
    private WebElement Addresses;

    @FindBy(css = "ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--edit-account > a")
    private WebElement AccountDetails;

    @FindBy(css = "ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--customer-logout > a")
    private WebElement Logout;

    public void loginToMyAccount(String email, String pass) {
        clickNavigationMenuBar(myAccountNavigationLink);
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.loginInputs(email, pass);
        loginPage.clickLoginBtn();
    }

    public boolean webElementsDisplayed() {
        boolean displayed = false;
        System.out.println(driver.getCurrentUrl());
        if (Dashboard.isDisplayed() && Addresses.isDisplayed() && Logout.isDisplayed()) {
            displayed = true;
        }
        return displayed;

    }




}

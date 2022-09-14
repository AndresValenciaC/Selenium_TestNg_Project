package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


public class CheckOutPage extends BasePage {

    public Logger logger = LogManager.getLogger();
    protected WebDriver driver;
    @FindBy(css = "input[name=\"billing_first_name\"]")
    private WebElement firstName;

    // Locators
    @FindBy(css = "input[name=\"billing_last_name\"]")
    private WebElement lastName;
    @FindBy(css = "input[name=\"billing_company\"]")
    private WebElement billingCompany;
    @FindBy(css = "input[name=\"billing_email\"]")
    private WebElement billingEmail;
    @FindBy(css = "input[name=\"billing_phone\"]")
    private WebElement billingPhone;
    @FindBy(id = "s2id_billing_country")
    private WebElement billingCountry;
    @FindBy(css = "#s2id_autogen1_search")
    private WebElement billingCountrySearchBox;
    @FindBy(css = "input[name=\"billing_address_1\"]")
    private WebElement billingAddress1;
    @FindBy(css = "input[name=\"billing_address_2\"]")
    private WebElement billingAddress2;
    @FindBy(css = "input[name = \"billing_city\"]")
    private WebElement townCity;
    @FindBy(css = "input[ name = \"billing_state\"]")
    private WebElement county;
    @FindBy(css = "input[name=\"billing_postcode\"]")
    private WebElement billingPostCode;
    @FindBy(css = "textarea[name = \"order_comments\"]")
    private WebElement additionalInfoTextArea;
    @FindBy(css = "#payment > ul")
    private WebElement ulPaymentMethods;
    @FindBy(id = "place_order")
    private WebElement placeOrderBtn;
    @FindBy(css = "form.checkout.woocommerce-checkout > ul[class=\"woocommerce-error\"]")
    private WebElement woocommerceError;
    @FindBy(css = " table > tfoot > tr.order-total > td > strong > span")
    private WebElement totalPriceAmount;

    @FindBy(css = "table > tfoot > tr.tax-rate.tax-rate-in-tax-1 > td > span")
    private WebElement tax;
    @FindBy(css = "table > tfoot > tr.tax-rate.tax-rate-roaming-tax-1 > td > span")
    private WebElement roamingTax;


    public CheckOutPage(WebDriver driver) {
        super(driver, 10);
        this.driver = driver;
    }

    public void clickBillingCountryBtn() {
        try {
            waitUntilElementToBeClickable(billingCountry);
            billingCountry.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickPlaceOrderBtn() {
        try {
            waitUntilElementToBeClickable(placeOrderBtn);
            placeOrderBtn.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }


    public void billingDetailsForm() {

        inputBoxSendKeys(firstName, "andres" + Keys.TAB);
        inputBoxSendKeys(lastName, "Valentino" + Keys.TAB);
        inputBoxSendKeys(billingCompany, "Corp_Val" + Keys.TAB);
        inputBoxSendKeys(billingEmail, "ahah@hotmail.com" + Keys.TAB);
        inputBoxSendKeys(billingPhone, "1818181" + Keys.TAB);

        clickBillingCountryBtn();
        inputBoxSendKeys(billingCountrySearchBox, "Colombia" + Keys.TAB);

        inputBoxSendKeys(billingAddress1, "Street 1" + Keys.TAB);
        inputBoxSendKeys(billingAddress2, "Street 2" + Keys.TAB);
        inputBoxSendKeys(townCity, "Colorado" + Keys.TAB);
        inputBoxSendKeys(county, "Nevada" + Keys.TAB);
        inputBoxSendKeys(billingPostCode, "95623" + Keys.TAB);
        inputBoxSendKeys(additionalInfoTextArea, "bdbudeuneduneudn uduneduneududn" + Keys.TAB);

        ul_liSelect(ulPaymentMethods, "Cash on Delivery", "label");


    }

    public String getPaymentMethodTxt() {
        return getUl_LiTxt(ulPaymentMethods);
    }

    public String getTotalAmountTxt() {
        try {
            waitUntilElementToBeVisible(totalPriceAmount);
            return totalPriceAmount.getText();

        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }

    public void productProceedToOrderDetails() {
        HomePage homePage = new HomePage(this.driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(this.driver);

        homePage.addFirstItemToCart();
        shoppingCartPage.clickProceedToCheckOutBtn();
        billingDetailsForm();

        clickPlaceOrderBtn();
    }


    public Boolean validationFormBillingDetails() {
        clickPlaceOrderBtn();
        return waitUntilElementToBeVisible(woocommerceError);
    }


    public void validateTaxRate() throws InterruptedException {
       /* The tax rate variers for India compared to other countries
        Tax rate for indian should be 2% and for abroad it should be 5%
        */

        clickBillingCountryBtn();
        billingCountrySearchBox.sendKeys("India" + Keys.TAB);
        float tax1 = convertTxtToFloat(tax, 1, 4); // 3.6
        System.out.println(tax1);

        clickBillingCountryBtn();
        billingCountrySearchBox.clear();
        billingCountrySearchBox.sendKeys("British Indian Ocean Territory" + Keys.TAB);

        float tax2 = convertTxtToFloat(roamingTax, 1, 4); // 9.0
        System.out.println(tax2);


    }


}

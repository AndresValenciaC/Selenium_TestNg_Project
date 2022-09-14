package checkOutPageTests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckOutPage;
import pages.ShoppingCartPage;


public class CheckOutTests extends BaseTests {

    @Test
    public void billingDetailsFormValidation() {
        CheckOutPage checkOutPage = new CheckOutPage(this.driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(this.driver);

        shoppingCartPage.productProceedCheckout();
        checkOutPage.billingDetailsForm();

        Assert.assertFalse(checkOutPage.validationFormBillingDetails());

    }


}

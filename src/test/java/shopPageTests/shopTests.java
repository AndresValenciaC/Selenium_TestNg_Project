package shopPageTests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckOutPage;
import pages.ProductPage;
import pages.ShopPage;
import pages.ShoppingCartPage;

public class shopTests extends BaseTests {

    @Test
    public void shopFilterProductAmountTest() {
        ShopPage shopPage = new ShopPage(this.driver);
        shopPage.clickShopBarMenuLink();
        Assert.assertTrue(shopPage.priceFunctionality(), "Test succeed.");
    }

    @Test
    public void shopProductClickTest() {
        String initialTitle, finalTitle;
        ShopPage shopPage = new ShopPage(this.driver);
        ProductPage productPage = new ProductPage(this.driver);
        shopPage.clickShopBarMenuLink();

        initialTitle = shopPage.productsLinkClickValidation();
        finalTitle = productPage.getProductPageTitleTxt();
        Assert.assertEquals(initialTitle, finalTitle);
    }

    @Test
    public void selectDropDownProductOptionsTests() {
        ShopPage shopPage = new ShopPage(this.driver);
        shopPage.clickShopBarMenuLink();

        Assert.assertTrue(shopPage.sortingDropDownProduct("Default sorting"));
        Assert.assertTrue(shopPage.sortingDropDownProduct("Sort by popularity"));
        Assert.assertTrue(shopPage.sortingDropDownProduct("Sort by average rating"));
        Assert.assertTrue(shopPage.sortingDropDownProduct("Sort by newness"));
        Assert.assertTrue(shopPage.sortingDropDownProduct("Sort by price: low to high"));
        Assert.assertTrue(shopPage.sortingDropDownProduct("Sort by price: high to low"));

    }


    @Test
    public void shopAddToBasketViewBasketTest() throws InterruptedException {
        ShopPage shopPage = new ShopPage(this.driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(this.driver);
        CheckOutPage checkOutPage = new CheckOutPage(this.driver);

        shopPage.clickShopBarMenuLink();
        Assert.assertTrue(shopPage.addToBasket_ViewBasket());
        Assert.assertTrue(shoppingCartPage.comparePriceAmounts());

        shoppingCartPage.clickProceedToCheckOutBtn();
        checkOutPage.billingDetailsForm();
        checkOutPage.clickPlaceOrderBtn();
        Assert.assertFalse(checkOutPage.validationFormBillingDetails());
    }


    @Test
    public void validateTaxTest() throws InterruptedException {
        /* The tax rate variers for India compared to other countries
        Tax rate for indian should be 2% and for abroad it should be 5%
        */
        ShopPage shopPage = new ShopPage(this.driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(this.driver);
        CheckOutPage checkOutPage = new CheckOutPage(this.driver);

        shopPage.clickShopBarMenuLink();
        shopPage.addToBasket_ViewBasket();
        shoppingCartPage.clickProceedToCheckOutBtn();

        checkOutPage.validateTaxRate();
// No understood the use case
    }


}

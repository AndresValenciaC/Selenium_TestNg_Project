package orderDetailsPageTests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckOutPage;
import pages.OrderDetails;


public class OrderDetailsTests extends BaseTests {

    @Test
    public void validateOrderDetailPage() {
        String checkOutAmountTxt, checkOutPaymentMethodTxt;
        String orderDetailAmountTxt, orderDetailPaymentMethodTxt;
        String orderDetailPageTitle;

        CheckOutPage checkOutPage = new CheckOutPage(this.driver);
        OrderDetails orderDetails = new OrderDetails(this.driver);

        checkOutPage.productProceedToOrderDetails();
        checkOutAmountTxt = checkOutPage.getTotalAmountTxt();


        checkOutPaymentMethodTxt = checkOutPage.getPaymentMethodTxt();

        orderDetailPageTitle = orderDetails.getOrderDetailsPageTitleTxt();

        orderDetailAmountTxt = orderDetails.getTotalAmountTxt();

        orderDetailPaymentMethodTxt = orderDetails.getPaymentMethodTxt();


        Assert.assertEquals(orderDetailPageTitle, "Order Details");
        Assert.assertEquals(checkOutAmountTxt, orderDetailAmountTxt);
        Assert.assertEquals(checkOutPaymentMethodTxt, orderDetailPaymentMethodTxt);


    }


}

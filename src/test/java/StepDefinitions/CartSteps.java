package StepDefinitions;

import PageObject.CartData;
import PageObject.CartPage;
import Utilities.JsonUtility;
import Utilities.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;

public class CartSteps extends Log{

    TestContext testContext;
    CartData cartData;
    CartPage cartPage;

    public CartSteps(TestContext context) {
        testContext = context;
        cartData = testContext.getPageObjectManager().getCartData();
        cartPage = testContext.getPageObjectManager().getCartPage();
    }

    @Then("verify the cart data")
    public void verifyTheCartData() {
        JSONArray productList =  JsonUtility.getJsonArrayUsingJsonPath("cartData",JsonUtility.commonConfigFilePath);
        for (int i =0;i<productList.size();i++){
            JSONObject productObj = (JSONObject) productList.get(i);
            String productName= (String) productObj.get("title");
            double productPrice  = (Double) productObj.get("price");
            int productQty = Math.toIntExact((Long) productObj.get("qty"));
            log("Verify that cart has product \t <b>Name</b>:"+productName+"\t <b>Price</b>:"+productPrice+"\t <b>Qty</b>:"+productQty);
            Assert.assertTrue(cartData.isProductAddedInCart(productName,productQty,productPrice));
            log("verification pass successfully , Cart has product \t <b>Name</b>:"+productName+"\t <b>Price</b>:"+productPrice+"\t <b>Qty</b>:"+productQty);

        }
    }

    @When("User navigate to cart page")
    public void userNavigateToCartPage() {
        cartData.openCart();
    }

    @Then("Verify all cart details on cart page")
    public void verifyAllCartDetailsOnCartPage() {
        Double expSubTotal = 0.0;
        JSONArray productList =  JsonUtility.getJsonArrayUsingJsonPath("cartData",JsonUtility.commonConfigFilePath);
        for (int i =0;i<productList.size();i++){
            JSONObject productObj = (JSONObject) productList.get(i);
            String productName= (String) productObj.get("title");
            double productPrice  = (Double) productObj.get("price");
            int productQty = Math.toIntExact((Long) productObj.get("qty"));
            expSubTotal = expSubTotal+productPrice;
            Assert.assertTrue(cartPage.isItemQtyPriceAvailable(productName,productQty,productPrice));
        }
       Assert.assertTrue(cartPage.getSubTotal()==expSubTotal);
       Assert.assertEquals(expSubTotal+cartPage.getShippingPrice(),cartPage.getTotalPrice());
    }

    @When("User click on the checkout button")
    public void userClickOnTheCheckoutButton() {
        cartPage.clickCheckout();
    }
}

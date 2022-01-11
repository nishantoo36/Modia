package StepDefinitions;
import PageObject.CartData;
import PageObject.GameProductListPage;
import Utilities.JsonUtility;
import Utilities.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;

public class ProductListSteps {

    TestContext testContext;
    GameProductListPage productListPage;
    CartData cartData;

    public ProductListSteps(TestContext context) {
        testContext = context;
        productListPage = testContext.getPageObjectManager().getProductListPage();
        cartData = testContext.getPageObjectManager().getCartData();
    }

    @Then("User should navigate to product list page")
    public void userShouldNavigateToProductListPage() {
        Assert.assertTrue(productListPage.isProductListPageAppearing());
    }

    @When("User select last product from game list")
    public void userSelectLastProductFromGameList() {
        productListPage.addLastProduct();
    }


    @Then("verify that last product added in cart")
    public void verifyThatLastProductAddedInCart() {
       JSONArray productList =  JsonUtility.getJsonArrayUsingJsonPath("cartData",JsonUtility.commonConfigFilePath);
       JSONObject productObj = (JSONObject) productList.get(0);
       String productName =  String.valueOf(productObj.get("title"));
       double price = (Double) productObj.get("price");
       int qty = Math.toIntExact((Long) productObj.get("qty"));
       Assert.assertTrue(cartData.isProductAddedInCart(productName,qty,price));
    }

    @When("User click on {int} product from list")
    public void userClickOnProductFromList(int productNumber) {
        productListPage.selectProductByNumber(productNumber);
    }


}

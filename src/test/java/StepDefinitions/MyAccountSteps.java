package StepDefinitions;

import PageObject.MyAccountPage;
import Utilities.JsonUtility;
import Utilities.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class MyAccountSteps {
    TestContext testContext;
    MyAccountPage myAccountPage;

    public MyAccountSteps(TestContext context) {
        testContext = context;
        myAccountPage = testContext.getPageObjectManager().getMyAccountPage();
    }

    @Then("User should navigate to my account page")
    public void userShouldNavigateToMyAccountPage() {
        Assert.assertTrue(myAccountPage.isUserOnMyAccountPage());
    }

    @And("User is able to see order status as {string}")
    public void userIsAbleToSeeOrderStatusAs(String status) {
        JSONArray productList =  JsonUtility.getJsonArrayUsingJsonPath("cartData",JsonUtility.commonConfigFilePath);
        List<String> productName = new ArrayList<>();
        for (int i =0;i<productList.size();i++){
            JSONObject productObj = (JSONObject) productList.get(i);
            productName.add((String) productObj.get("title"));
        }
        Assert.assertTrue(myAccountPage.isOrderStatus(productName,status,1));
    }
}

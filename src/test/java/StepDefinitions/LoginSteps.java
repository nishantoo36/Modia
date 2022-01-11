package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import PageObject.LoginPage;
import Utilities.TestContext;

public class LoginSteps {

    TestContext testContext;
    LoginPage loginPage;

    public LoginSteps(TestContext context) {
        testContext = context;
        loginPage = testContext.getPageObjectManager().getLoginPage();
    }

    @Then("User should navigate to login page")
    public void userShouldNavigateToLoginPage() {
       Assert.assertTrue(loginPage.isUserOnLoginPage());
    }


    @When("User click on the registration button")
    public void userClickOnTheRegistrationButton() {
        loginPage.clickOnRegisterBtn();
    }

}

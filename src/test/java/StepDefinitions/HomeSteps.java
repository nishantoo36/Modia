package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import PageObject.HomePage;
import Utilities.TestContext;

public class HomeSteps{

    HomePage homePage;
    TestContext testContext;

    public HomeSteps(TestContext context) {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();
    }

    @Given("Home page without authorizations")
    public void homePageWithoutAuthorizations() {
        Assert.assertTrue(homePage.defaultHomePageIsDisplayed());
    }

    @When("User click on product menu")
    public void userClickOnProductMenu() {
        homePage.clickProductMenu();
    }


    @Then("verify that product submenu is appearing")
    public void verifyThatProductSubmenuIsAppearing() {
        Assert.assertTrue(homePage.isProductSubMenuDisplay());
    }

    @When("User Select Games categories")
    public void userSelectGamesCategories() {
        homePage.selectGame();
    }


}

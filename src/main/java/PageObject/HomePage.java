package PageObject;

import Utilities.SeleniumUtility;
import Utilities.Wait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class HomePage {
    private final WebDriver webDriver;
    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 15), this);
    }

    @FindBy(xpath = "//a[text()='Products']")
    private WebElement productMenu;

    @FindBy(xpath = "//a[text()='Products']//following::ul[1]")
    private WebElement productSubMenu;

    @FindBy(id = "search-input")
    private WebElement searchInput;

    @FindBy(xpath = "//a[text()='Games']")
    private WebElement gameSubMenu;

    public boolean defaultHomePageIsDisplayed() {
        return searchInput.isDisplayed() && productMenu.isDisplayed();
    }

    public void clickProductMenu() {
        productMenu.isDisplayed();
        productMenu.click();
    }

    public boolean isProductSubMenuDisplay() {
        return productSubMenu.isDisplayed();
    }

    public void selectGame() {
        SeleniumUtility.getAction(webDriver).moveToElement(gameSubMenu).doubleClick().build().perform();
    }
}

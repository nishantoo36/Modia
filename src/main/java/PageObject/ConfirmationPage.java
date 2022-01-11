package PageObject;

import Utilities.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
    private final WebDriver webDriver;

    public ConfirmationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "page-title")
    private WebElement pageTitle;

    @FindBy(id = "continue-button")
    private WebElement confirmBtn;


    public boolean isUserConfirmOrderPage() {
        Wait.untilAjaxCallIsDone(webDriver, 15L);
        return pageTitle.getText().equals("Order Confirmation");
    }

    public void clickONConfirmBtn() {
        confirmBtn.click();
    }

}

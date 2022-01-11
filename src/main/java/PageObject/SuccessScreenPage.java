package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessScreenPage {
    private final WebDriver webDriver;

    public SuccessScreenPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "page-title")
    private WebElement pageTitle;

    @FindBy(id = "continue-button")
    private WebElement confirmBtn;

    public boolean isUserConfirmSuccess(){
        return pageTitle.getText().equals("Your Order Has Been Processed");
    }

    public void clickONConfirmBtn(){
        confirmBtn.click();
    }

}

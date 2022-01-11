package PageObject;

import Utilities.JsonUtility;
import Utilities.SeleniumUtility;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utilities.Wait;

import java.util.List;

public class GameProductListPage {

    private final WebDriver webDriver;

    public GameProductListPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//a[contains(@id,'next')]")
    private WebElement nextIcon;

    @FindBy(xpath = "//a[contains(@id,'prev')]")
    private WebElement previousIcon;

    @FindBy(css = ".item")
    private List<WebElement> items;

    @FindBy(css = ".item .add-to-cart-button")
    private List<WebElement> addToCardButton;

    @FindBy(css = ".next-items-inactive")
    private WebElement inActiveNextBtn;

    @FindBy(css = ".prev-items-inactive")
    private WebElement inActivePreviousBtn;

    @FindBy(xpath = "//*[invalid locators]")
    private WebElement invalidLocators;

    @FindBy(css = ".shopping-cart-item-title")
    private List<WebElement> productTitle;

    @FindBy(css = ".shopping-cart-item-price")
    private List<WebElement> productQtyAndPrice;

    @FindBy(css = ".price")
    private List<WebElement> prices;

    @FindBy(css = ".item-title")
    private List<WebElement> itemTitles;

    public boolean isProductListPageAppearing() {
        return webDriver.getTitle().equals("Games");
    }

    public void addLastProduct() {
        int len = addToCardButton.size() - 1;
        scrollToProductByNumber(len + 1);
        clickAddToCart(items.get(len), addToCardButton.get(len));
        String title = itemTitles.get(len).getText();
        double price = Double.parseDouble((prices.get(len).getText().replace("$", "")));
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("title", title);
        jsonObject1.put("price", price);
        jsonObject1.put("qty", 1);
        jsonArray.add(jsonObject1);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("cartData", jsonArray);
        JsonUtility.writeJson(jsonObject2, JsonUtility.commonConfigFilePath);
    }



    public void clickAddToCart(WebElement itemEle, WebElement actionBtnEle) {
        SeleniumUtility.getAction(webDriver).
                moveToElement(itemEle).build().perform();
        SeleniumUtility.getAction(webDriver).
                moveToElement(actionBtnEle).
                click().build().perform();
    }

    public void selectProductByNumber(int number) {
        JSONObject jsonObject = new JSONObject();
        if (items.get(number - 1).isDisplayed()) {
            jsonObject.put("Selected_product", itemTitles.get(number - 1).getText());
            items.get(number - 1).click();
        } else {
            scrollToProductByNumber(number);
            jsonObject.put("Selected_product", itemTitles.get(number - 1).getText());
            items.get(number - 1).click();
        }
        JsonUtility.appendJson(jsonObject, JsonUtility.commonConfigFilePath);
    }

    public void scrollToProductByNumber(int number) {
        int firstDisplayNum = 0;
        for (WebElement ele : items) {
            if (ele.isDisplayed()) {
                break;
            } else {
                firstDisplayNum = firstDisplayNum + 1;
            }
        }
        boolean temp = false;
        while (!temp) {
            if (firstDisplayNum < number - 1) {
                nextIcon.click();
                temp = Wait.isElementPresentAfterWaitApply(webDriver, inActiveNextBtn, 1L);
            } else {
                previousIcon.click();
                temp = Wait.isElementPresentAfterWaitApply(webDriver, inActivePreviousBtn, 1L);
            }
        }
    }


}

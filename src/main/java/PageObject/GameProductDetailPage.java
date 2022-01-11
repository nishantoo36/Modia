package PageObject;

import Utilities.JsonUtility;
import Utilities.SeleniumUtility;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GameProductDetailPage {

    private final WebDriver webDriver;

    public GameProductDetailPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "page-title")
    private WebElement title;

    @FindBy(css = "#gallery_nav a img")
    private List<WebElement> images;

    @FindBy(xpath = "//a[text()='Add to Cart']")
    private List<WebElement> addToCartBtn;

    @FindBy(css = ".add-to-cart-qty")
    private List<WebElement> qtySelector;

    @FindBy(className= "product-price-current")
    private List<WebElement> price;



    public boolean isUserOnDetailsPageOf(String pageTitle){
        return title.getText().equals(pageTitle);
    }

    public boolean isImageCount(int expected){
        return images.size()==expected;
    }

    public void setProductQty(int qty){
        SeleniumUtility.getSelector(qtySelector.get(1)).selectByVisibleText(String.valueOf(qty));
        String productTitle = title.getText();
        double productPrice = Double.parseDouble((price.get(1).getText().replace("$", "")));;
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("title", productTitle);
        jsonObject1.put("price", productPrice*qty);
        jsonObject1.put("qty", qty);
        jsonArray.add(jsonObject1);
        JsonUtility.appendJsonWithKey((JSONObject) jsonArray.get(0), "cartData",JsonUtility.commonConfigFilePath);
    }

    public void addProductToCart(){
        addToCartBtn.get(1).click();
    }


}

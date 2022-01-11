package Utilities;

import Manager.AllDriverManager;
import Manager.PageObjectManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeleniumUtility {
    public static Actions getAction(WebDriver webDriver){
        Actions actions = new Actions(webDriver);
        return actions;
    }

    public static JavascriptExecutor getJSExecutor(WebDriver webDriver){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        return jsExecutor;
    }

    public static String getTextViaJS(WebDriver webDriver,WebElement element){
        return (String) getJSExecutor(webDriver).executeAsyncScript("return arguments[0].innerHTML", element);
    }


    public static List<String> getStringByReg(String pattern , String data ) {
        List<String> val = new ArrayList<>();
        Pattern reg = Pattern.compile(pattern);
        Matcher m = reg.matcher(data);
        while(m.find()){
            val.add(m.group());
        }
        return val;
    }

    public static Select getSelector(WebElement element){
        Select select = new Select(element);
        return select;
    }

    public static String generateTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
          return String.valueOf(timestamp.getTime());
    }

    }

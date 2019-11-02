package testui.pages;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SearchPage extends BasePage {
    By cardSel = By.xpath("//div[@class='card-wrapper']");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public int countSearchedPlanCards() {
        return findElements(cardSel).size();
    }
}

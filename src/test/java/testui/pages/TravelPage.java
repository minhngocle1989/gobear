package testui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TravelPage extends BasePage {
    String pageUrl = "https://www.gobear.com/ph?x_session_type=UAT";
    By insuranceTabSel = By.xpath("//li[@data-gb-name='Insurance']");
    By travelTabSel = By.xpath("//li[@data-gb-name='Travel']");
    By tripDropdown = By.xpath("//span[text()='single trip']/ancestor::button");
    By optionSingleTrip = By.xpath("//div[@class='dropdown-menu open']//span[text()='single trip']/ancestor::li");
    By justMeDropdown = By.xpath("//span[text()='just me']/ancestor::button");
    By optionJustMe = By.xpath("//div[@class='dropdown-menu open']//span[text()='just me']/ancestor::li");
    By showResultBtnSel = By.name("product-form-submit");

    public TravelPage(WebDriver driver) {
        super(driver);
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void clickTabInsurance() {
        findElement(insuranceTabSel).click();
    }

    public void clickTabTravel() {
        findElement(travelTabSel).click();
    }

    public void selectSingleTrip() {
        findElement(tripDropdown).click();
        findElement(optionSingleTrip).click();
        findElement(justMeDropdown).click();
        findElement(optionJustMe).click();
    }

    public void clickShowMyResult() {
        findElement(showResultBtnSel).click();
    }

    public void searchSinglePlans() {
        clickTabInsurance();
        clickTabTravel();
        selectSingleTrip();
        clickShowMyResult();
    }
}

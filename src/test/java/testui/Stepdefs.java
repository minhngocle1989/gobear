package testui;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testui.pages.BasePage;
import testui.pages.SearchPage;
import testui.pages.TravelPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Stepdefs {
    WebDriver driver;
    BasePage currentPage;
    int waitTimeout = 30;

    private WebElement findElement(String xpathSelector) {
        WebElement element = driver.findElement(By.xpath(xpathSelector));
        WebDriverWait wait = new WebDriverWait(driver, waitTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    @Before
    public void setup() {
        File chromeDriver = new File(getClass().getClassLoader().getResource("drivers/chromedriver").getFile());
        chromeDriver.setExecutable(true);
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(waitTimeout, TimeUnit.SECONDS);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Given("I am on travel page")
    public void i_am_on_travel_page() {
        TravelPage page = new TravelPage(driver);
        currentPage = page;
        driver.get(page.getPageUrl());
        driver.manage().window().maximize();
    }

    @When("I search for single plans")
    public void i_search_for_single_plans() {
        ((TravelPage) currentPage).searchSinglePlans();
    }

    @Then("There should be more than {int} plan cards")
    public void there_should_be_more_than_plans(Integer planCount) throws ValueException, InterruptedException {
        SearchPage page = new SearchPage(driver);
        int count = 0;
        for (int i = 0; i < waitTimeout; i++) {
            Thread.sleep(1000);
            count = page.countSearchedPlanCards();
            currentPage = page;
            if (count > planCount) {
                return;
            }
        }
        throw new ValueException(String.format("Number of found plans is %s smaller than expected %s plans", count, planCount));
    }

    @Then("There should be {int} plan cards")
    public void there_should_be_plans(Integer planCount) throws InterruptedException {
        SearchPage page = new SearchPage(driver);
        int count = 0;
        for (int i = 0; i < waitTimeout; i++) {
            Thread.sleep(1000);
            count = page.countSearchedPlanCards();
            currentPage = page;
            if (count == planCount) {
                return;
            }
        }
        throw new ValueException(String.format("Number of found plans is %s not expected %s plans", count, planCount));
    }

    @When("I click on element {string} with xpath {string}")
    public void i_click_on(String elementName, String xpathSelector) {
        WebElement element = findElement(xpathSelector);
        if (element == null) {
            throw new ValueException(String.format("Unable to find element %s with xpath %s", elementName, xpathSelector));
        }
        element.click();
    }

    @When("I select element {string} with xpath {string}")
    public void i_select_element(String elementName, String xpathSelector) {
        WebElement element = findElement(xpathSelector);
        if (element == null) {
            throw new ValueException(String.format("Unable to find element %s with xpath %s", elementName, xpathSelector));
        }
        element.click();
        if (!element.isEnabled()) {
            throw new ValueException(String.format("Unable to select element %s with xpath %s", elementName, xpathSelector));
        }
    }

    @When("I move max value {int} pixels of element {string} with xpath {string}")
    public void i_move_element(int xOffset, String elementName, String xpathSelector) {
        WebElement element = findElement(xpathSelector);
        if (element == null) {
            throw new ValueException(String.format("Unable to find element %s with xpath %s", elementName, xpathSelector));
        }
        Actions action = new Actions(driver);
        action.dragAndDropBy(element, xOffset, 0);
        action.perform();
    }
}
